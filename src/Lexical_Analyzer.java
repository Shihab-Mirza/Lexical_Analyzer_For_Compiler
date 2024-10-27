import java.io.*;
import java.util.*;
public class Lexical_Analyzer {
    public static void main(String[] args) {
        List<String> tokens = new ArrayList<>();
        try {
            File Text_File = new File("C:\\Users\\sm7\\IdeaProjects\\CSC_437_Assignment\\src\\input.text");
            Scanner input = new Scanner(Text_File);
            input.useDelimiter("\\s+|(?=[^\\w\\s(){}\\[\\].])");
            while (input.hasNext()) {
                String token = input.next();
                if (Keyword(token)) {
                    tokens.add("Keyword: " + token);
                } else if (Identifier(token)) {
                    tokens.add("Id: " + token);
                } else if (InvalidIdentifier(token)) {
                    tokens.add("ERROR: Invalid Id: " + token);
                } else if (Number(token)) {
                    tokens.add("Number: " + token);
                } else if (Operator(token)) {
                    tokens.add("Operator: " + token);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        for (String token : tokens) {
            System.out.println(token);
        }
    }private static boolean Keyword(String token) {
        String[] keywords = { "public" , "class" , "static", "println", "void", "int" };
        for (String keyword : keywords) {
            if (token.equals(keyword)) {
                return true;
            }
        }
        return false;
    }private static boolean Identifier(String token) {
        if (Character.isDigit(token.charAt(0))) {
            return false; // Invalid identifier if it starts with a digit
        }
        return token.matches("[a-zA-Z][a-zA-Z0-9]*");
    }private static boolean InvalidIdentifier(String token) {
        return token.matches("^[0-9][a-zA-Z][a-zA-Z0-9]*") ;
    }
    private static boolean Number(String token) {
        return token.matches("-?\\d+(\\.\\d+)?");
    }private static boolean Operator(String token) {
        String[] operators = { "+", "-", "*", "/", "=" };
        for (String operator : operators) {
            if (token.equals(operator)) {
                return true;
            }
        }
        return false;
    }
}