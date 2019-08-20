package com.adilsonmendes.test.creditSuisse.LogsAnalyser.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}
