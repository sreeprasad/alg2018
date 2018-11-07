package LinkedIn;

import dataStructures.ListNode;

/**
 * 24. Swap Nodes in Pairs
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Note:
 *
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 */
public class SwapNodesInPairs {
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode n1 = head;
		ListNode n2 = head.next;
		head = n2;
		ListNode prev = null;
		while (n1 != null && n2 != null) {
			n1.next = n2.next; // n1.next = n3
			n2.next = n1;
			if (prev != null) prev.next = n2;
			prev = n1;
			n1 = n1.next;
			if (n1 != null) n2 = n1.next;
		}
		return head;
	}
}
