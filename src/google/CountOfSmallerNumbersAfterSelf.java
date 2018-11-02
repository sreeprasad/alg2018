package google;

import dataStructures.FenwickTree;

import java.util.*;

/**
 * 315.
 * Count of Smaller Numbers After Self
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */
public class CountOfSmallerNumbersAfterSelf {
	public static List<Integer> countSmaller(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return result;
		}

		// sort array to get rank of each element
		int[] sortedArr = nums.clone();
		Arrays.sort(sortedArr);

		// remove duplicate elements to calculate rank
		Map<Integer, Integer> rankMap = new HashMap<>(); // this is what we really need
		for (int i = 0; i < sortedArr.length; i++) {
			if (i > 0 && sortedArr[i] == sortedArr[i - 1]) {
				continue;
			}
			rankMap.put(sortedArr[i], rankMap.size() + 1);
		}

		FenwickTree fenwickTree = new FenwickTree(rankMap.size());

		// loop reversely as binary index tree is calculating prefix sum
		for (int i = nums.length - 1; i >= 0; i--) { // [1, 6, 2, 5]
			result.add(fenwickTree.query(rankMap.get(nums[i]) - 1)); // query before update
			fenwickTree.update(rankMap.get(nums[i]), 1);
		}
		Collections.reverse(result);
		return result;
	}
	public static void main(String[] args) {
		System.out.println(countSmaller(new int[]{5,2,6,1})); // 2, 1, 1, 0
	}
}
