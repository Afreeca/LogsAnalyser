package com.adilsonmendes.test.creditSuisse.LogsAnalyser.service;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

import com.adilsonmendes.test.creditSuisse.LogsAnalyser.dbOperation.DatabaseOperation;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.model.Event.Status;
import com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils.Helper;

public class EventService {
	private static Logger LOG = LogManager.getLogger(DatabaseOperation.class);
	
	/* 
	 * this method will allow us to iterate through every single line in the file, process it without keeping it in the memory
	 * allowing us to process large files.
	 * For every entry in the log with the state = START, we will create a new thread to process it(search for the FINISHED state)
	 * and flag it(log it in the database if it's necessary)
	 */
	public static void processLogFile(String path) throws IOException, ParseException {
		LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");
		try {
		    while (it.hasNext()) {
		        String line = it.nextLine();
		        Event ev = Helper.convertStringJson(line);
		        
		        LOG.info(String.format("log event id: %s",ev.getId()));
		        
		        if(Status.valueOf(ev.getState().toString()) == Status.STARTED){
		        	Thread newThread = new SearchLogThread(ev, path);
			    	newThread.setName(ev.getId());
					newThread.start();
		        }
		    }
		} finally {
		    it.close();
		}
	}
}
