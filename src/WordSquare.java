import java.util.ArrayList;
import java.util.List;

public class WordSquare {
    private static List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) return null;
        List<List<String>> result = new ArrayList<>();
        dfs(words, new ArrayList<>(), result);
        return result;
    }
    private static void dfs(String[] words, List<String> list, List<List<String>> result) {
        int curLen = list.size();
        int expLen = words[0].length();
        if (curLen == expLen) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < words.length; i++) {
            if (isValid(words[i], list)) {
                list.add(words[i]);
                dfs(words, list, result);
                list.remove(list.size() - 1);
            }
        }
    }
    private static boolean isValid(String word, List<String> list) {
        if (list.size() == 0) return true;
        for (int i = 0; i < list.size(); i++) {
            if (word.charAt(i) != list.get(i).charAt(list.size())) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String[] words = {"area", "lead", "wall", "lady", "ball"};
        List<List<String>> result = wordSquares(words);
        for (List<String> wordSquare : result) {
            for (String aWordSquare : wordSquare) {
                System.out.print(aWordSquare + ", ");
            }
            System.out.println();
        }
    }
}
