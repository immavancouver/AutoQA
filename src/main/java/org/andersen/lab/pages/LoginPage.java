package org.andersen.lab.pages;

import io.qameta.allure.Step;
import org.andersen.lab.utils.links.Links;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "email")
	private WebElement email;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(css = "button[type='submit']")
	private WebElement signInButton;

	@FindBy(xpath = "//span[text()='Email or password is not valid']")
	private WebElement invalidEmailOrPasswordErrorMessage;

	@FindBy(xpath = "//span[text()='Required']")
	private WebElement requiredFieldErrorMessage;

	@Step("Opening Login Page")
	public LoginPage openLoginPage() {
		logger.info("Navigating to login page: " + Links.ANDERSEN_LOGIN.getLink());
		driver.get(Links.ANDERSEN_LOGIN.getLink());
		return this;
	}

	@Step("Setting Email address")
	public LoginPage setEmail(String value) {
		logger.debug("Entering email: " + value);
		sendKeys(email, value);
		return this;
	}

	public void sendKeys(WebElement locator, String text) {
		wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
	}

	@Step("Clicking on Sign In button")
	public LoginPage clickOnSignInButton() {
		logger.info("Clicking on the Sign In button");
		wait.until(ExpectedConditions.visibilityOf(signInButton)).click();
		return this;
	}

	@Step("Setting user's password")
	public LoginPage setPassword(String value) {
		sendKeys(password, value);
		return this;
	}

	@Step("Checking for Error Message")
	public LoginPage checkInvalidEmailOrPasswordErrorMessage() {
		String expectedAlertMessage = "Email or password is not valid";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(invalidEmailOrPasswordErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	@Step("Checking for an Error Message")
	public LoginPage checkRequiredFieldErrorMessage() {
		String expectedAlertMessage = "Required";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(requiredFieldErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}
}