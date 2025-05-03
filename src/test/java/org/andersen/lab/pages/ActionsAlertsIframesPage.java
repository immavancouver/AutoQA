package org.andersen.lab.pages;

import org.andersen.lab.locators.ActionsAlertsIframesLocators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsAlertsIframesPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;

	public ActionsAlertsIframesPage(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		this.actions = new Actions(driver);
	}

	public void switchToFrame() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
				ActionsAlertsIframesLocators.SINGLE_IFRAME_LOCATOR));
	}
	public void clickSingleButton() {
		switchToFrame();
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ActionsAlertsIframesLocators.SINGLE_CLICK_BUTTON));
		button.click();
		handleAlertWithText("You have called alert!");
	}

	public void doubleClickButton() {
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ActionsAlertsIframesLocators.DOUBLE_CLICK_BUTTON));
		actions.doubleClick(button).perform();
		handleAlertWithText("Are you sure you want to apply the discount?");
	}

	public void rightClickButtonAndEnterText(String input) {
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ActionsAlertsIframesLocators.RIGHT_CLICK_BUTTON));
		actions.contextClick(button).perform();
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String alertText = alert.getText();
		if (!alertText.contains("Here you may describe a reason")) {
			throw new AssertionError("Unexpected alert text: " + alertText);
		}
		alert.sendKeys(input);
		alert.accept();
	}

	private void handleAlertWithText(String expectedText) {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String alertText = alert.getText();
		if (!alertText.equals(expectedText)) {
			throw new AssertionError("Expected alert text: '" + expectedText + "', but got: '" + alertText + "'");
		}
		alert.accept();
	}

	public String getResultMessage() {
		return driver.findElement(ActionsAlertsIframesLocators.RESULT_MESSAGE).getText();
	}
}
