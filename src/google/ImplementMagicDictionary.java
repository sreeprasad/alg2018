package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 676. Implement Magic Dictionary
 * Medium
 * Implement a magic directory with buildDict, and search methods.
 *
 * For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
 *
 * For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.
 *
 * Example 1:
 * Input: buildDict(["hello", "leetcode"]), Output: Null
 * Input: search("hello"), Output: False
 * Input: search("hhllo"), Output: True
 * Input: search("hell"), Output: False
 * Input: search("leetcoded"), Output: False
 * Note:
 * You may assume that all the inputs are consist of lowercase letters a-z.
 * For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
 * Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */

public class ImplementMagicDictionary {

	private Map<String, List<int[]>> map;

	/** Initialize your data structure here. */
	public ImplementMagicDictionary() {
		map = new HashMap<>();
	}

	/** Build a dictionary through a list of words */
	public void buildDict(String[] dict) {
		for (String word : dict) {
			for (int i = 0; i < word.length(); i++) {
				String key = word.substring(0, i) + word.substring(i + 1);
				map.putIfAbsent(key, new ArrayList<>());
				map.get(key).add(new int[]{i, word.charAt(i)});
			}
		}
	}

	/** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
	public boolean search(String word) {
		for (int i = 0; i < word.length(); i++) {
			String modified = word.substring(0, i) + word.substring(i + 1);
			List<int[]> list = map.get(modified);
			if (list != null) {
				for (int[] pair : list) {
					if (pair[0] == i && pair[1] != word.charAt(i)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		ImplementMagicDictionary imd = new ImplementMagicDictionary();
		imd.buildDict(new String[]{"hello","leetcode"});
		System.out.println(imd.search("hello")); // false
		System.out.println(imd.search("hhllo")); // true
		System.out.println(imd.search("hell")); // false
		System.out.println(imd.search("leetcoded")); // false;
	}
}



/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */