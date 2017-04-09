package operandHandlers;

import systemGeneralClasses.OperandAnalyzer;

/**
 * Necessary utils for validating user input
 * 
 * @author Joel Torres
 *
 */
public class OperandValidatorUtils {

	/**
	 * Determine if input name (var_name) is valid
	 * 
	 * @param operand
	 *            operand to analyze
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidName(String operand) {
		if (operand.length() == 0)
			return false;

		// operand is not empty string
		boolean isName = (Character.isLetter(operand.charAt(0)));
		int cp = 1;
		while (cp < operand.length() && isName) {
			char c = operand.charAt(cp);
			if (!(Character.isDigit(c) || Character.isLetter(c)))
				isName = false;
			cp++;
		}
		return isName;

	}

	/**
	 * Determines if input value is valid
	 * 
	 * @param operand
	 *            operand number to test
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidValue(String operand) {
		int s = 0;
		int dc = 0; // dot counter
		boolean valid = true;
		if (operand.charAt(0) == '-' || operand.charAt(0) == '+') {
			// number has positive or negative sign
			s++;
		}
		for (int i = s; i < operand.length() && valid; i++) {
			char c = operand.charAt(i);
			if (Character.isDigit(c)) {
				valid = true;
			} else if (c == '.') {
				valid = true;
				dc++;
			} else
				valid = false;

			if (dc > 1)
				valid = false;
		}
		return valid;
	}

	/**
	 * Determines if file name is valid
	 * 
	 * @param operand
	 *            operand to evaluate if valid
	 * @return true if valid, false otherwise
	 */
	public static boolean isValidFileName(String operand) {
		// checking if name is a file name

		boolean isFileName = false;
		int dotIndex = 0;
		if (operand.substring(0, operand.length() - 4).length() > 10)
			return isFileName;

		for (int i = 0; i < operand.length(); i++) {
			if (operand.charAt(i) == '.')
				dotIndex = i;
		}

		dotIndex++;
		if (operand.charAt(dotIndex) == 't') {
			dotIndex++;
			if (operand.charAt(dotIndex) == 'x') {
				dotIndex++;
				if (operand.charAt(dotIndex) == 't')
					isFileName = true;
			}
		}
		return isFileName;
	}

	/**
	 * Return analyzer for respective operand
	 * 
	 * @param op
	 *            operand to get analyzer for
	 * @return OperandAnalyzer
	 */
	public static OperandAnalyzer getAnalyzerFor(String op) {
		if (op.equals("var_name"))
			return NameOperandAnalyzer.getInstance();
		return null; // if nothing matches
	}

}
