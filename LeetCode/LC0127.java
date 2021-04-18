package LeetCode;

import java.util.*;
//Word Ladder I

// 双向更快，互相check，能缩小每次的搜索范围
// 双向TC = O(v+e) = O(n)，worst case; 只是在constant factor优化，可能系数会因为双向降低
public class LC0127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // cc
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0
            || beginWord.length() != endWord.length()) return -1;
        Set<String> set = new HashSet<>(wordList);
        // for (String word : wordList) {
        //     set.add(word);
        // }
        if (!set.contains(endWord)) return 0;

        set.remove(beginWord); //确保一开始没有beginWord，相当于已经对dummy做了一次offer+remove
        // 否则会再绕一圈回来，多走2步

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int minLen = 1; //初始就算一个了

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> nexts = convert(cur, set);
                for (String next : nexts)  {
                    if (next.equals(endWord)) return minLen + 1;//再加1，直接加到结果中
                    queue.offer(next);
                    set.remove(next);//删除可以查重，或者用一个HashSet存已经遍历过的String element
                }
            }
            minLen++;
        }

        return 0; //审题，返回0
    }
    //一个一个char查重，在词长不长的情况下，TC不如主动改变某个字符再check快
    private List<String> convert(String cur, Set<String> set) { //单词长度很长时，才会使得TC特别大
        List<String> nexts = new ArrayList<>();
        char[] chars = cur.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c0 = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                chars[i] = c;
                String newString = new String(chars); // new String转换不会出错，sb.toString()适用于string builder
                // 也可以用 = String.valueOf(chars);
                if (c != c0 && set.contains(newString)) { // check newString要避免endWord startWord不会看到
                    nexts.add(newString);
                }

            }
            chars[i] = c0;
        }
        return nexts;
    }
//    private List<String> convert(String cur, Set<String> wordList) {
//        List<String> nexts = new ArrayList<>();
//        for (String word : wordList) {
//            int diff = 0; // difference between two words
//            for (int i = 0; i < cur.length(); i++) {
//                if (cur.charAt(i) != word.charAt(i)) diff++;
//            }
//            if (diff == 1) nexts.add(word);
//        }
//        return nexts;
//    }
    public static void main(String args[]) {
        String beginWord = new String("hit");
        String endWord = new String("cog");
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

        LC0127 sol = new LC0127();
        int res = sol.ladderLength(beginWord, endWord, wordList);
        System.out.print(res);
    }
}
