package google;

/**
 * 361. Bomb Enemy
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
 * Note: You can only put the bomb at an empty cell.
 *
 * Example:
 *
 * Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * Output: 3
 * Explanation: For the given grid,
 *
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 *
 * Placing a bomb at (1,1) kills 3 enemies.
 */
public class BombEnemy {
	public int maxKilledEnemies(char[][] grid) {
		int max = 0;
		int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {

				if (grid[i][j] != '0') continue;
				int enemy = 0;

				for (int k = 0; k < dir.length; k++) { // four directions
					int m = i + dir[k][0], n = j + dir[k][1];
					while (m >= 0 && m < grid.length && n >= 0 && n < grid[0].length) {
						if (grid[m][n] == 'W') {
							break;
						}
						if (grid[m][n] == 'E') {
							enemy++;
						}
						m += dir[k][0];
						n += dir[k][1];
					}
				}

				max = enemy > max ? enemy : max;
			}
		}

		return max;
	}

	public static void main(String[] args) {
		BombEnemy be = new BombEnemy();
		System.out.println(be.maxKilledEnemies(new char[][]{{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}})); // 3
		System.out.println(be.maxKilledEnemies(new char[][]{{'E'}})); // 0
		System.out.println(be.maxKilledEnemies(new char[][]{{'W'},{'E'},{'W'},{'0'},{'E'}})); // 1
	}
}
