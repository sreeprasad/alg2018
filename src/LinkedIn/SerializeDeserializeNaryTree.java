package LinkedIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 428. Serialize and Deserialize N-ary Tree
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following 3-ary tree

 * as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 * Note:
 *
 * N is in the range of [1, 1000]
 * Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeDeserializeNaryTree {

//	// Encodes a tree to a single string.
//	public String serialize(Node root) {
//		if (root == null) return null;
//		StringBuilder sb = new StringBuilder();
//
//		sb.append("[");
//		sb.append(root.val);
//		sb.append("[");
//		for (Node child : root.children) {
//			sb.append(serialize(child));
//		}
//		sb.append("]");
//		sb.append("]");
//		return sb.toString();
//	}
//
//	// Decodes your encoded data to tree.
//	public Node deserialize(String data) {
//		if (data == null || data.equals("")) return null;
//		Node root = new Node(Integer.parseInt(data.substring(1, 1 + data.substring(1).indexOf('['))), new ArrayList<>());
//		Node cur = root;
//		Stack<Node> stack = new Stack<>();
//
//		for (int i = 1 + data.substring(1).indexOf('['); i < data.length() - 1;) {
//			char c = data.charAt(i);
//			switch(c) {
//				case '[':
//					stack.push(cur);
//					i++;
//					break;
//				case ']':
//					cur = stack.pop();
//					i++;
//					break;
//				default:
//					int i1 = data.substring(i).indexOf('[');
//					int i2 = data.substring(i).indexOf(']');
//					int index = i + ((i1 == -1) ? (i2 == -1 ? i1 : i2) : Math.min(i1, i2));
//					int value = Integer.parseInt(data.substring(i, index));
//					cur = new Node(value, new ArrayList<>());
//					stack.peek().children.add(cur);
//					i = index;
//			}
//		}
//		return root;
//	}

	// Encodes a tree to a single string.
	public String serializeII(Node root) { // 1[2[]3[5[]6[]]4[]]
		if (root == null) return null;
		StringBuilder sb = new StringBuilder();

		sb.append(root.val);
		sb.append("[");
		for (Node child : root.children) {
			sb.append(serializeII(child));
		}
		sb.append("]");
		return sb.toString();
	}

	public Node deserializeII(String data) {
		int i = 0;
		int len = data.length();
		Node root = null;
		Stack<Node> stack = new Stack<>();

		while (i < len) { // 1[2[]3[5[]6[]]4[]] -> a number always comes with one '[' following right behind
			int start = i;
			while (i < len && Character.isDigit(data.charAt(i))) i++; // '[' or ']'
			if (start == i) { // ']'
				Node child = stack.pop();
				if (stack.isEmpty()) {
					root = child; // root is at bottom of stack
					break;
				}
				stack.peek().children.add(child);
			} else { // number
				stack.push(new Node(Integer.parseInt(data.substring(start, i)), new ArrayList<>()));
			}
			i++; // skip '[' right after number
		}
		return root;
	}

	static class Node {
		int val;
		List<Node> children;
		public Node() {}
		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}

	public static void main(String[] args) {
		Node root = new Node(1, new ArrayList<>());
		Node n2 = new Node(2, new ArrayList<>());
		Node n3 = new Node(3, new ArrayList<>());
		Node n4 = new Node(4, new ArrayList<>());
		Node n5 = new Node(5, new ArrayList<>());
		Node n6 = new Node(6, new ArrayList<>());
		root.children.add(n2); root.children.add(n3); root.children.add(n4);
		n3.children.add(n5); n3.children.add(n6);
		SerializeDeserializeNaryTree s = new SerializeDeserializeNaryTree();
//		String serialized = s.serialize(root); System.out.println(serialized);
//		Node deserialized = s.deserialize(serialized);
//		System.out.println(s.deserialize(s.serialize(null)));
//		n6 = s.deserialize(s.serialize(n6));


		String serialized2 = s.serializeII(root); System.out.println(serialized2);
		Node deserialized = s.deserializeII(serialized2);
		System.out.println();
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

