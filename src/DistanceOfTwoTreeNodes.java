import dataStructures.TreeNode;
import utils.PrintTreeNodeList;

import java.util.ArrayList;
import java.util.List;

public class DistanceOfTwoTreeNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        root.left = n1;
        n1.left = n2;
        n1.right = n3;
        n3.right = n4;

        System.out.println(getDistanceOfTwoTreeNodes(root, n4, n2));
    }
    public static int getDistanceOfTwoTreeNodes(TreeNode root, TreeNode node1, TreeNode node2) {
        if (node1 == node2) {
            return 0;
        }
        List<List<TreeNode>> paths = new ArrayList<>();
        dfs(paths, new ArrayList<>(), root, node1, node2);

        List<TreeNode> path1 = paths.get(0);
        List<TreeNode> path2 = paths.get(1);
        PrintTreeNodeList.printTreeNodeList(path1);
        PrintTreeNodeList.printTreeNodeList(path2);
        int len1 = paths.get(0).size();
        int len2 = paths.get(1).size();

        int i = 0;
        while (i < len1 && i < len2 && path1.get(i) == path2.get(i)) {
            i++;
        }
        return (len1 - i) + (len2 - i);
    }
    private static void dfs(List<List<TreeNode>> paths, List<TreeNode> path, TreeNode cur, TreeNode node1, TreeNode node2) {
        if (cur == null) {
            return;
        }
        if (paths.size() == 2) {
            return;
        }

        path.add(cur);

        if (cur == node1 || cur == node2) {
            paths.add(new ArrayList<>(path));
        }

        dfs(paths, path, cur.left, node1, node2);
        dfs(paths, path, cur.right, node1, node2);

        path.remove(path.size() - 1);
    }
}
