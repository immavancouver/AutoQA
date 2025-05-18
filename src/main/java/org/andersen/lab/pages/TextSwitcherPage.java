package org.andersen.lab.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextSwitcherPage {
	private final AppiumDriver driver;
	private final WebDriverWait wait;


	private final By nextButton = AppiumBy.id("io.appium.android.apis:id/next");
	private final By counterText = AppiumBy.androidUIAutomator(
			"new UiSelector().resourceId(\"io.appium.android.apis:id/switcher\")" +
					".childSelector(new UiSelector().className(\"android.widget.TextView\"))");

	public TextSwitcherPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickNextButton(int times) {
		WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(nextButton));
		for (int i = 0; i < times; i++) {
			nextBtn.click();
			waitFor(300);
		}
	}

	public int getCurrentCounterValue() {
		WebElement counter = wait.until(ExpectedConditions.presenceOfElementLocated(counterText));

		return Integer.parseInt(counter.getText());
	}

	private void waitFor(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
