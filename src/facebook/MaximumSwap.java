package facebook;

import java.util.Arrays;

/**
 * 670. Maximum Swap
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.
 *
 * Example
 * Example 1:
 *
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 *
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 * Notice
 * The given number is in the range [0, 10^8]
 */
public class MaximumSwap {
	/**
	 * @param num: a non-negative intege
	 * @return: the maximum valued number
	 */
	public int maximumSwap(int num) {
		// Write your code here
		if (num < 10) return num;
		char[] charArr = String.valueOf(num).toCharArray();
		char[] charArrSorted = charArr.clone();
		Arrays.sort(charArrSorted); // ascending, but need descending
		int i = 0;
		int len = charArr.length;
		while (i < len) {
			if (charArr[i] != charArrSorted[len - 1 - i]) {
				break;
			}
			i++;
		}
		if (i == len) {
			return num; // return num since it's already maximum
		}
		char cur = charArrSorted[len - 1 - i];
		int j = len - 1;
		while (j > i) {
			if (charArr[j] == cur) {
				break;
			}
			j--;
		}
		// swap
		char temp = charArr[i];
		charArr[i] = charArr[j];
		charArr[j] = temp;

		return Integer.valueOf(new String(charArr));
	}
	public static void main(String[] args) {
		MaximumSwap ms = new MaximumSwap();
		System.out.println(ms.maximumSwap(2736)); // 7236
		System.out.println(ms.maximumSwap(9973)); // 9973
		System.out.println(ms.maximumSwap(5656626)); // 6656625
	}
}
