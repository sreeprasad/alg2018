package google;

import dataStructures.TreeNode;

import java.util.Stack;

/**
 * 222. Count Complete Tree Nodes
 * DescriptionHintsSubmissionsDiscussSolution
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example:
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 */
public class CountCompleteTreeNodes {
	// Solution 1: binary search on bottom level
	public int countNodes(TreeNode root) {
		if (root == null) return 0;
		int height = 0;
		TreeNode cur = root;
		while (cur.left != null) {
			height++;
			cur = cur.left;
		}
		int l = (int) Math.pow(2, height); // index of first node of bottom level
		int r = (int) Math.pow(2, height + 1) - 1; // index of last node of bottom level
		while (l + 1 < r) { // binary search on bottom level
			int mid = l + (r - l) / 2;
			if (exists(root, mid)) {
				l = mid;
			} else {
				r = mid - 1;
			}
		}
		if (exists(root, r)) return r;
		else return l;
	}
	private boolean exists(TreeNode root, int index) {
		Stack<Integer> stack = new Stack<>();
		while (index > 1) {
			stack.push(index); // start from second level 2, 3, ...
			index /= 2;
		}
		TreeNode cur = root;
		int i = 1;
		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (cur.left != null && i * 2 == v) {
				cur = cur.left;
				i *= 2;
			} else if (cur.right != null && i * 2 + 1 == v) {
				cur = cur.right;
				i = i * 2 + 1;
			} else {
				return false;
			}
		}
		return true;
	}

	// Solution 2:
	public int countNodesII(TreeNode root) {
		int h = height(root);
		int count = 0;
		TreeNode cur = root;
		while (cur != null) {
			if (height(cur.right) == h - 1) {
				count += (1 << h);
				cur = cur.right;
			} else {
				count += (1 << (h - 1));
				cur = cur.left;
			}
			h--; // next level
		}
		return count;
	}
	private int height(TreeNode node) {
		if (node == null) return -1;
		return 1 + height(node.left);
	}

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		n1.left = n2; n1.right = n3;
		n2.left = n4; n2.right = n5;
		n3.left = n6;
		CountCompleteTreeNodes cctn = new CountCompleteTreeNodes();
		System.out.println(cctn.countNodesII(n1)); // 6
	}
}
