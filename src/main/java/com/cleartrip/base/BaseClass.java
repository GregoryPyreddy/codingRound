package com.cleartrip.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.cleartrip.listeners.EventHandler;
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
		log.info("Initiating the browser");
		WebDriver driver = BrowserProvider.getBrowserInstance(browserType, os).init();
		driverInstance.set(driver);
		initializeEventDriver();
		registerDriver();
	}
	
	@AfterClass
	public void quitBrowser() {
		if(getDriver()!=null) {
			getDriver().quit();
		}
	}
	
	public void initializeEventDriver() {
		EventFiringWebDriver eventDriver = new EventFiringWebDriver(getDriver());
		setDriver(eventDriver);
	}
	
	public void registerDriver() {
		EventHandler handler = new EventHandler();
		((EventFiringWebDriver)driverInstance.get()).register(handler);
	}
}
