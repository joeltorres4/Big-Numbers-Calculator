package systemGeneralClasses;

import java.util.ArrayList;

/**
 * OperandAnalyzer ADT
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public interface OperandAnalyzer {
	/**
	 * Returns the status of the current instance value as to whether it is a
	 * valid operand value for the type of operand type that this analyzer
	 * corresponds to.
	 * 
	 * @param is
	 *            input to disect
	 * @param cp
	 *            index used for disection
	 * 
	 * @return the array list of the items in this operand. if the operand is a
	 *         single item, then the array list will contain that item at
	 *         position 0. If the operand is a list of items, then the array
	 *         list contains the items, one at each position and in the same
	 *         order as they appear in the input line. Separators, if any are
	 *         required by the operand syntax rules, are not included (although,
	 *         if the operand syntax requires them, they should be taken into
	 *         account to validate syntax).
	 * 
	 *         returns null if the attempted operand in the input line is not a
	 *         valid operand for this particular type of operand.
	 */
	ArrayList<String> disectOperandFromInput(String is, int cp);

	/**
	 * Returns current index in input
	 * 
	 * @return current index in input
	 */
	int currentIndexInInput();
}
