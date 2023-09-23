package onboarding;

import java.util.ArrayList;
import java.util.List;

public class Problem5 {

    private static final int[] CURRENCIES = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
    public static List<Integer> solution(int money) {
        return getCurrencyCount(money);
    }

    public static List<Integer> getCurrencyCount(int money){
        List<Integer> currencyCount = new ArrayList<>();

        for(int currency : CURRENCIES){
            currencyCount.add(money / currency);
            money %= currency;
        }

        return currencyCount;
    }
}
