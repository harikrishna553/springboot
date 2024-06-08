package com.sample.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.app.dto.ScriptExecRequestDto;
import com.sample.app.entity.GroovyScript;
import com.sample.app.service.GroovyService;

import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/scripts")
public class GroovyScriptController {

	@Autowired
	private GroovyService groovyService;

	@GetMapping
	public List<GroovyScript> getAllScripts() {
		return groovyService.getAllScripts();
	}

	@PostMapping("/execute")
	public ResponseEntity<Object> executeScript(@RequestBody ScriptExecRequestDto dto) {
		Object result = groovyService.executeScript(dto);

		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "/by-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<GroovyScript> byFile(@RequestPart("name") @NotEmpty String name,
			@RequestPart("script") MultipartFile scriptFile) throws IOException {
		GroovyScript groovyScript = groovyService.byFile(scriptFile, name);
		return ResponseEntity.status(201).body(groovyScript);
	}
}
