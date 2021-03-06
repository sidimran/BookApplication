package com.zkteco.book.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.zkteco.book")).paths(regex("/api/v1/book.*")).build()
				.apiInfo(metaInfo());

	}

	private ApiInfo metaInfo() {

		return new ApiInfo(

				"Spring Boot Swagger Example API", "Spring Boot Swagger Example API for Youtube", "1.0",
				"Terms of Service",
				new Contact("SyedMudassir", "https://www.youtube.com/TechPrimers", "sidimran4455@gmail.com"),
				"Apache License Version 2.1", "https://www.apache.org/license.html"

		);

	}

}
