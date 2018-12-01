package google;

import java.util.Stack;

/**
 * 224. Basic Calculator
 * Hard
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculator {
	public int calculate(String s) {

		Stack<Integer> stack = new Stack<>();

		int result = 0;
		int sign = 1;
		int curNum = 0;

		char[] charArr = s.toCharArray();
		for (char c : charArr) {
			if (Character.isDigit(c)) {
				curNum = curNum * 10 + (c - '0');

			} else if (c == '+' || c == '-') {
				result += sign * curNum;
				sign = (c == '+') ? 1 : -1;
				curNum = 0;

			} else if (c == '(') { // cache previous operand and operator
				stack.push(result);
				stack.push(sign);
				sign = 1; // initialize these 3 values and start calculating again
				curNum = 0;
				result = 0;

			} else if (c == ')') {
				result += sign * curNum;
				result *= stack.pop();
				result += stack.pop();
				curNum = 0;
			}
		}

		result += sign * curNum;

		return result;
	}
	public static void main(String[] args) {
		BasicCalculator bc = new BasicCalculator();
		System.out.println(bc.calculate("1 + 1")); // 2
		System.out.println(bc.calculate("2-(5-6)")); // 3
		System.out.println(bc.calculate("(1+(4+5+2)-3)+(6+8)")); // 23
	}
}
