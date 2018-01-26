package by.tc.task31.controller.listener;

import by.tc.task31.dao.connector.ConnectionPool;
import by.tc.task31.dao.connector.ConnectionPoolException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class InitializerListener implements ServletContextListener{
	public final static Logger logger = Logger.getLogger(ServletContextListener.class);
	private static final String LOG4J_CONFIG_LOCATION = "log4j-config-location";

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		createLogger(servletContextEvent);
		createConnectionPool();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		destroyConnectionPool();
	}

	private void createConnectionPool() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			logger.log(Level.FATAL, "Failed to init connection pool", e);
			throw new ConnectionPoolException("Failed to init connection pool", e);
		}
	}

	private void createLogger(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		String log4jConfigFile = context.getInitParameter(LOG4J_CONFIG_LOCATION);
		String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

		PropertyConfigurator.configure(fullPath);
	}

	private void destroyConnectionPool() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.dispose();
	}
}
