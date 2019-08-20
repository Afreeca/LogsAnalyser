package com.adilsonmendes.test.creditSuisse.LogsAnalyser.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.json.simple.parser.ParseException;

import com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation.DatabaseOperation;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event.Status;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Log;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils.Helper;

public class SearchLogThread extends Thread{
	
	Event eventStart;
	String path;
	
	static DatabaseOperation con = DatabaseOperation.create();
	
	public SearchLogThread(Event eventStart, String path) {
		this.eventStart = eventStart;
		this.path = path;
	}
	
	@Override
	public void run() {		
		LineIterator it = null;
		try {
			it = FileUtils.lineIterator(new File(path), "UTF-8");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
		    while (it.hasNext()) {
		        String line = it.nextLine();
		        Event ev = Helper.convertStringJson(line);

		        if(eventStart.getId().equalsIgnoreCase(ev.getId()) && Status.valueOf(ev.getState().toString()) == Status.FINISHED){
		        	boolean alertStatus = false;
		        	long duration = Helper.getTimestampDifference(eventStart.getTimestamp(), ev.getTimestamp());
		        	if(duration > 4)
		        		alertStatus = true;
		        	storeLog(eventStart, duration, alertStatus);
		        }
		    }

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try {
				it.close();
				con.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void storeLog(Event event, long duration, boolean alertStatus) throws SQLException {
		con.insertLog(new Log(event.getId(), duration, event.getType(), event.getHost(), alertStatus));
	}	
}
