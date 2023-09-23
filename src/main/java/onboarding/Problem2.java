package onboarding;

import java.util.ArrayList;
import java.util.List;

public class Problem2 {
    public static String solution(String cryptogram) {
        return getResultValue(cryptogram);
    }

    private static String getResultValue(String cryptogram){
        String result = deleteConsecutiveLetters(cryptogram);

        if(hasNoChar(result) || hasNoMoreConsecutiveDuplicate(result)){
            return result;
        }

        return getResultValue(result);
    }

    private static boolean hasNoChar(String cryptogram){
        return cryptogram.length() == 0;
    }

    private static boolean hasNoMoreConsecutiveDuplicate(String cryptogram){
        for(int i = 0; i < cryptogram.length() - 1; i++){
            if(cryptogram.charAt(i) == cryptogram.charAt(i + 1)){
                return false;
            }
        }

        return true;
    }

    private static String deleteConsecutiveLetters(String cryptogram){
        List<Integer> deleteIndex = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < cryptogram.length() - 1; i++){
            if(cryptogram.charAt(i) == cryptogram.charAt(i + 1)){
                deleteIndex.add(i);
                deleteIndex.add(i + 1);
            }
        }

        for(int i = 0; i < cryptogram.length(); i++){
            if(!deleteIndex.contains(i)){
                result.append(cryptogram.charAt(i));
            }
        }

        return result.toString();
    }
}
