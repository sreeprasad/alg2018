package google;

/**
 * 135.
 * Candy

 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 */
public class Candy {
	public static int candy(int[] ratings) {
		// space O(n)
		int[] candy = new int[ratings.length];
		candy[0] = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candy[i] = candy[i - 1] + 1;
			} else {
				candy[i] = 1;
			}
		}

		int sum = candy[candy.length - 1];
		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) { // don't forget part after &&
				candy[i] = candy[i + 1] + 1;
			}
			sum += candy[i];
		}

		return sum;
	}
	public static int candyII(int[] ratings) {
		// space O(1)
		int sum = 1;
		int countDown = 0;
		int prev = 1;
		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] >= ratings[i - 1]) {
				if (countDown > 0) {
					sum += (1+countDown)*countDown/2;
					if (countDown >= prev) {
						sum += countDown - prev + 1;
					}
					countDown = 0;
					prev = 1;
				}
				prev = ratings[i] > ratings[i - 1] ? prev + 1 : 1;
				sum += prev;
			} else { // count numbers of descending ratings
				countDown++;
			}
		}
		if (countDown > 0) {
			sum += (1+countDown)*countDown/2;
			if (countDown >= prev) {
				sum += countDown - prev + 1;
			}
		}
		return sum;
	}
	public static void main(String[] args) {
		System.out.println(candyII(new int[]{1,3,4,5,2})); // 11
		System.out.println(candyII(new int[]{1,3,2,2,1})); // 7
	}
}
