package Airbnb;

import java.util.PriorityQueue;

/**
 * There are 10 wizards, 0-9, you are given a list that each entry is a list of wizards known by wizard.
 * Define the cost between wizards and wizard[j] as square of different of i and j. To find the min cost between 0 and 9.
 *
 * Dijkstra, similar to CheapestFlightsWithKStops
 *
 *
 * 0-9 represent 10 islands
 * each island has one way bridge connecting to other islands, e.g.
 *
 * 0： [1, 3, 5],
 * 1:   [2, 4],
 * ...
 * 9: [3]
 *
 * cost is square of diff of island number: island0- island2 (2-0)^2 = 4
 * find min cost from 0 to 9
 */
public class Wizard {
    public static int minCost(int[][] bridges) {

        // island, cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curIsland = cur[0];
            int curCost = cur[1];
            if (curIsland == 9) {
                return curCost;
            }
            for (int toIsland : bridges[curIsland]) {
                int diff = curIsland - toIsland;
                pq.offer(new int[]{toIsland, curCost + diff * diff});
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(minCost(new int[][]{{1,3,5},{2,4},{9},{},{9},{},{},{},{},{},{3}})); // 35
    }
}
