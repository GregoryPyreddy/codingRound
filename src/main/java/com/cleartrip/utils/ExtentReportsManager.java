package com.cleartrip.utils;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportsManager {
	
	static ExtentReports extent = null;
	
	public static ExtentReports getInstance() {
		return extent;
	}
	
	public static ExtentReports createInstance(String fileName) {
		extent = new ExtentReports(fileName);
		return extent;
	}

}
