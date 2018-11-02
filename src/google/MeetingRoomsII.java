package google;

import dataStructures.Interval;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * 253. Meeting Rooms II
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 */
public class MeetingRoomsII {
	public int minMeetingRooms(Interval[] intervals) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < intervals.length; i++) {
			int start = intervals[i].start;
			int end = intervals[i].end;
			if (!map.containsKey(start)) {
				map.put(start, 1); // every time a meeting starts, a meeting room is needed
			} else {
				map.put(start, map.get(start) + 1);
			}
			if (!map.containsKey(end)) {
				map.put(end, -1);
			} else {
				map.put(end, map.get(end) - 1); // meeting room is released after meeting ends
			}
		}
		int maxRooms = 0;
		int tempRooms = 0;
		for (int value : map.values()) { // in time order, check every moment meeting room status is changed
			tempRooms += value;
			maxRooms = Math.max(maxRooms, tempRooms);
		}
		return maxRooms;
	}

	// similar to above solution
	public int minMeetingRoomsII(Interval[] intervals) {
		int[] starts = new int[intervals.length];
		int[] ends = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			starts[i] = intervals[i].start;
			ends[i] = intervals[i].end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int endIterator = 0;
		int rooms = 0;
		for (int i = 0; i < starts.length; i++) {
			if (starts[i] < ends[endIterator]) {
				rooms++;
			} else {
				endIterator++;
			}
		}
		return rooms;
	}
}
