package org.andersen.lab.test_cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.andersen.lab.pages.RegistrationPage;
import org.andersen.lab.utils.driver.DriverSetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger logger = LogManager.getLogger(LoginPageTest.class);

	@BeforeClass
	public void setUp() {
		logger.info("Initializing WebDriver and RegistrationPage object...");
		driver = DriverSetUp.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		registrationPage = new RegistrationPage(driver);
	}

	@AfterClass
	public void close() {
		logger.info("Closing the driver...");
		DriverSetUp.quitDriver();
	}

	@Description("Checking user registration with valid credentials")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 2")
	@Story("US 2.1")
	@Test
	public void registrationWithValidInputs() {
		logger.info("Test: registerWithValidInputs - Registering with valid credentials");
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


	@Description("Checking user registration with missing fields")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 2")
	@Story("US 2.2")
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

	@Description("Checking user registration with invalid email")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 2")
	@Story("US 2.3")
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

	@Description("Checking user registration with mismatched passwords")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 2")
	@Story("US 2.4")
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

	@Description("Checking user registration with registered email address")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 2")
	@Story("US 2.5")
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
