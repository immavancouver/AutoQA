package org.andersen.lab.test_cases;

import io.qameta.allure.*;

import org.andersen.lab.pages.LoginPage;

import org.andersen.lab.utils.driver.DriverSetUp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;


import java.time.Duration;

public class LoginPageTest {
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static LoginPage loginPage;
	private static final Logger logger = LogManager.getLogger(LoginPageTest.class);

	@BeforeClass
	public void setUp() {
		logger.info("Setting up driver and page objects...");
		driver = DriverSetUp.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginPage = new LoginPage(driver);
	}

	@AfterClass
	public void close() {
		logger.info("Closing the driver...");
		DriverSetUp.quitDriver();
	}

	@Description("Checking user sign in with valid credentials")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 1")
	@Story("US 1.1")
	@Test
	public void signInWithValidInputs() {
		logger.info("Test: signInWithValidInputs - Signing in with valid credentials");
		loginPage
				.openLoginPage()
				.setEmail("registrarfocean@gmail.com")
				.setPassword("bpNANETAq5xvPrG")
				.clickOnSignInButton();
	}

	@Description("Checkin user sign in with invalid password")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 1")
	@Story("US 1.2")
	@Test
	public void signInWithInvalidPassword() {
		logger.info("Test: signInWithInvalidPassword - Trying to login with an incorrect password");
		loginPage
				.openLoginPage()
				.setEmail("registrarfocean@gmail.com")
				.setPassword("bpN!!ANETAq5xvPr")
				.clickOnSignInButton()
				.checkInvalidEmailOrPasswordErrorMessage();
	}

	@Description("Check user sign in with non-existent email address")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 1")
	@Story("US 1.3")
	@Test
	public void signInWithNonExistingEmail() {
		logger.info("Test: signInWithNonExistingEmail - Trying to login with non-existent email address");
		loginPage
				.openLoginPage()
				.setEmail("registrarean@gmail.com")
				.setPassword("bpNANETAq5xvPrG")
				.clickOnSignInButton()
				.checkInvalidEmailOrPasswordErrorMessage();
	}

	@Description("Checking user sign in with empty fields")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 1")
	@Story("US 1.4")
	@Test
	public void signInWithEmptyFields() {
		logger.info("Test: signInWithEmptyFields - Trying to login with empty fields");
		loginPage
				.openLoginPage()
				.setEmail("")
				.setPassword("")
				.clickOnSignInButton()
				.checkRequiredFieldErrorMessage();
	}

	@Description("Checking user sign in with case-sensitive email")
	@Severity(SeverityLevel.NORMAL)
	@Epic("Epic 1")
	@Story("US 1.5")
	@Test
	public void signInWithCaseSensitiveEmail() {
		logger.info("Test: signInWithCaseSensitiveEmail - Trying to login with case-sensitive email");
		loginPage
				.openLoginPage()
				.setEmail("Registrarfocean@gmail.com")
				.setPassword("bpNANETAq5xvPr")
				.clickOnSignInButton()
				.checkInvalidEmailOrPasswordErrorMessage();
	}
}
