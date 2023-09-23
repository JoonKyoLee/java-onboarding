package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {

    private static final int VISIT_SCORE = 1;
    private static final int FRIEND_SCORE = 10;
    private static final int FIRST_FRIEND_INDEX = 0;
    private static final int SECOND_FRIEND_INDEX = 1;
    private static final int RESULT_FRIEND_NUMBER = 5;

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        Map<String, List<String>> friendMap = setFriendMap(friends, user);

        return getRecommendationResult(friendMap, visitors, user);
    }

    private static Map<String, List<String>> setFriendMap(List<List<String>> friends, String userName){
        HashMap<String, List<String>> friendMap = new HashMap<>();

        for(List<String> friend : friends){
            String firstFriend = friend.get(FIRST_FRIEND_INDEX);
            String secondFriend = friend.get(SECOND_FRIEND_INDEX);

            friendMap.computeIfAbsent(firstFriend, k -> new ArrayList<>()).add(secondFriend);
            friendMap.computeIfAbsent(secondFriend, k -> new ArrayList<>()).add(firstFriend);
        }

        deleteUserAndNotExistedFriend(friendMap, userName);

        return friendMap;
    }

    private static void deleteUserAndNotExistedFriend(Map<String, List<String>> friendMap, String userName){
        List<String> allName = new ArrayList<>(friendMap.keySet());
        List<String> deleteList = new ArrayList<>(friendMap.get(userName));

        for(String name : allName){
            if(!deleteList.contains(name)){
                friendMap.remove(name);
            }
        }
    }

    private static List<String> getRecommendationResult(Map<String, List<String>> friendMap, List<String> visitor, String userName){
        List<String> friends = setRecommendationResult(friendMap, visitor, userName);

        return getFiveUsers(friends);
    }

    private static List<String> setRecommendationResult(Map<String, List<String>> friendMap, List<String> visitor, String userName){
        Map<String, List<Integer>> recommendFriend = setRecommendationScore(friendMap, visitor, userName);
        Map<String, Integer> recommendationScore = calculateFriendScore(recommendFriend);

        return getSortedList(recommendationScore);
    }

    private static List<String> getSortedList(Map<String, Integer> recommendationScore){
        return recommendationScore.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static List<String> getFiveUsers(List<String> friendList){
        return friendList.stream()
                .limit(RESULT_FRIEND_NUMBER)
                .collect(Collectors.toList());
    }
    private static Map<String, Integer> calculateFriendScore(Map<String, List<Integer>> recommendFriend){
        Map<String, Integer> recommendationScore = new HashMap<>();
        List<String> name = new ArrayList<>(recommendFriend.keySet());
        int sumOfScore;

        for(String key : name){
            List<Integer> scores = recommendFriend.get(key);

            sumOfScore = 0;

            for(int score : scores){
                sumOfScore += score;
            }

            recommendationScore.put(key, sumOfScore);
        }

        return recommendationScore;
    }

    private static Map<String, List<Integer>> setRecommendationScore(Map<String, List<String>> friendMap, List<String> visitor, String userName){
        Map<String, List<Integer>> recommendFriend = new HashMap<>();

        setVisitorScore(recommendFriend, friendMap, visitor, userName);
        setFriendOfFriendScore(recommendFriend, friendMap, userName);

        return recommendFriend;
    }

    private static void setVisitorScore(Map<String, List<Integer>> recommendFriend, Map<String, List<String>> friendMap, List<String> visitors, String userName){
        List<String> friend = new ArrayList<>(friendMap.keySet());

        for(String name : visitors){
            if(!userName.equals(name) && !friend.contains(name)){
                recommendFriend.computeIfAbsent(name, k -> new ArrayList<>()).add(VISIT_SCORE);
            }
        }
    }

    private static void setFriendOfFriendScore(Map<String, List<Integer>> recommendFriend, Map<String, List<String>> friendMap, String userName){
        List<String> allName = new ArrayList<>();

        for(List<String> names : friendMap.values()){
            allName.addAll(names);
        }

        for(String name : allName){
            if(!userName.equals(name)){
                recommendFriend.computeIfAbsent(name, k -> new ArrayList<>()).add(FRIEND_SCORE);
            }
        }
    }
}
