package com.sample.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.app.model.UserDto;
import com.sample.app.model.UserResponseDto;

@RestController
public class UserController {

	@PostMapping("/profile")
	public ResponseEntity<UserResponseDto> createProfile(@ModelAttribute UserDto userDto) throws IOException {

		MultipartFile resume = userDto.getResume();

		if (resume == null || resume.isEmpty()) {
			throw new RuntimeException("Resume must not be empty");
		}

		// String originalFileName = resume.getOriginalFilename();
		InputStream inputStream = resume.getInputStream();
		String content = convertToString(inputStream);

		UserResponseDto responseDto = new UserResponseDto();
		responseDto.setFirstName(userDto.getFirstName());
		responseDto.setLastName(userDto.getLastName());
		responseDto.setResume(content);

		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	private static String convertToString(InputStream in) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = in.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toString("UTF-8");
	}
}