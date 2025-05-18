package org.andersen.lab.api.demos.tests;

import io.appium.java_client.AppiumDriver;
import org.andersen.lab.pages.ApiDemosMainPage;
import org.andersen.lab.pages.ViewsPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.DriverManager;

public class ViewsPageTest {

	AppiumDriver driver;
	ApiDemosMainPage apiDemosMainPage;

	@BeforeClass
	public void setUp() {
		driver = new DriverManager().getDriver();
		apiDemosMainPage = new ApiDemosMainPage(driver);
	}

	@Test
	public void testViewsItemCount() {
		apiDemosMainPage.openViews();

		ViewsPage viewsPage = new ViewsPage(driver);
		int actualCount = viewsPage.getViewsItemCountWithScroll(); // Теперь с прокруткой

		System.out.println("Total Views items found: " + actualCount);
		Assert.assertEquals(actualCount, 42, "Expected 42 view items");
	}

	@AfterClass
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
