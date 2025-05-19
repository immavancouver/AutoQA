package org.andersen.lab.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.andersen.lab.constants.PageElements.VIEWS;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ApiDemosMainPage {

	private final AppiumDriver driver;
	private final WebDriverWait wait;

	public ApiDemosMainPage(AppiumDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public ViewsPage openViewsPage() {
		wait.until(visibilityOfElementLocated(VIEWS))
				.click();

		return new ViewsPage(driver);
	}

}
