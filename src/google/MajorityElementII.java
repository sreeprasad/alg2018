package google;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. Majority Element II
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElementII {
	public List<Integer> majorityElement(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return result;
		}
		int ele1 = 0, ele2 = 0;
		int count1 = 0, count2 = 0;
		for (int num : nums) {
			if (num == ele1) {
				count1++;
			} else if (num == ele2) {
				count2++;
			} else if (count1 == 0) {
				ele1 = num;
				count1++;
			} else if (count2 == 0) {
				ele2 = num;
				count2++;
			} else {
				count1--;
				count2--;
			}
		}
		count1 = 0;
		count2 = 0;
		for (int num : nums) {
			if (num == ele1) count1++;
			if (num == ele2) count2++;
		}
		int len = nums.length;
		if (count1 > len / 3) result.add(ele1);
		if (count2 > len / 3 && ele1 != ele2) result.add(ele2);
		return result;
	}
	public static void main(String[] args) {
		MajorityElementII meii = new MajorityElementII();
		System.out.println(meii.majorityElement(new int[]{3,2,1,1,1,2,2}));
		System.out.println(meii.majorityElement(new int[]{0,0,0}));
	}
}
