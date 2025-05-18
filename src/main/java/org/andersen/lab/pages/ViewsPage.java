package org.andersen.lab.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
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

public class ViewsPage {
	private final AppiumDriver driver;
	private final WebDriverWait wait;

	public ViewsPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private final By viewOptionsLocator = AppiumBy.xpath(
			"//android.widget.ListView/android.widget.TextView"
	);

	private final By dateWidgetsOption = AppiumBy.accessibilityId("Date Widgets");

	private final By textSwitcherOption = AppiumBy.accessibilityId("TextSwitcher");

	public void openDateWidgets() {
		wait.until(ExpectedConditions.elementToBeClickable(dateWidgetsOption)).click();
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
					ExpectedConditions.presenceOfAllElementsLocatedBy(viewOptionsLocator)
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
					Thread.sleep(1000); // Даем время для загрузки новых элементов
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
				.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), end.x, end.y))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(scroll));
	}
}