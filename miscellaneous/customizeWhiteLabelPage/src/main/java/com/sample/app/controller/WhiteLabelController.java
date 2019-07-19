package com.sample.app.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WhiteLabelController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping(PATH)
	public ModelAndView handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status == null) {
			ModelAndView modelAndView = new ModelAndView("error");
			return modelAndView;
		}

		Integer statusCode = Integer.valueOf(status.toString());

		if (statusCode == HttpStatus.NOT_FOUND.value()) {
			ModelAndView modelAndView = new ModelAndView("404");
			return modelAndView;

		} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			ModelAndView modelAndView = new ModelAndView("500");
			return modelAndView;
		}

		ModelAndView modelAndView = new ModelAndView("error");
		return modelAndView;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

}

