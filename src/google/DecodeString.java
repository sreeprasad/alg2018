package google;

/**
 * 394. Decode String
 * Medium
 * Given an encoded string, return it's decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {
	public String decodeString(String s) {
		if (s.isEmpty()) return s;
		int k = 0;
		int i = 0;
		StringBuilder sb = new StringBuilder();

		// "abc"
		if (!Character.isDigit(s.charAt(i))) {
			while (i < s.length() && !Character.isDigit(s.charAt(i))) {
				sb.append(s.charAt(i));
				i++;
			}
			return sb.append(decodeString(s.substring(i))).toString();
		}

		// "3[abc]"
		while (i < s.length() && Character.isDigit(s.charAt(i))) {
			k = k * 10 + Character.getNumericValue(s.charAt(i));
			i++;
		}
		int closingIndex = closing(s, i); // index of closing ']'
		String sub = closingIndex == -1 ? "" : decodeString(s.substring(i + 1, closingIndex));

		while (k > 0) {
			sb.append(sub);
			k--;
		}
		if (closingIndex != -1) {
			sb.append(decodeString(s.substring(closingIndex + 1)));
		}
		return sb.toString();
	}
	private int closing(String s, int index) {
		int count = 1;
		for (int i = index + 1; i < s.length(); i++) {
			if (s.charAt(i) == '[') count++;
			else if (s.charAt(i) == ']') {
				count--;
				if (count == 0) return i;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		DecodeString ds = new DecodeString();
		System.out.println(ds.decodeString("3[a]2[bc]")); // aaabcbc
		System.out.println(ds.decodeString("3[a2[c]]")); // accaccacc
		System.out.println(ds.decodeString("2[abc]3[cd]ef")); // abcabccdcdcdef
	}
}
