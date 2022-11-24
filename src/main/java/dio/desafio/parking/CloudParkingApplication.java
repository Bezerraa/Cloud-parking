package dio.desafio.parking;

import dio.desafio.parking.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Trabalhando com JPA. Configurando o Banco de dados e criando um container.
@SpringBootApplication
public class CloudParkingApplication {


	public static void main(String[] args) {
		SpringApplication.run(CloudParkingApplication.class, args);


	}

}
