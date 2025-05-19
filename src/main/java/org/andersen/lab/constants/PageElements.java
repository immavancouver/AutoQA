package org.andersen.lab.constants;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class PageElements {

	public static final By VIEWS = AppiumBy.accessibilityId("Views");

	public static final By DIALOG_OPTION = AppiumBy.accessibilityId("1. Dialog");

	public static final By DATE_WIDGETS_OPTIONS = AppiumBy.accessibilityId("Date Widgets");

	public static final By CHANGE_TIME_BTN = AppiumBy.accessibilityId("change the time (spinner)");
	public static final By OK_BTN = AppiumBy.id("android:id/button1");

	public static final By HOUR_PICKER = AppiumBy.xpath("(//android.widget.NumberPicker)[1]");
	public static final By MINUTE_PICKER = AppiumBy.xpath("(//android.widget.NumberPicker)[2]");
	public static final By AM_PM_PICKER = AppiumBy.xpath("(//android.widget.NumberPicker)[3]");

	public static final By PICK_DATE = AppiumBy.id("io.appium.android.apis:id/pickDate");
	public static final By NUMBER_PICKER = AppiumBy.id("android:id/numberpicker_input");

	public static final By NEXT_BTN = AppiumBy.id("io.appium.android.apis:id/next");

	public static final By COUNTER_TEXT = AppiumBy.androidUIAutomator(
			"new UiSelector().resourceId(\"io.appium.android.apis:id/switcher\")"
					+ ".childSelector(new UiSelector().className(\"android.widget.TextView\"))");

	public static final By TEXT_SWITCHER = AppiumBy.androidUIAutomator(
			"new UiScrollable(new UiSelector().scrollable(true))" +
					".scrollIntoView(new UiSelector().text(\"TextSwitcher\"))");

	public static final By DATE_DISPLAY = AppiumBy.id("io.appium.android.apis:id/dateDisplay");

	private PageElements() {
		throw new UnsupportedOperationException();
	}

}
