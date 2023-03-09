import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {
    PrintWriter writer;
    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

 
    public void generate(List<Movie> movieList) {
        String head = 
        "<head>\n<meta charset=\"utf-8\">\n" + 
        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" + 
        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"" +
        "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
        "</head>\n";

        String divTemplate = 
        "<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">\n" +
        "<h4 class=\"card-header\">%s</h4>\n" +
        "<div class=\"card-body\">\n" + 
            "<img class=\"card-img\" src=\"%s\" alt=\"%s\">\n" +
            "<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>\n" +
        "</div>\n" +
        "</div>";

        writer.write(head);
        for(Movie movie : movieList) {
            writer.println(String.format(divTemplate, movie.getTitle(), movie.getUrlImage(), movie.getTitle(), movie.getRating(), movie.getYear()));
        }
        writer.flush();
        writer.close();
    }
}
