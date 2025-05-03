
package org.andersen.lab.base;


import org.andersen.lab.utils.driver.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.time.Duration;

public class BaseTest {
	protected WebDriver driver;
	protected WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		driver = DriverSetUp.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}