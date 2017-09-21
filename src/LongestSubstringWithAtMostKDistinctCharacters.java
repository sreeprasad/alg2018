import java.util.HashMap;
import java.util.Map;

/**
 * Created by lingjmeng on 9/20/17.
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {

        Map<Character, Integer> map = new HashMap<>();
        int l = 0;
        int best = 0;

        for (int r = 0; r < s.length(); r++) { // i is right pointer
            char c = s.charAt(r);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char leftChar = s.charAt(l);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                l++;
            }
            best = Math.max(best, r - l + 1);
        }
        return best;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
    }
}
