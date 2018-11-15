package google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 939. Minimum Area Rectangle
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
 *
 * If there isn't any rectangle, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * Example 2:
 *
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 *
 *
 * Note:
 *
 * 1 <= points.length <= 500
 * 0 <= points[i][0] <= 40000
 * 0 <= points[i][1] <= 40000
 * All points are distinct.
 */
public class MinimumAreaRectangle {
	public int minAreaRect(int[][] points) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < points.length; i++) {
			map.putIfAbsent(points[i][0], new HashSet<>());
			map.get(points[i][0]).add(points[i][1]);
		}

		int minArea = Integer.MAX_VALUE;
		for (int[] point1 : points) {
			for (int[] point2 : points) {
				if (point1[0] == point2[0] || point1[1] == point2[1]) {
					continue;
				}
				if (map.get(point1[0]).contains(point2[1]) && map.get(point2[0]).contains(point1[1])) {
					minArea = Math.min(minArea, Math.abs(point1[0] - point2[0]) * Math.abs(point1[1] - point2[1]));
				}
			}
		}
		return minArea == Integer.MAX_VALUE ? 0 : minArea;
	}
}
