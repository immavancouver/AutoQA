package org.andersen.lab.dataprovider;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

	
	private static final String[][] USERS = {
			{"email1@example.com", "password1"},
			{"email2@example.com", "password2"},
			{"email3@example.com", "password3"}
	};

	@DataProvider(name = "loginData")
	public Object[][] loginData() {
		return USERS;
	}

	@Test(dataProvider = "loginData")
	public void testLogin(String email, String password) {

		boolean isLoggedIn = simulateLogin(email, password);
		Assert.assertTrue(isLoggedIn, "Login failed for user: " + email);
	}


	private boolean simulateLogin(String email, String password) {

		System.out.println("Logging in with email: " + email + " and password: " + password);
		return true;
	}
}

