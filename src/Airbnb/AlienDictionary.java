package Airbnb;


import java.util.*;

/**
 * 269. Alien Dictionary
 *
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 *
 * Example 1:
 *
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * Output: "wertf"
 * Example 2:
 *
 * Input:
 * [
 *   "z",
 *   "x"
 * ]
 *
 * Output: "zx"
 * Example 3:
 *
 * Input:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 *
 * Output: ""
 *
 * Explanation: The order is invalid, so return "".
 * Note:
 *
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {
    public static String alienOrder(String[] words) {
        // check corner cases
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0];
        }

        Map<Character, Integer> degree = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                degree.put(words[i].charAt(j), 0);
            }
        }

        // find all the pairs, e.g. w->e, e->r
        Map<Character, Set<Character>> map = new HashMap<>();

        int len = words.length;
        for (int i = 1; i < len; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            int shortLen = Math.min(word1.length(), word2.length());
            for (int j = 0; j < shortLen; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    if (map.containsKey(c1)) {
                        if (!map.get(c1).contains(c2)) {
                            map.get(c1).add(c2);
                            degree.put(c2, degree.get(c2) + 1);
                        }
                    } else {
                        Set<Character> set = new HashSet<>();
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        // topological sort
        Queue<Character> queue = new LinkedList<>();
        for (Character c : degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.offer(c);
            }
        }

        String result = "";
        while (!queue.isEmpty()) {
            Character ch = queue.poll();
            result += ch;
            if (map.containsKey(ch)) {
                for (Character child : map.get(ch)) {
                    degree.put(child, degree.get(child) - 1);
                    if (degree.get(child) == 0) {
                        queue.offer(child);
                    }
                }
            }
        }

        // invalid case
        if (result.length() != degree.size()) {
            return "";
        }
        return result;
    }

    public static void main(String[] args) {
        String[] input1 = {"ri","xz","qxf","jhsguaw","dztqrbwbm","dhdqfb","jdv","fcgfsilnb","ooby"};
        String[] input2 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(alienOrder(input1));
        System.out.println(alienOrder(input2));
    }
}
