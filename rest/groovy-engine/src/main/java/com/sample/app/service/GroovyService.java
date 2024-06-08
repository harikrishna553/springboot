package com.sample.app.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sample.app.constants.AppConstants;
import com.sample.app.dto.ScriptExecRequestDto;
import com.sample.app.entity.GroovyScript;
import com.sample.app.repository.GroovyScriptRepository;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import jakarta.validation.Valid;

@Service
public class GroovyService {

	@Autowired
	private GroovyScriptRepository scriptRepository;

	public List<GroovyScript> getAllScripts() {
		return scriptRepository.findAll();
	}

	public GroovyScript byFile(MultipartFile scriptFile, String name) throws IOException {
		MultipartFile file = scriptFile;
		InputStream inputStream = file.getInputStream();
		String sript = convertToString(inputStream);
		GroovyScript groovyScript = GroovyScript.fromScriptAndName(sript, name);
		return scriptRepository.save(groovyScript);

	}

	public Object executeScript(@Valid ScriptExecRequestDto dto) {
		Optional<GroovyScript> optionalScript = scriptRepository.findById(dto.getScriptId());
		if (optionalScript.isPresent()) {
			Binding binding = new Binding();
			binding.setVariable("input", dto.getInput());

			// Set an initial value for result variable
			binding.setVariable("result", "");

			GroovyShell shell = new GroovyShell(binding);
			StringBuilder builder = new StringBuilder();
			builder.append(AppConstants.SCRIPT_TEMPLATE);
			builder.append(optionalScript.get().getScript());
			return shell.evaluate(builder.toString());

		} else {
			throw new NoSuchElementException("Script not found");
		}
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
