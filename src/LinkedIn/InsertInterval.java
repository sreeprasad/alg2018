package LinkedIn;

import dataStructures.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. Insert Interval
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
	public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {

		if (intervals == null || intervals.size() == 0) {
			intervals.add(newInterval);
			return intervals;
		}

		// binary search
		int left = 0;
		int right = intervals.size() - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (intervals.get(mid).start <= newInterval.start) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}

		// index of interval to start merging
		int index = intervals.get(right).start <= newInterval.start ? right : left;

		if (newInterval.end < intervals.get(index).start) {
			intervals.add(0, newInterval);
			return intervals;
		}

		// if no overlap, add
		if (newInterval.start > intervals.get(index).end) {
			intervals.add(index + 1, newInterval);
			index++;
		}

		intervals.get(index).start = Math.min(intervals.get(index).start, newInterval.start);
		intervals.get(index).end = Math.max(intervals.get(index).end, newInterval.end);

		while (index < intervals.size() - 1 && intervals.get(index).end >= intervals.get(index + 1).start) {
			intervals.get(index).end = Math.max(intervals.get(index).end, intervals.get(index + 1).end);
			intervals.remove(index + 1);
		}

		return intervals;
	}

	public static void main(String[] args) {
		List<Interval> intervals = new ArrayList<>(Arrays.asList(new Interval(3, 5), new Interval(7, 9)));
		System.out.println(insert(intervals, new Interval(0, 0)));
		System.out.println(insert(intervals, new Interval(0, 4)));
		System.out.println(insert(intervals, new Interval(2, 6)));
		System.out.println(insert(intervals, new Interval(2, 8)));
		System.out.println(insert(intervals, new Interval(10, 11)));
	}
}
