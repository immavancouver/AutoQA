package org.andersen.lab.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.andersen.lab.constants.PageElements.DATE_OF_BIRTH;
import static org.andersen.lab.constants.PageElements.EMAIL;
import static org.andersen.lab.constants.PageElements.FIRST_NAME;
import static org.andersen.lab.constants.PageElements.LAST_NAME;
import static org.andersen.lab.constants.PageElements.PASSWORD;
import static org.andersen.lab.constants.PageElements.PASSWORD_CONFIRMATION;
import static org.andersen.lab.constants.PageElements.REQUIRED_FIELD_ERROR_MESSAGE;
import static org.andersen.lab.constants.PageElements.SIGN_IN_HEADER;
import static org.andersen.lab.constants.PageElements.SUBMIT_BUTTON;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RegistrationPage {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void enterFirstName(String firstName) {
		WebElement firstNameField = wait.until(presenceOfElementLocated(FIRST_NAME));

		firstNameField.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		WebElement lastNameField = wait.until(presenceOfElementLocated(LAST_NAME));

		lastNameField.sendKeys(lastName);
	}

	public void enterDateOfBirth(String dateOfBirth) {
		WebElement dateOfBirthField = wait.until(presenceOfElementLocated(DATE_OF_BIRTH));

		dateOfBirthField.sendKeys(dateOfBirth);
	}

	public void enterEmail(String email) {
		WebElement emailField = wait.until(presenceOfElementLocated(EMAIL));

		emailField.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement passwordField = wait.until(presenceOfElementLocated(PASSWORD));

		passwordField.sendKeys(password);
	}

	public void enterPasswordConfirmation(String passwordConfirmation) {
		WebElement passwordConfirmationField = wait.until(presenceOfElementLocated(PASSWORD_CONFIRMATION));

		passwordConfirmationField.sendKeys(passwordConfirmation);
	}

	public void clickSubmitButton() {
		WebElement button = wait.until(elementToBeClickable(SUBMIT_BUTTON));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
	}

	public boolean isRedirectedToLoginPage() {
		String signInHeaderText = wait.until(visibilityOfElementLocated(SIGN_IN_HEADER))
				.getText();

		return "Sign In".equals(signInHeaderText);
	}

	public boolean hasMissingRequiredField() {
		String requiredFieldErrorMessageText = wait.until(visibilityOfElementLocated(REQUIRED_FIELD_ERROR_MESSAGE))
				.getText();

		return "Required".equals(requiredFieldErrorMessageText);
	}

}
