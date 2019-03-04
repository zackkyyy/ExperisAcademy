package no.noroff;
import no.noroff.repositories.DBHandler;
import no.noroff.repositories.RepositoryPerson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class Program {
    public static DBHandler dbHandler = new DBHandler();
    public static void main(String[] args){
        SpringApplication.run(Program.class, args);

        RepositoryPerson rep = new RepositoryPerson();
        rep.addTests();

        dbHandler.closeConnection();
    }
}