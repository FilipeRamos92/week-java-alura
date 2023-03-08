import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeekJava {
    public static void main(String[] args) throws IOException, InterruptedException {
        ImdbConsumer consumer = new ImdbConsumer("https://imdb-api.com/en/API/Top250Movies/");

        Pattern titlePattern = Pattern.compile("title");
        Pattern imagesPattern = Pattern.compile("image");
        Pattern yearPattern = Pattern.compile("year");
        Pattern ratingPattern = Pattern.compile("\\b"+"imDbRating"+"\\b");

        String consumerResponse = consumer.getJSON();

        ArrayList<Movie> movies = new ArrayList<>();

        String[] parts = consumerResponse.split(",");

        for (String p : parts) {
            Matcher titleMatcher = titlePattern.matcher(p);
            Matcher imagesMatcher = imagesPattern.matcher(p);
            Matcher yearMatcher = yearPattern.matcher(p);
            Matcher ratingMatcher = ratingPattern.matcher(p);

            String t = "";
            String u = "";
            String r = "";
            String y = "";

            if (titleMatcher.find()) {
                String[] title = p.split(":");
                t = title[1];
            } else if (imagesMatcher.find()) {
                String[] image = p.split(":");
                u = image[1] + image[2];
            } else if (yearMatcher.find()) {
                String[] year = p.split(":");
                y = year[1];
            } else if (ratingMatcher.find()) {
                String[] rating = p.split(":");
                r = rating[1];
            }

            if (
                t == null || u == null || y == null || r == null ||
                t == "" || u == "" || y == "" || r == "") {
                continue;
            }

            Movie movie = new Movie(t, u, r, y);
            movies.add(movie);
        }
        for (Movie movie : movies) {
            movie.getAttributes();
        }
    }
}

