package no.noroff.Task21;

import no.noroff.Task21.database.DBHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class Task21Application {
	public static void main(String[] args) {
		DBHandler db = new DBHandler();
		db.addTests();

		SpringApplication.run(Task21Application.class, args);
	}
}
