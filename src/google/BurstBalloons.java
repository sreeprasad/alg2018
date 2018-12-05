package google;

/**
 * 312. Burst Balloons
 * Hard
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {
	public int maxCoins(int[] nums) {
		int len = nums.length;

		int[] arr = new int[len + 2];
		arr[0] = arr[len + 1] = 1;
		for (int i = 0; i < len; i++) {
			arr[i + 1] = nums[i];
		}

		int[][] dp = new int[len + 2][len + 2];
		dp[0][0] = dp[len+1][len+1] = 1;
		for (int i = 1; i <= len; i++) {
			dp[i][i] = arr[i-1] * arr[i] * arr[i+1];
		}

		for (int k = 2; k < len + 2; k++) {
			for (int i = 0; i + k <= len + 1; i++) {
				int j = i + k;
				for (int m = i + 1; m < j; m++) {
					dp[i][j] = Math.max(dp[i][j], dp[i][m] + arr[i]*arr[m]*arr[j] + dp[m][j]);
				}
			}
		}

		return dp[1][len];
	}
	public static void main(String[] args) {
		BurstBalloons bb = new BurstBalloons();
		System.out.println(bb.maxCoins(new int[]{3,1,5,8})); // 167
	}
}
