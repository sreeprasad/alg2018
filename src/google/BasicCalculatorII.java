package google;

import java.util.Stack;

/**
 * 227. Basic Calculator II
 * Medium
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculatorII {
	public int calculate(String s) {

		Stack<Integer> stack = new Stack<>();
		int curNum = 0;
		char sign = '+';

		char[] charArr = s.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			char c = charArr[i];
			if (Character.isDigit(c)) {
				curNum = curNum * 10 + (c - '0');
			}
			if (!Character.isDigit(c) && c != ' '
					|| i == charArr.length - 1) {
				if (sign == '+') {
					stack.push(curNum);
				} else if (sign == '-') {
					stack.push(-curNum);
				} else if (sign == '*') {
					stack.push(stack.pop() * curNum);
				} else if (sign == '/') {
					stack.push(stack.pop() / curNum);
				}
				curNum = 0;
				sign = c;
			}
		}

		int result = 0;
		while (!stack.isEmpty()) {
			result += stack.pop();
		}
		return result;
	}
	public static void main(String[] args) {
		BasicCalculatorII bcii = new BasicCalculatorII();
		System.out.println(bcii.calculate(" 3+5 / 2 ")); // 5
	}
}
