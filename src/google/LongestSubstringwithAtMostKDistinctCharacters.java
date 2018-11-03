package google;

import java.util.HashMap;
import java.util.Map;

/**
 * 340. Longest Substring with At Most K Distinct Characters
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Example 1:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 */
public class LongestSubstringwithAtMostKDistinctCharacters {
	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		int[] countArr = new int[256];
		int numOfDisChar = 0;
		int result = 0;
		int len = 0;
		int left = 0;
		for (int i = 0; i < s.length(); i++) {
			if (countArr[s.charAt(i)]++ == 0) {
				numOfDisChar++;
			}
			if (numOfDisChar > k) { // get rid of all duplicate chars of the additional char
				if (--countArr[s.charAt(left)] == 0) {
					numOfDisChar--;
				}
				left++;
			}
			result = Math.max(result, i - left + 1);
		}
		return result;
	}
	// another solution with map, similar thoughts
	public static int lengthOfLongestSubstringKDistinctII(String s, int k) {
		Map<Character, Integer> map = new HashMap<>();
		int left = 0;
		int best = 0;
		for(int i = 0; i < s.length(); i++) {
			// character at the right pointer
			char c = s.charAt(i);
			map.put(c, map.getOrDefault(c, 0) + 1);
			// make sure map size is valid, no need to check left pointer less than s.length()
			while (map.size() > k) {
				char leftChar = s.charAt(left);
				map.put(leftChar, map.get(leftChar) - 1);
				if (map.get(leftChar) == 0) {
					map.remove(leftChar);
				}
				left++;
			}
			best = Math.max(best, i - left + 1);
		}
		return best;
	}
	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstringKDistinct("abcc", 2));
		System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));

		System.out.println(lengthOfLongestSubstringKDistinctII("abcc", 2));
		System.out.println(lengthOfLongestSubstringKDistinctII("eceba", 2));
	}
}
