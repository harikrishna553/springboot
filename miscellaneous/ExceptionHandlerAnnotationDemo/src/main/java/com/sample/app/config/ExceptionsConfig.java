package com.sample.app.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sample.app.model.AppError;

@Configuration
public class ExceptionsConfig {

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<AppError> handleNumberFormatException(NumberFormatException ex) {
		AppError error = new AppError();
		error.setMessaage(ex.getMessage());

		Optional<AppError> err = Optional.of(error);

		ResponseEntity<AppError> errEntity = ResponseEntity.of(err);

		return errEntity;

	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<AppError> handleRuntimeException(RuntimeException ex) {
		AppError error = new AppError();
		error.setMessaage(ex.getMessage());
		
		Optional<AppError> err = Optional.of(error);

		ResponseEntity<AppError> errEntity = ResponseEntity.of(err);

		return errEntity;

	}
}

