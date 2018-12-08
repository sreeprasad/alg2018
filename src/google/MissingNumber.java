package google;

/**
 * 268. Missing Number
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * Example 1:
 *
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 *
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 */
public class MissingNumber {
	public int missingNumber(int[] nums) {
		// 1. sort time O(nlgn) space O(1)
		// 2. HashSet time O(n) space O(n)

		// 3. xor time O(n) space O(1)
		int xor = nums.length;
		for (int i = 0; i < nums.length; i++) {
			xor ^= i ^ nums[i];
		}
		return xor;

		// 4. sum time O(n) space O(1)
		// sum(0 ~ nums.length) - sum(nums)
	}
}
