public class SentenceScreenFitting {
    public static int wordsTyping(String[] sentence, int rows, int cols) {

        int count = 0;
        int i = 0, j = cols;

        while (i <= rows) {

            for (String word : sentence) {

                // word is too long to fit in a row, hence the sentence can't be fitted also
                if (word.length() > cols) {
                    return 0;
                }

                // need to subtract 1 for space
                int spaceLeftInCurrentRow = cols - j - 1;

                if (word.length() <= spaceLeftInCurrentRow) {
                    j = j + 1 + word.length();
                } else {
                    i++;
                    if (i > rows) {
                        return count;
                    }
                    j = word.length();
                }

            }

            // after the sentence fitted once, increase count
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(SentenceScreenFitting.wordsTyping(new String[]{"a", "b", "e"}, 20000, 20000));
        System.out.println(SentenceScreenFitting.wordsTyping(new String[]{"Hello", "leetcode"}, 1, 10));
        System.out.println(SentenceScreenFitting.wordsTyping(new String[]{"hello", "world"}, 2, 8));
        System.out.println(SentenceScreenFitting.wordsTyping(new String[]{"a", "bcd", "e"}, 3, 6));
        System.out.println(SentenceScreenFitting.wordsTyping(new String[]{"I", "had", "apple", "pie"}, 4, 5));
    }
}
