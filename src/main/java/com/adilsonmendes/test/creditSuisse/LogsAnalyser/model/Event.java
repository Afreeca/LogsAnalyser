package com.adilsonmendes.test.creditSuisse.LogsAnalyser.model;

import java.sql.Timestamp;

public class Event {
	private String id;
	private String type;
	private String host;
	private Timestamp timestamp;
	private Status State; 
	
	public Event() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	} 

	public Status getState() {
		return State;
	}

	public void setState(Status state) {
		State = state;
	}

	public enum Status { STARTED, FINISHED }
}