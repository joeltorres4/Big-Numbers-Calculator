
package exceptions;

/**
 * Exception class for types of exceptions thrown when accessing an empty list
 * 
 * @author Pedro I. Rivera-Vega
 *
 */
public class EmptyListException extends RuntimeException {

	/**
	 * Default constructor for this type of object
	 */
	public EmptyListException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 *            message of exception
	 */
	public EmptyListException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and Throwable object representing cause of
	 * exception
	 * 
	 * @param message
	 *            message of exception
	 * @param cause
	 *            Throwable object
	 */
	public EmptyListException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor with only Throwable object, no message
	 * 
	 * @param cause
	 *            Throwable object
	 */
	public EmptyListException(Throwable cause) {
		super(cause);
	}

}
