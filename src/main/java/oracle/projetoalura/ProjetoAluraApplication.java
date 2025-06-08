package oracle.projetoalura;

import oracle.projetoalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjetoAluraApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProjetoAluraApplication.class, args);
    }


    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibeMenu();
    }}



