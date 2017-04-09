package exceptions;

/**
 * Represent type of exception launch when accessing invalid position in list
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public class InvalidPositionException extends RuntimeException {

	/**
	 * InvalidPositionException default constructor
	 */
	public InvalidPositionException() {
		super();
	}

	/**
	 * InvalidPositionException constructor with message parameter
	 * 
	 * @param message
	 *            message of exception
	 */
	public InvalidPositionException(String message) {
		super(message);
	}

	/**
	 * InvalidPositionException constructor with message and Throwable object
	 * parameter
	 * 
	 * @param message
	 *            message of exception
	 * @param cause
	 *            Throwable object of exception
	 */
	public InvalidPositionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * InvalidPositionException constructor with only Throwable object as
	 * parameter
	 * 
	 * @param cause
	 *            Throwable object of exception
	 */
	public InvalidPositionException(Throwable cause) {
		super(cause);
	}

}
