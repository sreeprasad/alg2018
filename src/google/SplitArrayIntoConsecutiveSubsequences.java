package google;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 659. Split Array into Consecutive Subsequences
 * Medium
 *
 * You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.
 *
 * Example 1:
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 * Example 2:
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * Example 3:
 * Input: [1,2,3,4,4,5]
 * Output: False
 * Note:
 * The length of the input is in range of [1, 10000]
 */
public class SplitArrayIntoConsecutiveSubsequences {
	public boolean isPossible(int[] nums) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		while (!map.isEmpty()) {
			int count = 0;
			int lastNum = -1;
			Iterator<Integer> keyIterator = map.keySet().iterator();
			while (keyIterator.hasNext()) {
				int key = keyIterator.next();
				if (count > 0 && count < 3) {
					if (lastNum + 1 != key || map.get(key) < map.getOrDefault(lastNum, 0) + 1) {
						return false;
					}
				} else if (count >= 3) {
					if (lastNum + 1 != key || map.get(key) < map.getOrDefault(lastNum, 0) + 1) {
						break;
					}
				}
				lastNum = key;
				map.put(key, map.get(key) - 1);
				if (map.get(key) == 0) keyIterator.remove();
				count++;
			}
			if (count < 3) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		SplitArrayIntoConsecutiveSubsequences saics = new SplitArrayIntoConsecutiveSubsequences();
		System.out.println(saics.isPossible(new int[]{1,2,3,3,4,5})); // true
		System.out.println(saics.isPossible(new int[]{1,2,3,3,4,4,5,5})); // true
		System.out.println(saics.isPossible(new int[]{1,2,3,4,4,5})); // false
	}
}
