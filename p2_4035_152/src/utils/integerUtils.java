package utils;

/**
 * Contains useful operation for integer numbers used in system
 * 
 * @author Joel Torres
 *
 */
public class integerUtils {

	/**
	 * Finds maximum of two given numbers
	 * 
	 * @param s1
	 *            first numeric value
	 * @param s2
	 *            second numeric value
	 * @return maximum of the two
	 */
	public static String findMax(String s1, String s2) {
		if (s1.length() > s2.length()) {
			// s1 is max (BY FIRST REMOVING LEFT ZEROES)
			return s1;
		} else if (s1.length() < s2.length()) {
			// s2 is max (BY FIRST REMOVING LEFT ZEROES)
			return s2;

		} else {
			// same length, check digit by digit to determine max
			String max = "";

			for (int i = 0; i < s1.length(); i++) {

				if (s1.charAt(i) > s2.charAt(i)) {
					// s1 is greater
					max = s1;
					break;
				} else if (s1.charAt(i) < s2.charAt(i)) {
					// s2 is greater
					max = s2;
					break;
				} else
					continue;

			}

			return max;
		}
	}

	/**
	 * Determines if value is all numeric
	 * 
	 * @param s
	 *            value to test
	 * @return true if numeric, false otherwise
	 */
	public static boolean isNumeric(String s) {
		int len = s.length();
		for (int i = 0; i < len; ++i) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}

		return true;

	}

	/**
	 * Removes zeroes in front from values
	 * 
	 * @param s
	 *            value to remove zeroes
	 * @return new string without front zeroes
	 */
	public static String removeZeroes(String s) {
		if(s.charAt(0)!='0')
			return s;
		String ns = "";
		int index = 0;
		while (s.charAt(index) == '0')
			index++;
		while (index < s.length()) {
			ns += s.charAt(index);
			index++;
		}

		return ns;
	}
}
