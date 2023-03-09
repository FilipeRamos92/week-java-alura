import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WeekJava {
    public static void main(String[] args) throws IOException, InterruptedException {
        ImdbConsumer consumer = new ImdbConsumer("https://imdb-api.com/en/API/Top250Movies/");

        String json = consumer.getJSON();

        List<Movie> movies = parse(json);

        System.out.println(movies.get(0).title);
        System.out.println(movies.get(0).year);
        System.out.println(movies.get(0).rating);
        System.out.println(movies.get(0).urlImage);
    }

    private static List<Movie> parse(String json) {
        String[] moviesArray = parseJsonMovies(json);

        List<String> titles = parseTitles(moviesArray);
        List<String> urlImages = parseUrlImages(moviesArray);
        List<String> ratings = parseRatings(moviesArray);
        List<String> years = parseYears(moviesArray);

        List<Movie> movies = new ArrayList<>(titles.size());

        for(int i=0; i < titles.size(); i++) {
            movies.add(new Movie(titles.get(i), urlImages.get(i), ratings.get(i), years.get(i)));
        }
        return movies;
    }

    private static String[] parseJsonMovies(String json) {
        Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);

        if(!matcher.matches()) {
            throw new IllegalArgumentException("na match in " + json);
        }

        String[] moviesArray = matcher.group(1).split("\\},\\{");
        moviesArray[0] = moviesArray[0].substring(1);
        int last = moviesArray.length - 1;
        String lastString = moviesArray[last];
        moviesArray[last] = lastString.substring(0, lastString.length() - 1);
        return moviesArray;
    }

    private static List<String> parseAttribute(String[] jsonMovies, int pos) {
        return Stream.of(jsonMovies)
            .map(e -> e.split("\",\"")[pos])
            .map(e -> e.split(":\"")[1])
            .map(e -> e.replaceAll("\"", ""))
            .collect(Collectors.toList());
    }

    private static List<String> parseTitles(String[] moviesArray) {
        return parseAttribute(moviesArray, 3);
    }

    private static List<String> parseUrlImages(String[] moviesArray) {
        return parseAttribute(moviesArray, 5);
    }

    private static List<String> parseRatings(String[] moviesArray) {
        return parseAttribute(moviesArray, 7);
    }

    private static List<String> parseYears(String[] moviesArray) { 
        return parseAttribute(moviesArray, 4);
    }
}

