package org.andersen.lab.test_cases;

import org.andersen.lab.pages.RegistrationPage;
import org.andersen.lab.utils.driver.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationPageTest {
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static RegistrationPage registrationPage;

	@BeforeClass
	public void setUp() {
		driver = DriverSetUp.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		registrationPage = new RegistrationPage(driver);
	}

	@AfterClass
	public void close() {
		driver.quit();
	}

	@Test
	public void registrationWithValidInputs() {
		registrationPage
				.openRegistrationPage()
				.setFirstName("John")
				.setLastName("Kendrick")
				.setDateOfBirth("1990-01-01")
				.setEmail("registrarrrfocean@gmail.com")
				.setPassword("bpNANETAq5xvPrG")
				.setConfirmationPassword("bpNANETAq5xvPrG")
				.clickOnSubmitButton();
	}

	@Test
	public void registrationWithMissingFields() {
		registrationPage
				.openRegistrationPage()
				.setFirstName("")
				.setEmail("registrarffocean@gmail.com")
				.setPassword("bpNANETAq5xvPrG")
				.setConfirmationPassword("bpNANETAq5xvPrG")
				.checkRequiredFieldErrorMessage();
	}

	@Test
	public void registrationWithInvalidEmail() {
		registrationPage
				.openRegistrationPage()
				.setFirstName("John")
				.setLastName("Kendrick")
				.setDateOfBirth("1990-01-01")
				.setEmail("registrarrrfoceangmail.com")
				.setPassword("bpNANETAq5xvPrG")
				.setConfirmationPassword("bpNANETAq5xvPrG")
				.checkInvalidEmailErrorMessage();
	}

	@Test
	public void registrationWithMismatchedPasswords() {
		registrationPage
				.openRegistrationPage()
				.setFirstName("John")
				.setLastName("Kendrick")
				.setDateOfBirth("1990-01-01")
				.setPassword("bpNANETAq5xvPrG")
				.setConfirmationPassword("pNANETAq5xvPrG")
				.setEmail("registrarrrfo@ceangmail.com")
				.checkMismatchedPasswordsErrorMessage();
	}

	@Test
	public void registrationWithRegisteredEmail() {
		registrationPage
				.openRegistrationPage()
				.setFirstName("John")
				.setLastName("Kendrick")
				.setDateOfBirth("1990-01-01")
				.setEmail("registrarfocean@gmail.com")
				.setPassword("bpNANETAq5xvPrG")
				.setConfirmationPassword("bpNANETAq5xvPrG")
				.clickOnSubmitButton()
				.checkRegisteredEmailErrorMessage();
	}
}
