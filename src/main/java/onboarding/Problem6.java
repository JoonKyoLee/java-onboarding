package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Problem6 {

    private static final int MAILINDEX = 0;
    private static final int NICKNAMEINDEX = 1;
    public static List<String> solution(List<List<String>> forms) {
        return getEmailWithDuplicateName(forms);
    }

    private static List<String> getEmailWithDuplicateName(List<List<String>> forms){
        List<String> mail = getMail(forms);
        List<String> name = getNickName(forms);
        List<String> mailWithDuplicateName = new ArrayList<>();

        List<String> splitNickname = getSplitNickname(name);
        List<String> duplicateName = ExtractDuplicateWord(splitNickname);

        for(int i = 0; i < name.size(); i++){
            if(hasDuplicateLetters(duplicateName, name.get(i))) {
                mailWithDuplicateName.add(mail.get(i));
            }
        }

        Collections.sort(mailWithDuplicateName);

        return mailWithDuplicateName;
    }

    private static List<String> getMail(List<List<String>> forms){
        List<String> mail = new ArrayList<>();

        for(List<String> form : forms){
            mail.add(form.get(MAILINDEX));
        }

        return mail;
    }

    private static List<String> getNickName(List<List<String>> forms){
        List<String> nickName = new ArrayList<>();

        for(List<String> form : forms){
            nickName.add(form.get(NICKNAMEINDEX));
        }

        return nickName;
    }

    private static List<String> getSplitNickname(List<String> nickname){
        List<String> splitNickname = new ArrayList<>();

        for(String name : nickname){
            if(!isOneWord(name)){
                splitNickname.addAll(setSplitNickname(name, splitNickname));
            }
        }

        return splitNickname;
    }

    private static boolean isOneWord(String nickName){
        return nickName.length() == 1;
    }

    private static List<String> setSplitNickname(String nickName, List<String> splitNickname){
        List<String> newLetter = new ArrayList<>();

        for(int i = 0; i < nickName.length() - 1; i++){
            String twoLetter = nickName.substring(i, i + 2);
            newLetter.add(twoLetter);
        }

        return newLetter;
    }

    private static List<String> ExtractDuplicateWord(List<String> splitWord){
        List <String> distinctList = splitWord.stream().
                distinct().collect(Collectors.toList());

        for(String word : distinctList){
            splitWord.remove(word);
        }

        return splitWord;
    }

    private static boolean hasDuplicateLetters(List<String> duplicateName, String nickname){
        for(String word : duplicateName){
            if(nickname.contains(word)){
                return true;
            }
        }
        return false;
    }
}
