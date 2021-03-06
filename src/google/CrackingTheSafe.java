package google;

import java.util.HashSet;
import java.util.Set;

/**
 * 753. Cracking the Safe
 * There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
 *
 * You can keep inputting the password, the password will automatically be matched against the last n digits entered.
 *
 * For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.
 *
 * Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.
 *
 * Example 1:
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 * Note:
 * n will be in the range [1, 4].
 * k will be in the range [1, 10].
 * k^n will be at most 4096.
 */
public class CrackingTheSafe {

	// De Bruijn Sequences
	// https://en.wikipedia.org/wiki/De_Bruijn_sequence
	// https://www.youtube.com/watch?time_continue=397&v=iPLQgXUiU14
	public String crackSafe(int n, int k) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) { // first password
			sb.append('0');
		}
		Set<String> visited = new HashSet<>();
		visited.add(sb.toString());

		dfs(sb, (int) (Math.pow(k, n)), visited, n, k);

		return sb.toString();
	}

	private boolean dfs(StringBuilder sb, int totalCount, Set<String> visited, int n, int k) {
		if (visited.size() == totalCount) { // all possibilities are looped
			return true;
		}
		String prev = sb.substring(sb.length() - n + 1);
		for (int i = 0; i < k; i++) {
			String next = prev + i;
			if (!visited.contains(next)) {
				sb.append(i);
				visited.add(next);
				if (dfs(sb, totalCount, visited, n, k)) {
					return true;
				} else {
					sb.deleteCharAt(sb.length() - 1);
					visited.remove(next);
				}
			}
		}
		return false;
	}
}
