package org.andersen.lab.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverManager;

import java.time.Duration;


public class ApiDemosMainPage {

	AppiumDriver driver;
	WebDriverWait wait;

	public ApiDemosMainPage(AppiumDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private static final By views = AppiumBy.accessibilityId("Views");

	public void openViews() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(views)).click();
	}
}
