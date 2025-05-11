package org.andersen.lab.pages;

import org.andersen.lab.utils.links.Links;
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

	public LoginPage openLoginPage() {
		driver.get(Links.ANDERSEN_LOGIN.getLink());
		return this;
	}

	public LoginPage setEmail(String value) {
		sendKeys(email, value);
		return this;
	}

	public void sendKeys(WebElement locator, String text) {
		wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
	}

	public LoginPage clickOnSignInButton() {
		wait.until(ExpectedConditions.visibilityOf(signInButton)).click();
		return this;
	}

	public LoginPage setPassword(String value) {
		sendKeys(password, value);
		return this;
	}

	public LoginPage checkInvalidEmailOrPasswordErrorMessage() {
		String expectedAlertMessage = "Email or password is not valid";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(invalidEmailOrPasswordErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}

	public LoginPage checkRequiredFieldErrorMessage() {
		String expectedAlertMessage = "Required";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOf(requiredFieldErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
		return this;
	}
}