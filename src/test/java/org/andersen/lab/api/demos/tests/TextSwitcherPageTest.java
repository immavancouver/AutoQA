package org.andersen.lab.api.demos.tests;

import io.appium.java_client.AppiumDriver;
import org.andersen.lab.pages.ApiDemosMainPage;
import org.andersen.lab.pages.TextSwitcherPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.AppiumDriverFactory;

public class TextSwitcherPageTest {

	private AppiumDriver driver;

	private ApiDemosMainPage apiDemosMainPage;

	@BeforeClass
	public void setUp() {
		driver = AppiumDriverFactory.getInstance().getDriver();
		apiDemosMainPage = new ApiDemosMainPage(driver);
	}

	@Test
	public void testTextSwitcherFunctionality() {
		TextSwitcherPage textSwitcherPage = apiDemosMainPage.openViewsPage()
				.openTextSwitcherPage();

		Assert.assertEquals(textSwitcherPage.getCurrentCounterValue(), 0,
				"Initial counter value should be 0");

		int clicksCount = 5;

		textSwitcherPage.clickNextButton(clicksCount);

		Assert.assertEquals(textSwitcherPage.getCurrentCounterValue(), clicksCount,
				String.format("Counter should show %d after %d clicks", clicksCount, clicksCount));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
