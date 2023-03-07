import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consumer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ImdbConsumer consumer = new ImdbConsumer("https://imdb-api.com/en/API/Top250Movies/");

        // "image" , "year", "imDbRating"

        Pattern titlePattern = Pattern.compile("title");
        Pattern imagesPattern = Pattern.compile("image");
        Pattern yearPattern = Pattern.compile("year");
        Pattern ratingPattern = Pattern.compile("\\b"+"imDbRating"+"\\b");

        String consumerResponse = consumer.getJSON();

        ArrayList<String> titlesList = new ArrayList<String>();
        ArrayList<String> imagesList = new ArrayList<String>();
        ArrayList<String> yearsList = new ArrayList<String>();
        ArrayList<String> ratingsList = new ArrayList<String>();

        String[] parts = consumerResponse.split(",");
        for (String p : parts) {
            Matcher titleMatcher = titlePattern.matcher(p);
            Matcher imagesMatcher = imagesPattern.matcher(p);
            Matcher yearMatcher = yearPattern.matcher(p);
            Matcher ratingMatcher = ratingPattern.matcher(p);

            if (titleMatcher.find()) {
                String[] title = p.split(":");
                titlesList.add(title[1]);
            } else if (imagesMatcher.find()) {
                String[] image = p.split(":");
                imagesList.add(image[1]);
            } else if (yearMatcher.find()) {
                String[] year = p.split(":");
                yearsList.add(year[1]);
            } else if (ratingMatcher.find()) {
                String[] rating = p.split(":");
                ratingsList.add(rating[1]);
            }
        }
        System.out.println('A' + Integer.toString(10));
    }
}

