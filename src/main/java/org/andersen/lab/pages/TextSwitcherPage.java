package org.andersen.lab.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Integer.parseInt;
import static org.andersen.lab.constants.PageElements.COUNTER_TEXT;
import static org.andersen.lab.constants.PageElements.NEXT_BTN;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TextSwitcherPage {

	private final AppiumDriver driver;
	private final WebDriverWait wait;

	public TextSwitcherPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickNextButton(int times) {
		WebElement nextBtn = wait.until(visibilityOfElementLocated(NEXT_BTN));

		for (int i = 0; i < times; i++) {
			nextBtn.click();

			waitFor(300);
		}
	}

	public int getCurrentCounterValue() {
		WebElement counter = wait.until(presenceOfElementLocated(COUNTER_TEXT));

		return parseInt(counter.getText());
	}

	private void waitFor(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
