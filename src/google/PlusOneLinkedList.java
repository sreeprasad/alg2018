package google;

import dataStructures.ListNode;

/**
 * 369. Plus One Linked List
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example :
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 */
public class PlusOneLinkedList {
	public ListNode plusOne(ListNode head) {
		// 1. add one to tail node
		// 2. carry one to second last node
		// 3. add one node at head
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode i = dummy; // whenever carry happens, i is the one to increment
		ListNode j = dummy;
		while (j.next != null) {
			j = j.next;
			if (j.val != 9) {
				i = j;
			}
		}
		if (j.val != 9) {
			j.val += 1;
		} else {
			i.val += 1;
			while (i.next != null) {
				i = i.next;
				i.val = 0;
			}
		}

		return dummy.val == 1? dummy : head;
	}
	public static void main(String[] args) {
		PlusOneLinkedList pol = new PlusOneLinkedList();
		ListNode n1 = new ListNode(9);
		ListNode n2 = new ListNode(9);
		ListNode n3 = new ListNode(9);
		n1.next = n2; n2.next = n3;
		ListNode result = pol.plusOne(n1);
		System.out.println();
	}
}
