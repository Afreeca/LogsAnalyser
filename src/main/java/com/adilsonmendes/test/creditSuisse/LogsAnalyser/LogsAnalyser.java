package com.adilsonmendes.test.creditSuisse.LogsAnalyser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation.DatabaseOperation;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.service.EventService;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils.Helper;

public class LogsAnalyser
{
	private static final String filepath = "log-events/logs.txt";

	public static void main(String[] args) throws SQLException, IOException, ParseException {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter the path of the log file that you wish to process\n>");
		String path = in.nextLine();
		if(path.isEmpty())
			path = filepath;
		
		DatabaseOperation con = DatabaseOperation.create();
		String createTable = Helper.readToString("sql/logEvent.sql");
		con.createTable(createTable);

		EventService.processLogFile(path);
	}
}
