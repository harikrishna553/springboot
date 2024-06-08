package com.sample.app.dto;

import java.util.Map;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public class ScriptExecRequestDto {
	
	@Positive
	private Integer scriptId;
	
	@NotEmpty
	private Map<String, Object> input;

	public Integer getScriptId() {
		return scriptId;
	}

	public void setScriptId(Integer scriptId) {
		this.scriptId = scriptId;
	}

	public Map<String, Object> getInput() {
		return input;
	}

	public void setInput(Map<String, Object> input) {
		this.input = input;
	}

}
