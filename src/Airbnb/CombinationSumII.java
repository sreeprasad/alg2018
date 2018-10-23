package Airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

 Each number in candidates may only be used once in the combination.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [10,1,2,7,6,1,5], target = 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 Example 2:

 Input: candidates = [2,5,2,1,2], target = 5,
 A solution set is:
 [
 [1,2,2],
 [5]
 ]

 menu order
 given menu, prices are double，how to spend all the money
 solution: price * 100, switch to integer，then same as Combination Sum II
 */
public class CombinationSumII {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(result, new ArrayList<>(), candidates, target, 0);
        for(List<Integer> list :result){
            for(int l:list){

            }
        }
        return result;
    }
    private static void dfs(List<List<Integer>> result, List<Integer> path, int[] candidates, int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // candidate[i - 1] will cover all cases at candidate[i]
            if (i > start && candidates[i] == candidates[i - 1] ) {
                continue;
            }
            path.add(candidates[i]);
            dfs(result, path, candidates, target - candidates[i], i + 1);
            path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{10,1,2,7,6,1,5}, 8));
        System.out.println(combinationSum(new int[]{2,5,2,1,2}, 5));
        System.out.println(combinationSum(new int[]{5,6,4,2,7}, 8));
    }
}
