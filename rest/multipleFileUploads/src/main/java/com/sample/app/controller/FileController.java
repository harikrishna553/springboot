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

import com.sample.app.model.RequestDto;
import com.sample.app.model.ResponseDto;

@RestController
public class FileController {

	@PostMapping("/profile")
	public ResponseEntity<ResponseDto> createProfile(@ModelAttribute RequestDto requestDto) throws IOException {

		MultipartFile[] files = requestDto.getDocuments();

		ResponseDto responseDto = new ResponseDto();
		responseDto.setDescription(requestDto.getDescription());

		if (files == null || files.length == 0) {
			return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
		}

		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			String content = convertToString(file.getInputStream());

			if (responseDto.getFileContent().containsKey(fileName)) {
				String existingContent = responseDto.getFileContent().get(fileName);
				content = existingContent + "\n\n" + content;

			}
			responseDto.getFileContent().put(fileName, content);

		}

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