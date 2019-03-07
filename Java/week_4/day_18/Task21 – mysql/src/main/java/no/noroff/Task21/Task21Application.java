package no.noroff.Task21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class Task21Application {
	public static void main(String[] args) {
		DBHandler db = new DBHandler();
		//db.addUser("xX_Dragon_Xx", "password123");
		//db.addClass("Hunter", "Multi shot");
		//db.addCharacter(1, "richkid", 1, "Rogue");

		SpringApplication.run(Task21Application.class, args);

		db.printAll();
	}
}
