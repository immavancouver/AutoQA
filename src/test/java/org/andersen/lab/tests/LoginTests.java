package org.andersen.lab.tests;

import org.andersen.lab.base.BaseTest;
import org.andersen.lab.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {

	@Test
	public void testSuccessfulLogin() {
		// Navigate to the login page
		driver.get("https://qa-course-01.andersenlab.com/login");

		// Create a LoginPage object
		LoginPage loginPage = new LoginPage(driver);

		// Enter valid credentials
		loginPage.enterEmail("registrarfocean@gmail.com");
		loginPage.enterPassword("bpNANETAq5xvPrG");

		// Click the sign-in button
		loginPage.clickSignInButton();

		// Verify successful login by checking if the profile page is loaded
		Assert.assertTrue(loginPage.isProfilePageLoaded(), "Login failed: Profile page not loaded");

		// Additional verification: Check the URL
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains(".com"), "Redirect to profile page failed");
	}
}