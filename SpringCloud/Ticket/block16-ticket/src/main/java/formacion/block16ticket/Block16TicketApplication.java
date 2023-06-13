package formacion.block16ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Block16TicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16TicketApplication.class, args);
	}

}
