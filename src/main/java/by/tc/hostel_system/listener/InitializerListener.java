package by.tc.hostel_system.listener;

import by.tc.hostel_system.dao.connector.ConnectionPool;
import by.tc.hostel_system.dao.connector.ConnectionPoolException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class InitializerListener implements ServletContextListener{
	public final static Logger logger = Logger.getLogger(ServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		createLogger(servletContextEvent);
		createConnectionPool();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		destroyConnectionPool();
	}

	/**
	 * Creates connection pool.
	 */
	private void createConnectionPool() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			String message = "Failed to init connection pool";
			logger.log(Level.FATAL, message, e);
			throw new ConnectionPoolException(message, e);
		}
	}

	/**
	 * Creates logger.
	 *
	 * @param servletContextEvent servlet context event
	 */
	private void createLogger(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		String location = "log4j-config-location";
		String log4jConfigFile = context.getInitParameter(location);
		String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

		PropertyConfigurator.configure(fullPath);
	}

	/**
	 * Destroys connection pool.
	 */
	private void destroyConnectionPool() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.dispose();
	}
}
