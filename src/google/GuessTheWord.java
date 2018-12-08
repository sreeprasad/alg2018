package google;

import java.util.*;

/**
 * 843. Guess the Word
 *
 * This problem is an interactive problem new to the LeetCode platform.
 *
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 *
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
 *
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
 *
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
 *
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
 *
 * Example 1:
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
 *
 * Explanation:
 *
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 *
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 * Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
 */
public class GuessTheWord {
	public void findSecretWord(String[] wordlist, Master master) {
		Set<String> set = new HashSet<>(Arrays.asList(wordlist));

		int count = 0;
		int len = wordlist.length;
		Random random = new Random();

		while (count++ < 10) {
			Iterator<String> iterator = set.iterator();
			if (iterator.hasNext()) {
				String selected = iterator.next();
				int curMatch = master.guess(selected);
				shrinkSet(set, selected, curMatch);
			}
		}
	}
	private void shrinkSet(Set<String> set, String s, int matchCount) {
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			if (getMatchCount(s, iterator.next()) != matchCount) {
				iterator.remove();
			}
		}
	}

	// solution 2 with Random
	public void findSecretWordII(String[] wordlist, Master master) {
		for (int i = 0; i < 10; ++i) {
			int index = new Random().nextInt(wordlist.length);
			String str = wordlist[index];
			int x = master.guess(str);

			if (x == 6) {
				return;
			}

			List<String> list = new ArrayList<>();

			for (String w : wordlist) {
				if (getMatchCount(str, w) == x) {
					list.add(w);
				}
			}

			wordlist = list.toArray(new String[list.size()]);
		}
	}

	private int getMatchCount(String s1, String s2) {
		int count = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) == s2.charAt(i)) {
				count++;
			}
		}
		return count;
	}
	// This is the Master's API interface.
	// You should not implement it, or speculate about its implementation
	interface Master {
		public int guess(String word);
	}
}
