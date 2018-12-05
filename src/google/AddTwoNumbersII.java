package google;

import dataStructures.ListNode;

import java.util.Stack;

/**
 * 445. Add Two Numbers II
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {
	// Solution 1: or we can add dummy nodes with value 0 to make two lists same length
	// Solution 2: using stack to reverse lists
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Stack<Integer> s1 = new Stack<>();
		Stack<Integer> s2 = new Stack<>();
		while (l1 != null) {
			s1.push(l1.val);
			l1 = l1.next;
		}
		while (l2 != null) {
			s2.push(l2.val);
			l2 = l2.next;
		}
		ListNode curHead = null;
		int carry = 0;
		while (!s1.empty() || !s2.isEmpty()) {
			int sum = (s1.isEmpty() ? 0 : s1.pop()) + (s2.isEmpty() ? 0 : s2.pop()) + carry;
			carry = sum / 10;
			ListNode node = new ListNode(sum % 10);
			node.next = curHead;
			curHead = node;
		}
		if (carry == 1) {
			ListNode node = new ListNode(1);
			node.next = curHead;
			curHead = node;
		}
		return curHead;
	}
}
