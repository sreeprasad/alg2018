package Airbnb;

import java.util.HashMap;
import java.util.Map;

/**
 * n = n%2==0 ? n/2 : 3n+1
 * find longest chain length
 *
 * 题目是：
 * n = n / 2   如果n是偶数
 * n = 3n+1  如果n是奇数
 * 比如 10->5->16->8->4->2->1，这个以10 为基数的链的长度是7.
 * (面试官提到，任何一个正整数最后都会变成1，虽然没有严格的数学证明，但是我们假设它是成立的)
 *
 * 要是你找出1-1M之间最长的链的长度。
 *
 * 存在的坑有：
 * 1. 一开始我定义了一个一个长度为1M+1的dp数组，但是其实过程中运算到的数会远大于1M（因为有个乘法因子3）
 * 2. 后来换成一个int to int的HashMap，小数还能跑通，但是后来发现用int会越界，最后换成了Long to int的map
 * 3. 最后，因为是在codepad上在线run的，结果显示out of memory
 *
 */
public class CollatzConjecture {

    public static int findLongestSequence(int n) {

        int longest = 0;
        Map<Long, Integer> cache = new HashMap<>(); // use long to avoid overflow
        cache.put(1L, 1);

        for (int i = 1; i <= n; i++) {
            longest = Math.max(longest, helper(i, cache));
        }
        return longest;
    }
    private static int helper(long i, Map<Long, Integer> cache) {
        if (cache.containsKey(i)) {
            return cache.get(i);
        }
        int len = (i % 2 == 0) ? 1 + helper(i / 2, cache) : 1 + helper(i * 3 + 1, cache);
        cache.put(i, len);
        return len;
//        if (n == 1) {
//            return 1;
//        }
//        return (i % 2 == 0) ? 1 + findLongestSequence(i / 2) : 1 + findLongestSequence(i * 3 + 1);
    }
    public static void main(String[] args) {
        System.out.println(findLongestSequence(1000000));
    }
}
