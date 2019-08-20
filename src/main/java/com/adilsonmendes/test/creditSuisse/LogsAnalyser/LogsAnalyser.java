package com.adilsonmendes.test.creditSuisse.LogsAnalyser;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation.DatabaseOperation;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Log;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.service.EventService;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.service.LogService;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils.Helper;


public class LogsAnalyser 
{
	private static final String filepath = "log-events/logs.txt";
	
	public static void main(String[] args) throws IOException, ParseException {
		EventService.processLogFile(filepath);
		
//		DatabaseOperation dbOperation = new DatabaseOperation();
//		dbOperation.OpenConnection();
//		dbOperation.ConnectToDB();
//
//		String createTable = Helper.readToString("sql/logEvent.sql");
//
//		LogService.createLogTable(dbOperation, createTable);
//		LogService.createLog(dbOperation, new Log("testID1", 1491377495210L, "APPLICATION_LOG", "12345", false));
	}

}
