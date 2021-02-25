package com.sample.app.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.app.controller.EmployeeController;

@ControllerAdvice(basePackageClasses = { EmployeeController.class })
@ResponseBody
public class CustomExceptionHandler {
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<Serializable> handleGlobalDataPortalException(BaseException ex) {

		ErrorResponse response = new ErrorResponse();
		response.setMsg(ex.getMessage());

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static class ErrorResponse implements Serializable {
		private static final long serialVersionUID = 1L;
		private String msg;

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}
}
