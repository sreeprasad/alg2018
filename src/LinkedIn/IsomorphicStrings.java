package LinkedIn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 205. Isomorphic Strings

 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 */
public class IsomorphicStrings {
	public boolean isIsomorphic(String s, String t) {

		int slen = s.length();
		int tlen = t.length();
		if (slen != tlen) return false;
		if (slen == 0 || slen == 1) return true;

		Map<Character, Character> map = new HashMap<>();
		Set<Character> set = new HashSet<>();
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();

		for (int i = 0; i < slen; i++) {
			char c1 = sArr[i];
			char c2 = tArr[i];
			if (!map.containsKey(c1)) {
				if (!set.contains(c2)) {
					map.put(c1, c2);
					set.add(c2);
				} else {
					return false;
				}
			} else {
				if (map.get(c1) != c2) {
					return false;
				}
			}
		}
		return true;
	}
}
