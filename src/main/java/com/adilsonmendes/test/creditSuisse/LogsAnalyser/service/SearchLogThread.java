package com.adilsonmendes.test.creditSuisse.LogsAnalyser.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.json.simple.parser.ParseException;

import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event.Status;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils.Helper;

public class SearchLogThread extends Thread{
	Event eventStart;
	String path;
	
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
		        	boolean test = validateLog(eventStart.getTimestamp(), ev.getTimestamp());
		        	if(test)
		        		System.out.println("viva caralho: " + ev.getId());
		        }
		    }
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
		    try {
				it.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static boolean validateLog(Timestamp started, Timestamp finished) {
    	return Helper.getTimestampDifference(started, finished) > 4;
	}
	
}
