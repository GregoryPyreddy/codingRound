package com.cleartrip.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {

	private static ExtentReports report;
	private static String fileSep = System.getProperty("file.separator");
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private Logger log = Logger.getLogger(Reports.class.getName());

	public void createReportFolder() {
		if (!(new File(System.getProperty("user.dir") + fileSep + "ExtentReports").exists())) {
			// Creating reports folder
			new File(System.getProperty("user.dir") + fileSep + "ExtentReports").mkdir();
		}
	}

	public void createHtmlReport() {
		String reportPath = System.getProperty("user.dir") + fileSep + "ExtentReports" + fileSep
				+ "AutomationReport.html";
		String extentConfigFilepath = System.getProperty("user.dir") + fileSep + "src" + fileSep + "test" + fileSep
				+ "resources" + fileSep + "extent-config.xml";
		if (ExtentReportsManager.getInstance() == null) {
			report = ExtentReportsManager.createInstance(reportPath);
		} else {
			report = ExtentReportsManager.getInstance();
		}

		report.loadConfig(new File(extentConfigFilepath));
	}

	public void createTest(String testName, String testDescription, String category) {
		ExtentTest test = report.startTest(testName, testDescription);
		test.assignCategory(category);
		extentTest.set(test);
		log.info("Execution of test " + testName + " just started");
		log.info("");// just to add a new empty line
	}

	public ExtentTest getTest() {
		return extentTest.get();
	}

	public void updateTestStatus(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			passTest(result);
		} else if (result.getStatus() == ITestResult.FAILURE) {
			failTest(result);
		} else if (result.getStatus() == ITestResult.SKIP) {
			skipTest(result);
		}
	}

	public void passTest(ITestResult result) {
		extentTest.get().log(LogStatus.PASS, "Test Case " + result.getName() + " is passed");
	}

	public void failTest(ITestResult result) {
		extentTest.get().log(LogStatus.FAIL, "Test Case " + result.getName() + " is failed");
	}

	public void skipTest(ITestResult result) {
		extentTest.get().log(LogStatus.SKIP, "Test Case " + result.getName() + " is skipped");
	}

	public static void logSuccess(String successMsg) {
		extentTest.get().log(LogStatus.PASS, successMsg);
	}

	public static void logFailure(String failureMsg) {
		extentTest.get().log(LogStatus.FAIL, failureMsg);
	}

	public static void logInfo(String msg) {
		extentTest.get().log(LogStatus.INFO, msg);
	}

	public static void addScreenshot() {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + fileSep + "src" + fileSep + "test" + fileSep
				+ "resources" + fileSep + "screenshots" + fileSep + dateName;
		try {
			Screenshot.takeSnapShot(screenshotPath);
		}catch(Exception e) {
			logFailure("Exception occured while taking screenshot with message "+e.getMessage());
		}
		extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture(screenshotPath));
	}
}
