package org.andersen.lab.api.demos.tests;

import io.appium.java_client.AppiumDriver;
import org.andersen.lab.pages.ApiDemosMainPage;
import org.andersen.lab.pages.DateWidgetsPage;
import org.andersen.lab.pages.ViewsPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.AppiumDriverFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateWidgetsPageTest {

	private AppiumDriver driver;

	private ApiDemosMainPage apiDemosMainPage;

	@BeforeClass
	public void setUp() {
		driver = AppiumDriverFactory.getInstance()
				.getDriver();

		apiDemosMainPage = new ApiDemosMainPage(driver);
	}

	@Test
	public void testSetTomorrowDateAndTime() {
		DateWidgetsPage dateWidgetsPage = apiDemosMainPage.openViewsPage()
				.openDateWidgets();

		dateWidgetsPage.openDialog();

		dateWidgetsPage.setTomorrowDateAndTime();

		String actualDateTime = dateWidgetsPage.getDisplayedDateTime();

		LocalDate tomorrow = LocalDate.now().plusDays(1);

		String expectedDateTime = tomorrow.format(DateTimeFormatter.ofPattern("M-d-yyyy")) + " 23:11";

		Assert.assertEquals(actualDateTime, expectedDateTime, "Displayed date and time does not match expected.");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
