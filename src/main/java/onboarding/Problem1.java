package onboarding;

import java.util.List;


class Problem1 {
    private static final int FIRST_PAGE_NUMBER = 1;
    private static final int LAST_PAGE_NUMBER = 400;
    private static final int LEFT_PAGE = 0;
    private static final int RIGHT_PAGE = 1;

    private static final int POBI_WINS = 1;
    private static final int CRONG_WINS = 2;
    private static final int DRAW = 0;
    private static final int EXCEPTION = -1;

    public static int solution(List<Integer> pobi, List<Integer> crong) {

        if(!isValidPages(pobi) || !isValidPages(crong)){
            return EXCEPTION;
        }

        int scoreOfPobi = getMaxValue(pobi);
        int scoreOfCrong = getMaxValue(crong);

        if(scoreOfPobi > scoreOfCrong){
            return POBI_WINS;
        }

        if(scoreOfPobi < scoreOfCrong){
            return CRONG_WINS;
        }

        return DRAW;
    }

    private static boolean isValidPages(List<Integer> pages){
        int leftPage = pages.get(LEFT_PAGE);
        int rightPage = pages.get(RIGHT_PAGE);

        if(!isContinousPages(leftPage, rightPage)){
            return false;
        }

        if(!isLeftPage(leftPage) || !isRightPage(rightPage)){
            return false;
        }

        return true;
    }

    private static boolean isContinousPages(int leftPage, int rightPage){
        return leftPage + 1 == rightPage;
    }
    private static boolean isLeftPage(int leftPage){
        return leftPage % 2 == 1 && leftPage >= FIRST_PAGE_NUMBER && leftPage <= LAST_PAGE_NUMBER;
    }

    private static boolean isRightPage(int rightPage){
        return rightPage % 2 == 0 && rightPage >= FIRST_PAGE_NUMBER && rightPage <= LAST_PAGE_NUMBER;
    }


    private static int getMaxValue(List<Integer> pages){
        int leftPage = pages.get(LEFT_PAGE);
        int rightPage = pages.get(RIGHT_PAGE);

        int leftPageMaxValue = Math.max(getSumOfPage(leftPage), getMultipleOfPage(leftPage));
        int rightPageMaxValue = Math.max(getSumOfPage(rightPage), getMultipleOfPage(rightPage));

        return Math.max(leftPageMaxValue, rightPageMaxValue);
    }

    private static int getSumOfPage(int page){
        int sumValue = 0;

        while(page != 0){
            sumValue += page % 10;
            page /= 10;
        }

        return sumValue;
    }

    private static int getMultipleOfPage(int page){
        int multipleValue = 1;

        while(page != 0){
            multipleValue *= page % 10;
            page /= 10;
        }

        return multipleValue;
    }
}