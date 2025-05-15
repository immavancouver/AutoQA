package org.andersen.lab.step_definition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.andersen.lab.utils.driver.BaseSteps;
import org.andersen.lab.utils.links.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps extends BaseSteps {

	By email = By.name("email");
	By password = By.name("password");
	By signInButton = By.cssSelector("button[type='submit']");
	By signOutButton = By.xpath("//div[@class='my-auto' and text()='Sign Out']");
	By invalidEmailOrPasswordErrorMessage = By.xpath("//span[text()='Email or password is not valid']");

	public void sendKeys(By locator, String text){
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
	}

	public void checkUserSignIn() {
		String expectedAlertMessage = "Sign Out";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(signOutButton)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
	}

	public void checkInvalidEmailOrPasswordErrorMessage() {
		String expectedAlertMessage = "Email or password is not valid";
		String actualAlertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(invalidEmailOrPasswordErrorMessage)).getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "Error message is incorrect...");
	}

	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(Links.ANDERSEN_LOGIN.getLink());
	}
	@When("the user enters email {string}")
	public void the_user_enters_email(String string) {
		sendKeys(email, string);
	}
	@And("the user enters password {string}")
	public void the_user_enters_password(String string) {
		sendKeys(password, string);
	}
	@And("the user clicks the Sign In button")
	public void the_user_clicks_the_sign_in_button() {
		driver.findElement(signInButton).click();
	}
	@Then("the user should be successfully signed in")
	public void the_user_should_be_successfully_signed_in() {
		checkUserSignIn();
		driver.quit();
	}
	@Then("the user should see an {string} error message")
	public void the_user_should_see_an_error_message(String string) {
		checkInvalidEmailOrPasswordErrorMessage();
		driver.quit();
	}

}
