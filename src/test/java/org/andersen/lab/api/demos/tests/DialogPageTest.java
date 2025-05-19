package org.andersen.lab.api.demos.tests;

import io.appium.java_client.AppiumDriver;
import org.andersen.lab.pages.ApiDemosMainPage;
import org.andersen.lab.pages.DialogPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.AppiumDriverFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class DialogPageTest {

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
		DialogPage dialogPage = apiDemosMainPage
				.openViewsPage()
				.openDateWidgets()
				.openDialogPage();

		LocalDateTime expectedDateTime = LocalDate.now()
				.plusDays(1L)
				.atTime(23, 11);

		dialogPage.setDate(expectedDateTime.toLocalDate());
		dialogPage.setTime(expectedDateTime.toLocalTime());

		assertEquals(dialogPage.getDateTime(), expectedDateTime);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
