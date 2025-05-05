package org.andersen.lab.test_cases;

import org.andersen.lab.pages.LoginPage;
import org.andersen.lab.utils.driver.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest {
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static LoginPage loginPage;

	@BeforeClass
	public void setUp() {
		driver = DriverSetUp.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		loginPage = new LoginPage(driver);
	}

	@AfterClass
	public void close() {
		driver.quit();
	}

	@Test
	public void SignInWithValidInputs() {
		loginPage
				.openLoginPage()
				.setEmail("registrarfocean@gmail.com")
				.setPassword("bpNANETAq5xvPrG")
				.clickOnSignInButton();
	}

	@Test
	public void SignInWithInvalidPassword() {
		loginPage
				.openLoginPage()
				.setEmail("registrarfocean@gmail.com")
				.setPassword("bpN!!ANETAq5xvPr")
				.clickOnSignInButton()
				.checkInvalidEmailOrPasswordErrorMessage();
	}

	@Test
	public void SignInWithNonExistingEmail() {
		loginPage
				.openLoginPage()
				.setEmail("registrarean@gmail.com")
				.setPassword("bpNANETAq5xvPrG")
				.clickOnSignInButton()
				.checkInvalidEmailOrPasswordErrorMessage();
	}

	@Test
	public void SignInWithEmptyFields() {
		loginPage
				.openLoginPage()
				.setEmail("")
				.setPassword("")
				.clickOnSignInButton()
				.checkRequiredFieldErrorMessage();
	}

	@Test
	public void SignInWithCaseSensitiveEmail() {
		loginPage
				.openLoginPage()
				.setEmail("Registrarfocean@gmail.com")
				.setPassword("bpNANETAq5xvPr")
				.clickOnSignInButton()
				.checkInvalidEmailOrPasswordErrorMessage();
	}
}
