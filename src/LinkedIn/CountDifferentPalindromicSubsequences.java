package LinkedIn;

/**
 * 730. Count Different Palindromic Subsequences
 *
 * Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.
 *
 * A subsequence of a string S is obtained by deleting 0 or more characters from S.
 *
 * A sequence is palindromic if it is equal to the sequence reversed.
 *
 * Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.
 *
 * Example 1:
 * Input:
 * S = 'bccb'
 * Output: 6
 * Explanation:
 * The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 * Note that 'bcb' is counted only once, even though it occurs twice.
 * Example 2:
 * Input:
 * S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
 * Output: 104860361
 * Explanation:
 * There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
 * Note:
 *
 * The length of S will be in the range [1, 1000].
 * Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
 *
 * Reference - https://www.youtube.com/watch?v=UjiFFYU3EKM
 *
 */
public class CountDifferentPalindromicSubsequences {
	public static int countPalindromicSubsequences(String S) {
		final int mod = 1000000007;
		int len = S.length();
		if (len == 0) return 0;
		int[][] dp = new int[len + 1][len + 1];
		for (int i = 0; i < len; i++) dp[i][i] = 1;
		for (int k = 1; k < len; k++) { // length of substring start from 1
			for (int i = 0; i + k < len; i++) { // start index of subsequence
				int j = i + k; // end index of subsequence
				if (S.charAt(i) == S.charAt(j)) {
					int l = i + 1;
					int r = j - 1;
					char c = S.charAt(i);
					while (l <= r && S.charAt(l) != c) l++;
					while (l <= r && S.charAt(r) != c) r--;

					if (l > r) {
						dp[i][j] = (2 * dp[i+1][j-1] % mod + 2) % mod;
					} else if (l == r) {
						dp[i][j] = (2 * dp[i+1][j-1] % mod + 1) % mod;
					} else {
						dp[i][j] = (2 * dp[i+1][j-1] % mod - dp[l+1][r-1] + mod) % mod; // +mod to avoid negative
					}
				}
				else dp[i][j] = ((dp[i+1][j] + dp[i][j-1]) % mod - dp[i+1][j-1] + mod) % mod;
			}
		}
		return dp[0][len-1] % mod;
	}
	public static void main(String[] args) {
		System.out.println(countPalindromicSubsequences("bccb")); // 6
		System.out.println(countPalindromicSubsequences("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba")); // 104860361
	}
}
