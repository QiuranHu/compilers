package qiuran.LexicalAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class LexicalAnalyzer {
    public static void main(String[] args) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        try {
            URL url = LexicalAnalyzer.class.getResource("input.txt");
            File file = new File(url.getPath());
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lexicalAnalyzer.analyze(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file doesn't exist.");
            e.printStackTrace();
        }
    }

    public void analyze(String str) {
        DFA dfa = new DFA();
        int currentIndex = 0;
        try {
            while (true) {
                while (str.charAt(currentIndex) == ' ')
                    currentIndex += 1;
                Token scanResult = dfa.scan(str, currentIndex);
                currentIndex = scanResult.getEndIndex();
                System.out.println(scanResult.getResult() + "   Category:" + scanResult.getCategory().toString()
                + ", startIndex: " + scanResult.getStartIndex());
                if (currentIndex == str.length())
                    break;
            }
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }
}
