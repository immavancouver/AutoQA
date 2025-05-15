package org.andersen.lab.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.andersen.lab.pages.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.UUID.randomUUID;
import static org.andersen.lab.constants.Links.ANDERSEN_REGISTRATION;
import static org.testng.Assert.assertTrue;

public class RegistrationSteps {

	private WebDriver driver;
	private Scenario scenario;

	private RegistrationPage registrationPage;

	@Before
	public void init(Scenario scenario) {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		registrationPage = new RegistrationPage(driver);

		this.scenario = scenario;
	}

	@After
	public void cleanup() {
		driver.quit();
	}

	@Given("the user is on the registration page")
	public void the_user_is_on_the_registration_page() {
		driver.get(ANDERSEN_REGISTRATION);
	}

	@When("the user enters first name {string}")
	public void the_user_enters_first_name(String string) {
		registrationPage.enterFirstName(string);
	}

	@When("the user enters last name {string}")
	public void the_user_enters_last_name(String string) {
		registrationPage.enterLastName(string);
	}

	@When("the user enters date of birth {string}")
	public void the_user_enters_date_of_birth(String string) {
		registrationPage.enterDateOfBirth(string);
	}

	@When("the user enters registration email {string}")
	public void the_user_enters_email(String string) {
		if ("<random>".equals(string)) {
			string = randomUUID() + "@gmail.com";

			scenario.log("Random email generated: <random> = " + string);
		}

		registrationPage.enterEmail(string);
	}

	@And("the user enters registration password {string}")
	public void the_user_enters_password(String string) {
		registrationPage.enterPassword(string);
	}

	@When("the user confirms the password {string}")
	public void the_user_confirms_the_password(String string) {
		registrationPage.enterPasswordConfirmation(string);
	}

	@When("the user clicks the Submit button")
	public void the_user_clicks_the_submit_button() {
		registrationPage.clickSubmitButton();
	}

	@Then("the user should be successfully registered")
	public void the_user_should_be_successfully_registered() {
		boolean isRedirectedToLoginPage = registrationPage.isRedirectedToLoginPage();

		assertTrue(isRedirectedToLoginPage, "User successful registration expected");
	}

	@Then("the required field error message should be displayed")
	public void the_required_field_error_message_should_be_displayed() {
		boolean hasMissingRequiredField = registrationPage.hasMissingRequiredField();

		assertTrue(hasMissingRequiredField, "User registration failure expected");
	}

}
