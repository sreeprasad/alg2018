package google;

import utils.PrintArray;

import java.util.HashSet;
import java.util.Set;

/**
 * 723. Candy Crush
 * This question is about implementing a basic elimination algorithm for Candy Crush.
 *
 * Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:
 *
 * If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
 * After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
 * After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 * If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
 * You need to perform the above rules until the board becomes stable, then return the current board.
 *
 *
 *
 * Example:
 *
 * Input:
 * board =
 * [[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]
 *
 * Output:
 * [[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]
 *
 * Note:
 *
 * The length of board will be in the range [3, 50].
 * The length of board[i] will be in the range [3, 50].
 * Each board[i][j] will initially start as an integer in the range [1, 2000].
 */
public class CandyCrush {
	public int[][] candyCrush(int[][] board) {

		Set<Integer> tobeDeleted = new HashSet<>();
		findAllCrushable(board, tobeDeleted);
		while (tobeDeleted.size() > 0) {
			updateBoard(board, tobeDeleted);
			tobeDeleted = new HashSet<>();
			findAllCrushable(board, tobeDeleted);
		}
		return board;
	}
	private void findAllCrushable(int[][] board, Set<Integer> tobeDeleted) {
		int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				for (int[] dir : dirs) {
					Set<Integer> tempSet = new HashSet<>();
					tempSet.add(i * board.length + j);
					int x = i + dir[0];
					int y = j + dir[1];
					while (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[i][j] != 0 && board[x][y] == board[i][j]) {
						tempSet.add(x * board.length + y);
						x += dir[0];
						y += dir[1];
					}
					if (tempSet.size() >= 3) {
						tobeDeleted.addAll(tempSet);
					}
				}
			}
		}
	}
	private void updateBoard(int[][] board, Set<Integer> tobeDeleted) {
		crash(board, tobeDeleted);
		drop(board);
	}
	private void crash(int[][] board, Set<Integer> tobeDeleted) {
		for (int key : tobeDeleted) {
			int x = key / board.length;
			int y = key % board.length;
			board[x][y] = 0;
		}
	}
	private void drop(int[][] board) {
		for (int i = 0; i < board[0].length; i++) {
			int j = board.length - 1;
			while (j >= 0) {
				while (j >= 0 && board[j][i] != 0) j--;
				int k = j - 1;
				while (k >= 0 && board[k][i] == 0) k--;
				if (j >= 0 && k >= 0) {
					int temp = board[j][i];
					board[j][i] = board[k][i];
					board[k][i] = temp;
				}
				j--;
			}
		}
	}
	public static void main(String[] args) {
		CandyCrush cc = new CandyCrush();
//		PrintArray.print2dimArray(cc.candyCrush(new int[][]{{110,5,112,113,114},{210,211,5,213,214},{310,311,3,313,314},{410,411,412,5,414},{5,1,512,3,3},{610,4,1,613,614},{710,1,2,713,714},{810,1,2,1,1},{1,1,2,2,2},{4,1,4,4,1014}}));
		PrintArray.print2dimArray(cc.candyCrush(new int[][]{{5,5,5,3,2},{3,4,3,2,4},{3,2,3,4,2},{1,1,2,3,1},{5,3,4,4,3}}));
	}
}
