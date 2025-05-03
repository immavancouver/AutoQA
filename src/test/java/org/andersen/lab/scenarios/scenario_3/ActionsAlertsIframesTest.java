package org.andersen.lab.scenarios.scenario_3;

import org.andersen.lab.base.BaseTest;
import org.andersen.lab.pages.AQAPracticeSelectPage;
import org.andersen.lab.pages.ActionsAlertsIframesPage;
import org.andersen.lab.pages.LoginPage;
import org.andersen.lab.utils.links.Links;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class ActionsAlertsIframesTest extends BaseTest {

	@Test
	public void testActionsAlertsIframesScenario() {

		driver.get(Links.ANDERSEN_LOGIN.getLink());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail("registrarfocean@gmail.com");
		loginPage.enterPassword("bpNANETAq5xvPrG");
		loginPage.clickSignInButton();

		AQAPracticeSelectPage aqaPracticeSelectPage = new AQAPracticeSelectPage(driver);
		aqaPracticeSelectPage.openActionsAlertsIframesPage();

		ActionsAlertsIframesPage actionsPage = new ActionsAlertsIframesPage(driver);

		actionsPage.clickSingleButton();
		Assertions.assertEquals(
				"Congratulations, you have successfully enrolled in the course!",
				actionsPage.getResultMessage()
		);

		actionsPage.doubleClickButton();
		Assertions.assertEquals(
				"You received a 10% discount on the second course.",
				actionsPage.getResultMessage()
		);

		actionsPage.rightClickButtonAndEnterText("Test");
		Assertions.assertTrue(
				actionsPage.getResultMessage().contains("Test"),
				"The result message should contain the input text 'Test'"
		);

	}
}
