package org.andersen.lab.locators;

import org.openqa.selenium.By;

public class DragAndDropLocators {

	public static final By SUCCESS_MESSAGE = By.xpath("//div[@class='text-lg flex absolute -top-10 right-0 px-10 py-8 shadow-custom transition-opacity duration-500 opacity-100']");
	public static final By WRITE_CASES = By.id("manual1");
	public static final By TESTING_REQUIREMENTS = By.id("manual2");
	public static final By WRITE_AUTOMATION_SCRIPTS = By.id("auto1");
	public static final By FRAMEWORK_SETUP = By.id("auto2");
	public static final By MANUAL_WORK_CELL_1 = By.id("target-manual1");
	public static final By MANUAL_WORK_CELL_2 = By.id("target-manual2");
	public static final By AUTOMATION_WORK_CELL_1 = By.id("target-auto1");
	public static final By AUTOMATION_WORK_CELL_2 = By.id("target-auto2");
	public static final By FINISH_BUTTON = By.xpath("//button[text()='Finish']");

}
