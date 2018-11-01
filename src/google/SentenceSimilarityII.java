package google;

import java.util.HashMap;
import java.util.Map;

/**
 * 737. Sentence Similarity II
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.
 *
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 *
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.
 *
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
 *
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.
 *
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 *
 * Note:
 *
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class SentenceSimilarityII {
	public static boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
		// Union
		Map<String, String> map = new HashMap<>();
		for (String[] p : pairs) {
			String p0 = findParent(p[0], map);
			String p1 = findParent(p[1], map);
			if (!p0.equals(p1)) {
				map.put(p0, p1);
			}
		}
		int len1 = words1.length;
		int len2 = words2.length;
		if (len1 != len2) {
			return false;
		}
		for (int i = 0; i < len1; i++) {
			String p0 = findParent(words1[i], map);
			String p1 = findParent(words2[i], map);
			if (!p0.equals(p1)) {
				return false;
			}
		}
		return true;
	}
	// Find
	private static String findParent(String s, Map<String, String> map) {
		while (map.containsKey(s)) {
			s = map.get(s);
		}
		return s;
	}
	public static void main(String[] args) {
		System.out.println(areSentencesSimilarTwo(new String[]{"great","acting","skills"}, new String[]{"fine","drama","talent"},
				new String[][]{{"great","fine"},{"drama","acting"},{"skills","talent"}}));
	}
}
