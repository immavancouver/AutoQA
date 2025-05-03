package org.andersen.lab.scenarios.scenario_1;

import org.andersen.lab.base.BaseTest;
import org.andersen.lab.locators.SelectPageLocators;
import org.andersen.lab.pages.AQAPracticeSelectPage;
import org.andersen.lab.pages.LoginPage;
import org.andersen.lab.pages.SelectPage;
import org.andersen.lab.utils.links.Links;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SelectTest extends BaseTest {

	@Test
	public void testSelectFormScenario() {
		// Step 1: Login
		driver.get(Links.ANDERSEN_LOGIN.getLink());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail("registrarfocean@gmail.com");
		loginPage.enterPassword("bpNANETAq5xvPrG");
		loginPage.clickSignInButton();

		AQAPracticeSelectPage aqaPracticeSelectPagePage = new AQAPracticeSelectPage(driver);
		aqaPracticeSelectPagePage.openSelectPage();

		SelectPage selectPage = new SelectPage(driver);
		selectPage.selectCountry(SelectPageLocators.DropDownOptions.COUNTRY_USA.getValue());
		selectPage.selectLanguage(SelectPageLocators.DropDownOptions.LANGUAGE_ENGLISH.getValue());
		selectPage.selectType(SelectPageLocators.DropDownOptions.TYPE_TESTING.getValue());
		selectPage.fillStartDate();
		selectPage.fillEndDate();
		selectPage.selectCourses(List.of(
				SelectPageLocators.CourseOptions.AQA_JAVA.getValue(),
				SelectPageLocators.CourseOptions.AQA_PYTHON.getValue()
		));

		selectPage.clickSearchButton();

		String expectedMessage = "Unfortunately, we did not find any courses matching your chosen criteria.";
		Assert.assertEquals(selectPage.getErrorMessage(), expectedMessage, "Error message mismatch!");
	}
}