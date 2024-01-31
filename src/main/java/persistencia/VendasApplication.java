package persistencia;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import persistencia.domain.entity.Client;
import persistencia.domain.repository.ClienteRepository;

@SpringBootApplication
public class VendasApplication {



    public static void main(String[] args) {

        SpringApplication.run(VendasApplication.class, args);
    }
}

