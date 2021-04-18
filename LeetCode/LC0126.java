package LeetCode;
//Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
//from beginWord to endWord, such that:
//
//        Only one letter can be changed at a time
//        Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
//        Note:
//
//        Return an empty list if there is no such transformation sequence.
//        All words have the same length.
//        All words contain only lowercase alphabetic characters.
//        You may assume no duplicates in the word list.
//        You may assume beginWord and endWord are non-empty and are not the same.

// Brute Force 完全DFS，再逐个找最短

// 改进2点，① 减少不会到达end的路径(反向存hashmap) ② 减少非最短路径的遍历(BFS)

// 先BFS获取最短距离，再DFS反向搜索，因为可能会漏掉结果
// BFS时需要防止同层遍历

// TC分析
// v + (v + 25k * v) + (v + e)  + levels * 路径数  = O(v)
// set     map建立        dfs       deep copy
// SC分析
// v +    v   +   v  +       list   + res_list    = O(v)
// set  queue    map   dfs(deep copy, worst case --> v)

import java.util.*;

public class LC0126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0
                || beginWord.length() != endWord.length()) throw new IllegalArgumentException("Please check input");

        Set<String> set = new HashSet<>(wordList);
        set.remove(beginWord);

        List<List<String>> result = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        boolean pathExist = false;

        while (!queue.isEmpty()) {
            Set<String> visitedCurLevel = new HashSet<>();//当前层的hashset，用来区分neighbor是当前层的下层。
            //如果用同一个Hashset来区分层之间和层内，会导致无法确定是否是需要存储的新edge
            int size = queue.size();
            while(size-- > 0) {
                String cur = queue.poll();
                assert cur != null;
                char[] chars = cur.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char c0  = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c0 == c) continue;
                        chars[i] = c;
                        String newString = String.valueOf(chars);
                        if (set.contains(newString)) {
                            if (newString.equals(endWord)) pathExist = true; //当前层可以走通
                            // if (!map.containsKey(newString)) map.put(newString, new ArrayList<>()); //也可以先放一个空list
                            if (visitedCurLevel.add(newString)) { //可以直接用set.add()来check并添加
//                                List<String> prevWord = new ArrayList<>();
//                                prevWord.add(cur);
//                                map.put(newString, prevWord);
//                                visitedCurLevel.add(newString);//遍历过了，在这层最后要删除
                                queue.offer(newString);//别忘了要加进queue
                            } //else {
//                                List<String> prevWord = map.get(newString);
//                                prevWord.add(cur);
//                                map.put(newString, prevWord);
                            List<String> prevWord = map.getOrDefault(newString, new ArrayList<>()); //getOrDefault实用
                            // getOrDefault如果不存在，map里还是没有该key的，不更新Map
                            prevWord.add(cur);
                            map.put(newString, prevWord);
//                            }
                        }
                    }
                    chars[i] = c0;
                }
            }
            set.removeAll(visitedCurLevel); //去掉遍历过的，便于下一层不会遍历在queue中的元素
            //用map将Graph的连接关系确定下来，从而避免交叉情况造成的对应关系不确定
            //map存list可以允许多个上一层的v对应同一个下一层v
            if(pathExist) {
                List<String> path = new ArrayList<>();
                path.add(endWord);
                dfs(map, endWord, beginWord, path, result);

                return result;
            }
        }
        return result;
    }
    private void dfs(Map<String, List<String>> map, String cur, String end,
                     List<String> path, List<List<String>> result) {
        if (cur.equals(end)) {
            List<String> oneResult = new ArrayList<>(path);
            result.add(oneResult);
            return;
        }
        List<String> nextWord = map.get(cur);
        for (String next : nextWord) {
            path.add(0, next); //在开头加，才能把顺序返回来
            dfs(map, next, end, path, result);
            path.remove(0);
        }

    }

    public static void main(String args[]) {
        String beginWord = new String("hit");
        String endWord = new String("cog");
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

        LC0126 sol = new LC0126();
        List<List<String>> res = sol.findLadders(beginWord, endWord, wordList);
        System.out.print(res);
    }
}
