package org.andersen.lab.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.andersen.lab.constants.PageElements.VIEWS;

public class ApiDemosMainPage {

	private final AppiumDriver driver;
	private final WebDriverWait wait;

	public ApiDemosMainPage(AppiumDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void openViews() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(VIEWS)).click();
	}
}
