package google;

import java.util.*;

/**
 * follow up类似于，假设A告诉B一件事就是A->B， 给定一串A->B, A->C, A->D, A->E, E->D, E->F, B->F, 之类的，被告诉的人可以被重复告诉，
 * 然后让你返回第一轮被告诉的人里面（对于这个case，第一轮被告诉的人是B, C, D, E），
 * 拥有最大非重复孩子数（就是A->E, E->D, 所以e,d都是a的孩子，有可能有人被重复告诉）的人
 */
public class DirectedGraphNumOfChildren4Node {
    public static void main(String[] args) {
        GraphNode n1 = new GraphNode('A');
        GraphNode n2 = new GraphNode('B');
        GraphNode n3 = new GraphNode('C');
        GraphNode n4 = new GraphNode('D');
        GraphNode n5 = new GraphNode('E');
        GraphNode n6 = new GraphNode('F');
        n1.children.add(n2); n1.children.add(n3); n1.children.add(n4); n1.children.add(n5);
        n2.children.add(n6); n5.children.add(n4); n5.children.add(n6);
        DirectedGraphNumOfChildren4Node noc = new DirectedGraphNumOfChildren4Node();
        System.out.println(noc.countChildrenOfNode(n1));
    }
    /**
     * find how many children dose a node have
     * @param root
     * @return
     */
    public Integer countChildrenOfNode(GraphNode root) {
        int[] result = new int[1];
        Set<GraphNode> visited = new HashSet<>();
        dfs(result, root, visited);
        return result[0];
    }
    private void dfs(int[] result, GraphNode node, Set<GraphNode> visited) {
        visited.add(node);
        for (GraphNode child : node.children) {
            if (!visited.contains(child)) {
                result[0]++;
                dfs(result, child, visited);
            }
        }
    }
    public static class GraphNode {
        private char value;
        private List<GraphNode> children;

        private GraphNode(char value) {
            this.value = value;
            children = new ArrayList<>();
        }

        private boolean equals(GraphNode n) {
            return this.value == n.value;
        }

        public int hashCode() {
            return (int) value;
        }
    }

}
