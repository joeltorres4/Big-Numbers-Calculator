package variablesManagementClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import lists.NodePositionList;
import lists.Position;
import utils.Division;
import utils.decimalUtils;
import utils.integerUtils;

/**
 * Variable operations and management methods are contained here
 * 
 * @author Joel Torres
 *
 */
public class VariableManager {

	// Array of variables
	private ArrayList<Variable> variables;

	/**
	 * Default constructor for VariableManager
	 */
	public VariableManager() {
		variables = new ArrayList<>();
	}

	/**
	 * Searchs variable within variables list
	 * 
	 * @param name
	 *            name of variable to look for
	 * @return Variable object corresponding to specified name, null if doesn't
	 *         exist
	 */
	public Variable searchVariable(String name) {
		for (Variable v : variables) {
			if (v.getName().equals(name))
				return v;

		}
		return null;
	}

	/**
	 * Search variable within variables list
	 * 
	 * @param name
	 *            name of variable
	 * @return index of variable if exists, -1 otherwise
	 */
	public int getIndexOfVariable(String name) {
		for (Variable v : variables) {
			if (v.getName().equals(name))
				return variables.indexOf(v);

		}
		return -1;
	}

	/**
	 * Creates new variable on system
	 * 
	 * @param name
	 *            name of variable to create
	 */
	public void createVar(String name) {
		variables.add(new Variable(name));
	}

	/**
	 * Deletes variable from system
	 * 
	 * @param name
	 *            name of variable to delete
	 */
	public void deleteVar(String name) {
		variables.remove(getIndexOfVariable(name));

	}

	/**
	 * Computes the addition of the two numeric values and assigns the result to
	 * the variable value being referenced by the varName parameter
	 * 
	 * 
	 * @param s1
	 *            first numeric value
	 * @param s2
	 *            second numeric value
	 * @return String representation of computed value
	 */
	public String add(String s1, String s2) {

		// First check if one of the two numbers is empty
		if (s1.length() == 0) {
			if (s2.length() == 0)
				return "";
			else {
				return s2;
			}
		} else {
			if (s2.length() == 0)
				return s1;

		}

		boolean neg = false;
		// First, check if numbers have any sign
		if (s1.charAt(0) == '-' && Character.isDigit(s2.charAt(0)))
			// negative plus positive, same as subtracting them and multiplying by
			// -1
			return subtract(s2, s1.substring(1));
		else if (Character.isDigit(s1.charAt(0)) && s2.charAt(0) == '-')
			// positive plus negative, becomes a subtraction... call sub!
			return subtract(s1,s2.substring(1));
		else if (s1.charAt(0)=='+' && s2.charAt(0)=='-')
			return subtract(s1,s2.substring(1));
		
		else {
			// either both negative or both positive
			// If here, numbers have valid signs for addition or both begin with
			// a dot
			// Now, construct new number substring from original one without the
			// sign...
			if (s1.charAt(0) == '+')
				s1 = s1.substring(1);
			if (s2.charAt(0) == '+')
				s2 = s2.substring(1);

			if (s1.charAt(0) == '-' && s2.charAt(0) == '-') {
				s1 = s1.substring(1);
				s2 = s2.substring(1);
				neg = true;
			}
			// Now, numbers have no sign, ready to add...
			// Next, check if numbers are decimals
			int dotIndex1 = decimalUtils.checkIfDecimal(s1);
			int dotIndex2 = decimalUtils.checkIfDecimal(s2);
			if (dotIndex1 != 0 && dotIndex2 != 0) {
				// both are decimals, align both
				String[] ad = decimalUtils.alignDecimals(s1, s2, dotIndex1, dotIndex2);
				s1 = ad[0];
				s2 = ad[1];
			} else if (dotIndex1 != 0 && dotIndex2 == 0) {
				// s1 is decimal, but s2 not
				String[] numbers = decimalUtils.makeDecimal(s1, s2, dotIndex1,
						dotIndex2);
				s1 = numbers[0];
				s2 = numbers[1];
			} else if (dotIndex1 == 0 && dotIndex2 != 0) {
				// s2 is decimal, but s1 not
				String[] numbers = decimalUtils.makeDecimal(s2, s1, dotIndex2,
						dotIndex1);
				s1 = numbers[0];
				s2 = numbers[1];
			}
			NodePositionList<String> v1 = convertToPL(s1);
			NodePositionList<String> v2 = convertToPL(s2);
			Position<String> c1 = v1.last(); // counter for value 1
			Position<String> c2 = v2.last(); // counter for value 2
			String nValue = "";
			// new value of variable with sum
			int carry = 0;

			// CASE 1: S1 IS SHORTER THAN S2
			if (s1.length() < s2.length()) {

				while (c1 != null) {
					int i1 = Integer.parseInt(c1.element());
					int i2 = Integer.parseInt(c2.element());
					if ((carry + i1 + i2) > 9) {
						// sum of the two digits is greater than 10, handle
						// carry
						int sum = (carry + i1 + i2) - 10;
						nValue = sum + nValue;
						carry = 1;
					} else {
						// sum of the two digits is less than 10, no carry
						nValue = String.valueOf(carry + i1 + i2) + nValue;
						carry = 0;
					}

					if (v1.prev(c1) == null) {
						c2 = v2.prev(c2);
						c1 = null; // to break while loop...
					} else {
						c1 = v1.prev(c1);
						c2 = v2.prev(c2);
					}
				}
				// Now, drop the remaining values from s2...
				while (c2 != null) {
					int s = carry + Integer.parseInt(c2.element());
					nValue = s + nValue;
					c2 = v2.prev(c2);
					carry = 0;
				}

			}
			// CASE 2: S1 IS LONGER THAN S2
			else if (s1.length() > s2.length()) {
				while (c2 != null) {
					int i1 = Integer.parseInt(c1.element());
					int i2 = Integer.parseInt(c2.element());
					if ((carry + i1 + i2) > 9) {
						// sum of the two digits is less than 10, handle
						// carry
						int sum = (carry + i1 + i2) - 10;
						nValue = sum + nValue;
						carry = 1;
					} else {
						// sum of the two digits is less than 10, no carry
						nValue = String.valueOf(carry + i1 + i2) + nValue;
						carry = 0;
					}

					if (v2.prev(c2) == null) {
						c1 = v1.prev(c1);
						c2 = null; // to break while loop...
					} else {
						c1 = v1.prev(c1);
						c2 = v2.prev(c2);
					}
				}
				// Now, drop the remaining values from s1...
				while (c1 != null) {
					int s = carry + Integer.parseInt(c1.element());
					nValue = s + nValue;
					c1 = v1.prev(c1);
					carry = 0;
				}

			}
			// CASE 3: S1 AND S2 HAVE THE SAME LENGTH
			// Decimals only enter in this case since they are aligned and
			// have
			// the same length
			else {
				while (c1 != null && c2 != null) {
					if (c1.element().charAt(0) == '.') {
						nValue = "." + nValue;
						c1 = v1.prev(c1);
						c2 = v2.prev(c2);
						if (c1 == null && c2 == null) {
							nValue = String.valueOf(carry) + nValue;
							break;
						}

					}
					int i1 = Integer.parseInt(c1.element());
					int i2 = Integer.parseInt(c2.element());
					if ((carry + i1 + i2) > 9) {
						// sum of the two digits is more than 9, handle
						// carry

						int sum = (carry + i1 + i2) - 10;
						nValue = sum + nValue;
						carry = 1;
					} else {
						// sum of the two digits is less than 10, no carry
						nValue = String.valueOf(carry + i1 + i2) + nValue;
						carry = 0;
					}

					if (v1.prev(c1) == null || v2.prev(c2) == null) {
						if (carry == 1) {
							nValue = String.valueOf(carry) + nValue;
						}
						break;
					} else {
						c1 = v1.prev(c1);
						c2 = v2.prev(c2);
					}
				}
			}
			// assign new value to variable...
			if (neg)
				nValue = "-" + nValue;
			return nValue;
		}

	}

	/**
	 * Computes the subtraction of the two numeric values and assigns the result
	 * to the variable value being referenced by the varName parameter
	 * 
	 * @param s1
	 *            first numeric value
	 * @param s2
	 *            second numeric value
	 * 
	 * @return String representation of computed subtraction
	 */
	public String subtract(String s1, String s2) {

		// First, check if numbers have any sign

		
		if (s1.charAt(0) == '-' && Character.isDigit(s2.charAt(0)))
			// negative minus positive, same as adding them and multiplying by
			// -1
			return "-" + add(s1.substring(1), s2);

		else if (Character.isDigit(s1.charAt(0)) && s2.charAt(0) == '-') {
			// positive minus negative, becomes an addition... call sum!

			return add(s1, s2.substring(1));
	
		} else {
			// either both negative or both positive

			if (s1.charAt(0) == '-' && s2.charAt(0) == '-') {
				// both negative, then negative minus negative becomes negative
				// plus positive
				// or, positive minus positive, so switch s1 and s2
				String temp = s1;
				s1 = s2.substring(1);
				s2 = temp.substring(1);
			}
			else if(s1.charAt(0) == '+' && s2.charAt(0) == '+'){
				s1 = s1.substring(1);
				s2 = s2.substring(1);
			}
			
			if (s1.charAt(0) == '+')
				s1 = s1.substring(1);
			if (s2.charAt(0) == '+')
				s2 = s2.substring(1);

			// Now, numbers have no sign, ready to add...
			// Next, check if numbers are decimals
			int dotIndex1 = decimalUtils.checkIfDecimal(s1);
			int dotIndex2 = decimalUtils.checkIfDecimal(s2);
			String[] numbers;
			if (dotIndex1 != 0 && dotIndex2 != 0) {
				// both are decimals, align both
				numbers = decimalUtils.alignDecimals(s1, s2, dotIndex1,
						dotIndex2);
				s1 = numbers[0];
				s2 = numbers[1];
			} else if (dotIndex1 != 0 && dotIndex2 == 0) {
				// s1 is decimal, but s2 not
				numbers = decimalUtils
						.makeDecimal(s1, s2, dotIndex1, dotIndex2);
				s1 = numbers[0];
				s2 = numbers[1];
			} else if (dotIndex1 == 0 && dotIndex2 != 0) {
				// s2 is decimal, but s1 not
				numbers = decimalUtils
						.makeDecimal(s2, s1, dotIndex2, dotIndex1);
				s1 = numbers[0];
				s2 = numbers[1];
			}

			// To make integer subtraction easier, fill blank spaces with zeroes
			// and convert numbers so they have the same length.
			int l1 = s1.length();
			int l2 = s2.length();
			int dif = 0;
			if (l1 < l2) {
				dif = l2 - l1;
				while (dif != 0) {
					s1 = "0" + s1;
					dif--;
				}
			} else if (l1 > l2) {
				dif = l1 - l2;
				while (dif != 0) {
					s2 = "0" + s2;
					dif--;
				}
			}

			// if here, both have same length (with filling procedure or not)
			// now, put greatest value on top of subtraction
			boolean negativeResult = false;
			if (integerUtils.findMax(s1, s2).equals(s2)) {
				String temp = s1;
				s1 = s2;
				s2 = temp;
				negativeResult = true;
			}

			NodePositionList<String> v1 = convertToPL(s1);
			NodePositionList<String> v2 = convertToPL(s2);
			Position<String> c1 = v1.last(); // counter for value 1
			Position<String> c2 = v2.last(); // counter for value 2
			String nValue = "";
			// new value of variable with sum
			int taken = 0;

			while (c1 != null && c2 != null) {
				if (c1.element().charAt(0) == '.') {
					nValue = "." + nValue;
					c1 = v1.prev(c1);
					c2 = v2.prev(c2);

					if (c1 == null && c2 == null) {
						break;
					}

				}

				int i1 = Integer.parseInt(c1.element());
				int i2 = Integer.parseInt(c2.element());
				if ((i1 - i2 - taken) < 0) {
					// subtraction of the two digits is less than 0, add 10
					// to next term
					i1 = (i1 - taken) + 10;
					nValue = (i1 - i2) + nValue;
					taken = 1;
				} else {
					// subtraction of the two digits is greater than 0
					nValue = String.valueOf((i1 - taken) - i2) + nValue;
					taken = 0;
				}

				if (v1.prev(c1) == null) {
					// no more to subtract
					c1 = null; // to break while loop...
				} else {
					c1 = v1.prev(c1);
					c2 = v2.prev(c2);
				}
			}
			if (negativeResult)
				nValue = "-" + nValue;

			// assign new value to variable...
			return nValue;
		}

	}

	/**
	 * Computes the product of the two numeric values and assigns the result to
	 * the variable value being referenced by the varName parameter
	 * 
	 * @param s1
	 *            first numeric value
	 * @param s2
	 *            second numeric value
	 * @return String representation of computed value
	 */
	public String multiply(String s1, String s2) {

		boolean negResult = false;
		if ((s1.charAt(0) == '-' && s2.charAt(0) == '+')
				|| (s1.charAt(0) == '+' && s2.charAt(0) == '-')) {
			negResult = true;
			s1 = s1.substring(1);
			s2 = s2.substring(1);
		} else if ((s1.charAt(0) == '-' && s2.charAt(0) == '-')
				|| (s1.charAt(0) == '+' && s2.charAt(0) == '+')) {
			negResult = false;
			s1 = s1.substring(1);
			s2 = s2.substring(1);
		} else if ((Character.isDigit(s1.charAt(0)) && s2.charAt(0) == '-')) {
			negResult = true;
			s2 = s2.substring(1);
		} else if ((Character.isDigit(s2.charAt(0)) && s1.charAt(0) == '-')) {
			negResult = true;
			s1 = s1.substring(1);
		} else if ((Character.isDigit(s1.charAt(0)) && s2.charAt(0) == '+')) {
			negResult = false;
			s2 = s2.substring(1);
		} else if ((Character.isDigit(s2.charAt(0)) && s1.charAt(0) == '+')) {
			negResult = false;
			s1 = s1.substring(1);
		}

		// put longest or largest number on top for multiplication
		if (!s1.equals(integerUtils.findMax(s1, s2))) {
			// s2 is longer or max, switch s1 and s2
			String temp = s1;
			s1 = s2;
			s2 = temp;
		}
		// Next, check if numbers are decimals
		int dotIndex1 = decimalUtils.checkIfDecimal(s1);
		int dotIndex2 = decimalUtils.checkIfDecimal(s2);
		boolean decimal = false;
		int dp = 0;
		if (dotIndex1 != 0 || dotIndex2 != 0) {
			// either one or the two numbers are decimals
			// count decimal places
			dp = (s1.length() - dotIndex1 - 1) + (s2.length() - dotIndex2 - 1);
			String[] dotsRemoved = decimalUtils.makeInteger(s1, s2);
			s1 = dotsRemoved[0];
			s2 = dotsRemoved[1];
			decimal = true;
		}

		NodePositionList<String> v1 = convertToPL(s1);
		NodePositionList<String> v2 = convertToPL(s2);
		Position<String> c1 = v1.last(); // counter for value 1
		Position<String> c2 = v2.last(); // counter for value 2
		String mult = "";
		String sum = "";
		String c = ""; // counter for zeroes
		// new value of variable with sum
		int carry = 0;
		while (c1 != null && c2 != null) {
			int i1 = Integer.parseInt(c1.element());
			int i2 = Integer.parseInt(c2.element());
			if (carry + (i1 * i2) > 9) {
				if (v1.prev(c1) == null) {
					mult = String.valueOf(carry + (i1 * i2)) + mult;
					carry = 0;
				} else {
					// handle carry...
					int dig = carry + (i1 * i2);
					String m = String.valueOf(dig);
					mult = m.charAt(1) + mult;
					carry = Character.getNumericValue(m.charAt(0));
					dig = 0;
				}

			} else {
				mult = String.valueOf((carry + i1 * i2)) + mult;
				carry = 0;
			}

			// Addition part...
			if (v1.prev(c1) == null) {

				// move c2 now
				c2 = v2.prev(c2);
				// put c1 at the beggining again
				c1 = v1.last();
				// sum
				sum = add(sum, mult + c);
				c += "0";
				mult = "";
			} else {
				// move c1 to prev
				c1 = v1.prev(c1);
			}

		}

		if (decimal) {
			if (negResult)
				return "-" + decimalUtils.insertDot(sum, dp);
			else
				return decimalUtils.insertDot(sum, dp);

		} else if (negResult) {
			return "-" + sum;
		}
		return sum;
	}

	/**
	 * Compute the factorial of the given value. The given value has to be a
	 * non-negative integer value; otherwise, the execution of the command ends
	 * displaying an error message.
	 * 
	 * @param value
	 *            value to compute factorial
	 * @return String representation of computed value
	 */
	public String factorial(String value) {
		String res = "1";
		if (same(value, "0"))
			return "1";
		
		while (!same(value, "1")) {
			res = multiply(res, value);
			value = subtract(value, "1");
		}
		return integerUtils.removeZeroes(res);

	}

	/**
	 * Determines if the given number is a prime number. A prime number is a
	 * positive integer value that has exactly two different integer factors.
	 * 
	 * @param value
	 *            value to determine if prime
	 * @return true if prime, false otherwise
	 */
	public boolean prime(String value) {
		if (value.charAt(0) == '+')
			value = value.substring(1);

		if (value.length() == 1) {
			if (value.equals("4"))
				return false;
			if (value.equals("6"))
				return false;
			if (value.equals("8"))
				return false;
			if (value.equals("9"))
				return false;
			return true;
		}

		if (!divisible(value))
			return false;

		String n = "3";
		String[] results;

		while (!n.equals(value)) {
			results = Division.Divide(value, n);
			if (results[1].equals("0"))
				return false;
			n = add(n, "2");
		}

		return true;

	}

	/**
	 * Determines if value is divisible
	 * 
	 * @param value
	 *            value to determine if divisible
	 * @return true if divisible, false otherwise
	 */
	public static boolean divisible(String value) {
		boolean stillPrime = true;

		if (value.charAt(value.length() - 1) == '0')
			return false;
		if (value.charAt(value.length() - 1) == '2')
			return false;
		if (value.charAt(value.length() - 1) == '4')
			return false;
		if (value.charAt(value.length() - 1) == '5')
			return false;
		if (value.charAt(value.length() - 1) == '6')
			return false;
		if (value.charAt(value.length() - 1) == '8')
			return false;

		return stillPrime;
	}

	/**
	 * Determines if a is larger than b
	 * 
	 * @param a
	 *            first numeric value
	 * @param b
	 *            second numeric value
	 * @return true if larger, false otherwise
	 */
	public static boolean larger(String a, String b) {
		while (a.length() > b.length()) {
			b = 0 + b;
		}
		while (a.length() < b.length()) {
			a = 0 + a;
		}

		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				if (Integer.parseInt(Character.toString(a.charAt(i))) > Integer
						.parseInt(Character.toString(b.charAt(i)))) {
					return true;
				}
				return false;
			}

		}

		if (a.equals(b))
			return false;

		return true;
	}

	/**
	 * Determines if a is same as b
	 * 
	 * @param a
	 *            first numeric value
	 * @param b
	 *            second numeric value
	 * @return true if same, false otherwise
	 */
	public static boolean same(String a, String b) {
		while (a.length() > b.length()) {
			b = 0 + b;
		}
		while (a.length() < b.length()) {
			a = 0 + a;
		}

		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i))
				return false;
		}

		return true;
	}

	/**
	 * Determines the prime factorization of number n. The given value has to be
	 * a positive integer value; otherwise, an error message is shown. Factors
	 * are displayed as a table in which each row has two parts, the first part
	 * is the prime factor and the second is the positive integer exponent that
	 * correspond to that prime number in the prime factorization of the given
	 * number.
	 * 
	 * @param value
	 *            value to determine factors
	 */
	public void factors(String value) {

		if (value.charAt(0) == '+')
			value = value.substring(1);

		String count = "0";
		String n = "2";
		String num;
		System.out.println("-------------------");
		System.out.println("factor\t|  exponent");
		System.out.println("-------------------");
		while (!n.equals(value)) {
			count = "0";
			String[] results = Division.Divide(value, n);
			num = results[1];

			while (results[1].equals("0")) {
				num = results[0];
				results = Division.Divide(num, n);
				count = add(count, "1");

			}

			if (!count.equals("0"))
				if (prime(n))
					System.out.println(n + "\t|\t" + count);

			n = add(n, "1");
		}
		System.out.println();
	}

	/**
	 * Loads the content of the given file adding it to the content that the
	 * current execution has up to the moment. Conflicts of variables with the
	 * same name are resolved by replacing the current variable in memory with
	 * the conflicting variable that is being read.
	 * 
	 * @param fname
	 *            name of file name to load
	 */
	public void load(String fname) {
		File f1 = new File(fname);
		try {
			Scanner in = new Scanner(f1);
			while (in.hasNext()) {
				String name = in.next();
				String value = in.next();
				boolean found = false;
				for (Variable v : variables) {
					if (v.getName().equals(name)) {
						v.setValue(convertToPL(value));
						found = true;
					}
				}
				if (!found) {
					Variable nv = new Variable(name);
					nv.setValue(convertToPL(value));
					variables.add(nv);
				}

			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

	}

	/**
	 * Saves the current content (variables and their values) in a file whose
	 * name is given.
	 * 
	 * @param fname
	 *            name of filename to save
	 */
	public void save(String fname) {
		File f1 = new File(fname);
		try {
			PrintWriter p1 = new PrintWriter(f1);
			for (Variable v : variables) {
				p1.println(v.getName());
				p1.println(convertPLToString(v.getValue()));
			}
			p1.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	/**
	 * Displays the value of the current variable
	 * 
	 * @param name
	 *            name of variable to show
	 * @return String representation of variable
	 */
	public String show(String name) {
		Variable vtr = searchVariable(name);
		if (vtr.getValue() == null)
			return vtr.getName() + "= 0";
		return vtr.getName() + "= " + convertPLToString(vtr.getValue());
	}

	/**
	 * Converts String to Positional List for system's main operations
	 * 
	 * @param number
	 *            number to convert to Positional List
	 * @return Positional List containing value converted
	 */
	public NodePositionList<String> convertToPL(String number) {
		NodePositionList<String> npl = new NodePositionList<>();
		for (int i = 0; i < number.length(); i++) {
			npl.addLast(String.valueOf(number.charAt(i)));
		}
		return npl;
	}

	/**
	 * Converts Positional List to String
	 * 
	 * @param npl
	 *            Positional List to convert
	 * @return String representation of Positional List
	 */
	public String convertPLToString(NodePositionList<String> npl) {
		String s = "";
		if (npl == null)
			return s;

		for (Position<String> p : npl.positions()) {
			s += p.element();
		}
		return s;
	}
}
