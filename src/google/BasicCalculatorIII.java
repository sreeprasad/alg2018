package google;

import java.util.Stack;

/**
 * 772. Basic Calculator III
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces .
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * Some examples:
 *
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 *
 *
 * Note: Do not use the eval built-in library function.
 */
public class BasicCalculatorIII {
	public int calculate(String s) {
		// Write your code here
		Stack<Integer> stack = new Stack<>();
		int curNum = 0;
		char sign = '+';

		char[] charArr = s.toCharArray();
		for (int i = 0; i < charArr.length; i++) {
			char c = charArr[i];
			if (Character.isDigit(c)) {
				curNum = curNum * 10 + (c - '0');
			} else if (c == '(') {
				int closingIndex = findClosing(s, i); // index of ')'
				curNum = calculate(s.substring(i + 1, closingIndex));
				i = closingIndex + 1;
				if (i < charArr.length) c = charArr[i];
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
	private int findClosing(String s, int left) {
		char[] charArr = s.toCharArray();
		int count = 1;
		for (int i = left + 1; i < charArr.length; i++) {
			if (charArr[i] == '(') count++;
			if (charArr[i] == ')') count--;
			if (count == 0) return i;
		}
		return -1;
	}
	public static void main(String[] args) {
		BasicCalculatorIII bciii = new BasicCalculatorIII();
		System.out.println(bciii.calculate("2*(5+5*2)/3+(6/2+8)")); // 21
		System.out.println(bciii.calculate("(2+6* 3+5- (3*14/7+2)*5)+3")); // -12
	}
}
