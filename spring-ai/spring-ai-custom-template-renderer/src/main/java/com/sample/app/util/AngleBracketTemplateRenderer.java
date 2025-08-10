package com.sample.app.util;

import java.util.Map;
import org.springframework.ai.template.TemplateRenderer;

public class AngleBracketTemplateRenderer implements TemplateRenderer {

  @Override
  public String apply(String template, Map<String, Object> variables) {
    if (template == null) {
      return "";
    }

    String result = template;
    for (Map.Entry<String, Object> entry : variables.entrySet()) {
      String placeholder = "<" + entry.getKey() + ">";
      result = result.replace(placeholder, String.valueOf(entry.getValue()));
    }
    return result;
  }
}
