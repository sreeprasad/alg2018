package LinkedIn;

import dataStructures.TreeNode;

import java.util.Stack;

/**
 * 173. Binary Search Tree Iterator
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 *
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 */
public class BinarySearchTreeIterator {

	Stack<TreeNode> stack = new Stack<>();

	public BinarySearchTreeIterator(TreeNode root) {
		TreeNode node = root;
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode cur = stack.pop();
		TreeNode next = cur.right;
		if (next != null) {
			stack.push(next);
			next = next.left;
			while (next != null) {
				stack.push(next);
				next = next.left;
			}
		}
		return cur.val;
	}
}
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */