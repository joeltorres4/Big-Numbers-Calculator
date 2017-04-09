package utils;

/**
 * Contains useful operation for decimal numbers used in system
 * 
 * @author Joel Torres
 *
 */
public class decimalUtils {

	/**
	 * Checks if value is decimal
	 * 
	 * @param s
	 *            value to test
	 * @return true if decimal, false otherwise
	 */
	public static int checkIfDecimal(String s) {
		int dotIndex = 0;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == '.') {
				// s1 is a decimal number
				dotIndex = i;
			}

		return dotIndex;
	}

	/**
	 * Aligns one decimal number with a non-decimal number
	 * 
	 * @param dec
	 *            decimal value
	 * @param notDec
	 *            non-decimal value
	 * @param dotIndexDec
	 *            decimal value dot index
	 * @param dotIndexNotDec
	 *            non-decimal value dot index
	 * @return array of the two fixed values
	 */
	public static String[] makeDecimal(String dec, String notDec,
			int dotIndexDec, int dotIndexNotDec) {
		// s1 has n decimal places, s2 has 0 decimal places. So just add n
		// zeroes to s2.
		notDec += ".";
		dotIndexNotDec = notDec.length() - 1;
		for (int i = dotIndexDec + 1; i < dec.length(); i++) {
			notDec += "0";
		}
		String[] numbers = alignDecimals(dec, notDec, dotIndexDec,
				dotIndexNotDec);
		return numbers;
	}

	/**
	 * Operation used to remove dot from decimal numbers, used in the
	 * multiplication of decimals
	 * 
	 * @param s1
	 *            first numeric value
	 * @param s2
	 *            second numeric value
	 * @return array of the two fixed values
	 */
	public static String[] makeInteger(String s1, String s2) {
		String n1 = "", n2 = "";
		String[] ns = new String[2];
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != '.') {
				n1 += s1.charAt(i);
			}
		}
		for (int i = 0; i < s2.length(); i++) {
			if (s2.charAt(i) != '.') {
				n2 += s2.charAt(i);
			}
		}
		ns[0] = n1;
		ns[1] = n2;
		return ns;
	}

	/**
	 * Inserts dot within value
	 * 
	 * @param s1
	 *            numeric value to insert dot
	 * @param decimalPlaces
	 *            decimal places two comply with
	 * @return new String of numeric value
	 */
	public static String insertDot(String s1, int decimalPlaces) {
		int dotLocation = s1.length() - decimalPlaces;
		return s1.substring(0, dotLocation) + "."
				+ s1.substring(dotLocation, s1.length());
	}

	/**
	 * Aligns two decimal numbers
	 * 
	 * @param s1
	 *            first numeric value
	 * @param s2
	 *            second numeric value
	 * @param dotIndex1
	 *            dot index of first numeric value
	 * @param dotIndex2
	 *            dot index of second numeric value
	 * @return array with the two aligned values
	 */
	public static String[] alignDecimals(String s1, String s2, int dotIndex1,
			int dotIndex2) {
		int dif = 0;
		String[] numbers = new String[2];
		if (dotIndex1 < dotIndex2) {
			// s1 has less left places, move s1 to the right...
			dif = dotIndex2 - dotIndex1;
			while (dif != 0) {
				// move one position to the right
				s1 = "0" + s1; // fill with zeroes
				dif--;
			}
			// filling other number with zeroes at the end
			if (s1.length() > s2.length()) {
				dif = s1.length() - s2.length();
				while (dif != 0) {
					s2 += "0";
					dif--;
				}
			} else if (s1.length() < s2.length()) {
				dif = s2.length() - s1.length();
				while (dif != 0) {
					s1 += "0";
					dif--;
				}
			}
		} else if (dotIndex1 > dotIndex2) {
			// s2 has less left places, move s2 to the right
			dif = dotIndex1 - dotIndex2;
			while (dif != 0) {
				// move one position to the right
				s2 = "0" + s2; // fill with zeroes
				dif--;
			}
			// filling other number with zeroes at the end
			if (s1.length() > s2.length()) {
				dif = s1.length() - s2.length();
				while (dif != 0) {
					s2 += "0";
					dif--;
				}
			} else if (s1.length() < s2.length()) {
				dif = s2.length() - s1.length();
				while (dif != 0) {
					s1 += "0";
					dif--;
				}
			}
		} else {
			// if here, numbers are already aligned... just have to fill
			// with zeroes if needed
			// filling other number with zeroes at the end
			if (s1.length() > s2.length()) {
				dif = s1.length() - s2.length();
				while (dif != 0) {
					s2 += "0";
					dif--;
				}
			} else if (s1.length() < s2.length()) {
				dif = s1.length() - s2.length();
				while (dif != 0) {
					s2 += "0";
					dif--;
				}
			}
		}
		numbers[0] = s1;
		numbers[1] = s2;
		return numbers;
	}

}
