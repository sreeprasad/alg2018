package google;

/**
 * 727. Minimum Window Subsequence
 *
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 *
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 *
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 */
public class MinimumWindowSubsequence {
	public static String minWindow(String S, String T) {
		String result = "";
		int minLen = Integer.MAX_VALUE;
		// search from each char that equals T.charAt(0)
		for (int i = 0; i < S.length() - T.length(); i++) {
			while (i < S.length() && S.charAt(i) != T.charAt(0)) {
				i++;
			}
			int len = -1;
			// find cur substring length and update minLen
			for (int indexS = i, indexT = 0; indexS < S.length() && indexT < T.length();) {
				if (S.charAt(indexS) == T.charAt(indexT)) {
					indexS++;
					indexT++;
					if (indexT == T.length()) {
						len = indexS - i;
						if (len > 0 && len < minLen) {
							minLen = len;
							result = S.substring(i, indexS);
						}
					}
				} else {
					indexS++;
				}
			}
		}
		return result;
//		String output = "";
//		int minLen = 20001;
//		for (int i = 0; i <= S.length() - T.length(); i++) {
//			while (i < S.length() && S.charAt(i) != T.charAt(0)) {
//				i++;
//			}
//			int l = find(S.substring(i, Math.min(i + minLen, S.length())), T);
//			if (l != -1 && l < minLen) {
//				minLen = l;
//				output = S.substring(i, i + l);
//			}
//		}
//		return output;
//	}
//
//	private static int find(String S, String T) {
//		for (int i = 0, j = 0; i < S.length() && j < T.length();) {
//			if (S.charAt(i) == T.charAt(j)) {
//				i++;
//				j++;
//				if (j == T.length()) {
//					return i;
//				}
//			} else {
//				i++;
//			}
//		}
//		return -1;
	}
	public static void main(String[] args) {
		System.out.println(minWindow("aabcd", "abcd"));
	}
}
