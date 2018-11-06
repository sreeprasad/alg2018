package LinkedIn;

import dataStructures.NestedInteger;

import java.util.List;

/**
 * 364. Nested List Weight Sum II
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.
 *
 * Example 1:
 *
 * Input: [[1,1],2,[1,1]]
 * Output: 8
 * Explanation: Four 1's at depth 1, one 2 at depth 2.
 * Example 2:
 *
 * Input: [1,[4,[6]]]
 * Output: 17
 * Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
 */
public class NestedListWeightSumII {
	private int flatSum;
	private int depth;

	public int depthSumInverse(List<NestedInteger> nestedList) { // 3x+2y+1z = (3+1)(x+y+z)-(1x+2y+3)
		int weightedSum = dfs(nestedList, 1);
		return (depth + 1) * flatSum - weightedSum;
	}
	private int dfs(List<NestedInteger> nestedList, int d) {
		int sum = 0;
		for (NestedInteger num : nestedList) {
			if (num.isInteger()) {
				flatSum += num.getInteger();
				sum += d * num.getInteger();
				depth = Math.max(depth, d);
			} else {
				sum += dfs(num.getList(), d + 1);
			}
		}
		return sum;
	}
}
