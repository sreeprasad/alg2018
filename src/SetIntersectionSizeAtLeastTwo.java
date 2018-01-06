import java.util.Arrays;
import java.util.Comparator;

/**
 * An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.

 Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has size at least 2.

 Example 1:
 Input: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 Output: 3
 Explanation:
 Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
 Also, there isn't a smaller size set that fulfills the above condition.
 Thus, we output the size of this set, which is 3.
 Example 2:
 Input: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 Output: 5
 Explanation:
 An example of a minimum sized set is {1, 2, 3, 4, 5}.
 Note:

 intervals will have length in range [1, 3000].
 intervals[i] will have length 2, representing some integer interval.
 intervals[i][j] will be an integer in [0, 10^8].
 */

public class SetIntersectionSizeAtLeastTwo {
    // wrong answer, I thought the set contains consecutive numbers but it doesn't have to be
//    public static int intersectionSizeTwo(int[][] intervals) {
//
//        int[] S = new int[2];
//
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] == o2[0]) {
//                    return o1[1] - o2[1];
//                } else {
//                    return o1[0] - o2[0];
//                }
//            }
//        });
//
//        S[0] = intervals[0][0];
//        S[1] = intervals[0][1];
//
//        for (int i = 1; i < intervals.length; i++) {
//            // calculate interval of at least size 2 of S and intervals[i]
//            // S = [a, b]; intervals[i] = [x, y]
//
//            // intersection <= 1 -> if (b <= x), S = [b - 1, x + 1], S.len is (x - b + 1 + 2)
//            if (S[1] <= intervals[i][0]) {
//                S[0] = S[1] - 1;
//                S[1] = intervals[i][0] + 1;
//
//            } else { // if (b > x), S = [x, min(b, y)], S.len is 2
//                S[0] = intervals[i][0];
//                S[1] = Math.min(S[1], intervals[i][1]);
//            }
//
//            System.out.println("S = [" + S[0] + ", " + S[1] + "]");
//        }
//
//        return S[1] - (intervals[0][1] - 1) + 1;
//    }

    // answer from leetcode
    public static int intersectionSizeTwo0(int[][] intervals) {
        Arrays.sort(intervals, (interval1, interval2) -> (interval1[1] - interval2[1]));
        int max1 = -1, max2 = -1, ans = 0;
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            if (start > max1) {
                ans += 2;
                max2 = end - 1;
                max1 = end;
            } else if (start > max2) {
                ans++;
                max2 = max1 == end ? max1 - 1 : max1;
                max1 = end;
            }
            System.out.println("max1 = " + max1 + ", max2 = " + max2 + ", ans = " + ans);
        }
        return ans;
    }

    public static int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
//                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
//                } else {
//                    return o1[0] - o2[0];
//                }
            }
        });
        int ans = 0;
        int val1 = -1;
        int val2 = -1; // S = {a, b, c, ..., val1, val2}

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (start > val2) {
                ans += 2;
                val1 = end - 1;
                val2 = end;
            } else if (start > val1) {
                ans++;
                val1 = val2 == end ? val2 - 1 : val2;
                val2 = end;
            } // else do nothing
            System.out.println("val1 = " + val1 + ", val2 = " + val2 + ", ans = " + ans);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] input = {{1, 3}, {1, 4}, {2, 5}, {3, 5}}; // 3
        int[][] input2 = {{1, 2}, {2, 3}, {2, 4}, {4, 5}}; // 5
        int[][] input3 = {{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}}; // 5
        int[][] input4 = {{2,3},{5,6}}; // 4
        System.out.println(SetIntersectionSizeAtLeastTwo.intersectionSizeTwo(input));
        System.out.println(SetIntersectionSizeAtLeastTwo.intersectionSizeTwo(input2));
        System.out.println(SetIntersectionSizeAtLeastTwo.intersectionSizeTwo(input3));
        System.out.println(SetIntersectionSizeAtLeastTwo.intersectionSizeTwo(input4));
    }
}