package google;

/**
 * 169. Majority Element
 *
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {
	public int majorityElement(int[] num) {
		int major = 0;
		int count = 0;
		for (int i : num){
			if (count == 0) {
				major = i;
				count++;
			} else if (major == i) {
				count++;
			} else {
				count--;
			}
		}
		return major;
	}
	// faster than 100%
	public int majorityElementII(int[] num) {
		int major = 0;
		int count = 0;
		for (int i : num){
			if (count == 0) {
				major = i;
			}
			count += major == i ? 1 : -1;
		}
		return major;
	}
}
