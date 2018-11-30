package google;

/**
 * 287. Find the Duplicate Number
 * Medium
 *
 *
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Example 1:
 *
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 *
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindTheDuplicateNumber {
	public int findDuplicate(int[] nums) {
		// 1. O(n) time
		// 2. O(1) space
		// 3. input array cannot be changed
		if (nums == null || nums.length <= 1) {
			return -1;
		}
		int slow = nums[0];
		int fast = nums[nums[0]];
		while (fast != slow) { // once fast and slow meets, means they are in cycle
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		fast = 0; // set fast or slow to 0
		while (fast != slow) {
			fast = nums[fast];
			slow = nums[slow];
		}
		return slow;
	}
	public static void main(String[] args) {
		FindTheDuplicateNumber fdn = new FindTheDuplicateNumber();
		System.out.println(fdn.findDuplicate(new int[]{1,3,3,2,4}));
	}
}
