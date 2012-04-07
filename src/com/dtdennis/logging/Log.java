package com.dtdennis.logging;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.TreeMap;

public class Log {
	private TreeMap<Long, String> logEntries;
	private long startTimeMillis;

	public Log() {
		startTimeMillis=System.currentTimeMillis();
		logEntries = new TreeMap<Long, String>();
		logEntries.put(startTimeMillis, "Log created");
	}

	public Log(Log existingLog){
		logEntries=existingLog.getLogMap();
		startTimeMillis=logEntries.firstKey();
	}

	private String getUtcFormattedTime(long milliseconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliseconds);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(cal.getTime());
	}
	
	public void append(Log appendageLog){
		logEntries.putAll(appendageLog.getLogMap());
	}
	
	public void addEntry(String msg) {
		logEntries.put(System.currentTimeMillis(), msg);
	}

	public void reset() {
		logEntries.clear();
	}

	public TreeMap<Long,String> getLogMap(){
		return logEntries;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		long timeKey = startTimeMillis;
		while (i < logEntries.size()) {
			if (logEntries.containsKey(timeKey)) {
				sb.append(getUtcFormattedTime(timeKey) + ": "
						+ logEntries.get(timeKey) + "\n");
				i++;
			}
			timeKey++;
		}
		return sb.toString();
	}
}
