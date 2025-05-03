package org.andersen.lab.pages;

import org.andersen.lab.locators.DragAndDropLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragAndDropPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public DragAndDropPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
	}

	public void performDragAndDrop() {

		WebElement writeTestCases = wait.until(ExpectedConditions.presenceOfElementLocated(DragAndDropLocators.WRITE_CASES));
		WebElement testingRequirements = wait.until(ExpectedConditions.presenceOfElementLocated(DragAndDropLocators.TESTING_REQUIREMENTS));
		WebElement writeAutomationScripts = wait.until(ExpectedConditions.presenceOfElementLocated(DragAndDropLocators.WRITE_AUTOMATION_SCRIPTS));
		WebElement frameworkSetUp = wait.until(ExpectedConditions.presenceOfElementLocated(DragAndDropLocators.FRAMEWORK_SETUP));

		WebElement manualWorkCell1 = wait.until(ExpectedConditions.presenceOfElementLocated(DragAndDropLocators.MANUAL_WORK_CELL_1));
		WebElement manualWorkCell2 = wait.until(ExpectedConditions.presenceOfElementLocated(DragAndDropLocators.MANUAL_WORK_CELL_2));
		WebElement automationWorkCell1 = wait.until(ExpectedConditions.presenceOfElementLocated(DragAndDropLocators.AUTOMATION_WORK_CELL_1));
		WebElement automationWorkCell2 = wait.until(ExpectedConditions.presenceOfElementLocated(DragAndDropLocators.AUTOMATION_WORK_CELL_2));

		Actions actions = new Actions(driver);

		actions.dragAndDrop(writeTestCases, manualWorkCell1).perform();
		actions.dragAndDrop(testingRequirements, manualWorkCell2).perform();
		actions.dragAndDrop(writeAutomationScripts, automationWorkCell1).perform();
		actions.dragAndDrop(frameworkSetUp, automationWorkCell2).perform();
	}

	public void clickFinishButton() {
		WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(DragAndDropLocators.FINISH_BUTTON));
		finishButton.click();
	}

	public String getSuccessMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(DragAndDropLocators.SUCCESS_MESSAGE)).getText();
	}
}