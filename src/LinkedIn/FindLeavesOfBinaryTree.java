package LinkedIn;

import dataStructures.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 366. Find Leaves of Binary Tree
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 *
 *
 *
 * Example:
 *
 * Input: [1,2,3,4,5]
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Output: [[4,5,3],[2],[1]]
 *
 *
 * Explanation:
 *
 * 1. Removing the leaves [4,5,3] would result in this tree:
 *
 *           1
 *          /
 *         2
 *
 *
 * 2. Now removing the leaf [2] would result in this tree:
 *
 *           1
 *
 *
 * 3. Now removing the leaf [1] would result in the empty tree:
 *
 *           []
 */
public class FindLeavesOfBinaryTree {
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		dfs(root, result);
		return result;
	}
	private int dfs(TreeNode node, List<List<Integer>> result) {
		if (node == null) return 0;
		int level = Math.max(dfs(node.left, result), dfs(node.right, result));
		if (result.size() < level) result.add(new ArrayList<>());
		result.get(level - 1).add(node.val);
		return level;
	}
}
