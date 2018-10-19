package google;

/**
 * blackjack -> 21
 * Consider a simplified game of blackjack / 21, with an infinite deck of cards labeled 1-10.
 * That means there is always a 10% chance to draw any card value regardless of what's been drawn before.
 * The dealer always plays deterministically, according to the following rules:
 * * If the dealer's total is less than 17, the dealer draws another card.
 * * If the dealer's total is at least 17, but not more than 21, the dealer stands.
 * * If the dealer's total is over 21 the dealer has busted (lost).
 * Implement a function to determine the probability the dealer will bust if the current total is ‘total’.
 */
public class BustProbabilityBlackJack {
    public static double bustProb(int total) {
        if (total >= 17 && total <= 21) {
            return 0;
        }
        if (total > 21) {
            return 1;
        }
        double prob = 0;
        for (int num = 1; num <= 10; num++) {
            prob += 0.1 * bustProb(total + num);
        }
        return prob;
    }
    public static void main(String[] args) {
        System.out.println(bustProb(0));
    }

}
