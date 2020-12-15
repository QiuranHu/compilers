package qiuran.LexicalAnalyzer;

import java.util.HashMap;

public class Token {
    public static HashMap<String, Category> categoryHashMap = new HashMap<String, Category>();

    static {
        for (Category category : Category.values())
            categoryHashMap.put(category.lexeme, category);
    }

    private final String result;
    private Integer startIndex;
    private final Integer endIndex; // Exclusive.
    private final Category category;

    public Token(String result, Integer startIndex, Integer endIndex, Token.Category category) {
        this.result = result;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        if (category != null) {
            this.category = category;
        }
        else {
            Token.Category lookupResult = Token.categoryHashMap.get(result);
            if (lookupResult != null)
                this.category = lookupResult;
            else
                this.category = Token.Category.IDENTIFIER;
        }
    }

    public String getResult() {
        return result;
    }

    public Token.Category getCategory() {
        return category;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public enum Category {
        GTEQ(">="), LTEQ("<="), EQ("=="), GT(">"), LT("<"),
        PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), ASSIGN("="),
        LPAR("("), RPAR(")"), SEMI(";"), COMMA(","),
        IF("if"), ELSE("else"), WHILE("while"), STATIC("static"), CLASS("class"),
        STRING("String"), LSB("["), RSB("]"),
        INT("int"), PUBLIC("public"), PRIVATE("private"), VOID("void"),
        NUMBER(null), IDENTIFIER(null), ERROR(null);
        final private String lexeme;

        Category(String str) {
            lexeme = str;
        }
    }
}
