package com.sample.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@GetMapping("/hello")
	public ModelAndView sayHello(Model model) {
		 ModelAndView modelAndView = new ModelAndView("hello");
		 return modelAndView;
	}
}
