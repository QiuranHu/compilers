package qiuran.SimpleLexicalAnalyzer;

public class SimpleLexicalAnalyzer {
    public static void main(String[] args) {
        SimpleLexicalAnalyzer simpleLexicalAnalyzer = new SimpleLexicalAnalyzer();
        String str = "int a = b + 1c;";
        simpleLexicalAnalyzer.parse(str);
    }

    boolean isDelimiter(char ch) {
        if (ch == ' ' || ch == '+' || ch == '-' || ch == '*'
                || ch == '/' || ch == ',' || ch == ';' || ch == '>'
                || ch == '<' || ch == '=' || ch == '(' || ch == ')'
                || ch == '[' || ch == ']' || ch == '{' || ch == '}')
            return true;
        return false;
    }

    boolean isOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/'
                || ch == '>' || ch == '<' || ch == '=')
            return true;
        return false;
    }

    boolean validIdentifier(String str) {
        char firstChar = str.charAt(0);
        // If the first char in the string is not a digit nor a delimiter, it is a valid identifier.
        if (firstChar == '0' || firstChar == '1' || firstChar == '2' || firstChar == '3'
                || firstChar == '4' || firstChar == '5' || firstChar == '6' || firstChar == '7'
                || firstChar == '8' || firstChar == '9' || isDelimiter(firstChar) == true)
            return false;
        return true;
    }

    boolean isKeyword(String str) {
        if (str.compareTo("if") == 0 || str.compareTo("else") == 0
                || str.compareTo("while") == 0 || str.compareTo("do") == 0
                || str.compareTo("break") == 0 || str.compareTo("break") == 0
                || str.compareTo("continue") == 0 || str.compareTo("int") == 0
                || str.compareTo("double") == 0 || str.compareTo("float") == 0
                || str.compareTo("return") == 0 || str.compareTo("char") == 0
                || str.compareTo("case") == 0 || str.compareTo("char") == 0
                || str.compareTo("sizeof") == 0 || str.compareTo("long") == 0
                || str.compareTo("short") == 0 || str.compareTo("typedef") == 0
                || str.compareTo("switch") == 0 || str.compareTo("unsigned") == 0
                || str.compareTo("void") == 0 || str.compareTo("static") == 0
                || str.compareTo("struct") == 0 || str.compareTo("goto") == 0)
            return true;
        return false;
    }

    // If there is a non-digit char in the string, or a minus sign '-' with a index greater than 0,
    // it is not an integer.
    boolean isInteger(String str) {
        int i, len = str.length();
        if (len == 0) {
            return false;
        }
        for (i = 0; i < len; i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1'
                    && str.charAt(i) != '2' && str.charAt(i) != '3'
                    && str.charAt(i) != '4' && str.charAt(i) != '5'
                    && str.charAt(i) != '6' && str.charAt(i) != '7'
                    && str.charAt(i) != '8' && str.charAt(i) != '9'
                    || (str.charAt(i) == '-' && i > 0))
                return false;
        }
        return true;
    }

    // Real number must have a decimal separator.
    boolean isRealNumber(String str) {
        int i, len = str.length();
        boolean hasDecimal = false;
        if (len == 0)
            return false;
        for (i = 0; i < len; i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1'
                    && str.charAt(i) != '2' && str.charAt(i) != '3'
                    && str.charAt(i) != '4' && str.charAt(i) != '5'
                    && str.charAt(i) != '6' && str.charAt(i) != '7'
                    && str.charAt(i) != '8' && str.charAt(i) != '9'
                    && str.charAt(i) != '.'
                    || (str.charAt(i) == '-' && i > 0))
                return false;
            if (str.charAt(i) == '.')
                hasDecimal = true;
        }
        return hasDecimal;
    }

    // Whenever char at index right is not a delimiter, move right forward.
    // if char at index right is a delimiter,
    //    if the substring has only one char,
    //       if the substring is a operator, print.
    //       Move left and right forward.
    //    else
    //       Check the substring.
    //       left = right.
    void parse(String str) {
        int left = 0, right = 0;
        int len = str.length();
        // The substring we are currently examine is str[left, right].
        while (right < len && left <= right) {
            if (!isDelimiter(str.charAt(right))) {
                // If char at index right is not a delimiter, move right forward.
                right++;
                continue;
            }

            if (left == right) {
                // If the substring we are currently examine has a length of 1, move left and right forward.
                if (isOperator(str.charAt(right)))
                    // If the substring we are currently examine is a operator, print.
                    System.out.println(str.charAt(right) + " is an operator.");
                right += 1;
                left = right;
            } else {
                // Here subStr is str[left, right - 1].
                // str[right] is the delimiter.
                String subStr = str.substring(left, right); // Right is not inclusive.

                if (isKeyword(subStr))
                    System.out.println(subStr + " is a keyword");
                else if (isInteger(subStr))
                    System.out.println(subStr + " is an integer");
                else if (isRealNumber(subStr))
                    System.out.println(subStr + " is a real number");
                else if (validIdentifier(subStr) && !isDelimiter(str.charAt(right - 1)))
                    System.out.println(subStr + " is a valid identifier");
                else if (!validIdentifier(subStr) && !isDelimiter(str.charAt(right - 1)))
                    System.out.println(subStr + " is not a valid identifier");
                left = right;
            }
        }
    }
}
