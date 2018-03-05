import helpers.PrintArray;

import java.util.ArrayList;
import java.util.List;

/**
 * LintCode 460. Given a target number, a non-negative integer target and an integer array A sorted in ascending order,
 * find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target.
 * Otherwise, sorted in ascending order by number if the difference is same.
 */
public class FindKClosestElements {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public static int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
        int[] result = new int[k];
        if (A == null || A.length == 0 || k == 0) return result;
        int l = 0;
        int r = A.length - 1;
        int closestIndex = 0;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (A[m] == target) {
                closestIndex = m;
                break;
            } else if (A[m] <= target) {
                l = m;
            } else {
                r = m;
            }
        }
        if (A[closestIndex] != target) {
            if (Math.abs(target - A[l]) <= Math.abs(target - A[r])) { // note: <= instead of <
                closestIndex = l;
            } else {
                closestIndex = r;
            }
        }

        l = closestIndex;
        r = closestIndex;
        List<Integer> list = new ArrayList<>();
        list.add(A[closestIndex]);
        int count = 1;
        while (count < k && count < A.length && l - 1 >= 0 && r + 1 < A.length) {
            if (Math.abs(A[l - 1] - target) <= Math.abs(A[r + 1] - target)) { // note: <= instead of <
                list.add(A[--l]);
            } else {
                list.add(A[++r]);
            }
            count++;
        }
        if (l - 1 < 0) {
            while (count < k && count < A.length && r + 1 < A.length) {
                count++;
                list.add(A[++r]);
            }
        }
        if (r + 1 >= A.length) {
            while (count < k && count < A.length && l - 1 >= 0) {
                count++;
                list.add(A[--l]);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int[] output = FindKClosestElements.kClosestNumbers(arr, 2, 3);
        PrintArray.printArray(output);

        int[] arr1 = {1, 4, 6, 8};
        int[] output1 = FindKClosestElements.kClosestNumbers(arr1, 3, 3);
        PrintArray.printArray(output1);

        int[] arr2 = {1,10,15,25,35,45,50,59};
        int[] output2 = FindKClosestElements.kClosestNumbers(arr2, 30, 7);
        PrintArray.printArray(output2);
    }
}
