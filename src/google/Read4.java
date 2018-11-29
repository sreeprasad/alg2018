package google;

import java.util.Random;

/**
 * 157. Read N Characters Given Read4
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 *
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 *
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 *
 * Example 1:
 *
 * Input: buf = "abc", n = 4
 * Output: "abc"
 * Explanation: The actual number of characters read is 3, which is "abc".
 * Example 2:
 *
 * Input: buf = "abcde", n = 5
 * Output: "abcde"
 * Note:
 * The read function will only be called once for each test case.
 */
public class Read4 {
	/**
	 * @param buf Destination buffer
	 * @param n   Maximum number of characters to read
	 * @return    The number of characters read
	 */
	public int read(char[] buf, int n) {
		char[] buffer = new char[4];
		int index = 0;
		int count = 0;

		int i = 0;
		while (i < n) {
			if (index == 0) {
				count = read4(buffer);
				if (count == 0) {
					return i;
				}
			}
			while (i < n && index < count) {
				buf[i++] = buffer[index++];
			}
			if (index == count) {
				index = 0;
			}
		}
		return i;
	}
	private int read4(char[] buf) {
		return new Random().nextInt(4);
	}
}
