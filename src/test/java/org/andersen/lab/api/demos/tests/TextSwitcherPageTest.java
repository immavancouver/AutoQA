package org.andersen.lab.api.demos.tests;

import io.appium.java_client.AppiumDriver;
import org.andersen.lab.pages.ApiDemosMainPage;
import org.andersen.lab.pages.TextSwitcherPage;
import org.andersen.lab.pages.ViewsPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.DriverManager;

public class TextSwitcherPageTest {
	private AppiumDriver driver;
	private ApiDemosMainPage apiDemosMainPage;
	private ViewsPage viewsPage;
	private TextSwitcherPage textSwitcherPage;

	@BeforeClass
	public void setUp() {
		driver = DriverManager.getDriver();
		apiDemosMainPage = new ApiDemosMainPage(driver);
	}

	@Test
	public void testTextSwitcherFunctionality() {

		apiDemosMainPage.openViews();

		viewsPage = new ViewsPage(driver);
		viewsPage.scrollUntilTextSwitcher();

		textSwitcherPage = new TextSwitcherPage(driver);

		Assert.assertEquals(textSwitcherPage.getCurrentCounterValue(), 0,
				"Initial counter value should be 0");

		int clicksCount = 5;
		textSwitcherPage.clickNextButton(clicksCount);

		Assert.assertEquals(textSwitcherPage.getCurrentCounterValue(), clicksCount,
				String.format("Counter should show %d after %d clicks", clicksCount, clicksCount));
	}

	@AfterClass
	public void tearDown() {
		DriverManager.quitDriver();
	}
}
