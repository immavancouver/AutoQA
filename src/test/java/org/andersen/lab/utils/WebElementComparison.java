package org.andersen.lab.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class WebElementComparison {

	public static void compareElements(WebElement element1, WebElement element2) {
		Point location1 = element1.getLocation();
		Dimension size1 = element1.getSize();

		Point location2 = element2.getLocation();
		Dimension size2 = element2.getSize();


		if (location1.getY() < location2.getY()) {
			System.out.println("Element 1 is positioned higher.");
		} else if (location1.getY() > location2.getY()) {
			System.out.println("Element 2 is positioned higher.");
		} else {
			System.out.println("Both elements are at the same vertical position.");
		}


		if (location1.getX() < location2.getX()) {
			System.out.println("Element 1 is positioned more to the left.");
		} else if (location1.getX() > location2.getX()) {
			System.out.println("Element 2 is positioned more to the left.");
		} else {
			System.out.println("Both elements are at the same horizontal position.");
		}


		int area1 = size1.getWidth() * size1.getHeight();
		int area2 = size2.getWidth() * size2.getHeight();

		if (area1 > area2) {
			System.out.println("Element 1 takes up more space.");
		} else if (area1 < area2) {
			System.out.println("Element 2 takes up more space.");
		} else {
			System.out.println("Both elements take up the same amount of space.");
		}
	}
}
