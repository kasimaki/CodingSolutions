package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC0472 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if(words == null || words.length <= 1) return new ArrayList<>();

        //List<Character> beginCharList = new ArrayList<>();
        HashMap<Character, List<String>> map = new HashMap<>();

        for (String word : words) {
            char beginChar = word.charAt(0);
            List<String> listOfWordsBeginWithChar = map.getOrDefault(beginChar, new ArrayList<>());
            listOfWordsBeginWithChar.add(word);
            map.put(beginChar, listOfWordsBeginWithChar);
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            findConcatenatedWordsDFS(res, map, words[i], 0);
        }
        return res;
    }

    private void findConcatenatedWordsDFS(List<String> res,  HashMap<Character, List<String>> map, String curWord, int subIdx) {
        if (curWord.length() == subIdx) {
            res.add(curWord);
            return;
        }

        char beginChar = curWord.charAt(subIdx);
        List<String> listOfWordsBeginWithChar = map.get(beginChar);
        if (listOfWordsBeginWithChar == null) return;
        if (subIdx == 0 && listOfWordsBeginWithChar.size() == 1) return;

        for (String word : listOfWordsBeginWithChar) {
            if (subIdx + word.length() > curWord.length()) continue;
            if (subIdx == 0 && subIdx + word.length() == curWord.length()) continue;
            if (curWord.substring(subIdx, subIdx + word.length()).equals(word)) {
                findConcatenatedWordsDFS(res, map, curWord, subIdx + word.length());
            }
        }
    }

    public static void main(String[] args) {
        String[] dict = new String[] {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        LC0472 sol = new LC0472();
        List<String> res = sol.findAllConcatenatedWordsInADict(dict);
        System.out.println(res);
    }
}
