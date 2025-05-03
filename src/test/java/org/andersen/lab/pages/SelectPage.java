package org.andersen.lab.pages;

import org.andersen.lab.locators.AQASelectPageLocators;
import org.andersen.lab.locators.SelectPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class SelectPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public SelectPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
	}

	public void selectCountry(String option) {
		selectFromDropdown(SelectPageLocators.COUNTRY_DROPDOWN, option);
	}

	public void selectLanguage(String option) {
		selectFromDropdown(SelectPageLocators.LANGUAGE_DROPDOWN, option);
	}

	public void selectType(String option) {
		selectFromDropdown(SelectPageLocators.TYPE_DROPDOWN, option);
	}

	// Fill start and end dates dynamically
	public String calculateNextMonday() {
		LocalDate nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		return formatDate(nextMonday);
	}

	public String calculateTwoWeeksLater() {
		LocalDate twoWeeksLater = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).plusWeeks(2);
		return formatDate(twoWeeksLater);
	}

	private String formatDate(LocalDate date) {
		return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	}

	public void fillStartDate() {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(SelectPageLocators.START_DATE_FIELD));
		field.clear();
		field.sendKeys(calculateNextMonday());
	}

	public void fillEndDate() {
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(SelectPageLocators.END_DATE_FIELD));
		field.clear();
		field.sendKeys(calculateTwoWeeksLater());
	}

	public void selectCourses(List<String> courseNames) {
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(SelectPageLocators.MULTIPLE_COURSES_SELECT));
		Select select = new Select(dropdown);

		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).perform();

		try {
			for (String course : courseNames) {
				WebElement courseOption = dropdown.findElement(By.xpath(".//option[text()='" + course + "']"));
				courseOption.click();
			}
		} finally {
			actions.keyUp(Keys.CONTROL).perform();
		}
	}

	// Submit form
	public void clickSearchButton() {
		wait.until(ExpectedConditions.elementToBeClickable(SelectPageLocators.SEARCH_BUTTON)).click();
	}

	// Get error message text
	public String getErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(SelectPageLocators.ERROR_MESSAGE)).getText();
	}

	// Helper method to select from dropdown
	private void selectFromDropdown(By locator, String option) {
		Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
		select.selectByVisibleText(option);
	}
}