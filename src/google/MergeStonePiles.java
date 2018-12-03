package google;


/**
 * Merge Stone Piles
 * DP 经典题
 * 有N堆石子排成一排，每堆石子有一定的数量。
 * 现要将N堆石子并成为一堆。合并的过程只能每次将相邻的两堆石子堆成一堆，
 * 每次合并花费的代价为这两堆石子的和，经过N-1次合并后成为一堆。求出总的代价最小值。
 *
 * 贪心法Greedy不work，反例:[7，6，5，7，100] -> 贪心179，答案175
 * 所以用区间DP
 */
public class MergeStonePiles {
	public int minCost(int[] stones) {
		int len = stones.length;
		int[][] sum = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				if (i == j) {
					sum[i][j] = stones[i];
				} else {
					sum[i][j] = sum[i][j - 1] + stones[j];
				}
			}
		}
		int[][] dp = new int[len][len];
		for (int k = 2; k <= len; k++) { // 区间长度为1时，dp[i][i] = 0, 省略
			for (int left = 0; left <= len - k; left++) {
				int right = left + k - 1;
				dp[left][right] = Integer.MAX_VALUE;
				for (int i = left; i < right; i++) {
					dp[left][right] = Math.min(dp[left][right], dp[left][i] + dp[i + 1][right] + sum[left][right]);
				}
			}
		}
		return dp[0][len - 1];
	}
	public static void main(String[] args) {
		MergeStonePiles ms = new MergeStonePiles();
		System.out.println(ms.minCost(new int[]{7,6,5,7,100})); // 175
	}
}
