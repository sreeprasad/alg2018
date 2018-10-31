package google;

import java.util.ArrayList;
import java.util.List;

/**
 * 248. Strobogrammatic Number III
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 *
 * Example:
 *
 * Input: low = "50", high = "100"
 * Output: 3
 * Explanation: 69, 88, and 96 are three strobogrammatic numbers.
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 */
public class StrobogrammaticNumberIII {
    public static List<String> strobogrammaticInRange(String low, String high) {
        if (low == null || high == null || low.length() == 0 || high.length() == 0
            || low.length() > high.length() || (low.length() == high.length() && low.compareTo(high) > 0)) {
            return null; // edge cases
        }
        final char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
        List<String> count = new ArrayList<>();
        for (int i = low.length(); i <= high.length(); i++) {
            dfs(low, high, new char[i], pairs, 0, i - 1, count);
        }
        return count;
    }
    private static void dfs(String low, String high, char[] charArr, char[][] pairs, int left, int right, List<String> count) {
        if (left > right) {
            String s = new String(charArr);
            if ((s.length() == low.length() && s.compareTo(low) < 0)
                    ||(s.length() == high.length() && s.compareTo(high) > 0)) {
                return; // out of range
            }
            count.add(s);
            return;
        }
        for (char[] p : pairs) {
            charArr[left] = p[0];
            charArr[right] = p[1];
            if (charArr.length > 1 && charArr[0] == '0') {
                continue;
            }
            if (left == right && (charArr[left] == '6' || charArr[left] == '9')) {
                continue;
            }
            dfs(low, high, charArr, pairs, left + 1, right - 1, count);
        }
    }
    public static void main(String[] args) {
        System.out.println(strobogrammaticInRange("50", "100"));
    }
}
