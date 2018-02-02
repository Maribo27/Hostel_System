package by.tc.hostel_system.dao.connector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.tc.hostel_system.dao.connector.ConnectorConst.*;

public class ConnectionPool {
	private static final Logger logger = Logger.getLogger(ConnectionPool.class);

	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	private static volatile ConnectionPool instance = new ConnectionPool();

	public static ConnectionPool getInstance() {

		return instance;
	}

	private ConnectionPool() {
		ResourceBundle dbBundle =
				ResourceBundle.getBundle(RESOURCE_NAME);

		this.driverName = dbBundle.getString(DRIVER);
		this.url = dbBundle.getString(URL);
		this.user = dbBundle.getString(USER);
		this.password = dbBundle.getString(PASSWORD);

		String poolSize = dbBundle.getString(DB_POOL_SIZE);
		Pattern isNumberPattern = Pattern.compile("\\d+");
		Matcher matcher = isNumberPattern.matcher(poolSize);
		this.poolSize = matcher.matches() ? Integer.parseInt(poolSize) : 5;
	}

	public void initPoolData() throws ConnectionPoolException {

		try {
			Class.forName(driverName);
			givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
			connectionQueue = new ArrayBlockingQueue<>(poolSize);

			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				connectionQueue.add(connection);
			}
		} catch (SQLException e) {
			final String message = "SQL Exception: cannot get connection";
			logger.error(message, e);
			throw new ConnectionPoolException(message, e);
		} catch (ClassNotFoundException e) {
			final String message = "Can't find database driver class";
			logger.error(message, e);
			throw new ConnectionPoolException(message, e);
		}
	}

	public void dispose() throws ConnectionPoolException {
		clearConnectionQueue();
	}

	private void clearConnectionQueue() throws ConnectionPoolException {
		try {
			closeConnectionsQueue(givenAwayConQueue);
			closeConnectionsQueue(connectionQueue);
		} catch (SQLException e) {
			final String message = "Closing connection error";
			logger.error(message, e);
			throw new ConnectionPoolException(message, e);
		}
	}

	public Connection getConnection() throws ConnectionPoolException {
		Connection connection;
		try {
			connection = connectionQueue.take();
			if (connection.isClosed()){
				connection = reopenConnection();
			}
			givenAwayConQueue.add(connection);
			return connection;
		} catch (InterruptedException e) {
			String message = "Error connecting to the data source";
			logger.error(message, e);
			throw new ConnectionPoolException(message, e);
		} catch (SQLException e) {
			String message = "Error while reopening connection";
			logger.error(message, e);
			throw new ConnectionPoolException(message, e);
		}
	}

	public void closeConnection(Connection connection) throws ConnectionPoolException {
		if (!givenAwayConQueue.remove(connection)) {
			final String message = "Error deleting connection from the given away connections pool";
			logger.log(Level.ERROR, message);
			throw new ConnectionPoolException(message);
		}
		try {
			if (connection.isClosed()) {
				connection = reopenConnection();
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}

		} catch (SQLException e) {
			final String message = "Can't access connection";
			logger.error(message, e);
			throw new ConnectionPoolException(message, e);
		}

		if (!connectionQueue.offer(connection)) {
			final String message = "Error allocating connection in the pool";
			throw new ConnectionPoolException(message);
		}
	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			connection.close();
		}
	}

	private Connection reopenConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
