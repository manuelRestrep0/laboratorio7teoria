import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> tokens = Lexer.tokenize(scanner.nextLine());
        for (String token : tokens) {
            System.out.println(token);
        }

        double result = Parser.parseExpression(tokens);
        System.out.println(result);
    }
}