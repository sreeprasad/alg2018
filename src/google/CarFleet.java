package google;

import java.util.ArrayList;
import java.util.List;

/**
 * 853. Car Fleet
 * DescriptionHintsSubmissionsDiscussSolution
 * N cars are going to the same destination along a one lane road.  The destination is target miles away.
 *
 * Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
 *
 * A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
 *
 * The distance between these two cars is ignored - they are assumed to have the same position.
 *
 * A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.
 *
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 *
 *
 * How many car fleets will arrive at the destination?
 *
 *
 *
 * Example 1:
 *
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * Output: 3
 * Explanation:
 * The cars starting at 10 and 8 become a fleet, meeting each other at 12.
 * The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 and 3 become a fleet, meeting each other at 6.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 *
 * Note:
 *
 * 0 <= N <= 10 ^ 4
 * 0 < target <= 10 ^ 6
 * 0 < speed[i] <= 10 ^ 6
 * 0 <= position[i] < target
 * All initial positions are different.
 */
public class CarFleet {
    public static int carFleet(int target, int[] position, int[] speed) {
        double[] timeToArriveDest = new double[target + 1]; // 0 - destination
        for (int i = 0; i < position.length; i++) {
            timeToArriveDest[position[i]] = (double) (target - position[i]) / speed[i]; // count sorting
        }
        int fleetCount = 0;
        double prev = 0;
        for (int i = timeToArriveDest.length - 1; i >= 0; i--) {
            if (timeToArriveDest[i] > prev) {
                fleetCount++;
                prev = timeToArriveDest[i];
            }
        }
        return fleetCount;
    }
    public static void main(String[] args) {
        System.out.println(carFleet(12, new int[]{10,8,0,5,3}, new int[]{2,4,1,1,3}));
        System.out.println(carFleetII(new int[]{2,4,1,3})); // [2, 2]
        System.out.println(carFleetII(new int[]{2,5,4,3,1})); // [4, 1]
    }
    /*
     * 变种：没有position
     * Imagine a scenario where there are N cars on an infinitely long single-lane road. Each car has a unique,
     * permanent integer speed ranging between 1 and N, inclusive (units are irrelevant).
     * The cars can be placed in any order along the road and then told to start driving indefinitely.
     * Let's assume that the cars are traveling from right-to-left. So the leftmost car is at the front.
     * Given an ordering of N cars, construct an algorithm to return an array of cluster sizes
     *
     * N=4
     *
     * [2, 4, 1, 3]   -> [2, 2]
     *
     * [2, 5, 4, 3, 1] -> [4, 1]
     *
     */
    public static List<Integer> carFleetII(int[] speed) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        while (i < speed.length) {
            int count = 1;
            int j = i + 1;
            while (j < speed.length && speed[j] > speed[i]) {
                count++;
                j++;
            }
            result.add(count);
            i = j;
        }
        return result;
    }
    /* followup：
     * New car speed = N+1.  Given an ordering of N cars, construct an algorithm to return an array of arrays of cluster sizes
     * that will arise when the N+1 car is inserted.
     * The ith row in the outer array corresponds to the cluster sizes that exist when the N+1 car is inserted into the ith index
     *
     * new car speed = 5
     * [2, 2]
     * [2, 4, 1, 3]  -> [[1, 2, 2], [3, 2], [3, 2], [2, 3], [2, 3]]
     */
    public static List<List<Integer>> carFleetIII(int[] speed, int newSpeed) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> carFleet = carFleetII(speed);
        // not finished

        return result;
    }
}
