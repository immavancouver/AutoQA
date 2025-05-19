package org.andersen.lab.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.andersen.lab.constants.PageElements.DATE_WIDGETS_OPTIONS;
import static org.andersen.lab.constants.PageElements.VIEWS_OPTION;

public class ViewsPage {

	private final AppiumDriver driver;
	private final WebDriverWait wait;

	public ViewsPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void openDateWidgets() {
		wait.until(ExpectedConditions
				.elementToBeClickable(DATE_WIDGETS_OPTIONS)).click();
	}

	public void scrollUntilTextSwitcher(){
		WebElement element =  driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true))" +
						".scrollIntoView(new UiSelector().text(\"TextSwitcher\"))"));

		element.click();
	}

	public int getViewsItemCountWithScroll() {
		Set<String> titles = new HashSet<>();
		int previousSize = 0;
		int attempts = 0;
		final int MAX_ATTEMPTS = 15;

		do {
			List<WebElement> items = wait.until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(VIEWS_OPTION)
			);

			for (WebElement item : items) {
				String text = item.getText();
				if (!text.isEmpty()) {
					titles.add(text);
					System.out.println("Found: " + text);
				}
			}

			if (titles.size() == previousSize) {
				attempts++;
			} else {
				attempts = 0;
			}
			previousSize = titles.size();

			if (attempts < MAX_ATTEMPTS && titles.size() < 42) {
				scrollDownW3C();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

		} while (attempts < MAX_ATTEMPTS && titles.size() < 42);

		System.out.println("Total Views items found: " + titles.size());
		return titles.size();
	}

	private void scrollDownW3C() {
		Dimension size = driver.manage().window().getSize();
		Point start = new Point(size.width / 2, (int) (size.height * 0.8));
		Point end = new Point(size.width / 2, (int) (size.height * 0.2));

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence scroll = new Sequence(finger, 0)
				.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(finger.createPointerMove(Duration.ofMillis(500),
						                            PointerInput.Origin.viewport(), end.x, end.y))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(scroll));
	}
}