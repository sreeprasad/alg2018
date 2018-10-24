package Airbnb;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 773. Sliding Puzzle
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 *
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 * Examples:
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 * Note:
 *
 * board will be a 2 x 3 array as described above.
 * board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 */
public class SlidingPuzzle {
    public static int slidingPuzzle(int[][] board) {
        // board can be treated as string "123450"
        StringBuilder sb = new StringBuilder();
        for (int[] arr : board) {
            for (int ele : arr) {
                sb.append(ele);
            }
        }
        String start = sb.toString();
        String target = "123450";

        // swappableIndex[i] stands for the list of indexes that can be swapped with 0 if 0 is at position i
        int[][] swappableIndex = new int[][]{{1,3},{0,2,4},{1,5},{0, 4},{1,3,5},{2,4}};

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);

        Set<String> visited = new HashSet<>();
        visited.add(start);

        int step = 0;

        // BFS
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return step;
                }
                int indexOfZero = cur.indexOf("0");
                int[] indexToSwap = swappableIndex[indexOfZero];

                for (int index : indexToSwap) {
                    String next = swap(indexOfZero, index, cur);
                    if (visited.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    visited.add(next);
                }
            }
            step++;
        }

        return -1;
    }
    private static String swap(int indexOfZero, int index, String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(indexOfZero, s.charAt(index));
        sb.setCharAt(index, s.charAt(indexOfZero));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(slidingPuzzle(new int[][]{{1,2,3}, {4,0,5}}));
        System.out.println(slidingPuzzle(new int[][]{{1,2,3}, {5,4,0}}));
        System.out.println(slidingPuzzle(new int[][]{{4,1,2}, {5,0,3}}));
        System.out.println(slidingPuzzle(new int[][]{{3,2,4}, {1,5,0}}));
    }
}
