package exceptions;

/**
 * Represents exceptions launch when working with invalid values for this system
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public class InvalidValueException extends RuntimeException {

	/**
	 * InvalidValueException default constructor
	 */
	public InvalidValueException() {
		super();
	}

	/**
	 * InvalidValueException constructor with message parameter
	 * 
	 * @param arg0
	 *            message of this exception
	 */
	public InvalidValueException(String arg0) {
		super(arg0);
	}

}
