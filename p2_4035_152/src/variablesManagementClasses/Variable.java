package variablesManagementClasses;

import lists.NodePositionList;

/**
 * Represent Variable objects managed by the system
 * 
 * @author Joel Torres
 *
 */
public class Variable {

	String name; // name of variable
	NodePositionList<String> value; // value(number) of variable

	/**
	 * Variable constructor
	 * 
	 * @param name
	 *            name of variable
	 */
	public Variable(String name) {
		this.name = name;
		this.value = null;
	}

	/**
	 * Getter for value
	 * 
	 * @return the value of the variable
	 */
	public NodePositionList<String> getValue() {
		return value;
	}

	/**
	 * Value setter
	 * 
	 * @param value
	 *            the value to set to variable
	 */
	public void setValue(NodePositionList<String> value) {
		this.value = value;
	}

	/**
	 * Name getter
	 * 
	 * @return the name of the variable
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name setter
	 * 
	 * @param name
	 *            the name to set to variable
	 */
	public void setName(String name) {
		this.name = name;
	}

}
