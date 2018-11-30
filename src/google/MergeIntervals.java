package google;

import dataStructures.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 56. Merge Intervals
 * Medium
 *
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {
	public List<Interval> merge(List<Interval> intervals) {
		intervals.sort((a, b) -> (a.start != b.start ? a.start - b.start : a.end - b.end));
		List<Interval> result = new ArrayList<>();
		if (intervals.size() == 0) return result;
		result.add(intervals.get(0));
		for (int i = 1; i < intervals.size(); i++) {
			Interval last = result.get(result.size() - 1);
			Interval cur = intervals.get(i);
			if (cur.start > last.end) {
				result.add(cur);
			} else {
				last.end = Math.max(last.end, cur.end);
			}
		}
		return result;
	}
}
