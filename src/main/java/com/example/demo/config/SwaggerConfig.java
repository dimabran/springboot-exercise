package com.example.demo.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
        		.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
        		.paths(PathSelectors.regex("/.*"))
        		.build().apiInfo(apiInfo());
    }
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Inventory API Exercise",
				"Excercise for Technical support engineer position",
				"1.0",
				"Terms of service",
				new springfox.documentation.service.Contact("Dima Brandorf", "https://dimabrandorf.com" , "dimabran@gmail.com"),
				"API License",
				"http://www.apache.org/licenses/LICENSE-2.0.html", new ArrayList<>());
	}
}
