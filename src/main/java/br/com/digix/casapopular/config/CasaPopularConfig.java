package br.com.digix.casapopular.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class CasaPopularConfig {
	
	@Bean
	public Docket detalheApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket
		.select()
		.apis(RequestHandlerSelectors.basePackage("br.com.digix"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(buildInfoApi().build());
		
		return docket;
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	private ApiInfoBuilder buildInfoApi() {
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		Contact contact = new Contact(
					"Jeferson Job Ribeiro dos Santos",
					"https://br.linkedin.com/in/jeferson-job-923027117",
					"jeferson.job@outlook.com"
				);
		
		apiInfoBuilder.title("Casa Popular API");
		apiInfoBuilder.description("API de cadastro de famílias para avaliação e aprovação de programas de casa própria do Governo Federal");
		apiInfoBuilder.version("0.0.1-SNAPSHOT");
		apiInfoBuilder.termsOfServiceUrl("Termos de serviço: Para avaliação da Digix Soluções em TI");
		apiInfoBuilder.license("Digix: Todos os direitos reservados");
		apiInfoBuilder.licenseUrl("");
		apiInfoBuilder.contact(contact);
		return apiInfoBuilder;
	}

}
