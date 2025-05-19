package org.andersen.lab.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.function.Predicate.not;
import static org.andersen.lab.constants.PageElements.DATE_WIDGETS_OPTIONS;
import static org.andersen.lab.constants.PageElements.TEXT_SWITCHER;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;

public class ViewsPage {

	private final AppiumDriver driver;
	private final WebDriverWait wait;

	public ViewsPage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public DateWidgetsPage openDateWidgets() {
		wait.until(elementToBeClickable(DATE_WIDGETS_OPTIONS)).click();

		return new DateWidgetsPage(driver);
	}

	public TextSwitcherPage openTextSwitcherPage() {
		WebElement element = driver.findElement(TEXT_SWITCHER);

		element.click();

		return new TextSwitcherPage(driver);
	}

	public int getClickableButtonsCount() {
		Set<String> uniqueButtons = new LinkedHashSet<>();

		boolean reachedEnd = false;
		while (!reachedEnd) {
			int beforeCount = uniqueButtons.size();

			List<String> buttons = wait.until(presenceOfAllElementsLocatedBy(
							xpath("//android.widget.TextView[@clickable='true']")))
					.stream()
					.map(WebElement::getText)
					.filter(Objects::nonNull)
					.filter(not(String::isEmpty))
					.toList();

			uniqueButtons.addAll(buttons);

			if (uniqueButtons.size() > beforeCount) {
				scrollDown();

				try {
					TimeUnit.SECONDS.sleep(10L);
				} catch (InterruptedException exc) {
					Thread.currentThread().interrupt();
				}
			} else {
				reachedEnd = true;
			}
		}

		return uniqueButtons.size();
	}

	private void scrollDown() {
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

		driver.perform(List.of(scroll));
	}

}