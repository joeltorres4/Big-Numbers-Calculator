package exceptions;

/**
 * Represents exceptions of this type, whose cause is accessing empty stacks
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public class EmptyStackException extends RuntimeException {

	/**
	 * EmptyStackException default constructor
	 */
	public EmptyStackException() {
	}

	/**
	 * EmptyStackException constructor with parameter message
	 * 
	 * @param arg0
	 *            message of exception
	 */
	public EmptyStackException(String arg0) {
		super(arg0);
	}

	/**
	 * EmptyStackException constructor with parameter Throwable object
	 * 
	 * @param arg0
	 *            Throwable object of this exception
	 */
	public EmptyStackException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * EmptyStackException constructor with parameter message and Throwable
	 * object
	 * 
	 * @param arg0
	 *            message of exception
	 * @param arg1
	 *            Throwable object
	 */
	public EmptyStackException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
