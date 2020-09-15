package com.sample.app.customizations;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class MyPromptProvider implements PromptProvider {

	public AttributedString getPrompt() {
		return new AttributedString("CLI-DEMO-PROMPT:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));
	}

}
