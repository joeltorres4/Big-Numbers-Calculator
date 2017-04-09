package systemGeneralClasses;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Abstract class to represent Command objects
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public abstract class Command {
	protected ArrayList<String> tokensList;

	/**
	 * Command objects default constructor
	 */
	public Command() {
	}

	/**
	 * Command objects with input string
	 * 
	 * @param input
	 *            input command from user
	 */
	public Command(String input) {
		tokensList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(input);
		while (st.hasMoreTokens())
			tokensList.add(st.nextToken());

	}

	/**
	 * Returns number of operands in tokensList
	 * 
	 * @return amount of operands
	 */
	public int getNumberOfOperands() {
		return tokensList.size() - 1;
	}

	/**
	 * Command name getter
	 * 
	 * @return name of Command
	 */
	public String getName() {
		return tokensList.get(0);
	}

	/**
	 * Returns string representation of tokens
	 * 
	 * @return tokensList with tokens
	 */
	public String toString() {
		String rs = "";
		for (int i = 0; i < tokensList.size(); i++)
			rs = rs + tokensList.get(i) + " ";
		return rs;
	}

}
