package qiuran.LexicalAnalyzer;

public class DFA {
    public Token scan(String str, int startIndex) {
        int status = 0;
        boolean shouldStop = false;
        int currentIndex = startIndex;
        Token.Category category = null;
        while (true) {
            char currentChar = str.charAt(currentIndex);
            switch (status) {
                case 0:
                    if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/'
                            || currentChar == ';' || currentChar == ',' || currentChar == '(' || currentChar == ')'
                            || currentChar == '{' || currentChar == '}'
                    ||currentChar == '[' || currentChar == ']')
                        status = 1;
                    else if (currentChar == '>' || currentChar == '<' || currentChar == '=')
                        status = 2;
                    else if (((int) currentChar >= (int) 'a' && (int) currentChar <= (int) 'z')
                            || (((int) currentChar >= (int) 'A' && (int) currentChar <= (int) 'Z')
                            || currentChar == '_'))
                        status = 4;
                    else if ((int) currentChar >= (int) '0' && (int) currentChar <= (int) '9') {
                        category = Token.Category.NUMBER;
                        status = 5;
                    }
                    else
                        shouldStop = true;
                    break;
                case 1:
                    shouldStop = true;
                    break;
                case 2:
                    if (currentChar == '=')
                        status = 3;
                    else
                        shouldStop = true;
                    break;
                case 4:
                    if (((int) currentChar < (int) 'a' || (int) currentChar > (int) 'z')
                            && (((int) currentChar < (int) 'A' || (int) currentChar > (int) 'Z')
                            && (((int) currentChar < (int) '0' || (int) currentChar > (int) '9')
                            && currentChar != '_'))) {
                        shouldStop = true;
                    }
                    break;
                case 5:
                    if ((int) currentChar < (int) '0' || (int) currentChar > (int) '9') {
                        shouldStop = true;
                    }
            }
            if (shouldStop)
                break;
            currentIndex += 1;
            if (currentIndex == str.length())
                break;
        }
        if (currentIndex == startIndex)
            throw new Error("Error encountered when processing '" + str.charAt(currentIndex) + "' at index " + currentIndex);
        return new Token(str.substring(startIndex, currentIndex), startIndex, currentIndex, category);
    }
}
