package org.serratec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("org.serratec.controller"))
			.paths(PathSelectors.any())
			.build()
		    .apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("API - Curso REST")
				.description("Api utilizada em aula")
				.license("Apache License 2.0")
				.termsOfServiceUrl("")
				.version("1.01")
				.contact(new Contact("Serratec", "www.serratec.org", "contato@serratec.org"))
				.build();
		return apiInfo;
	}
}
