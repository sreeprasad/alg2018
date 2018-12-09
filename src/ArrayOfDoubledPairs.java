import java.util.Map;
import java.util.TreeMap;

/**
 * 954. Array of Doubled Pairs
 * Medium
 *
 * Given an array of integers A with even length, return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,1,3,6]
 * Output: false
 * Example 2:
 *
 * Input: [2,1,2,6]
 * Output: false
 * Example 3:
 *
 * Input: [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 * Example 4:
 *
 * Input: [1,2,4,16,8,4]
 * Output: false
 *
 *
 * Note:
 *
 * 0 <= A.length <= 30000
 * A.length is even
 * -100000 <= A[i] <= 100000
 */
public class ArrayOfDoubledPairs {
	public boolean canReorderDoubled(int[] A) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (int a : A) {
			map.put(a, map.getOrDefault(a, 0) + 1);
		}
		for (int a : map.keySet()) {
			if (map.get(a) == 0) continue; // been used up already
			if (a < 0 && a % 2 == 1) return false;
			int want = a < 0 ? a / 2 : a * 2;
			if (map.get(want) == null || map.get(a) > map.get(want)) return false;
			map.put(want, map.get(want) - map.get(a));
		}
		return true;
	}
}
