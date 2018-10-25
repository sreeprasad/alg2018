package Airbnb;

import utils.PrintArray;

import java.util.PriorityQueue;

/**
 * input A = [1.2, 2.3, 3.4]
 * Round(1.2 + 2.3 + 3.4) = 6.9 => 7
 * 1 + 2 + 3 => 6
 * 0.2 + 0.3 + 0.4 = 0.9
 *
 * 1 + 3 + 3 => 7
 * 0.2 + 0.7 + 0.4 = 1.3
 *
 * 1 + 2 + 4 => 7
 * 0.2 + 0.3 + 0.6 = 1.1
 * [1,2,4] is better than [1,3,3]
 */
public class RoundPrices {
    public static void main(String[] args) {
        PrintArray.printLongArray(roundPrices(new double[]{1.2, 2.3, 3.4}));    // 1, 2, 4
        PrintArray.printLongArray(roundPrices(new double[]{1.3, 1.6}));         // 1, 2
        PrintArray.printLongArray(roundPrices(new double[]{1.1, 1.6, 1.7}));    // 1, 1, 2
        PrintArray.printLongArray(roundPrices(new double[]{1.3, 1.4, 1.9}));    // 1, 2, 2
        PrintArray.printLongArray(roundPrices(new double[]{1.0, 1.0, 1.0}));    // 1, 1, 1
    }
    public static long[] roundPrices(double[] prices) {

        double sum = 0;
        long floorSum = 0;
        long[] floorPrices = new long[prices.length];

        for (int i = 0; i < prices.length; i++) {
            sum += prices[i];
            floorPrices[i] = (long) Math.floor(prices[i]);
            floorSum += floorPrices[i];
        }

        long roundOfSum = Math.round(sum);
        long diffOfSum = roundOfSum - floorSum; // can be positive or zero

        if (diffOfSum == 0) {
            return floorPrices;
        }


        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0] == 0 ? 0 : (a[0] - b[0] > 0 ? 1 : -1)));
        for (int i = 0; i < prices.length; i++) {
            pq.offer(new double[]{Math.ceil(prices[i]) - prices[i], i});
        }

        for (int i = 0; i < diffOfSum; i++) {
            double[] pair = pq.poll();
            floorPrices[(int) pair[1]]++;
        }

        return floorPrices;


        // my first idea, little bit complicated, gave up

        // roundOfSum == sumOfRound
        // 1.3 + 1.6 = 2.9 -> 3
        // 1 + 2 = 3

        // roundOfSum < sumOfRound
        // 1.1 + 1.6 + 1.7 = 4.4 -> 4
        // 1 + 2 + 2 = 5, 0.1 + 0.4 + 0.3 = 0.8
        // 1 + 1 + 2 = 4, 0.1 + 0.6 + 0.3 = 1.0
        // decrease the top k (k = diff) number whose decimal part > .5 and closest to .5 (floor())

        // roundOfSum > sumOfRound
        // 1.3 + 1.4 + 1.9 = 4.6 -> 5
        // 1 + 1 + 2 = 4, 0.3 + 0.4 + 0.1 = 0.8
        // 1 + 2 + 2 = 5, 0.3 + 0.6 + 0.1 = 1.0
        // increase the top k (k = diff) number whose decimal part < .5 and closest to .5 (ceil())


    }
}
