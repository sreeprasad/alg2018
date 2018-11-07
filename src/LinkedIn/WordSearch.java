package LinkedIn;

/**
 * 79. Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class WordSearch {
	public static boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0 || board[0].length == 0) return false;
		if (word == null || word.length() == 0) return true;
		boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (dfs(board, word, visited, i, j, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	private static boolean dfs(char[][] board, String word, boolean[][] visited, int i, int j, int index) {
		if (index == word.length()) return true; // need to be checked first
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
		if (board[i][j] != word.charAt(index) || visited[i][j]) return false;

		visited[i][j] = true;
		boolean result = dfs(board, word, visited, i - 1, j, index + 1)
				|| dfs(board, word, visited, i + 1, j, index + 1)
				|| dfs(board, word, visited, i, j - 1, index + 1)
				|| dfs(board, word, visited, i, j + 1, index + 1);
		visited[i][j] = false;

		return result;
	}
	public static void main(String[] args) {
		System.out.println(exist(new char[][]{{'a'},{'a'}}, "aaa")); // false
	}
}
