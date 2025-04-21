package org.andersen.lab.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Set;

public class MultipleWindowsTests {
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		try {

			driver.get("https://qa-course-01.andersenlab.com/");


			String[] urls = {
					"http://www.automationpractice.pl/index.php",
					"https://zoo.waw.pl/",
					"https://www.w3schools.com/",
					"https://www.clickspeedtester.com/click-counter/",
					"https://andersenlab.com/"
			};


			for (String url : urls) {
				((JavascriptExecutor) driver).executeScript("window.open('" + url + "', '_blank');");
			}


			Set<String> windowHandles = driver.getWindowHandles();
			for (String handle : windowHandles) {
				driver.switchTo().window(handle);
				System.out.println("Title: " + driver.getTitle() + ", URL: " + driver.getCurrentUrl());
			}

		} finally {

			driver.quit();
		}
	}
}