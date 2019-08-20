package com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.Database;

public class DatabaseOperation {
	private static Logger LOG = LogManager.getLogger(DatabaseOperation.class);
	
	private Connection connection;
	private static DatabaseOperation dbIsntance;
	
	
	private DatabaseOperation(){};
	
	 public static DatabaseOperation getInstance(){
	    if(dbIsntance==null){
	        dbIsntance= new DatabaseOperation();
	    }
	    return dbIsntance;
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

	
	public void closeConnection(Connection con) {
		try {
			con.close();
			LOG.debug("DB connection Close");
		} catch (Exception e) {
			LOG.error("Failed to close the connection to the host: ");
			LOG.debug(e);
		}
	}
	
	public  Connection getConnection() throws SQLException{

		if(connection==null || connection.isClosed()){
            try {
            	 final String host = "jdbc:hsqldb:file:database-data/logdb;shutdown=true";
            	 final String username = "SA";
            	 final String password = "";
            	connection = DriverManager.getConnection( host, username, password );
            } catch (SQLException ex) {

            }
        }

        return connection;
    }
}
