package systemGeneralClasses;

/**
 * 
 * @author Pedro I. Rivera Vega
 * 
 *         This class is meant to represent object to hold some type of error
 *         messages. That type of object will hold some extra message whenever
 *         and invalid command is detected.
 * 
 */
public class ErrMsg {
	private String msg;

	/**
	 * ErrMsg object default constructor, set msg as null
	 */
	public ErrMsg() {
		msg = null;
	}

	/**
	 * ErrMsg object constructor, set param as msg
	 * 
	 * @param msg
	 *            error message to set
	 */
	public ErrMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Determines if ErrMsg is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return msg == null;
	}

	/**
	 * Resets error message
	 */
	public void reset() {
		msg = null;
	}

	/**
	 * Error message getter
	 * 
	 * @return message from ErrMsg object
	 */
	public String getMessage() {
		return msg;
	}

	/**
	 * Sets param msg to object's msg
	 * 
	 * @param msg
	 *            message to set
	 */
	public void setMessage(String msg) {
		this.msg = msg;
	}

}
