package exceptions;

/**
 * Exception class for exceptions that launch when working with full stacks
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public class FullStackException extends RuntimeException {

	/**
	 * FullStackException default constructor
	 */
	public FullStackException() {
		super();
	}

	/**
	 * FullStackException constructor with message parameter
	 * 
	 * @param message
	 *            message of exception
	 */
	public FullStackException(String message) {
		super(message);
	}

	/**
	 * FullStackException constructor with message and Throwable object as
	 * parameters
	 * 
	 * @param message
	 *            message of exception
	 * @param cause
	 *            Throwable object of exception
	 */
	public FullStackException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * FullStackException constructor with only Throwable object
	 * 
	 * @param cause
	 *            Throwable object of this exception
	 */
	public FullStackException(Throwable cause) {
		super(cause);
	}

}
