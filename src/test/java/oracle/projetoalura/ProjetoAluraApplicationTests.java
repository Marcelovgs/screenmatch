package oracle.projetoalura;

import oracle.projetoalura.Model.DadosTemporada;
import oracle.projetoalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProjetoAluraApplicationTests implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProjetoAluraApplicationTests.class, args);
    }


    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibeMenu();
    }}



