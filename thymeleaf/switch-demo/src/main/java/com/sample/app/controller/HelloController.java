package com.sample.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@GetMapping("/welcome")
	public ModelAndView emps(@RequestParam(name = "msgCode", defaultValue = "msgCode") Integer msgCode) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("welcome");

		modelAndView.addObject("msgCode", msgCode);

		return modelAndView;
	}

}
