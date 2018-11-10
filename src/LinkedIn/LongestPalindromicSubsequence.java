package LinkedIn;

/**
 * 516. Longest Palindromic Subsequence
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 *
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 *
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubsequence {

	// recursion
	public static int longestPalindromeSubseqI(String s) {
		if (s == null || s.length() == 0) return 0;
		if (s.length() == 1) return 1;
		if (s.charAt(0) == s.charAt(s.length() - 1))
			return 2 + longestPalindromeSubseqI(s.substring(1, s.length() - 1));
		else
			return Math.max(longestPalindromeSubseqI(s.substring(0, s.length() - 1)),
					longestPalindromeSubseqI(s.substring(1, s.length())));
	}

	// longest common sub-sequence of reversed string
	public static int longestPalindromeSubseqII(String s) {
		if (s == null || s.length() == 0) return 0;
		if (s.length() == 1) return 1;
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		String reversedStr = sb.reverse().toString();
		int[][] dp = new int[s.length() + 1][s.length() + 1];

		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(i) == reversedStr.charAt(j)) {
					dp[i+1][j+1] = dp[i][j] + 1;
				} else {
					dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j + 1]);
				}
			}
		}
		return dp[s.length()][s.length()];
	}

	// dp
	public static int longestPalindromeSubseqIII(String s) {
		if (s == null || s.length() == 0) return 0;
		int len = s.length();
		int[][] dp = new int[len][len];

		// substring.length == 1
		for (int i = 0; i < len; i++) {
			dp[i][i] = 1;
		}

		// substring.length > 1
		for (int l = 2; l <= len; l++) {
			for (int i = 0; i <= len - l; i++) {
				int j = i + l - 1; // end index of substring
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = 2 + dp[i + 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[0][len - 1];
	}

	public static void main(String[] args) {
		System.out.println(longestPalindromeSubseqI("bbbab"));
		System.out.println(longestPalindromeSubseqII("bbbab"));
		System.out.println(longestPalindromeSubseqIII("bbbab"));
	}
}
