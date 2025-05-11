package org.andersen.lab.pages;

import org.andersen.lab.utils.links.Links;
import org.openqa.selenium.By;
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

	public RegistrationPage openRegistrationPage() {
		driver.get(Links.ANDERSEN_REGISTRATION.getLink());
		return this;
	}

	public RegistrationPage setPassword(String value) {
		sendKeys(password, value);
		return this;
	}

	public RegistrationPage setEmail(String value) {
		sendKeys(email, value);
		return this;
	}

	public RegistrationPage setConfirmationPassword(String value) {
		sendKeys(passwordConfirmation, value);
		return this;
	}

	public RegistrationPage setFirstName(String value) {
		sendKeys(firstName, value);
		return this;
	}

	public RegistrationPage setLastName(String value) {
		sendKeys(lastName, value);
		return this;
	}

	public RegistrationPage setDateOfBirth(String value) {
		sendKeys(dateOfBirth, value);
		return this;
	}

	public RegistrationPage clickOnSubmitButton() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", submitButton);
		return this;
	}

	public RegistrationPage checkRequiredFieldErrorMessage() {
		String expectedAlertMessage = "Required";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(requiredFieldErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	public RegistrationPage checkInvalidEmailErrorMessage() {
		String expectedAlertMessage = "Invalid email address";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(invalidEmailErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	public RegistrationPage checkMismatchedPasswordsErrorMessage() {
		String expectedAlertMessage = "Passwords must match";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(mismatchedPasswordsErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	public RegistrationPage checkRegisteredEmailErrorMessage() {
		String expectedAlertMessage = "This email address is already in use";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(registeredEmailErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	public void sendKeys(WebElement locator, String text) {
		wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
	}


}
