package operandHandlers;

/**
 * Contains important methods for validating input such as variable names,
 * values and file names
 * 
 * @author Joel Torres
 *
 */
public class SimpleOperandHandler {

	/**
	 * Validates of a token complies with the grammar
	 * 
	 * @param tType
	 *            The type of token to be matched with (the grammar)
	 * @param token
	 *            The token content to be analyzed.
	 * @return true if valid; false, otherwise.
	 */
	public static boolean isValidToken(String tType, String token) {
		// current simple token types are: name, int,
		// more need to be added...
		if (tType.equals("var_name"))
			return isValidName(token);
		else if (tType.equals("value"))
			return isValidValue(token);
		else if (tType.equals("file_name"))
			return isValidFileName(token);
		else
			return false;

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
	 * Determine if name is valid for system
	 * 
	 * @param operand
	 *            operand to check if valid
	 * @return true if valid, false otherwise
	 */
	private static boolean isValidName(String operand) {
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
	 * Determines if value is valid
	 * 
	 * @param operand
	 *            operand to check if valid
	 * @return true if valid, false otherwise
	 */
	private static boolean isValidValue(String operand) {
		int dc = 0; // dot count
		int sc = 0; // sign count
		boolean valid = false;

		if (operand.length() == 0)
			return valid;

		for (int i = 0; i < operand.length(); i++) {
			if (operand.charAt(i) == '-' || operand.charAt(i) == '+') {
				if (i != 0)
					return false;
				sc++;
				valid = true;
			} else if (operand.charAt(i) == '.') {
				dc++;
				valid = true;
			} else if (Character.isLetter(operand.charAt(i)))
				return false;
			else if (Character.isDigit(operand.charAt(i)))
				valid = true;
			else
				return false;
			// now check dc and sc (have to be less or equal to 1)
			if (dc > 1 || sc > 1)
				return false;
		}
		return valid;
	}

}
