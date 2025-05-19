package org.andersen.lab.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.andersen.lab.constants.PageElements.DIALOG_OPTION;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class DateWidgetsPage {

	private final AppiumDriver driver;
	private final WebDriverWait wait;

	public DateWidgetsPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public DialogPage openDialogPage() {
		wait.until(elementToBeClickable(DIALOG_OPTION))
				.click();

		return new DialogPage(driver);
	}

}
