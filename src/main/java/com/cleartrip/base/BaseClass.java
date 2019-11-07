package com.cleartrip.base;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.cleartrip.utils.Timeouts;

public class BaseClass implements Timeouts {

	private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<WebDriver>();
	private Logger log = Logger.getLogger(BaseClass.class.getName());
	public String host = "https://www.cleartrip.com/";
	
	public static WebDriver getDriver() {
		return driverInstance.get();
	}
	
	public void setDriver(WebDriver driver) {
		driverInstance.set(driver);
	}
	
	@BeforeClass
	@Parameters({"browserType","os"})
	public void intiateDriver(@Optional("chrome") String browserType, @Optional("Windows") String os) {
		
	}
}
