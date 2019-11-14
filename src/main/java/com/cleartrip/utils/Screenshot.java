package com.cleartrip.utils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.cleartrip.base.BaseClass;


public class Screenshot extends BaseClass {
	
	public static void takeSnapShot(String screenshotPath) throws Exception {
		
		TakesScreenshot scrShot = ((TakesScreenshot)getDriver());
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(screenshotPath);
		FileUtils.copyFile(srcFile, DestFile);
	}

}
