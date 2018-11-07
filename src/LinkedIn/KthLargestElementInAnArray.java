package LinkedIn;

import java.util.Random;

/**
 * 215. Kth Largest Element in an Array
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 */
public class KthLargestElementInAnArray {
	public static int findKthLargest(int[] nums, int k) {
		if (k < 1 || nums == null) return 0;
		return quickSelect(nums, 0, nums.length - 1, nums.length - k); // kth largest is (nums.length - k)th smallest
	}
	private static int quickSelect(int[] nums, int start, int end, int k) {
		if (start == end) return nums[start];

		int randomPivotIndex = start + new Random().nextInt(end - start + 1);
		int pivot = nums[randomPivotIndex]; // better to be random, faster
		swap(nums, randomPivotIndex, end);

		int left = start;
		int right = end;
		while (left < right) {
			while (nums[left] <= pivot && left < right) left++;
			while (nums[right] >= pivot && left < right) right--;
			swap(nums, left, right);
		}
		swap(nums, left, end); // left === right here

		if (left == k) { // nums[left] is pivot
			return nums[left]; // pivot
		} else if (left > k) {
			return quickSelect(nums, start, left - 1, k);
		} else {
			return quickSelect(nums, left + 1, end, k);
		}
	}
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	public static void main(String[] args) {
		System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4)); // 1,2,2,3,3,4,5,5,6
		System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2)); // 5
	}
}
