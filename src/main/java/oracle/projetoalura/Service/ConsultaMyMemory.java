package oracle.projetoalura.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultaMyMemory {

    public static String traduzir(String texto) {
        try {
            String textoCodificado = URLEncoder.encode(texto, StandardCharsets.UTF_8);
            String url = "https://api.mymemory.translated.net/get?q=" + textoCodificado + "&langpair=en|pt";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            // Extrair traduzido de forma simples e mais segura
            String tag = "\"translatedText\":\"";
            int start = json.indexOf(tag);
            if (start == -1) return "Erro na tradução";
            start += tag.length();
            int end = json.indexOf("\"", start);
            if (end == -1) return "Erro na tradução";

            String traduzido = json.substring(start, end);
            return traduzido;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao traduzir o texto.";
        }
    }
}
