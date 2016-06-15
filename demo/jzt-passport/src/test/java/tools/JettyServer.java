package tools;
/**
 * 
 */

import org.eclipse.jetty.http.ssl.SslContextFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.eclipse.jetty.util.log.StdErrLog;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 开发调试使用的 Jetty Server
 * 
 * @author badqiu
 * 
 */
public class JettyServer {

	public static void main(String[] args) throws Exception {
		Server server = buildNormalServer(443, "/");
		server.start();
	}

	/**
	 * 创建用于正常运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server buildNormalServer(int port, String contextPath) {
		System.setProperty("org.eclipse.jetty.util.log.class", StdErrLog.class.getName());
		// 设置Jetty日志
        Server server = new Server();
        // 设置ssl连接器
        SslSocketConnector ssl_connector = new SslSocketConnector();
        ssl_connector.setPort(port);
        SslContextFactory cf = ssl_connector.getSslContextFactory();
        cf.setKeyStorePath("localhost.keystore");
        cf.setKeyStorePassword("123456");
        cf.setKeyManagerPassword("123456");
        server.addConnector(ssl_connector);
        // 设置context
        WebAppContext context = new WebAppContext();
        context.setResourceBase("./src/main/webapp");
        context.setContextPath(contextPath);
//        context.setDefaultsDescriptor("src/test/java/jetty/webdefault.xml");
        // PS:嵌入式的Jetty，应用当前工程的ClassPath，如果不设置将使用WebAppClassLoder，WEB-INF/lib目录加载jar。
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        return server;
	}
}