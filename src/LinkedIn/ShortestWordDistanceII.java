package LinkedIn;

/**
 * 244. Shortest Word Distance II
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.
 *
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
public class ShortestWordDistanceII {
	private Map<String, List<Integer>> map;
	public ShortestWordDistanceII(String[] words) {
		map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			map.putIfAbsent(words[i], new ArrayList<>());
			map.get(words[i]).add(i);
		}
	}

	public int shortest(String word1, String word2) {
		List<Integer> list1 = map.get(word1);
		List<Integer> list2 = map.get(word2);
		int result = Integer.MAX_VALUE;
		for(int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
			int index1 = list1.get(i), index2 = list2.get(j);
			if(index1 < index2) {
				result = Math.min(result, index2 - index1);
				i++;
			} else {
				result = Math.min(result, index1 - index2);
				j++;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		ShortestWordDistanceII obj = new ShortestWordDistanceII(new String[]{"practice", "makes", "perfect", "coding", "makes"});
		System.out.println(obj.shortest("coding","practice"));
		ShortestWordDistanceII obj2 = new ShortestWordDistanceII(new String[]{"a","a","b","b"});
		System.out.println(obj2.shortest("a","b"));
	}
}
