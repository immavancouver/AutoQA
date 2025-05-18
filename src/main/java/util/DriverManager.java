package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

	private static AppiumDriver driver;

	public DriverManager() {

	}

	public static AppiumDriver getDriver() {
		if (driver == null) {
			try {
				driver = createDriver();
			} catch (MalformedURLException e) {
				throw new RuntimeException("Failed to initialize Appium driver", e);
			}
		}
		return driver;
	}

	private static AppiumDriver createDriver() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options()
				.setDeviceName("Pixel_5_API_33")
				.setPlatformName("Android")
				.setPlatformVersion("13.0")
				.setAutomationName("UiAutomator2")
				.setAppPackage("io.appium.android.apis")
				.setAppActivity("io.appium.android.apis.ApiDemos");

		return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
