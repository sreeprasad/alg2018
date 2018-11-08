package LinkedIn;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. Permutations
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		dfs(result, new ArrayList<>(), nums);
		return result;
	}
	private void dfs(List<List<Integer>> result, List<Integer> path, int[] nums) {
		if (path.size() == nums.length) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (path.contains(nums[i])) {
				continue;
			}
			path.add(nums[i]);
			dfs(result, path, nums);
			path.remove(path.size() - 1);
		}
	}
	public static void main(String[] args) {
		System.out.println(new Permutations().permute(new int[]{1,2,3}));
	}
}
