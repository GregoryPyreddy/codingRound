package com.cleartrip.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.javafx.PlatformUtil;

public class ChromeBrowser implements IBrowser {
	private WebDriver driver;

	public WebDriver init() {
		driver = new ChromeDriver();
		return driver;
	}

	ChromeBrowser(String os) {
		if (os.equalsIgnoreCase("Mac")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (os.equalsIgnoreCase("Windows")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (os.equalsIgnoreCase("Linux")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

}
