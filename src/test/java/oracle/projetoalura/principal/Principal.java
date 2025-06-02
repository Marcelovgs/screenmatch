package oracle.projetoalura.principal;

import oracle.projetoalura.Model.DadosEpisodio;
import oracle.projetoalura.Model.DadosSerie;
import oracle.projetoalura.Model.DadosTemporada;
import oracle.projetoalura.Model.Episodio;
import oracle.projetoalura.Service.ConsumoApi;
import oracle.projetoalura.Service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private static final String BASE_URL = "https://www.omdbapi.com/?";
    private static final String API_KEY = "apikey=41c18d96";
    public void exibeMenu() {
        System.out.println("Digite o nome da série para a busca:");
        var nomeSerie = leitura.nextLine();

        // Monta a URL corretamente
        String url = BASE_URL + API_KEY + "&t=" + nomeSerie.replace(" ", "+") + "&type=series";

        var json = consumo.obterDados(url);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

        System.out.println("\nDados encontrados:");
        System.out.println(dados);

             List<DadosTemporada> temporadas = new ArrayList<>();



        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            url = BASE_URL + API_KEY + "&t=" + nomeSerie.replace(" ", "+") + "&season=" + i;
            json = consumo.obterDados(url);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);

              temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

              List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

              System.out.println("\nTop 10 Episodios:");
              dadosEpisodios.stream()
                      .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                      .peek(e -> System.out.println("Primeiro filtro (N/A) " + e) )
                      .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                      .peek(e -> System.out.println("Ordenação " + e) )
                      .limit(10)
                      .peek(e -> System.out.println("Limite " + e) )
                      .map(e -> e.titulo().toUpperCase())
                      .peek(e -> System.out.println("Mapeamento " + e) )
                      .forEach(System.out::println);

                List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.temporada(), d))
                )
                .collect(Collectors.toList());

                episodios.forEach(System.out::println);

        System.out.println("A partir de que ano voce deseja ver os episodios?");
        var ano = leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(1, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e != null & e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        " Temporada: " + e.getTemporada() +
                                " Episodio: " + e.getTitulo() +
                                " Data lançamento: " + e.getDataLancamento().format(formatador)
                ));

    }
    }
