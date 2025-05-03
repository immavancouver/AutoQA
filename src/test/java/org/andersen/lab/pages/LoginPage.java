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
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void enterEmail(String email) {
		WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		emailField.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
		passwordField.sendKeys(password);
	}

	public void clickSignInButton() {
		WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
		signInButton.click();
	}
}