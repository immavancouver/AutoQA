package org.andersen.lab.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.andersen.lab.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.andersen.lab.constants.Links.ANDERSEN_LOGIN;
import static org.testng.Assert.assertTrue;

public class LoginSteps {

	private WebDriver driver;

	private LoginPage loginPage;

	@Before
	public void init() {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		loginPage = new LoginPage(driver);
	}

	@After
	public void cleanup() {
		driver.quit();
	}

	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		driver.get(ANDERSEN_LOGIN);
	}

	@When("the user enters login email {string}")
	public void the_user_enters_email(String string) {
		loginPage.enterEmail(string);
	}

	@And("the user enters login password {string}")
	public void the_user_enters_password(String string) {
		loginPage.enterPassword(string);
	}

	@And("the user clicks the Sign In button")
	public void the_user_clicks_the_sign_in_button() {
		loginPage.clickSignInButton();
	}

	@Then("the user should be successfully signed in")
	public void the_user_should_be_successfully_signed_in() {
		boolean hasSignOutButton = loginPage.hasSignOutButton();

		assertTrue(hasSignOutButton, "User successful sign in expected");
	}

	@Then("the user should see an 'Invalid email or password' error message")
	public void the_user_should_see_an_error_message() {
		boolean hasInvalidEmailOrPasswordMessage = loginPage.hasInvalidEmailOrPasswordMessage();

		assertTrue(hasInvalidEmailOrPasswordMessage, "User sign in error expected");
	}

}
