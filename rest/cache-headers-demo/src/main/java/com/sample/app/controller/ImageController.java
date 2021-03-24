package com.sample.app.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.app.dto.ImageUploadDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/v1/images/")
@Api(tags = "Image controller", description = "This section contains image related APIs")
public class ImageController {

	private static final String IMAGES_PATH = "/Users/Shared/images/";

	@ApiOperation(value = "Get given image", notes = "This API will get the image")
	@GetMapping(value = "by-name/{name}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<Resource> image(
			@ApiParam(name = "name", value = "Image name.", required = true) @PathVariable("name") String name)
			throws IOException {
		CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.DAYS).noTransform().mustRevalidate();

		final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(IMAGES_PATH + name)));
		return ResponseEntity.ok().cacheControl(cacheControl).contentLength(inputStream.contentLength())
				.body(inputStream);
	}

	@ApiOperation(value = "Upload the image", notes = "This API will upload the image")
	@PostMapping("upload")
	public ResponseEntity<String> uploadFiles(@ModelAttribute ImageUploadDto requestDto) throws IOException {

		System.out.println(requestDto.getDescription());
		MultipartFile[] multiPartFiles = requestDto.getDocuments();

		for (MultipartFile multiPartFile : multiPartFiles) {
			// String name = multiPartFile.getName();
			String originalFileName = multiPartFile.getOriginalFilename();

			try (InputStream is = multiPartFile.getInputStream();
					OutputStream os = new FileOutputStream(IMAGES_PATH + originalFileName)) {
				IOUtils.copy(is, os);
			}

		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Success");

	}

	@ApiOperation(value = "Get given image", notes = "This API will get the image")
	@GetMapping(value = "intercept/by-name/{name}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<Resource> getImage(
			@ApiParam(name = "name", value = "Image name.", required = true) @PathVariable("name") String name)
			throws IOException {
		
		final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(IMAGES_PATH + name)));
		return ResponseEntity.ok().contentLength(inputStream.contentLength())
				.body(inputStream);
	}
}
