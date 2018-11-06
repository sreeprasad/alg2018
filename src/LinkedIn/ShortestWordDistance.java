package LinkedIn;

/**
 * 243. Shortest Word Distance
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 */
public class ShortestWordDistance {
	public int shortestDistance(String[] words, String word1, String word2) {
		int lastPos1 = -1;
		int lastPos2 = -1;
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < words.length; i++) {
			String cur = words[i];
			if (cur.equals(word1)) {
				if (lastPos2 != -1) result = Math.min(result, i - lastPos2);
				lastPos1 = i;
			}
			if (cur.equals(word2)) {
				if (lastPos1 != -1) result = Math.min(result, i - lastPos1);
				lastPos2 = i;
			}
		}
		return result;
	}
}
