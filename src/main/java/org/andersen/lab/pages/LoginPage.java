package org.andersen.lab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.andersen.lab.constants.PageElements.EMAIL;
import static org.andersen.lab.constants.PageElements.INVALID_EMAIL_OR_PASSWORD_ERROR_MESSAGE;
import static org.andersen.lab.constants.PageElements.PASSWORD;
import static org.andersen.lab.constants.PageElements.SIGN_IN_BUTTON;
import static org.andersen.lab.constants.PageElements.SIGN_OUT_BUTTON;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class LoginPage {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void enterEmail(String email) {
		WebElement emailField = wait.until(presenceOfElementLocated(EMAIL));

		emailField.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement passwordField = wait.until(presenceOfElementLocated(PASSWORD));

		passwordField.sendKeys(password);
	}

	public void clickSignInButton() {
		WebElement signInButton = wait.until(elementToBeClickable(SIGN_IN_BUTTON));

		signInButton.click();
	}

	public boolean hasSignOutButton() {
		String signedOutButtonText = wait.until(visibilityOfElementLocated(SIGN_OUT_BUTTON))
				.getText();

		return "Sign Out".equals(signedOutButtonText);
	}

	public boolean hasInvalidEmailOrPasswordMessage() {
		String invalidEmailOrPasswordMessageText =
				wait.until(visibilityOfElementLocated(INVALID_EMAIL_OR_PASSWORD_ERROR_MESSAGE))
						.getText();

		return "Email or password is not valid".equals(invalidEmailOrPasswordMessageText);
	}

}
