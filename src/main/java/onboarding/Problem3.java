package onboarding;
import java.util.stream.IntStream;

public class Problem3 {

    private static final int THREE = 3;
    private static final int SIX = 6;
    private static final int NINE = 9;

    public static int solution(int number) {
        return IntStream.rangeClosed(1, number)
                .map(Problem3::countClap)
                .sum();
    }

    private static int countClap(int number){
        int clapCount = 0;

        while(number != 0){
            if(hasThreeSixNine(number % 10)){
                clapCount++;
            }
            number /= 10;
        }

        return clapCount;
    }

    private static boolean hasThreeSixNine(int digit){
        return digit == THREE || digit == SIX || digit == NINE;
    }
}
