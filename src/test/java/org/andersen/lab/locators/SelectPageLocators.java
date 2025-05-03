package org.andersen.lab.locators;

import org.openqa.selenium.By;

public class SelectPageLocators {

	public static final By COUNTRY_DROPDOWN = By.cssSelector("select[title='Select country']");
	public static final By LANGUAGE_DROPDOWN = By.cssSelector("select[title='Select language']");
	public static final By TYPE_DROPDOWN = By.cssSelector("select[title='Select type']");

	public static final By START_DATE_FIELD = By.cssSelector("input[title='Start date']");
	public static final By END_DATE_FIELD = By.cssSelector("input[title='End date']");

	public static final By MULTIPLE_COURSES_SELECT = By.id("MultipleSelect");

	public static final By SEARCH_BUTTON = By.cssSelector("button[name='SelectPageSearchButton']");

	public static final By ERROR_MESSAGE = By.xpath("//h2[text()='Unfortunately, we did not find any courses matching your chosen criteria.']");

	public enum DropDownOptions {
		COUNTRY_USA("USA"),
		LANGUAGE_ENGLISH("English"),
		TYPE_TESTING("Testing");

		private final String value;

		DropDownOptions(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum CourseOptions {
		AQA_JAVA("AQA Java"),
		AQA_PYTHON("AQA Python");

		private final String value;

		CourseOptions(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

}
