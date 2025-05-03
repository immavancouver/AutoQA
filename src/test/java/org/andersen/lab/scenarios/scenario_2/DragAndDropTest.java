package org.andersen.lab.scenarios.scenario_2;

import org.andersen.lab.base.BaseTest;
import org.andersen.lab.pages.AQAPracticeSelectPage;
import org.andersen.lab.pages.DragAndDropPage;
import org.andersen.lab.pages.LoginPage;
import org.andersen.lab.utils.links.Links;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends BaseTest {

	@Test
	public void testDragAndDropScenario() {

		driver.get(Links.ANDERSEN_LOGIN.getLink());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail("registrarfocean@gmail.com");
		loginPage.enterPassword("bpNANETAq5xvPrG");
		loginPage.clickSignInButton();

		AQAPracticeSelectPage aqaPracticeSelectPage = new AQAPracticeSelectPage(driver);
		aqaPracticeSelectPage.openDragAndDropPage();

		DragAndDropPage dragAndDropPage = new DragAndDropPage(driver);
		dragAndDropPage.performDragAndDrop();

		String expectedMessage = "Congratulations! Let's test for the best!";
		Assert.assertEquals(dragAndDropPage.getSuccessMessage(), expectedMessage, "Success message mismatch!");

		dragAndDropPage.clickFinishButton();
	}
}