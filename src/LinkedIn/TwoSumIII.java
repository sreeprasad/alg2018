package LinkedIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 170. Two Sum III - Data structure design
 *
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * Example 1:
 *
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 * Example 2:
 *
 * add(3); add(1); add(2);
 * find(3) -> true
 * find(6) -> false
 */
public class TwoSumIII {
	Map<Integer, Integer> map;
	List<Integer> list;

	/** Initialize your data structure here. */
	public TwoSumIII() {
		map = new HashMap<>();
		list = new ArrayList<>();
	}

	/** Add the number to an internal data structure.. */
	public void add(int number) {
		if (!map.containsKey(number)) {
			list.add(number);
		}
		map.put(number, map.getOrDefault(number, 0) + 1);
	}

	/** Find if there exists any pair of numbers which sum is equal to the value. */
	public boolean find(int value) {
		for (int key : list) { // iterating list is faster than iterating map.keySet()
			int num = value - key;
			if (map.containsKey(num)) {
				if (key == num && map.get(num) < 2) continue;
				return true;
			}
		}
		return false;
	}
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */