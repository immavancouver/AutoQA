package org.andersen.lab.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.andersen.lab.constants.PageElements.AM_PM_PICKER;
import static org.andersen.lab.constants.PageElements.CHANGE_DATE_BTN;
import static org.andersen.lab.constants.PageElements.CHANGE_TIME_BTN;
import static org.andersen.lab.constants.PageElements.DATE_PICKER;
import static org.andersen.lab.constants.PageElements.DIALOG_OPTION;
import static org.andersen.lab.constants.PageElements.HOUR_PICKER;
import static org.andersen.lab.constants.PageElements.MINUTE_PICKER;
import static org.andersen.lab.constants.PageElements.MONTH_YEAR_HEADER;
import static org.andersen.lab.constants.PageElements.NEXT_MONTH_BTN;
import static org.andersen.lab.constants.PageElements.OK_BTN;

public class DateWidgetsPage {

	private final AppiumDriver driver;
	private final WebDriverWait wait;

	public DateWidgetsPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void openDialog() {
		wait.until(ExpectedConditions.elementToBeClickable(DIALOG_OPTION)).click();
	}

	public void setTomorrowDateAndTime() {
		setTomorrowDate();
		setSpecificTime(23, 11);
	}

	private void setTomorrowDate() {
		wait.until(ExpectedConditions.elementToBeClickable(CHANGE_DATE_BTN)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(DATE_PICKER));

		LocalDate tomorrow = LocalDate.now().plusDays(1);
		int tomorrowDay = tomorrow.getDayOfMonth();
		String tomorrowMonth = tomorrow.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		int tomorrowYear = tomorrow.getYear();

		int attempts = 0;
		final int MAX_ATTEMPTS = 24;

		while (attempts < MAX_ATTEMPTS) {
			String currentHeaderText = wait.until(ExpectedConditions
					.visibilityOfElementLocated(MONTH_YEAR_HEADER)).getText();

			System.out.println("Current header: '" + currentHeaderText + "'");
			System.out.println("Looking for: '" + tomorrowMonth + " " + tomorrowYear +
					"' or year '" + tomorrowYear + "'");

			if (currentHeaderText.equals(String.valueOf(tomorrowYear))) {

				if (isMonthCorrect(tomorrowMonth)) {

					break;
				}
			} else if (currentHeaderText.contains(tomorrowMonth) &&
					currentHeaderText.contains(String.valueOf(tomorrowYear))) {

				break;
			}

			wait.until(ExpectedConditions.elementToBeClickable(NEXT_MONTH_BTN)).click();

			attempts++;
			waitFor(500);
		}

		if (attempts >= MAX_ATTEMPTS) {
			throw new RuntimeException("Failed to set date: " + tomorrowMonth + " " + tomorrowYear);
		}

		By dayLocator = AppiumBy.xpath(String.format("//android.view.View[@text='%d']", tomorrowDay));

		wait.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();

		wait.until(ExpectedConditions.elementToBeClickable(OK_BTN)).click();
	}


	private boolean isMonthCorrect(String targetMonth) {
		try {

			WebElement selectedDay = driver.findElement(AppiumBy.xpath(
					"//android.view.View[@selected='true']"));

			String contentDesc = selectedDay.getAttribute("content-desc");
			System.out.println("Selected day info: " + contentDesc);

			if (contentDesc != null) {
				for (String word : contentDesc.split(" ")) {
					if (word.equalsIgnoreCase(targetMonth)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Could not verify month: " + e.getMessage());
		}
		return false;
	}

	public void setSpecificTime(int hour, int minute) {
		wait.until(ExpectedConditions.elementToBeClickable(CHANGE_TIME_BTN)).click();

		setNumberPickerValue(HOUR_PICKER, hour % 12 == 0 ? 12 : hour % 12);

		setNumberPickerValue(MINUTE_PICKER, minute);

		String amPm = hour < 12 ? "AM" : "PM";
		setNumberPickerValue(AM_PM_PICKER, amPm);

		wait.until(ExpectedConditions.elementToBeClickable(OK_BTN)).click();
	}

	private void setNumberPickerValue(By pickerLocator, Object value) {
		WebElement picker = wait.until(ExpectedConditions.presenceOfElementLocated(pickerLocator));
		WebElement input = picker.findElement(AppiumBy.id("android:id/numberpicker_input"));

		input.click();
		input.clear();
		input.sendKeys(String.valueOf(value));

		try {
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		} catch (Exception e) {

		}
	}

	private void waitFor(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}