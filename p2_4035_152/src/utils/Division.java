package utils;

/**
 * Class for division operation used mainly for prime testing
 * 
 * @author Joel Torres
 *
 */
public class Division {

	/**
	 * Divides two numbers
	 * 
	 * @param n1
	 *            first value
	 * @param n2
	 *            second value
	 * @return String array with division
	 */
	public static String[] Divide(String n1, String n2) {
		Boolean negative = false;
		if (n1.charAt(0) == '-' ^ n2.charAt(0) == '-')
			negative = true;
		if (n1.charAt(0) == '-')
			n1 = n1.substring(1);
		if (n2.charAt(0) == '-')
			n2 = n2.substring(1);
		if (n1.equals("0") && n2.equals("0"))
			return new String[] { "Not a number", "0" };

		if (n2.equals("0")) {
			if (!negative)
				return new String[] { "Infinity", "0" };
			return new String[] { "-Infinity", "0" };
		}

		int len1 = n1.length();
		int len2 = n2.length();
		if (len1 < len2)
			return new String[] { "0", n1 };
		StringBuilder digits = new StringBuilder();
		String n3 = n1.substring(0, len2);
		int len3 = len2;
		String n4;
		int quotient;
		int index = len2 - 1;

		while (true) {
			quotient = 0;

			while (true) {
				n4 = Subtract(n3, n2);

				if (n4 == "-1") {
					break;
				}

				quotient++;

				if (n4 == "0") {
					n3 = "0";
					break;
				}

				n3 = n4;
			}

			if (digits.toString().equals("0")) {
				digits.setCharAt(0, (char) (quotient + 48));
			} else {
				digits.append((char) (quotient + 48));
			}

			if (index < len1 - 1) {
				index++;
				if (n3.equals("0"))
					n3 = "";
				n3 += n1.charAt(index);
				len3 = n3.length();
			} else {
				String result = new String(digits);
				if (negative) {
					if (!result.equals("0"))
						result = "-" + result;
					if (!n3.equals("0"))
						n3 = "-" + n3;
				}
				return new String[] { result, n3 };

			}
		}
	}

	/**
	 * Subtracts two given values
	 * 
	 * @param n1
	 *            first value
	 * @param n2
	 *            second value
	 * @return subtraction of the two values
	 */
	public static String Subtract(String n1, String n2) {
		int len1 = n1.length();
		int len2 = n2.length();
		if (len1 < len2)
			return "-1";
		int max = Math.max(len1, len2);
		int[] ia1 = new int[max];
		int[] ia2 = new int[max];
		int[] ia3 = new int[max];
		for (int i = max - len1; i < max; i++)
			ia1[i] = n1.charAt(i + len1 - max) - 48;
		for (int i = max - len2; i < max; i++)
			ia2[i] = n2.charAt(i + len2 - max) - 48;
		int diff = 0;
		int carry = 0;

		for (int i = max - 1; i >= 0; i--) {
			diff = ia1[i] - ia2[i] - carry;
			carry = 0;
			if (diff < 0) {
				diff += 10;
				carry = 1;
			}
			ia3[i] = diff;
		}

		if (carry == 1)
			return "-1";

		// find first non-zero element of array ia3
		int first = -1;
		for (int i = 0; i < max; i++) {
			if (ia3[i] != 0) {
				first = i;
				break;
			}
		}

		if (first == -1)
			first = max - 1;
		char[] c3 = new char[max - first];
		for (int i = first; i < max; i++)
			c3[i - first] = (char) (ia3[i] + 48);
		return new String(c3);
	}
}