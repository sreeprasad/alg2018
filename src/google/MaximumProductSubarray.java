package google;

/**
 * 152.
 * Maximum Product Subarray
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = 1;
        int max = 1;
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int tempMax = max;
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(tempMax * nums[i], min * nums[i]));
            maxProduct = Math.max(max, maxProduct);
        }
        return maxProduct;
    }
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4})); // 6
        System.out.println(maxProduct(new int[]{-2,0,-1})); // 0
    }
}
