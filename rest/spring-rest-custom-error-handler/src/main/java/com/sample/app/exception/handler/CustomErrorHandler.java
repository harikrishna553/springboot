package com.sample.app.exception.handler;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sample.app.model.ApiError;

@ControllerAdvice(basePackages = "com.sample.app")
public class CustomErrorHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<String>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + " -> " + error.getDefaultMessage());
		}

		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + " -> " + error.getDefaultMessage());
		}

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);

		// If you want to throw apiError directly, uncomment this
		// return new ResponseEntity(apiError, apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " -> parameter is missing in request";

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);

		// If you want to throw apiError directly, uncomment this
		// return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);

		// If you want to throw apiError directly, uncomment this
		// return new ResponseEntity<Object>(apiError, new HttpHeaders(),
		// apiError.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);

		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);

		// If you want to throw apiError directly, uncomment this
		// return new ResponseEntity<Object>(apiError, new HttpHeaders(),
		// apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);

		// If you want to throw apiError directly, uncomment this
		// return new ResponseEntity<Object>(apiError, new HttpHeaders(),
		// apiError.getStatus());
	}
}
