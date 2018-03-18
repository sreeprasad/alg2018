import java.util.*;

/**
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs.
 * The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4.
 * Reconstruction means building a shortest common supersequence of the sequences in seqs
 * (i.e., a shortest sequence so that all sequences in seqs are subsequences of it).
 * Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 *
 Given org = [1,2,3], seqs = [[1,2],[1,3]]
 Return false
 Explanation:
 [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.

 Given org = [1,2,3], seqs = [[1,2]]
 Return false
 Explanation:
 The reconstructed sequence can only be [1,2].

 Given org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
 Return true
 Explanation:
 The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

 Given org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
 Return true
 */
public class SequenceReconstruction {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public static boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // write your code here

        int n = org.length;
        int[] inDegree = new int[n + 1];
        Map<Integer, Set<Integer>> edges = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            edges.put(i, new HashSet<Integer>());
        }

        int count = 0;
        for (int i = 0; i < seqs.length; i++) {
            int[] seq = seqs[i];
            count += seq.length;
            if (seq.length > 0 && (seq[0] <= 0 || seq[0] > n)) { // validation 1
                return false;
            }
            for (int j = 1; j < seq.length; j++) {
                if (seq[j] <= 0 || seq[j] > n) {
                    return false;
                }
                if (edges.containsKey(seq[j - 1])) {
                    if (edges.get(seq[j - 1]).add(seq[j])) { // check if already added
                        inDegree[seq[j]]++;
                    }
                } else {
                    return false; // validation 2
                }
            }
        }

        if (count < n) {
            return false; // validation 3
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        count = 0;
        while (queue.size() == 1) {
            int cur = queue.poll();
            if (org[count] != cur) { // validation 4
                return false;
            }
            count++;
            for (int neighbor : edges.get(cur)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return count == n; // validation 5
    }

    public static void main(String[] args) {
        int[] org = {5,3,2,4,1};
        int[][] seqs = {{5,3,2,4},{4,1},{1},{3},{2,4},{1,1000000000}};
        boolean result = SequenceReconstruction.sequenceReconstruction(org, seqs);
        System.out.println(result);
    }
}
