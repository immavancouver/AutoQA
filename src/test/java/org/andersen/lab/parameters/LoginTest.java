package org.andersen.lab.parameters;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

	@Test
	@Parameters({"email", "password"})
	public void testLogin(String email, String password) {

		boolean isLoggedIn = simulateLogin(email, password);
		Assert.assertTrue(isLoggedIn, "Login failed for user: " + email);
	}

	private boolean simulateLogin(String email, String password) {

		System.out.println("Logging in with email: " + email + " and password: " + password);
		return true;
	}
}
