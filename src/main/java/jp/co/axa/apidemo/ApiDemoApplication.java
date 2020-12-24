package jp.co.axa.apidemo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import jp.co.axa.apidemo.entities.Employee;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@EnableSwagger2
@SpringBootApplication
public class ApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDemoApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("jp.co.axa.apidemo")).build().apiInfo(getApiDetails());
	}

	private ApiInfo getApiDetails() {
		return new ApiInfo("Axa Employee REST API", "Demo API", "0.1", "Free to use!",
				new springfox.documentation.service.Contact("David Fu", "http://axa.com", "debisoft21@yahoo.co.jp"),
				"License", "http://www.fsf.org", Collections.emptyList());
	}

}
