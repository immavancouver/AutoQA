package org.andersen.lab.pages;

import org.andersen.lab.locators.AQASelectPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AQAPracticeSelectPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public AQAPracticeSelectPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
	}


	public void hoverOverAQAPractice() {
		WebElement aqaPracticeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
				AQASelectPageLocators.AQA_PRACTICE_DROPDOWN
		));
		Actions actions = new Actions(driver);
		actions.moveToElement(aqaPracticeElement).perform();
	}

	public void openSelectPage() {
		hoverOverAQAPractice();
		WebElement selectOption = wait.until(ExpectedConditions.elementToBeClickable(
				AQASelectPageLocators.SELECT_SUBMENU_ITEM
		));
		selectOption.click();
	}

	public void openActionsAlertsIframesPage() {
		hoverOverAQAPractice();
		WebElement selectOption = wait.until(ExpectedConditions.elementToBeClickable(
				AQASelectPageLocators.ACTIONS_ALERTS_IFRAMES_SUBMENU_ITEM
		));
		selectOption.click();
	}


	public void openDragAndDropPage() {
		hoverOverAQAPractice();
		WebElement selectOption = wait.until(ExpectedConditions.elementToBeClickable(
				AQASelectPageLocators.DRAG_AND_DROP_SUBMENU_ITEM
		));
		selectOption.click();
	}
}
