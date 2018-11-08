package LinkedIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. Permutations II
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class PermutationsII {
	public List<List<Integer>> permuteUnique(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		dfs(result, new ArrayList<>(), nums, new boolean[nums.length]);
		return result;
	}
	private void dfs(List<List<Integer>> result, List<Integer> path, int[] nums, boolean[] visited) {
		if (path.size() == nums.length) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) continue;
			if (i > 0 && visited[i - 1] && nums[i] == nums[i - 1]) continue;
			path.add(nums[i]);
			visited[i] = true;
			dfs(result, path, nums, visited);
			path.remove(path.size() - 1);
			visited[i] = false;
		}
	}
	public static void main(String[] args) {
		System.out.println(new PermutationsII().permuteUnique(new int[]{1,1,2}));
	}
}
