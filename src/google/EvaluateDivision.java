package google;

import utils.PrintArray;

import java.util.*;

/**
 * 399. Evaluate Division
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */
public class EvaluateDivision {
	public static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		// can be treated like a directed weighted graph
		Map<String, Map<String, Double>> graph = new HashMap<>();
		// build graph
		for (int i = 0; i < equations.length; i++) {
			graph.putIfAbsent(equations[i][0], new HashMap<>());
			graph.putIfAbsent(equations[i][1], new HashMap<>());
			graph.get(equations[i][0]).putIfAbsent(equations[i][1], values[i]);
			graph.get(equations[i][1]).putIfAbsent(equations[i][0], 1.0 / values[i]);
		}
		double[] answers = new double[queries.length];

		for (int i = 0; i < queries.length; i++) {
			Set<String> visited = new HashSet<>(); // avoid infinite cycle
			answers[i] = dfs(graph, queries[i][0], queries[i][1], 1.0, visited);
		}
		return answers;
	}
	private static double dfs(Map<String, Map<String, Double>> graph, String start, String end, double answer, Set<String> visited) {
		if (graph.get(start) == null || graph.get(end) == null) { // start or end does not exist
			return -1.0;
		}
		if (visited.contains(start)) {
			return -1.0;
		}
		visited.add(start);
		if (graph.get(start) != null && graph.get(start).get(end) != null) { // found
			 return answer * graph.get(start).get(end);
		}
		for (String child : graph.get(start).keySet()) {
			double next = dfs(graph, child, end, answer * graph.get(start).get(child), visited);
			if (next != -1.0) {
				return next;
			}
		}
		return -1.0;
	}
	public static void main(String[] args) {
		double[] result = calcEquation(new String[][]{{"a", "b"}, {"b", "c"}}, new double[]{2.0, 3.0}, new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}});
		PrintArray.printDoubleArray(result);
	}
}
