package exceptions;

/**
 * Exception class for types of exceptions thrown when accessing invalid
 * positions
 * 
 * @author Pedro I. Rivera Vega
 *
 */
@SuppressWarnings("serial")
public class BoundaryViolationException extends RuntimeException {

	/**
	 * Default constructor
	 */
	public BoundaryViolationException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message message of exception
	 */
	public BoundaryViolationException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and Throwable object
	 * 
	 * @param message
	 *            message of exception
	 * @param cause
	 *            Throwable object
	 */
	public BoundaryViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor with Throwable object
	 * 
	 * @param cause
	 *            Throwable object
	 */
	public BoundaryViolationException(Throwable cause) {
		super(cause);
	}

}
