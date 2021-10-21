package com.sample.app.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.app.controller.ProjectController;

@ControllerAdvice(basePackageClasses = { ProjectController.class })
@ResponseBody
public class AppExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(AppException.class)
	public ResponseEntity<ExceptionResult> handleThrowable(AppException ex) {
		return new ResponseEntity<>(new ExceptionResult(ex), ex.getErrorCode());

	}
}
