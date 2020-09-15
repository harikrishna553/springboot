package com.sample.app.customizations;

import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Value;

import com.sample.app.enums.ShellPromptColor;

public class ShellOutputHelper {

	@Value("${shell.out.info}")
	public String infoColor;

	@Value("${shell.out.success}")
	public String successColor;

	@Value("${shell.out.warning}")
	public String warningColor;

	@Value("${shell.out.error}")
	public String errorColor;

	private Terminal terminal;

	public ShellOutputHelper(Terminal terminal) {
		this.terminal = terminal;
	}

	public String getColored(String message, ShellPromptColor color) {
		return (new AttributedStringBuilder())
				.append(message, AttributedStyle.DEFAULT.foreground(color.toJlineAttributedStyle())).toAnsi();
	}

	public String getInfoMessage(String message) {
		return getColored(message, ShellPromptColor.valueOf(infoColor));
	}

	public String getSuccessMessage(String message) {
		return getColored(message, ShellPromptColor.valueOf(successColor));
	}

	public String getWarningMessage(String message) {
		return getColored(message, ShellPromptColor.valueOf(warningColor));
	}

	public String getErrorMessage(String message) {
		return getColored(message, ShellPromptColor.valueOf(errorColor));
	}
}