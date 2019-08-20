package com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseOperation {
	private static Logger LOG = LogManager.getLogger(DatabaseOperation.class);
	
	private Connection connection = null;
	private final String host = "jdbc:hsqldb:file:database-data/logdb;shutdown=true";
	private final String username = "SA";
	private final String password = "";
	
	public DatabaseOperation(){};
	
	public Connection getConnection() {
		return connection;
	}
	
	public void OpenConnection()
	{	
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			LOG.debug("Driver acessed: DB connection open");
		}
		catch (Exception e) {
			LOG.error("Error: Failed to open a DB coonecton");
			LOG.debug(e);
		}
	}

	public void ConnectToDB()
	{
		try {
			connection = (DriverManager.getConnection(host, username, password));
			LOG.debug("Connected to host: " + host);
		}catch (Exception e) {
			LOG.error(String.format("Failed to connect to the  host s%", host));
			LOG.debug(e);
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
			LOG.debug("DB connection Close");
		} catch (Exception e) {
			LOG.error("Failed to close the connection to the host: ");
			LOG.debug(e);
		}
	}
}
