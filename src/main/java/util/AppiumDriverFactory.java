package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverFactory {

	public static final class InstanceHolder {

		private static final AppiumDriverFactory INSTANCE = new AppiumDriverFactory();

	}

	public static AppiumDriverFactory getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public AppiumDriver getDriver() {
		UiAutomator2Options options = new UiAutomator2Options()
				.setDeviceName("Pixel_5_API_33")
				.setPlatformName("Android")
				.setPlatformVersion("13.0")
				.setAutomationName("UiAutomator2")
				.setAppPackage("io.appium.android.apis")
				.setAppActivity("io.appium.android.apis.ApiDemos");

		try {
			return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		} catch (MalformedURLException exc) {
			throw new RuntimeException(exc);
		}
	}

	private AppiumDriverFactory() {

	}

}