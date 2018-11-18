import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 943. Find the Shortest Superstring
 * Given an array A of strings, find any smallest string that contains each string in A as a substring.
 *
 * We may assume that no string in A is substring of another string in A.
 *
 *
 * Example 1:
 *
 * Input: ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
 * Example 2:
 *
 * Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 *
 *
 * Note:
 *
 * 1 <= A.length <= 12
 * 1 <= A[i].length <= 20
 */
public class FindShortestSuperstring {

	// my own solution, can get right answer, however get time limit exceeded
	private String shortest = "";
	public String shortestSuperstring(String[] A) {
		Map<String, Map<String, Integer>> graph = new HashMap<>();
		for (String s1 : A) {
			shortest += s1;
			for (String s2 : A) {
				if (s1.equals(s2)) continue;
				int overlap1 = overlap(s1, s2);
				int overlap2 = overlap(s2, s1);
				graph.putIfAbsent(s1, new HashMap<>());
				graph.putIfAbsent(s2, new HashMap<>());
				graph.get(s1).put(s2, overlap1);
				graph.get(s2).put(s1, overlap2);
			}
		}
		for (String s1 : graph.keySet()) {
			Set<String> visited = new HashSet<>();
			visited.add(s1);
			dfs(graph, s1, s1, visited);
		}
		return shortest;
	}
	private void dfs(Map<String, Map<String, Integer>> graph, String path, String prev, Set<String> visited) {
		if (visited.size() == graph.size()) {
			if (path.length() < shortest.length()) {
				shortest = new String(path);
			}
			return;
		}
		for (String s2 : graph.get(prev).keySet()) {
			if (visited.contains(s2)) {
				continue;
			}
			visited.add(s2);
			dfs(graph, path + s2.substring(graph.get(prev).get(s2)), s2, visited);
			visited.remove(s2);
		}
	}
	private int overlap(String s1, String s2) {
		for (int i = Math.min(s1.length(), s2.length()); i > 0; i--) {
			if (s1.substring(s1.length() - i).equals(s2.substring(0, i))) {
				return i;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		FindShortestSuperstring fss = new FindShortestSuperstring();
		System.out.println(fss.shortestSuperstring(new String[]{"catg","ctaagt","gcta","ttca","atgcatc"}).equals("gctaagttcatgcatc")); // "gctaagttcatgcatc"
		// Got TLE for this test case
		System.out.println(fss.shortestSuperstring(new String[]{"uhozqhxcqmkifljvcie","epuhozqhxcqmkifljvci","ugmqnepuhozqhxcqm","iexdudtvurjkrovrhmpa","rovrhmpaasblgaosw","qmkifljvciexdudtv","zsgtuowskyzphybeugm","uowskyzphybeugmq","qhxcqmkifljvciex"}).equals("zsgtuowskyzphybeugmqnepuhozqhxcqmkifljvciexdudtvurjkrovrhmpaasblgaosw"));
	}
}
