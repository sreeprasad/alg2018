package google;

import java.util.HashSet;
import java.util.Set;

/**
 * 947. Most Stones Removed with Same Row or Column
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 *
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 *
 * What is the largest possible number of moves we can make?
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 */
public class MostStonesRemovedWithSameRowOrColumn {
	// solution 1: DFS
	public int removeStones(int[][] stones) {
		int countGroup = 0;
		Set<int[]> visited = new HashSet<>();
		for (int i = 0; i < stones.length; i++) {
			if (visited.contains(stones[i])) {
				continue;
			}
			countGroup++;
			dfs(stones, visited, stones[i]);
		}
		return stones.length - countGroup;
	}
	private void dfs(int[][] stones, Set<int[]> visited, int[] cur) {
		if (visited.size() == stones.length) {
			return;
		}
		visited.add(cur);
		for (int i = 0; i < stones.length; i++) {
			if (visited.contains(stones[i])) {
				continue;
			}
			if (cur[0] == stones[i][0] || cur[1] == stones[i][1]) {
				dfs(stones, visited, stones[i]);
			}
		}
	}



	// solution 2: union find
	public int removeStonesII(int[][] stones) {
		int removeCount = 0;
		UnionFind uf = new UnionFind(stones.length);
		for (int i = 0; i < stones.length - 1; i++) {
			for (int j = i + 1; j < stones.length; j++) {
				if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
					if (uf.union(i, j)) {
						removeCount++;
					}
				}
			}
		}
		return removeCount;
	}
	class UnionFind {

		int[] parent;

		UnionFind(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		boolean union(int i, int j) {
			int pi = find(i);
			int pj = find(j);
			if (pi != pj) {
				parent[pj] = pi;
				return true;
			}
			return false;
		}

		int find(int i) {
			while (parent[i] != i) {
				i = parent[i];
			}
			return i;
		}
	}
	public static void main(String[] args) {
		MostStonesRemovedWithSameRowOrColumn msrw = new MostStonesRemovedWithSameRowOrColumn();
		System.out.println(msrw.removeStonesII(new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}})); // 5
	}
}
