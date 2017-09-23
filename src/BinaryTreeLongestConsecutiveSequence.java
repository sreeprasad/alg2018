import dataStructures.TreeNode;


public class BinaryTreeLongestConsecutiveSequence {

    private static int longestConsecutive(TreeNode root) {

        if (root == null) return 0;

        return Math.max(dfs(root.left, root.val, 1), dfs(root.right, root.val, 1));
    }

    private static int dfs(TreeNode node, int last, int count) {

        if (node == null) return count;

        if (node.val - last == 1) {
            count++;
        } else {
            count = 1;
        }

        int left = dfs(node.left, node.val, count);
        int right = dfs(node.right, node.val, count);

        return Math.max(count, Math.max(left, right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(3);
        root.left = n1;
        TreeNode n2 = new TreeNode(2);
        n1.left = n2;
        TreeNode n3 = new TreeNode(4);
        n1.right = n3;
        TreeNode n4 = new TreeNode(5);
        n3.right = n4;

        System.out.println(BinaryTreeLongestConsecutiveSequence.longestConsecutive(root));
    }
}
