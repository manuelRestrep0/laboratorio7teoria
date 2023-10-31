import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public static ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        String pattern = "\\d+\\.\\d+|\\d+|\\+|\\-|\\*|\\/|\\(|\\)";
        Pattern tokenPattern = Pattern.compile(pattern);
        Matcher matcher = tokenPattern.matcher(input);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        return tokens;
    }

    public static void main(String[] args) {
        String input = "3.14 + 2 - 5 * 2.5 / 3";
        ArrayList<String> tokens = tokenize(input);

        for (String token : tokens) {
            System.out.println(token);
        }
    }
}
