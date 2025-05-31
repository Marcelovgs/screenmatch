package oracle.projetoalura;

import oracle.projetoalura.Model.DadosEpisodio;
import oracle.projetoalura.Model.DadosSerie;
import oracle.projetoalura.Model.DadosTemporada;
import oracle.projetoalura.Service.ConsumoApi;
import oracle.projetoalura.Service.ConverteDados;
import org.junit.jupiter.api.Test;
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
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados("http://www.omdbapi.com/?apikey=41c18d96&t=Gilmore+Girls&");
        System.out.println(json);

        ConverteDados conversor = new ConverteDados();
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
        json = consumoApi.obterDados("https://www.omdbapi.com/?apikey=41c18d96&t=Gilmore+Girls&Season=1&Episode=2");
        DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
        System.out.println(dadosEpisodio);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumoApi.obterDados("https://www.omdbapi.com/?apikey=41c18d96&t=Gilmore+Girls&Season=" + i);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
}
