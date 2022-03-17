package com.sample.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition (info =
@Info(
          title = "openAPI Demo applicaiton",
          version = "1.0",
          description = "OpenAPI Demo to document license",
          license = @License(name = "Project license can be found here", url = "https://www.apache.org/licenses/LICENSE-2.0")
  )
)
public class SwaggerConfig {

}
