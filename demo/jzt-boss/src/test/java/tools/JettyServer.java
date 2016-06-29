package tools;
/**
 * 
 */

import org.apache.commons.lang.ArrayUtils;
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
		int port =80;
		String contextPath = "/";
		if(!ArrayUtils.isEmpty(args)){
			switch(args.length){
			case 1: port=Integer.parseInt(args[0]); break;
			case 2: port=Integer.parseInt(args[0]); contextPath=args[1]; break;
			}
		}
		Server server = buildNormalServer(port,contextPath);
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