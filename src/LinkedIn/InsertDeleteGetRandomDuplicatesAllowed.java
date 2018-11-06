package LinkedIn;

import java.util.*;

/**
 * 381. Insert Delete GetRandom O(1) - Duplicates allowed
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
 * Example:
 *
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 *
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 *
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 * collection.insert(1);
 *
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 *
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 *
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 *
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 */
public class InsertDeleteGetRandomDuplicatesAllowed {
	private Map<Integer, Set<Integer>> map;
	private List<Integer> list;
	private Random random;

	/** Initialize your data structure here. */
	public InsertDeleteGetRandomDuplicatesAllowed() {
		map = new HashMap<>();
		list = new ArrayList<>();
		random = new Random();
	}

	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		Set<Integer> prev = map.putIfAbsent(val, new HashSet<>());
		map.get(val).add(list.size());
		list.add(val);
		if (prev == null) return true;
		else return false;
	}

	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		}
		int removedIndex = map.get(val).iterator().next();
		map.get(val).remove(removedIndex);
		if (map.get(val).size() == 0) map.remove(val);

		int lastEle = list.get(list.size() - 1);
		list.set(removedIndex, lastEle);
		if (map.containsKey(lastEle)) {
			map.get(lastEle).add(removedIndex);
			map.get(lastEle).remove(list.size() - 1);
		}

		list.remove(list.size() - 1);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return list.get(random.nextInt(list.size()));
	}

	public static void main(String[] args) {
		InsertDeleteGetRandomDuplicatesAllowed idgrda = new InsertDeleteGetRandomDuplicatesAllowed();
		idgrda.insert(4);idgrda.insert(3);idgrda.insert(4);idgrda.insert(2);idgrda.insert(4);
		idgrda.remove(4);idgrda.remove(3);idgrda.remove(4);idgrda.remove(4);
	}
}
