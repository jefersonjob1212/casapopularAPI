package br.com.digix.casapopular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "br.com.digix.casapopular")
@EnableMongoRepositories
public class CasaPopularApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasaPopularApplication.class, args);
	}

}
