package tools;
/**
 * 
 */

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 开发调试使用的 Jetty Server
 * 
 * @author badqiu
 * 
 */
public class JettyServer {

	public static void main(String[] args) throws Exception {
		Server server = buildNormalServer(80, "/");
		server.start();
	}

	/**
	 * 创建用于正常运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server buildNormalServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext(
				"src/main/webapp", contextPath);

		webContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		server.setHandler(webContext);
		server.setAttribute("org.eclipse.jetty.Request.maxFormContentSize", 10000000);
		server.setStopAtShutdown(true);
		return server;
	}
}