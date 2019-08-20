package com.adilsonmendes.test.creditSuisse.LogsAnalyser;

import com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation.DatabaseOperation;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Log;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.service.LogService;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils.Helper;


public class LogsAnalyser 
{
	public static void main(String[] args) {
		DatabaseOperation dbOperation = new DatabaseOperation();
		dbOperation.OpenConnection();
		dbOperation.ConnectToDB();

		String createTable = Helper.readToString("sql/logEvent.sql");

		LogService.createLogTable(dbOperation, createTable);
		LogService.createLog(dbOperation, new Log("testID1", 1491377495210L, "APPLICATION_LOG", "12345", false));
	}

}
