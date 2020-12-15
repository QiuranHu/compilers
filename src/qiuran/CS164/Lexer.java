package qiuran.CS164;

import java.io.Reader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.Pattern;


/**
 * A lexer producers a stream of tokens taken from an arbitrary Reader
 * supplied to the constructor.
 */
public class Lexer {
    private static final Pattern tokenPat = Pattern.compile("\\s+|#.*" +
            "|>=|<=|-->|if|def|else|fi|while" +
            "|([a-zA-Z][a-zA-Z0-9]*)|(\\d+)" +
            "|.");
    private static HashMap<String, Category> tokenMap = new HashMap<String, Category>();

    static {
        for (Category c : Category.values())
            tokenMap.put(c.lexeme, c);
    }

    public String lastLexeme;
    private Scanner inp;

    public Lexer(Reader reader) {
        inp = new Scanner(reader);
    }

    public Category nextToken() {
        if (inp.findWithinHorizon(tokenPat, 0) == null)
            return Category.EOF;
        else {
            lastLexeme = inp.match().group(0);
            if (inp.match().start(1) != -1)
                return nextToken();
            else if (inp.match().start(2) != -1)
                return Category.IDENT;
            else if (inp.match().start(3) != -1)
                return Category.NUMERAL;
            Category result = tokenMap.get(lastLexeme);
            if (result == null)
                return Category.ERROR;
            else
                return result;
        }
    }

    public static enum Category {
        GTEQ(">="), LTEQ("<="), GT(">"), LT("<"), ARROW("-->"),
        PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), ASSIGN("="),
        LPAR("("), RPAR(")"), SEMI(";"), COMMA(","),
        IF("if"), DEF("def"), ELSE("else"), FI("fi"), WHILE("while"),
        IDENT(null), NUMERAL(null), EOF(null), ERROR(null);

        final private String lexeme;

        Category(String s) {
            lexeme = s;
        }
    }
}
