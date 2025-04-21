package org.andersen.lab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void enterFirstName(String firstName) {
		WebElement firstNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
		firstNameField.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		WebElement lastNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
		lastNameField.sendKeys(lastName);
	}

	public void enterDateOfBirth(String dateOfBirth) {
		WebElement dobField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("dateOfBirth")));
		dobField.sendKeys(dateOfBirth);
	}

	public void enterEmail(String email) {
		WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		emailField.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
		passwordField.sendKeys(password);
	}

	public void confirmPassword(String confirmPassword) {
		WebElement confirmPasswordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("passwordConfirmation")));
		confirmPasswordField.sendKeys(confirmPassword);
	}

	public void clickSubmitButton() {
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
		submitButton.click();
	}

	public boolean isProfilePageLoaded() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			return wait.until(ExpectedConditions.urlContains(".com"));
		} catch (Exception e) {
			System.out.println("Failed to load profile page. Current URL: " + driver.getCurrentUrl());
			return false;
		}
	}
}