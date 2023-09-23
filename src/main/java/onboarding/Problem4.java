package onboarding;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;

public class Problem4 {
    public static String solution(String word) {
        return getFrogWord(word);
    }

    private static String getFrogWord(String word) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            result.append(getFrogLetter(word.charAt(i)));
        }

        return result.toString();
    }

    private static char getFrogLetter(char letter) {
        if (isUpperAlphabet(letter)) {
            return convertUpperLetter(letter);
        }

        if (isLowerAlphabet(letter)) {
            return convertLowerLetter(letter);
        }

        return letter;
    }

    private static boolean isUpperAlphabet(char letter) {
        return Character.isUpperCase(letter);
    }

    private static boolean isLowerAlphabet(char letter) {
        return Character.isLowerCase(letter);
    }

    private static char convertUpperLetter(char letter) {
        return (char) ('A' + ('Z' - letter));
    }

    private static char convertLowerLetter(char letter) {
        return (char) ('a' + ('z' - letter));
    }
}