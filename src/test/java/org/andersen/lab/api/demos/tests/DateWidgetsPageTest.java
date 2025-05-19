package org.andersen.lab.api.demos.tests;

import io.appium.java_client.AppiumDriver;
import org.andersen.lab.pages.ApiDemosMainPage;
import org.andersen.lab.pages.DateWidgetsPage;
import org.andersen.lab.pages.ViewsPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.DriverManager;

	public class DateWidgetsPageTest {

		private AppiumDriver driver;

		private ApiDemosMainPage apiDemosMainPage;

		private ViewsPage viewsPage;

		private DateWidgetsPage dateWidgetsPage;

		@BeforeClass
		public void setUp() {
			driver = DriverManager.getDriver();
			apiDemosMainPage = new ApiDemosMainPage(driver);
		}

		@Test
		public void testSetTomorrowDateAndTime() {

			apiDemosMainPage.openViews();

			viewsPage = new ViewsPage(driver);
			viewsPage.openDateWidgets();

			dateWidgetsPage = new DateWidgetsPage(driver);
			dateWidgetsPage.openDialog();
			dateWidgetsPage.setTomorrowDateAndTime();

		}

		@AfterClass
		public void tearDown() {
			DriverManager.quitDriver();
		}
	}
