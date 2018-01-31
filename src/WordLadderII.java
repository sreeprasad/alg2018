import java.util.*;

public class WordLadderII {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        if (dict == null) return null;

        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> distanceMap = new HashMap<>(); // distance to end
        Map<String, List<String>> graphMap = new HashMap<>();

        dict.add(start);
        dict.add(end);

        bfs(end, distanceMap, graphMap, dict);
        dfs(distanceMap, graphMap, start, end, dict, result, new ArrayList<String>());

        return result;
    }
    private void dfs(Map<String, Integer> distanceMap, Map<String, List<String>> graphMap, String curStr, String end, Set<String> dict, List<List<String>> result, List<String> path) {
        path.add(curStr);
        if (curStr.equals(end)) {
            result.add(new ArrayList<String>(path));
        } else {
            for (String nextLevelStr : graphMap.get(curStr)) {
                if (distanceMap.containsKey(nextLevelStr) && distanceMap.get(nextLevelStr) == distanceMap.get(curStr) - 1) {
                    dfs(distanceMap, graphMap, nextLevelStr, end, dict, result, path);
                }
            }
        }
        path.remove(path.size() - 1); // remember to backtrack
    }
    private void bfs(String startWord, Map<String, Integer> distanceMap, Map<String, List<String>> graphMap, Set<String> dict) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(startWord);
        distanceMap.put(startWord, 0);
        for (String word : dict) {
            graphMap.put(word, new ArrayList<String>());
        }
        while (!queue.isEmpty()) {
            String curStr = queue.poll();
            List<String> nextLevel = getNextLevel(curStr, dict);
            for (String nextLevelStr : nextLevel) {
                if (graphMap.containsKey(curStr)) {
                    graphMap.get(nextLevelStr).add(curStr);
                }
                if (!distanceMap.containsKey(nextLevelStr)) {
                    distanceMap.put(nextLevelStr, distanceMap.get(curStr) + 1);
                    queue.offer(nextLevelStr);
                }
            }
        }
    }
    private List<String> getNextLevel(String word, Set<String> dict) {
        List<String> nextLevel = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (word.charAt(i) != ch) {
                    String newStr = word.substring(0, i) + ch + word.substring(i + 1);
                    if (dict.contains(newStr)) {
                        nextLevel.add(newStr);
                    }
                }
            }
        }
        return nextLevel;
    }

    public static void main(String[] args) {
        WordLadderII wlii = new WordLadderII();
        Set<String> dict = new HashSet<>();
        dict.add("hot"); dict.add("dot"); dict.add("dog"); dict.add("lot"); dict.add("log");
        System.out.println(wlii.findLadders("hit", "cog", dict));
    }
}