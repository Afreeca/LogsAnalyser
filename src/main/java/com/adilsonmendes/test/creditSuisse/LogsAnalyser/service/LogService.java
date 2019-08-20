package com.adilsonmendes.test.creditSuisse.LogsAnalyser.service;

import com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation.DatabaseOperation;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Log;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogService {

	private static Logger LOG = LogManager.getLogger(LogService.class.getName());

	public static void createLogTable(DatabaseOperation dbOperation, String script) {
		try {
			Connection con = dbOperation.getConnection();
			con.createStatement().executeUpdate(script);
			LOG.info("logEvent table created");
		}catch (Exception e) {
			LOG.error("Failed to create table 'logEvent'");
			LOG.debug(e);
		}
	}

	public static void createLog(DatabaseOperation dbOperation, Log log){
		Connection con = dbOperation.getConnection();
		
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
			LOG.error(e);
		} finally {
			dbOperation.closeConnection();
		}
	};
}
