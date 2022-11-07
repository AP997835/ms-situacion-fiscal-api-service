package com.profuturo.constancia.situacionfiscal.config;

import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String REGEX_ENDPOINTS = "/SituacionFiscal";

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.profuturo.constancia.situacionfiscal.controladores"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				;
	}

	private ApiInfo getApiInfo() {

		return new ApiInfoBuilder().title("Constancia Situacion Fiscal API")
				.description("Constancia Situacion Fiscal  - API Description").version("1.0").license("https://www.profuturo.mx")
				.contact(new Contact("Contact", "https://www.profuturo.mx", "ayuda@profuturo.com.mx")).build();
	}
}
