package Airbnb;

import java.util.*;

/**
 * one user post, the followers would repost，find the minimum group of people could trigger the whole network

 * Given a directed graph, find the minimal number of vertices that can traverse all the vertices from them.
 * For example
 * 2->3->1->2->0, 4->5
 * Then we need [1, 4] (or [2, 4], [3, 4]) to traverse all the vertices.
 * Only one solution is needed if there are more than one possibilities.
 */
public class MinimumVerticesToTraverseDirectedGraph {
    // use topological sort
    // nodes that without in-degree are the result, if no cycle in the graph
    // if cycle exists
    // 1. get all indegree=0 node and add them to result; 2. extract all nodes that are reachable from step 1 (BFS)
    // 3. extract from node set, left node in cycles
    // 4. put an unvisited node to result and do dfs on it
    // 5. repeat step 4 until all nodes are visited

/*
    public List<Integer> getMinVertices(int[][] edges, int n) {
        Set<Integer> res = new HashSet<>();

        Map<Integer, Set<Integer>> nodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodes.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            nodes.get(edge[0]).add(edge[1]);
        }

        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) {
                continue;
            }
            res.add(i); // means a new group entry
            visited.add(i);
            Set<Integer> curGroup = new HashSet<>();
            dfs(res, nodes, visited, curGroup, i, i);
        }

        return new ArrayList<>(res);
    }
    private void dfs(Set<Integer> result, Map<Integer, Set<Integer>> nodes, Set<Integer> visited, Set<Integer> curGroup, int cur, int start) {
        for (int next : nodes.get(cur)) {
            // if 1->2->3, 1 is start, but 2 has been visited, remove 2 as 2 is childre of 1
            if (result.contains(next) && next != start) {
                result.remove(next);
            }
            if (!curGroup.contains(next)) {
                curGroup.add(next);
                visited.add(next);
                dfs(result, nodes, visited, curGroup, next, start);
            }
        }
    }
    */
}
