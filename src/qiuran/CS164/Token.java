package qiuran.CS164;

public class Token {
    SyntacticCategory syntax;
    Object value;

    enum SyntacticCategory {
        IF, LPAR, ID, EQUALS, RPAR, ASSIGN
    }
    // Location sourcePosition;
}
