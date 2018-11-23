package google;

/**
 * 304. Range Sum Query 2D - Immutable
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DImmutable {
	private int[][] sumMatrix;
	private int[][] matrix;

	public RangeSumQuery2DImmutable(int[][] matrix) {
		this.matrix = matrix;
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			sumMatrix = matrix;
			return;
		}
		sumMatrix = new int[matrix.length + 1][matrix[0].length + 1];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				sumMatrix[i + 1][j + 1] = matrix[i][j] + sumMatrix[i + 1][j] + sumMatrix[i][j + 1] - sumMatrix[i][j];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (sumMatrix == null || sumMatrix.length == 0 || sumMatrix[0].length == 0) return -1;
		return sumMatrix[row2 + 1][col2 + 1] - sumMatrix[row2 + 1][col1] - sumMatrix[row1][col2 + 1] + sumMatrix[row1][col1];
	}

	public static void main(String[] args) {
		RangeSumQuery2DImmutable rs = new RangeSumQuery2DImmutable(new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}});
		System.out.println(rs.sumRegion(2,1,4,3)); // 8
		System.out.println(rs.sumRegion(1,1,2,2)); // 11
		System.out.println(rs.sumRegion(1,2,2,4)); // 12
		RangeSumQuery2DImmutable rs1 = new RangeSumQuery2DImmutable(new int[0][]);
	}
}
