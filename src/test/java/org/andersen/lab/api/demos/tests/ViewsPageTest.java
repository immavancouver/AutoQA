package org.andersen.lab.api.demos.tests;

import io.appium.java_client.AppiumDriver;
import org.andersen.lab.pages.ApiDemosMainPage;
import org.andersen.lab.pages.ViewsPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.AppiumDriverFactory;

public class ViewsPageTest {

	private AppiumDriver driver;

	private ApiDemosMainPage apiDemosMainPage;

	@BeforeClass
	public void setUp() {
		driver = AppiumDriverFactory.getInstance().getDriver();
		apiDemosMainPage = new ApiDemosMainPage(driver);
	}

	@Test
	public void testViewsItemCount() {
		ViewsPage viewsPage = apiDemosMainPage.openViewsPage();

		int actualCount = viewsPage.getViewsItemCountWithScroll();

		Assert.assertEquals(actualCount, 42, "Expected 42 view items");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
