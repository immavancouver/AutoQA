package org.andersen.lab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
	private WebDriver driver;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
	}

	public String getUserName() {
		return driver.findElement(By.cssSelector("h1")).getText();
	}

	public boolean isSignOutButtonPresent() {
		return driver.findElement(By.linkText("Sign Out")).isDisplayed();
	}

	public String getUserEmail() {
		return driver.findElement(By.xpath("//span[text()='E-mail']/following-sibling::span")).getText();
	}

	public String getUserPosition() {
		return driver.findElement(By.xpath("//span[text()='Position']/following-sibling::span")).getText();
	}
}