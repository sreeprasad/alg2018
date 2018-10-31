package google;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=453698&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
 *
 * 可乐饮料机，有一系列按钮，每个按钮按下去会得到一定体积范围的可乐。先给定一个目标体积范围，问不限制按按钮次数，能否确定一定能得到目标范围内的可乐？
 * 举例：有三个按钮，按下去得到的范围是[100, 120], [200, 240], [400, 410],
 * 假设目标是[100, 110], 那答案是不能。因为按下一，可能得到120体积的可乐，不在目标范围里。
 * 假设目标是[90, 120]，那答案是可以。因为按下一，一定可以得到此范围内的可乐
 * 假设目标是[300, 360], 那答案是可以，因为按下一再按二，一定可以得到此范围内
 * 假设目标是[310, 360], 那答案是不能，因为按下一再按二，有可能得到300，永远没可能确定得到这个范围内的可乐。
 * 假设目标是[1, 9999999999]，那答案是可以。随便按一个都确定满足此范围。
 */
public class ColaMachine {
	public static boolean canGet(int[] target, int[][] buttons) {
		Map<String, Boolean> memo = new HashMap<>();
		return dfs(new int[]{0, 0}, target, buttons, memo);
	}
	private static boolean dfs(int[] path, int[] target, int[][] buttons, Map<String, Boolean> memo) {

		String key = Arrays.toString(path);
		if (memo.containsKey(key))  {
			return memo.get(key);
		}
		if (path[0] > target[0]) {
			memo.put(key, false);
			return false;
		}
		if (isSubset(path, target)) {
			memo.put(key, true);
			return true;
		}
		for (int[] button : buttons) {
			if (dfs(new int[]{path[0] + button[0], path[1] + button[1]}, target, buttons, memo)) {
				memo.put(key, true);
				return true;
			}
		}
		memo.put(key, false);
		return false;
	}

	/**
	 * returns true if range1 is subset of range2
	 */
	private static boolean isSubset(int[] range1, int[] range2) {
		return range1[0] >= range2[0] && range1[1] <= range2[1];
	}
	public static void main(String[] args) {
		System.out.println(canGet(new int[]{100, 110}, new int[][]{{100, 120}, {200, 240}, {400, 410}}));
		System.out.println(canGet(new int[]{90, 120}, new int[][]{{100, 120}, {200, 240}, {400, 410}}));
		System.out.println(canGet(new int[]{300, 360}, new int[][]{{100, 120}, {200, 240}, {400, 410}}));
		System.out.println(canGet(new int[]{310, 360}, new int[][]{{100, 120}, {200, 240}, {400, 410}}));
	}
}
