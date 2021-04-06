package com.sample.app.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.*;

@MessagingGateway(defaultHeaders = {@GatewayHeader(name = "root-header", value = "root123")})
public interface CustomGateway {

	@Gateway(requestChannel = "printChannel", headers = {
			@GatewayHeader(name = "secret", value = "my-secret123"),
			@GatewayHeader(name = "service-name", value = "my-service") })
	public String sendToPrintChannel(String message);

}
