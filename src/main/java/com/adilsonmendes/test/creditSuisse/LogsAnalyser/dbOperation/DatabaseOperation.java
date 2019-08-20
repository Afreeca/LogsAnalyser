package com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.Database;

import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Log;

public class DatabaseOperation {
	private static Logger LOG = LogManager.getLogger(DatabaseOperation.class);

	private static DatabaseOperation db = null;
	private Connection con = null;
	private Statement stmt = null;
	private final static String host = "jdbc:hsqldb:file:database-data/mydatabase";
	private final static String username = "SA";
	private final static String password = "";

	private DatabaseOperation()
	{
		OpenConnection();
		ConnectToDB();
	}

	private void OpenConnection()
	{
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			LOG.debug("instantiate com.mysql.jdbc.Driver: DB connection open");
		}
		catch (Exception e) {
			LOG.error("Error: Failed to open a DB coonecton");
			LOG.debug(e);
		}
	}

	private void ConnectToDB()
	{
		try {
			con = DriverManager.getConnection(host, username, password);
			stmt = con.createStatement();
			LOG.info("Connected to host: " + host);
		}catch (Exception e) {
			LOG.error(String.format("Failed to connect to the DB host s%", host));
			LOG.debug(e);
		}
	}

	public static DatabaseOperation create()
	{
		if(db == null)
		{
			db = new DatabaseOperation();
		}
		return db;
	}

	public void createTable(String sql)
	{
		try {
			stmt.executeUpdate(sql);
			LOG.info("logEvent table created");
		}catch (Exception e) {
			LOG.info("Failed to create table 'logEvent'");
			LOG.debug(e);
		}
	}

	public void insertLog(Log log) throws SQLException{

		try{
			PreparedStatement statement = null;

			statement = con.prepareStatement("insert into logEvent (eventId, duration, type, host, alert) values (?, ?, ?, ?, ?)");

			statement.setString(1, log.getEventId());
			statement.setLong(2, log.getDuration());
			statement.setString(3, log.getHost());
			statement.setString(4, log.getType());
			statement.setBoolean(5, log.getAlert());

			statement.executeUpdate();

			statement.close();
			LOG.info("logEvent entry created");

		} catch (SQLException e)
		{
			LOG.info("Failed to insert a new entry into the logEvent table");
			LOG.debug(e);
		}
	};

	public void close()
	{
		if(con != null)
		{
			try{
				stmt.close();
				con.close();
			}catch(Exception e){
				LOG.info("Failed to close DB connection");
				LOG.debug(e);
			}
		}
	}
}
