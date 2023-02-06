package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.app.constants.AppConstants;
import com.sample.app.util.JsonUtil;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/v1/publisher")
public class PublisherController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@PostMapping("/publish-message")
	public ResponseEntity sendMessage(
			@RequestBody(description = "Publish new message", required = true, content = @Content(schema = @Schema(implementation = HashMap.class))) @org.springframework.web.bind.annotation.RequestBody final Map<String, Object> payload) {

		Thread t1 = new Thread() {
			public void run() {
				try {
					String json = JsonUtil.marshal(payload);
					kafkaTemplate.send(AppConstants.NEW_TOPIC_NAME, json);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};

		t1.start();

		return ResponseEntity.ok().build();

	}

}
