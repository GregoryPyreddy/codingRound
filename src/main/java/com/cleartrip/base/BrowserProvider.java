package com.cleartrip.base;

public class BrowserProvider {
	
	public static IBrowser getBrowserInstance(String browserType, String os) {
		switch(browserType) {
		case "chrome" :
			return new ChromeBrowser(os);
		default:
			return new ChromeBrowser(os);
		}
	}

}
