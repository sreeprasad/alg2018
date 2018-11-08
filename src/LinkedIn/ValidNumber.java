package LinkedIn;

/**
 * 65. Valid Number
 * Validate if a given string can be interpreted as a decimal number.
 *
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 *
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:
 *
 * Numbers 0-9
 * Exponent - "e"
 * Positive/negative sign - "+"/"-"
 * Decimal point - "."
 * Of course, the context of these characters also matters in the input.
 *
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.
 */
public class ValidNumber {
	public boolean isNumber(String s) {
//		return s.matches("(\\s*)[+-]?((\\.[0-9]+)|([0-9]+(\\.[0-9]*)?))(e[+-]?[0-9]+)?(\\s*)");

		// 面经不需要e
		boolean hasDecimalPoint = false;
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
				case '+':
				case '-':
					if (i > 0) return false;
					break;
				case '.':
					if (i == 1 && chars[0] == '+' || chars[0] == '-' && chars.length == 2) return false;
					if (!hasDecimalPoint) hasDecimalPoint = true;
					else return false;
					break;
				default:
					if (chars[i] < '0' || chars[i] > '9') return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		ValidNumber vn = new ValidNumber();
		System.out.println(vn.isNumber("0.1"));
		System.out.println(vn.isNumber("0.0"));
		System.out.println(vn.isNumber("5."));
		System.out.println(vn.isNumber("135.789"));
		System.out.println(vn.isNumber("+123.45"));
		System.out.println(vn.isNumber("-4.3"));
		System.out.println("+12.3.3 " + vn.isNumber("+12.3.3"));
		System.out.println(vn.isNumber("5e-1"));
		System.out.println(vn.isNumber("a123"));
		System.out.println(vn.isNumber("-.5"));
		System.out.println(vn.isNumber("a"));
		System.out.println(-.5);
//		System.out.println(vn.isNumber("-90e3"));
//		System.out.println(vn.isNumber("6e-1"));
//		System.out.println(vn.isNumber("53.5e93"));
//		System.out.println(vn.isNumber("e3")); // false
	}
}
