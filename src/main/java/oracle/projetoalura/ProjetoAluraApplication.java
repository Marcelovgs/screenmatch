package oracle.projetoalura;

import oracle.projetoalura.principal.Principal;
import oracle.projetoalura.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjetoAluraApplication implements CommandLineRunner {

    @Autowired
    private SerieRepository repositorio;

    public static void main(String[] args) {
        SpringApplication.run(ProjetoAluraApplication.class, args);
    }


    public void run(String... args) throws Exception {
        Principal principal = new Principal(repositorio);
        principal.exibeMenu();
    }}



