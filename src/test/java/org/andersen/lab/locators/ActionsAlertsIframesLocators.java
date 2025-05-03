package org.andersen.lab.locators;

import org.openqa.selenium.By;

public class ActionsAlertsIframesLocators {

	public static final By SINGLE_CLICK_BUTTON = By.xpath("//button[@id='AlertButton']");
	public static final By DOUBLE_CLICK_BUTTON = By.xpath("//button[text()='Get Discount']");
	public static final By RIGHT_CLICK_BUTTON = By.xpath("//button[text()='Cancel course']");
	public static final By RESULT_MESSAGE = By.xpath("//span[@class='font-light flex']");
	public static final By SINGLE_IFRAME_LOCATOR = By.xpath("//iframe[@title='Finish your registration']");

}
