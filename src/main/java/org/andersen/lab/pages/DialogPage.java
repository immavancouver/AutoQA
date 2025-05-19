package org.andersen.lab.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static java.lang.Long.signum;
import static java.lang.Math.abs;
import static org.andersen.lab.constants.PageElements.AM_PM_PICKER;
import static org.andersen.lab.constants.PageElements.CHANGE_TIME_BTN;
import static org.andersen.lab.constants.PageElements.DATE_DISPLAY;
import static org.andersen.lab.constants.PageElements.HOUR_PICKER;
import static org.andersen.lab.constants.PageElements.MINUTE_PICKER;
import static org.andersen.lab.constants.PageElements.NUMBER_PICKER;
import static org.andersen.lab.constants.PageElements.OK_BTN;
import static org.andersen.lab.constants.PageElements.PICK_DATE;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class DialogPage {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("M-d-yyyy HH:mm", Locale.ENGLISH);
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);

	private final AppiumDriver driver;
	private final WebDriverWait wait;

	public DialogPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void setDate(LocalDate date) {
		var currentDate = getDateTime()
				.toLocalDate();

		if (currentDate.isEqual(date)) {
			return;
		}

		wait.until(visibilityOfElementLocated(PICK_DATE))
				.click();

		if (date.getYear() != currentDate.getYear() || date.getMonth() != currentDate.getMonth()) {
			var months = ChronoUnit.MONTHS.between(currentDate, date);
			for (long i = 0; i <= abs(months); i++) {
				var button = signum(months) >= 0
						? "android:id/next"
						: "android:id/prev";

				wait.until(visibilityOfElementLocated(id(button)))
						.click();
			}
		}

		var dateString = date.format(DATE_FORMATTER);
		wait.until(visibilityOfElementLocated(accessibilityId(dateString)))
				.click();

		wait.until(elementToBeClickable(OK_BTN))
				.click();
	}

	public void setTime(LocalTime time) {
		int hour = time.getHour();
		int minute = time.getMinute();

		int hour12 = (hour % 12 == 0) ? 12 : hour % 12;
		String amPm = hour < 12 ? "AM" : "PM";

		wait.until(elementToBeClickable(CHANGE_TIME_BTN))
				.click();

		setNumberPickerValue(HOUR_PICKER, hour12);
		setNumberPickerValue(MINUTE_PICKER, minute);
		setNumberPickerValue(AM_PM_PICKER, amPm);

		wait.until(elementToBeClickable(OK_BTN))
				.click();
	}

	private void setNumberPickerValue(By pickerLocator, Object value) {
		WebElement input = wait.until(presenceOfElementLocated(pickerLocator))
				.findElement(NUMBER_PICKER);

		input.click();
		input.clear();
		input.sendKeys(value.toString());

		try {
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	}

	public LocalDateTime getDateTime() {
		var dateTimeString = wait.until(visibilityOfElementLocated(DATE_DISPLAY))
				.getText();

		return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
	}

}