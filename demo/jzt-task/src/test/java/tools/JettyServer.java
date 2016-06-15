package tools;
/**
 * 
 */

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *Jetty Server
 * 
 * @author badqiu
 * 
 */
public class JettyServer {

	public static void main(String[] args) throws Exception {
		Server server = buildNormalServer(8080, "/");
		server.start();
	}

	/**
	 * src/main/webapp
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