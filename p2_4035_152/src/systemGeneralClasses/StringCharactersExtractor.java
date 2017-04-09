package systemGeneralClasses;

/**
 * An object of this type is a wrapper over a String. It allows disect the
 * string, character by character...
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public class StringCharactersExtractor {
	private int cp;
	private String input;

	/**
	 * StringCharactersExtractor objects constructor
	 * 
	 * @param input
	 *            input from user to disect
	 * @param cp
	 *            character counter
	 */
	public StringCharactersExtractor(String input, int cp) {
		this.input = input;
		this.cp = cp;
	}

	/**
	 * Skips blank spaces on input
	 * 
	 * @throws IndexOutOfBoundsException
	 *             thrown when accessing invalid character
	 */
	public void skipSpaces() throws IndexOutOfBoundsException {
		while (Character.isWhitespace(currentChar())) {
			skipCurrentChar();
		}
	}

	/**
	 * Skips current character on input
	 */
	public void skipCurrentChar() {
		cp++;
	}

	/**
	 * Returns current character on input
	 * 
	 * @return current character
	 */
	public char currentChar() {
		return input.charAt(cp);
	}

	/**
	 * Extracts current character on input
	 * 
	 * @return current character
	 * @throws IndexOutOfBoundsException
	 *             thrown when accessing invalid character
	 */
	public char extractCurrentChar() throws IndexOutOfBoundsException {
		char cc = input.charAt(cp);
		cp++;
		return cc;
	}

	/**
	 * Checks if input has more characters
	 * 
	 * @return true if more, false otherwise
	 */
	public boolean hasMoreContent() {
		for (int i = cp; i < input.length(); i++)
			if (!Character.isWhitespace(input.charAt(i)))
				return true;
		return false;
	}

	/**
	 * Current index getter
	 * 
	 * @return current index
	 */
	public int currentIndexValue() {
		return cp;
	}

	/**
	 * Reads the substring in input (inherited instance field), from the current
	 * character up to right before the first '\"' (character ") found, if any.
	 * 
	 * @return the substring read
	 * @throws IndexOutOfBoundsException
	 *             if second closing quote " is never found
	 */
	public String extractStringUpToQuote() throws IndexOutOfBoundsException {
		String s = "";
		while (currentChar() != '\"') {
			s = s + extractCurrentChar();
		}
		return s;
	}

	/**
	 * Extracts string up to blank space
	 * 
	 * @return String up to blank space
	 */
	public String extractStringUpToWhiteSpaceChar() {
		String s = "";
		while (hasMoreContent() && !Character.isWhitespace(currentChar())) {
			s = s + extractCurrentChar();
		}
		return s;
	}

}
