package Airbnb;

import java.util.*;

/**
 * LeetCode 336. Palindrome Pairs
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 *
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 *
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 *
 * more than one approach?
 */
public class PalindromePairs {
    public static List<List<Integer>> palindromePairs(String[] words) {

        // 1st solution is brute force, use O(n2) time to loop all combinations of two strings, check if palindrome with O(k), k is avg length of words
        // 2nd solution is with a wordIndexMap, use O(n) time to loop all strings, use O(k) time to generate & check possible matches, with O(n) space

        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> wordIndexMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordIndexMap.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            // a bcd
            // ab cd
            // abc d
            // either left part or right part will be compared to the other string
            int left = 0;
            int right = 1;
            while (left < right) {
                // lookup reverse of substring(left, right) in map
                // either left or right devides words[i] into 2 parts
                Integer index = wordIndexMap.get(new StringBuilder(words[i].substring(left, right)).reverse().toString());

                int leftIndexOfLeftOverStr = left == 0 ? right : 0;
                int rightIndexOfLeftOverStr = left == 0 ? words[i].length() : left;

                if (index != null && index != i && isPalindrome(words[i].substring(leftIndexOfLeftOverStr, rightIndexOfLeftOverStr))) {
                    result.add(left == 0 ? Arrays.asList(i, index) : Arrays.asList(index, i));
                }

                // once right pointer is right-most, move left pointer
                if (right < words[i].length()) {
                    right++;
                } else {
                    left++;
                }
            }
        }
        return result;
    }
    private static boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
        System.out.println(palindromePairs(new String[]{"bat","tab","cat"}));
    }
}
