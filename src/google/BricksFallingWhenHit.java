package google;

import utils.PrintArray;

/**
 * 803. Bricks Falling When Hit
 * We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.
 *
 * We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.
 *
 * Return an array representing the number of bricks that will drop after each erasure in sequence.
 *
 * Example 1:
 * Input:
 * grid = [[1,0,0,0],[1,1,1,0]]
 * hits = [[1,0]]
 * Output: [2]
 * Explanation:
 * If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
 * Example 2:
 * Input:
 * grid = [[1,0,0,0],[1,1,0,0]]
 * hits = [[1,1],[1,0]]
 * Output: [0,0]
 * Explanation:
 * When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
 *
 *
 * Note:
 *
 * The number of rows and columns in the grid will be in the range [1, 200].
 * The number of erasures will not exceed the area of the grid.
 * It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
 * An erasure may refer to a location with no brick - if it does, no bricks drop.
 */
public class BricksFallingWhenHit {

	private boolean connected = false;

	public int[] hitBricks(int[][] grid, int[][] hits) {
		int[] result = new int[hits.length];
		for (int i = 0; i < hits.length; i++) {
			result[i] = getDroppedBricks(grid, hits[i]);
		}
		return result;
	}

	// number of falling bricks when erase one brick
	private int getDroppedBricks(int[][] grid, int[] hit) {
		if (grid[hit[0]][hit[1]] == 0) return 0;
		grid[hit[0]][hit[1]] = 0; // remove current brick
		int[][] directions = new int[][]{{0, -1}, {-1, 0}, {1, 0}, {0, 1}}; // up, left, right, down
		int result = 0;
		int[] count = new int[]{0}; // count bricks that drops

		boolean[][] visited = new boolean[grid.length][grid[0].length];
		for (int i = 0; i < 4; i++) { // count four directions - up, left, right, down
			int x = hit[0] + directions[i][0];
			int y = hit[1] + directions[i][1];
			if (x > 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) { // x > 0, not x >= 0. if x==0, not remove
				dfs(grid, visited, new int[]{x, y}, count, directions);
				if (connected) {
					connected = false;
				} else {
					result += count[0];
					updateGrid(grid, x, y, directions);
				}
				count[0] = 0;
			}
		}
		return result;
	}
	private void dfs(int[][] grid, boolean[][] visited, int[] coor, int[] count, int[][] directions) {
		int i = coor[0];
		int j = coor[1];
		if (grid[i][j] == 0) {
			return;
		}
		if (i == 0) { // it's connected to top
			count[0] = 0;
			return;
		}
		if (visited[i][j]) {
			return;
		}
		visited[i][j] = true;

		count[0]++;
		for (int k = 0; k < 4; k++) {
			int x = coor[0] + directions[k][0];
			int y = coor[1] + directions[k][1];
			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
				if (x == 0) {
					connected = true;
					return;
				}
				if (!visited[x][y]) {
					dfs(grid, visited, new int[]{x, y}, count, directions);
				}
			}
		}
	}
	private void updateGrid(int[][] grid, int i, int j, int[][] directions) {
		grid[i][j] = 0;
		for (int k = 0; i < 4; i++) {
			int x = i + directions[k][0];
			int y = j + directions[k][1];
			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
				if (grid[x][y] == 0) {
					continue;
				}
				updateGrid(grid, x, y, directions);
			}
		}
	}
	public static void main(String[] args) {
		BricksFallingWhenHit bfwh = new BricksFallingWhenHit();
//		PrintArray.printArray(bfwh.hitBricks(new int[][]{{1,0,0,0},{1,1,1,0}}, new int[][]{{1, 0}})); // 2
//		PrintArray.printArray(bfwh.hitBricks(new int[][]{{1,0,0,0},{1,1,0,0}}, new int[][]{{1, 1}, {1, 0}})); // 0 0
//		PrintArray.printArray(bfwh.hitBricks(new int[][]{{0,1,1,1,1},{1,1,1,1,0},{1,1,1,1,0},{0,0,1,1,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}},
//				new int[][]{{6,0},{1,0},{4,3},{1,2},{7,1},{6,3},{5,2},{5,1},{2,4},{4,4},{7,3}})); // 0 0 0 0 0 0 0 0 0 0 0
//		PrintArray.printArray(bfwh.hitBricks(new int[][]{{1},{1},{1},{1},{1}}, new int[][]{{3, 0}, {4, 0}, {1, 0}, {2, 0}, {0, 0}})); // 1 0 1 0 0
//		PrintArray.printArray(bfwh.hitBricks(new int[][]{{1,1,1},{0,1,0},{0,0,0}}, new int[][]{{0,2},{2,0},{0,1},{1,2}})); // 0 0 1 0
//		PrintArray.printArray(bfwh.hitBricks(new int[][]{{1,1,0,1,0},{1,1,0,1,1},{0,0,0,1,1},{0,0,0,1,0},{0,0,0,0,0},{0,0,0,0,0}}, new int[][]{{5,1},{1,3}})); // 0 4
		// 这道题没做完 8/16 test cases passed
		PrintArray.printArray(bfwh.hitBricks(new int[][]{{0,1,1,1,1},{1,1,1,1,0},{1,1,1,1,0},{0,0,1,1,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}},
				new int[][]{{6,0},{1,0},{4,3},{1,2},{7,1},{6,3},{5,2},{5,1},{2,4},{4,4},{7,3}})); //[0,0,0,0,0,0,0,0,0,0,0] this is not correct
	}
}
