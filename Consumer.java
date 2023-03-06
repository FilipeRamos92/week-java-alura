import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import mypack.ApiKey;

public class Consumer {
    public static void main(String[] args) {
        ApiKey apikey = new ApiKey();
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apikey.getValue()))
            .header("Content-Type", "application/json")
            .GET()
            .build();

        client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(response -> { System.out.println(response.statusCode());
                                     return response;})
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println)
            .join();
         
    }
}

