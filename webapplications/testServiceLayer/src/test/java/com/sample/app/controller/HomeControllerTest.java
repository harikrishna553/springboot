package com.sample.app.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HomeControllerTest {
	@Test
	public void testHomePage() {
		HomeController homeController = new HomeController();
		String result = homeController.homePage();
		assertEquals(result, "Welcome to Spring boot Application Development");
	}
}
