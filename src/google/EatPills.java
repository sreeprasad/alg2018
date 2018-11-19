package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 100 pills, take half everyday, simulate the process and give back sequences of "FHFHFFFFH..."
 * https://www.1point3acres.com/bbs/thread-159581-1-1.html
 * https://www.1point3acres.com/bbs/thread-206106-1-1.html
 * https://www.1point3acres.com/bbs/thread-443600-1-1.html
 * https://www.1point3acres.com/bbs/thread-459125-1-1.html
 */
public class EatPills {
	// all combinations
	public List<String> takePills(int n) { // n is number of pills
		List<String> result = new ArrayList<>();
		int[] count = new int[]{0, 0};
		Map<String, String> memo = new HashMap<>();
		dfs(n, 0, result, "", n * 2, count, memo);
		// calculate probability of 1 full pill and 0 half pill left
		System.out.println((double)count[1]/count[0]);
		return result;
	}
	private void dfs(int n, int m, List<String> result, String path, int goal, int[] count, Map<String, String> memo) { // n - number of full pills; m - number of half pills
//		System.out.println("n="+n+" m="+m);
//		String key = "n"+n+"m"+m; // how to use memo?
//		if (memo.containsKey(key)) {
//			String value = path + memo.get(key);
//			result.add(value);
//			return;
//		}
		if (n==1 && m==0) count[1]++;
		if (path.length() == goal) {
			count[0]++;
			result.add(path);
			return;
		}
		if (n > 0) dfs(n - 1, m + 1, result, path + "F", goal, count, memo);
		if (m > 0) dfs(n, m - 1, result, path + "H", goal, count, memo);
	}

	// calculate probability of 1 full pill and 0 half pill left
	public double takePillsII(int n) { // n is number of pills
		int[] count = new int[]{0, 0};
		dfsII(n, 0, count);
		return (double)count[1]/(count[0]+count[1]);
	}
	private void dfsII(int n, int m, int[] count) { // n - number of full pills; m - number of half pills
//		System.out.println("n="+n+" m="+m);
		if (n==1 && m==0) count[1]++;
		if (n==0 && m==2) count[0]++;
		if (n > 0) dfsII(n - 1, m + 1, count);
		if (m > 0) dfsII(n, m - 1, count);
	}

	public static void main(String[] args) {
		EatPills ep = new EatPills();
		System.out.println(ep.takePills(5 ));
//		System.out.println(ep.takePillsII(5));
	}
}
