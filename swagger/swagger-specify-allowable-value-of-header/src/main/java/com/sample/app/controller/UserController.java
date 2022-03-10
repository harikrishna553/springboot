package com.sample.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/users")
@Api(tags = { "user" })
@CrossOrigin("*")
public class UserController {

	@ApiOperation(value = "Get information about me", notes = "This API will get information about me")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "please pass sso token in the format Beare XXASDACS", dataType = "String", paramType = "header", required = true),
			@ApiImplicitParam(name = "processingType", value = "some random string", dataType = "String", paramType = "header", required = false, allowableValues = "fullDetails, nameAndIdOnly,nameOnly") })
	@GetMapping("/about-me")
	public ResponseEntity<Map<String, Object>> aboutMe() {
		Map<String, Object> myDetails = new HashMap<>();
		myDetails.put("id", 1);
		myDetails.put("name", "Krishna");

		return ResponseEntity.ok(myDetails);

	}

}
