package google;

/**
 * 777. Swap Adjacent in LR String
 * Medium
 *
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 * Example:
 *
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: True
 * Explanation:
 * We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * Note:
 *
 * 1 <= len(start) = len(end) <= 10000.
 * Both start and end will only consist of characters in {'L', 'R', 'X'}.
 */
public class SwapAdjacentInLRString {
	public boolean canTransform(String start, String end) {
		int len1 = start.length();
		int len2 = end.length();
		if (len1 != len2) return false;

		char[] arr1 = start.toCharArray();
		char[] arr2 = end.toCharArray();

		int i = 0, j = 0;
		while (i < len1 && j < len2) {
			while (i < len1 && arr1[i] == 'X') i++;
			while (j < len2 && arr2[j] == 'X') j++;
			if (i < len1 && j < len2) {
				if (arr1[i] != arr2[j]) { // relative order of L and R remain the same
					return false;
				} else if (arr1[i] == 'L') { // L can't move right
					if (i < j) return false;
				} else { // arr1[i] = arr2[j] = 'R', R can't move left
					if (i > j) return false;
				}
				i++;
				j++;
			}

		}
		// deal with rest of chars
		if (i >= len1 && j >= len1) return true;
		else if (i >= len1) {
			while (j < len2) {
				if (arr2[j] != 'X') return false;
				j++;
			}
		} else {
			while (i < len1) {
				 if (arr1[i] != 'X') return false;
				 i++;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		SwapAdjacentInLRString salr = new SwapAdjacentInLRString();
		System.out.println(salr.canTransform("XXRXXLXXXX", "XXXXRXXLXX")); // false
		System.out.println(salr.canTransform("RXXLRXRXL", "XRLXXRRLX")); // true
		System.out.println(salr.canTransform("X", "R")); // false
	}
}
