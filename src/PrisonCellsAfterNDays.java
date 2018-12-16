import utils.PrintArray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 957. Prison Cells After N Days
 *
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 *
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 *
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 *
 * Note:
 *
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 */
public class PrisonCellsAfterNDays {
	public int[] prisonAfterNDays(int[] cells, int N) {

		Map<String, Integer> map1 = new HashMap<>();
		Map<Integer, String> map2 = new HashMap<>();

		int[] cur = cells;

		map1.put(Arrays.toString(cur), 0);
		map2.put(0, Arrays.toString(cur));

		int i = 1;
		while (i <= N) {
			cur = oneDayPassed(cur);
			String key = Arrays.toString(cur);
			if (!map1.keySet().contains(key)) {
				map1.put(key, i);
				map2.put(i, key);
			} else {
				int cycle = i - map1.get(key);
				map2.put(i, key); // to avoid NPE at return at Line 78
				int index = N % cycle;
				String str = map2.get(index == 0 ? cycle : index);
				return fromString(str);
			}
			i++;
		}
		return fromString(map2.get(N));
	}

	private int[] oneDayPassed(int[] prev) {
		int[] cur = new int[prev.length];
		for (int i = 1; i < prev.length - 1; i++) {
			cur[i] = prev[i - 1] == prev[i + 1] ? 1 : 0;
		}
		return cur;
	}
	private int[] fromString(String string) {
		String[] strings = string.replace("[", "").replace("]", "").split(", ");
		int result[] = new int[strings.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Integer.parseInt(strings[i]);
		}
		return result;
	}
	public static void main(String[] args) {
		PrisonCellsAfterNDays pc = new PrisonCellsAfterNDays();
		PrintArray.printArray(pc.prisonAfterNDays(new int[]{0,0,0,1,1,0,1,0}, 574)); // [0 0 0 1 1 0 1 0]
		PrintArray.printArray(pc.prisonAfterNDays(new int[]{1, 1, 0, 1, 1, 0, 1, 1}, 6)); // [0,0,1,0,0,1,0,0]
		PrintArray.printArray(pc.prisonAfterNDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000)); // [0,0,1,1,1,1,1,0]
	}
}
