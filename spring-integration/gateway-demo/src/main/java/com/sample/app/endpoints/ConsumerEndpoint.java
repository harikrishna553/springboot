package com.sample.app.endpoints;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint {

	@ServiceActivator(inputChannel = "printChannel")
	public void consumeStringMessage(String message) {
		System.out.println("Received message from printChannel : " + message);
	}

	@ServiceActivator(inputChannel = "lengthCalcChannel", outputChannel = "printChannel")
	public String lenghtCalc(String str) {
		System.out.println("Received message from lengthCalcChannel : " + str);

		return "'" + str + "' has length " + str.length();
	}

	@ServiceActivator(inputChannel = "myUpperCaseChannel", outputChannel = "lengthCalcChannel")
	public String toUpper(String str) {
		System.out.println("Received message from myUpperCaseChannel : " + str);
		return str.toUpperCase();
	}
}
