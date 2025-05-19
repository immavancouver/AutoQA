package org.andersen.lab.constants;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public final class PageElements {

	public static final By VIEWS = AppiumBy.accessibilityId("Views");

	public static final By DIALOG_OPTION = AppiumBy.accessibilityId("1. Dialog");

	public static final By VIEWS_OPTION = AppiumBy.xpath(
			"//android.widget.ListView/android.widget.TextView"
	);

	public static final By DATE_WIDGETS_OPTIONS = AppiumBy.accessibilityId("Date Widgets");

	public static final By CHANGE_DATE_BTN = AppiumBy.accessibilityId("change the date");
	public static final By CHANGE_TIME_BTN = AppiumBy.accessibilityId("change the time (spinner)");
	public static final By OK_BTN = AppiumBy.id("android:id/button1");

	public static final By HOUR_PICKER = AppiumBy.xpath("(//android.widget.NumberPicker)[1]");
	public static final By MINUTE_PICKER = AppiumBy.xpath("(//android.widget.NumberPicker)[2]");
	public static final By AM_PM_PICKER = AppiumBy.xpath("(//android.widget.NumberPicker)[3]");

	public static final By DATE_PICKER = AppiumBy.id("android:id/datePicker");
	public static final By MONTH_YEAR_HEADER = AppiumBy.id("android:id/date_picker_header_year");
	public static final By NEXT_MONTH_BTN = AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Next month']");

	public static final By NEXT_BTN = AppiumBy.id("io.appium.android.apis:id/next");
	public static final By COUNTER_TEXT = AppiumBy.androidUIAutomator(
			"new UiSelector().resourceId(\"io.appium.android.apis:id/switcher\")" +
					".childSelector(new UiSelector().className(\"android.widget.TextView\"))");

	private PageElements() {
		throw new UnsupportedOperationException();
	}

}
