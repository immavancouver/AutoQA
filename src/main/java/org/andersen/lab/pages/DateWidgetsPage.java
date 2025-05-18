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

public class DateWidgetsPage {
	private final AppiumDriver driver;
	private final WebDriverWait wait;

	private final By dialogOption = AppiumBy.accessibilityId("1. Dialog");
	private final By changeDateBtn = AppiumBy.accessibilityId("change the date");
	private final By changeTimeBtn = AppiumBy.accessibilityId("change the time (spinner)");
	private final By okButton = AppiumBy.id("android:id/button1");

	private final By hourPicker = AppiumBy.xpath("(//android.widget.NumberPicker)[1]");
	private final By minutePicker = AppiumBy.xpath("(//android.widget.NumberPicker)[2]");
	private final By amPmPicker = AppiumBy.xpath("(//android.widget.NumberPicker)[3]");

	private final By datePicker = AppiumBy.id("android:id/datePicker");
	private final By monthYearHeader = AppiumBy.id("android:id/date_picker_header_year");
	private final By nextMonthBtn = AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Next month']");

	public DateWidgetsPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void openDialog() {
		wait.until(ExpectedConditions.elementToBeClickable(dialogOption)).click();
	}

	public void setTomorrowDateAndTime() {
		setTomorrowDate();
		setSpecificTime(23, 11);
	}

	private void setTomorrowDate() {
		wait.until(ExpectedConditions.elementToBeClickable(changeDateBtn)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(datePicker));

		LocalDate tomorrow = LocalDate.now().plusDays(1);
		int tomorrowDay = tomorrow.getDayOfMonth();
		String tomorrowMonth = tomorrow.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		int tomorrowYear = tomorrow.getYear();

		int attempts = 0;
		final int MAX_ATTEMPTS = 24;

		while (attempts < MAX_ATTEMPTS) {
			String currentHeaderText = wait.until(ExpectedConditions.visibilityOfElementLocated(monthYearHeader)).getText();

			System.out.println("Current header: '" + currentHeaderText + "'");
			System.out.println("Looking for: '" + tomorrowMonth + " " + tomorrowYear + "' or year '" + tomorrowYear + "'");

			if (currentHeaderText.equals(String.valueOf(tomorrowYear))) {

				if (isMonthCorrect(tomorrowMonth)) {

					break;
				}
			} else if (currentHeaderText.contains(tomorrowMonth) && currentHeaderText.contains(String.valueOf(tomorrowYear))) {

				break;
			}

			wait.until(ExpectedConditions.elementToBeClickable(nextMonthBtn)).click();
			attempts++;
			waitFor(500);
		}

		if (attempts >= MAX_ATTEMPTS) {
			throw new RuntimeException("Failed to set date: " + tomorrowMonth + " " + tomorrowYear);
		}

		By dayLocator = AppiumBy.xpath(String.format("//android.view.View[@text='%d']", tomorrowDay));
		wait.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();

		wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
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
		wait.until(ExpectedConditions.elementToBeClickable(changeTimeBtn)).click();

		setNumberPickerValue(hourPicker, hour % 12 == 0 ? 12 : hour % 12);

		setNumberPickerValue(minutePicker, minute);

		String amPm = hour < 12 ? "AM" : "PM";
		setNumberPickerValue(amPmPicker, amPm);

		wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
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