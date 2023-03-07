import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import mypack.ApiKey;

public class ImdbConsumer {
    String uri;
    ApiKey apiKey;

    public ImdbConsumer(String uri) {
        ApiKey apiKey = new ApiKey();
        this.apiKey = apiKey;
        this.uri = uri;
    }

    public String getJSON() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(uri + apiKey.getValue()))
        .GET()
        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        return json;
    }
}
