package org.andersen.lab.constants;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public final class PageElements {

	public static final By FIRST_NAME = By.name("firstName");
	public static final By LAST_NAME = By.name("lastName");
	public static final By DATE_OF_BIRTH = By.name("dateOfBirth");
	public static final By EMAIL = name("email");
	public static final By PASSWORD = name("password");
	public static final By PASSWORD_CONFIRMATION = By.name("passwordConfirmation");

	public static final By SIGN_IN_HEADER = By.xpath("//h1[@class='text-2xl' and text()='Sign In']");

	public static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");
	public static final By SIGN_IN_BUTTON = cssSelector("button[type='submit']");
	public static final By SIGN_OUT_BUTTON = xpath("//div[@class='my-auto' and text()='Sign Out']");

	public static final By REQUIRED_FIELD_ERROR_MESSAGE = By.xpath("//span[text()='Required']");
	public static final By INVALID_EMAIL_OR_PASSWORD_ERROR_MESSAGE
			= xpath("//span[text()='Email or password is not valid']");

	private PageElements() {
		throw new UnsupportedOperationException();
	}

}
