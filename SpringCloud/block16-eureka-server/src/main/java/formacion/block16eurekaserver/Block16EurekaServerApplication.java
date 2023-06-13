package formacion.block16eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Block16EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16EurekaServerApplication.class, args);
	}

}
