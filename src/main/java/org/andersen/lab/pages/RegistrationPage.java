package org.andersen.lab.pages;

import io.qameta.allure.Step;
import org.andersen.lab.utils.links.Links;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationPage {

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	private static WebDriver driver;
	private static WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	@FindBy(name = "email")
	private WebElement email;

	@FindBy(name = "firstName")
	private WebElement firstName;

	@FindBy(name = "lastName")
	private WebElement lastName;

	@FindBy(name = "dateOfBirth")
	private WebElement dateOfBirth;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(name = "passwordConfirmation")
	private WebElement passwordConfirmation;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//span[text()='Required']")
	private WebElement requiredFieldErrorMessage;

	@FindBy(xpath = "//span[text()='Invalid email address']")
	private WebElement invalidEmailErrorMessage;

	@FindBy(xpath = "//span[text()='Passwords must match']")
	private WebElement mismatchedPasswordsErrorMessage;

	@FindBy(xpath = "//span[text()='This email address is  already in use']")
	private WebElement registeredEmailErrorMessage;

	@Step("Opening Registration Page")
	public RegistrationPage openRegistrationPage() {
		logger.info("Navigating to registration page: " + Links.ANDERSEN_REGISTRATION.getLink());
		driver.get(Links.ANDERSEN_REGISTRATION.getLink());
		return this;
	}

	@Step("Setting user's password")
	public RegistrationPage setPassword(String value) {
		logger.debug("Entering password: " + value);
		sendKeys(password, value);
		return this;
	}

	@Step("Setting user's email address")
	public RegistrationPage setEmail(String value) {
		logger.debug("Entering email: " + value);
		sendKeys(email, value);
		return this;
	}

	@Step("Setting user's confirmation password")
	public RegistrationPage setConfirmationPassword(String value) {
		logger.debug("Entering confirmation password: " + value);
		sendKeys(passwordConfirmation, value);
		return this;
	}

	@Step("Setting user's first name")
	public RegistrationPage setFirstName(String value) {
		logger.debug("Entering user's first name: " + value);
		sendKeys(firstName, value);
		return this;
	}

	@Step("Setting user's last name")
	public RegistrationPage setLastName(String value) {
		logger.debug("Entering user's last name: " + value);
		sendKeys(lastName, value);
		return this;
	}

	@Step("Setting user's date of birth")
	public RegistrationPage setDateOfBirth(String value) {
		logger.debug("Entering user's date of birth: " + value);
		sendKeys(dateOfBirth, value);
		return this;
	}

	@Step("Clicking on submit button")
	public RegistrationPage clickOnSubmitButton() {
		logger.info("Clicking on submit button");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", submitButton);
		return this;
	}

	@Step("Checking for an Error Message")
	public RegistrationPage checkRequiredFieldErrorMessage() {
		String expectedAlertMessage = "Required";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(requiredFieldErrorMessage)).getText();
		logger.info("Validating 'Required field' message: expected '{}', actual '{}'", expectedAlertMessage, actualAlertMessage);
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	@Step("Checking for an Error Message")
	public RegistrationPage checkInvalidEmailErrorMessage() {
		String expectedAlertMessage = "Invalid email address";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(invalidEmailErrorMessage)).getText();
		logger.info("Validating 'Invalid email format' message: expected '{}', actual '{}'", expectedAlertMessage, actualAlertMessage);
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	@Step("Checking for an Error Message")
	public RegistrationPage checkMismatchedPasswordsErrorMessage() {
		String expectedAlertMessage = "Passwords must match";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(mismatchedPasswordsErrorMessage)).getText();
		logger.info("Validating 'Mismatched passwords' message: expected '{}', actual '{}'", expectedAlertMessage, actualAlertMessage);
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	@Step("Checking for an Error Message")
	public RegistrationPage checkRegisteredEmailErrorMessage() {
		String expectedAlertMessage = "This email address is already in use";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(registeredEmailErrorMessage)).getText();
		logger.info("Validating 'Existing email address' message: expected '{}', actual '{}'", expectedAlertMessage, actualAlertMessage);
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	public void sendKeys(WebElement locator, String text) {
		wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
	}
}
