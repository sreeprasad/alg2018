package Airbnb;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }
    private static void dfs(List<List<Integer>> result, List<Integer> path, int[] candidates, int target, int start) {
        if (start >= candidates.length || target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(result, path, candidates, target - candidates[i], i);
            path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,6,7}, 7));
        System.out.println(combinationSum(new int[]{2,3,5}, 8));
        System.out.println(combinationSum(new int[]{5,6,4,2,7}, 8));
    }
}
