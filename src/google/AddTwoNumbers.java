package google;

import dataStructures.ListNode;

/**
 * 2. Add Two Numbers
 * Medium
 *
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
	// more decent solution
	public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode p = dummy;
		int carry = 0;
		while (l1 != null || l2 != null || carry > 0) {
			int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
			if (l1 != null) l1 = l1.next;
			if (l2 != null) l2 = l2.next;
			sum = sum + carry;

			carry = sum / 10;
			sum = sum % 10;

			p.next = new ListNode(sum);
			p = p.next;

		}
		return dummy.next;
	}
	// my original solution, basically the same
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode n1 = l1;
		ListNode n2 = l2;
		ListNode dummy = new ListNode(0);
		ListNode p = dummy;
		int carry = 0;
		while (n1 != null && n2 != null) {
			int sum = n1.val + n2.val;
			ListNode node = new ListNode((sum + carry) % 10);
			carry = (sum + carry) / 10;
			p.next = node;
			p = p.next;
			n1 = n1.next;
			n2 = n2.next;
		}
		ListNode temp = n1 != null ? n1 : n2;
		n1 = temp;
		n2 = null;

		while (n1 != null) {
			ListNode node = new ListNode((n1.val + carry) % 10);
			carry = (n1.val + carry) / 10;
			p.next = node;
			p = p.next;
			n1 = n1.next;
		}

		if (carry == 1) {
			p.next = new ListNode(carry);
		}

		return dummy.next;
	}
}
