package google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 679. 24 Game
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 *
 * Example 1:
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Example 2:
 * Input: [1, 2, 1, 2]
 * Output: False
 * Note:
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */
public class TwentyFourGame {
	public boolean judgePoint24(int[] nums) {
		List<Double> list = new ArrayList<>();
		for (int num : nums) {
			list.add((double) num);
		}
		return dfs(list);
	}
	private boolean dfs(List<Double> list) {
		if (list.size() == 1) {
			if (Math.abs(list.get(0) - 24.0) < 0.001) {
				return true;
			}
			return false;
		}
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				for (Double d : getPossibleValues(list.get(i), list.get(j))) {
					List<Double> nextRound = new ArrayList<>();
					nextRound.add(d);
					for (int k = 0; k < list.size(); k++) {
						if (k == i || k == j) continue;
						nextRound.add(list.get(k));
					}
					if (dfs(nextRound)) return true;
				}
			}
		}
		return false;
	}
	private List<Double> getPossibleValues(double a, double b) {
		return Arrays.asList(a+b, a-b, b-a, a*b, a/b, b/a);
	}
	public static void main(String[] args) {
		TwentyFourGame tfg = new TwentyFourGame();
		System.out.println(tfg.judgePoint24(new int[]{4, 1, 8, 7}));
	}
}
