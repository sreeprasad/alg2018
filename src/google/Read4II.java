package google;

import java.util.Random;

/**
 * 158. Read N Characters Given Read4 II - Call multiple times
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 *
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 *
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 *
 * Note:
 * The read function may be called multiple times.
 *
 * Example 1:
 *
 * Given buf = "abc"
 * read("abc", 1) // returns "a"
 * read("abc", 2); // returns "bc"
 * read("abc", 1); // returns ""
 * Example 2:
 *
 * Given buf = "abc"
 * read("abc", 4) // returns "abc"
 * read("abc", 1); // returns ""
 */
public class Read4II {
	/**
	 * @param buf Destination buffer
	 * @param n   Maximum number of characters to read
	 * @return    The number of characters read
	 */
	private int index = 0;
	private int count = 0;
	private char[] read = new char[4];

	public int read(char[] buf, int n) {
		int i = 0;
		while (i < n) {
			if (index == 0) {
				count = read4(read);
			}
			if (count == 0) break;
			while (i < n && index < count) {
				buf[i++] = read[index++];
			}
			if (index >= count) index = 0;
		}
		return i;
	}

	private int read4(char[] buf) {
		return new Random().nextInt(4);
	}

	public static void main(String[] args) {
		Read4II r4 = new Read4II();
		System.out.println(r4.read("filetestbuffer".toCharArray(), 6));
	}
}
/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */