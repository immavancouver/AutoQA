package org.andersen.lab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Method to enter email
	public void enterEmail(String email) {
		WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		emailField.sendKeys(email);
	}

	// Method to enter password
	public void enterPassword(String password) {
		WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
		passwordField.sendKeys(password);
	}

	// Method to click the sign-in button
	public void clickSignInButton() {
		WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
		signInButton.click();
	}

	// Method to verify successful login
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