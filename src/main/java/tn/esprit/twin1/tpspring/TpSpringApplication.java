package tn.esprit.twin1.tpspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TpSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpSpringApplication.class, args);
	}

}
