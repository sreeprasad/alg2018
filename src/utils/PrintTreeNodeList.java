package utils;

import dataStructures.TreeNode;

import java.util.List;

public class PrintTreeNodeList {
    public static void printTreeNodeList(List<TreeNode> list) {
        for (TreeNode node : list) {
            System.out.print(node.val + " ");
        }
        System.out.println();
    }
}
