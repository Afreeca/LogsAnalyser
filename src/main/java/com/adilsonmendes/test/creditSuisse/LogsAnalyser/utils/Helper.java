package com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event.Status;

public class Helper {
	private static Logger LOG = LogManager.getLogger(Helper.class);
	
	public static String readToString(String filename) {
		try {
			File file = new File(filename);
			String string = FileUtils.readFileToString(file, "utf-8");
			return string;
			
		}catch (Exception e) {
			LOG.error(String.format("Failed to read from the file named '%s'", filename));
			LOG.debug(e);
		}
		return null;
	}
	
	public static void copyFileTo(String from, String to) throws IOException{
        FileUtils.copyFile(new File(from), new File(to));
    }
	
	public static Event convertStringJson(String jsonStr) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jObj =  (JSONObject) parser.parse(jsonStr);
		Event event = new Event();
		
		
		if (jObj.get("id") != null)
			event.setId(jObj.get("id").toString());
		if (jObj.get("state")!= null)
			event.setState(Status.valueOf(jObj.get("state").toString()) == Status.STARTED ? Status.STARTED : Status.FINISHED);
		if (jObj.get("type") != null)
			event.setType(jObj.get("type").toString());
		if (jObj.get("timestamp") != null)
			event.setTimestamp(new Timestamp(Long.parseLong(jObj.get("timestamp").toString())));
		return event;
		
	}
	
	public static long getTimestampDifference(Timestamp a, Timestamp b){
		return b.getTime() - a.getTime();
	}
}
