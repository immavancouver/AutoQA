package org.andersen.lab.step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.andersen.lab.utils.driver.BaseSteps;
import org.andersen.lab.utils.links.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationSteps extends BaseSteps {

	By firstName = By.name("firstName");
	By lastName = By.name("lastName");
	By dateOfBirth = By.name("dateOfBirth");
	By passwordConfirmation = By.name("passwordConfirmation");

	By submitButton = By.xpath("//button[@type='submit']");
	By signInHeader = By.xpath("//h1[@class='text-2xl' and text()='Sign In']");
	By requiredFieldErrorMessage = By.xpath("//span[text()='Required']");

	public void sendKeys(By locator, String text){
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
	}

	public void checkUserRegistration() {
		String expectedAlertMessage = "Sign In";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(signInHeader)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
	}

	public void checkRequiredFieldErrorMessage() {
		String expectedAlertMessage = "Required";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(requiredFieldErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
	}

	@Given("the user is on the registration page")
	public void the_user_is_on_the_registration_page() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(Links.ANDERSEN_REGISTRATION.getLink());
	}
	@When("the user enters first name {string}")
	public void the_user_enters_first_name(String string) {
		sendKeys(firstName, string);
	}
	@When("the user enters last name {string}")
	public void the_user_enters_last_name(String string) {
		sendKeys(lastName, string);
	}
	@When("the user enters date of birth {string}")
	public void the_user_enters_date_of_birth(String string) {
		sendKeys(dateOfBirth, string);
	}
	@When("the user confirms the password {string}")
	public void the_user_confirms_the_password(String string) {
		sendKeys(passwordConfirmation, string);
	}
	@When("the user clicks the Submit button")
	public void the_user_clicks_the_submit_button() {
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", button);
	}
	@Then("the user should be successfully registered")
	public void the_user_should_be_successfully_registered() {
		checkUserRegistration();
		driver.quit();
	}
	@Then("the required field error message should be displayed")
	public void the_required_field_error_message_should_be_displayed() {
		checkRequiredFieldErrorMessage();
		driver.quit();
	}
}
