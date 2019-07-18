package com.sample.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/hello")
	public ModelAndView sayHello(Model model) {
		ModelAndView modelAndView = new ModelAndView("hello");
		return modelAndView;
	}

	@GetMapping("/secure")
	public ModelAndView securePage(Model model) {
		ModelAndView modelAndView = new ModelAndView("secure");
		return modelAndView;
	}

	@GetMapping("/login")
	public ModelAndView loginPage(Model model) {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@GetMapping("/logoutSuccess")
	public ModelAndView logoutPage(Model model) {
		ModelAndView modelAndView = new ModelAndView("logoutSuccess");
		return modelAndView;
	}

	@GetMapping("/")
	public ModelAndView homePage(Model model) {
		ModelAndView modelAndView = new ModelAndView("homePage");
		return modelAndView;
	}

}
