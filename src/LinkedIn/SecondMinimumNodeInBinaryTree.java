package LinkedIn;

import dataStructures.TreeNode;

/**
 * 671. Second Minimum Node In a Binary Tree
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
 *
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead.
 *
 * Example 1:
 * Input:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 * Example 2:
 * Input:
 *     2
 *    / \
 *   2   2
 *
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class SecondMinimumNodeInBinaryTree {
	public static int findSecondMinimumValue(TreeNode root) {
		if ((root == null) || (root.left == null && root.right == null)) return -1;

		int left = root.left.val;
		int right = root.right.val;

		// if value same as root value, need to find the next candidate
		// check both of the children in case two children have same value
		if (root.left.val == root.val) {
			left = findSecondMinimumValue(root.left);
		}
		if (root.right.val == root.val) {
			right = findSecondMinimumValue(root.right);
		}

		if (left != -1 && right != -1) return Math.min(left, right);
		else if (left != -1) return left;
		else return right;

	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(5); root.left = n1; root.right = n2;
		TreeNode n3 = new TreeNode(5);
		TreeNode n4 = new TreeNode(7); n2.left = n3; n2.right = n4;
		System.out.println(findSecondMinimumValue(root));

		root = new TreeNode(2);
		TreeNode n5 = new TreeNode(2);
		TreeNode n6 = new TreeNode(2);
		root.left = n5; root.right = n6;
		System.out.println(findSecondMinimumValue(root));
	}
}
