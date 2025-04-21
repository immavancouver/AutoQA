package org.andersen.lab.tests;

import org.andersen.lab.base.BaseTest;
import org.andersen.lab.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

	@Test
	public void testValidRegistration() {

		driver.get("https://qa-course-01.andersenlab.com/registration");


		RegistrationPage registrationPage = new RegistrationPage(driver);

		registrationPage.enterFirstName("John");
		registrationPage.enterLastName("Kendrick");
		registrationPage.enterDateOfBirth("1990-01-01");
		registrationPage.enterEmail("registrarfocean@gmail.com");
		registrationPage.enterPassword("bpNANETAq5xvPrG");
		registrationPage.confirmPassword("bpNANETAq5xvPrG");
		registrationPage.clickSubmitButton();

		boolean profilePageLoaded = registrationPage.isProfilePageLoaded();
		Assert.assertEquals(profilePageLoaded, "User registered successfully", "Registration failed");
	}
}