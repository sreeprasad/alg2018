package google;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 846. Hand of Straights
 * Alice has a hand of cards, given as an array of integers.
 *
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 *
 * Return true if and only if she can.
 *
 *
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 */
public class HandofStraights {
	public static boolean isNStraightHand(int[] hand, int W) {
		int len = hand.length;
		if (len % W != 0) return false;

		Map<Integer, Integer> map = new TreeMap<>();
		for (int card : hand) {
			map.put(card, map.getOrDefault(card, 0) + 1);
		}
		for (int card : map.keySet()) {
			int count = map.get(card);
			if (count == 0) {
				continue;
			}
			for (int i = card; i < card + W; i++) { // i is end of consecutive cards starting from card
				if (map.get(i) == null || map.get(i) < count) {
					return false;
				}
				map.put(i, map.get(i) - count);
			}
		}
		return true;
	}
//	public static boolean isNStraightHand(int[] hand, int W) {
//		int len = hand.length;
//		if (len % W != 0) {
//			return false;
//		}
//		if (W == 1) {
//			return true;
//		}
//
//		Map<Integer, Integer> map = new TreeMap<>();
//		for (int card : hand) {
//			map.put(card, map.getOrDefault(card, 0) + 1); // dup card count
//		}
//
//
//		while (!map.isEmpty()) {
//			Integer lastKey = -1;
//			int count = 0;
//
//			Iterator it = map.entrySet().iterator();
//
//			while (it.hasNext()) {
//				if (map.size() == 1 && W - count > 1) {
//					return false;
//				}
//				Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
//				int key = entry.getKey();
//				int value = entry.getValue();
//
//				count++;
//				if (lastKey == -1) { // assign first card of each group
//					lastKey = key;
//					if (value == 1) {
//						it.remove();
//					} else {
//						entry.setValue(value - 1);
//					}
//					continue;
//				}
//
//				if (key - lastKey != 1) {
//					return false;
//				}
//				if (value == 1) {
//					it.remove();
//				} else {
//					entry.setValue(value - 1);
//				}
//
//				lastKey = key;
//				if (count == W) {
//					break;
//				}
//			}
//		}
//		return true;
//	}
	public static void main(String[] args) {
		System.out.println(isNStraightHand(new int[]{1,1,2,2,3,3}, 2));
		System.out.println(isNStraightHand(new int[]{5,1}, 1));
		System.out.println(isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3));
	}
}
