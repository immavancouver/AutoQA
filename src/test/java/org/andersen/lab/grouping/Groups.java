package org.andersen.lab.grouping;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Groups {

	@Test(groups = "first", priority = 1)
	public void one() {
		Assert.assertTrue(true);
	}

	@Test(groups = "second", priority = 2)
	public void two() {
		Assert.assertTrue(true);
	}

	@Test(groups = "first", priority = 3)
	public void three() {
		Assert.assertTrue(true);
	}

	@Test(groups = "second", priority = 4)
	public void four() {
		Assert.assertTrue(true);
	}

	@Test(groups = "first", priority = 5)
	public void five() {
		Assert.assertTrue(true);
	}

	@Test(groups = "second", priority = 6)
	public void six() {
		Assert.assertTrue(true);
	}

	@Test(groups = "first", priority = 7)
	public void seven() {
		Assert.assertTrue(true);
	}

	@Test(groups = "second", priority = 8)
	public void eight() {
		Assert.assertTrue(true);
	}
}
