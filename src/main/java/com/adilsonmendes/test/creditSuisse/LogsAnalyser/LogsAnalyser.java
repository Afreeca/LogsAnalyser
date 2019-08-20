package com.adilsonmendes.test.creditSuisse.LogsAnalyser;

import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation.DatabaseOperation;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.service.EventService;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils.Helper;

public class LogsAnalyser
{
	private static final String filepath = "log-events/logs.txt";

	public static void main(String[] args) throws SQLException, IOException, ParseException {
		DatabaseOperation con = DatabaseOperation.create();
		String createTable = Helper.readToString("sql/logEvent.sql");
		con.createTable(createTable);

		EventService.processLogFile(filepath);
	}
}
