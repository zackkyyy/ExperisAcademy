package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
	public static void main(String[] args) {
		String ENV_PORT = System.getenv().get("PORT");
		String ENV_DYNO = System.getenv().get("DYNO");
		if(ENV_PORT != null && ENV_DYNO != null){
			System.getProperties().put("server.port", ENV_PORT);
		}

		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "Hello Docker World\n\nUploaded by Jan Le";
	}

}
