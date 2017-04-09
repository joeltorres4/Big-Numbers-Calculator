package systemGeneralClasses;

import operandHandlers.SimpleOperandHandler;

/**
 * Represent FixedLengthCommandValidator objects, used to validate commands with
 * fixed length
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public class FixedLengthCommandValidator implements CommandValidator {

	/**
	 * Validates input by comparing system command and command line
	 * 
	 * @return true if valid, false otherwise
	 */
	public boolean validate(SystemCommand mSCommand, CommandLine c, ErrMsg errMsg) {
		if (c.getNumberOfTokens() != mSCommand.getNumberOfOperands() + 1) {
			errMsg.setMessage(
					"Command " + mSCommand.getName() + " requires " + mSCommand.getNumberOfOperands() + " operand(s).");
			return false;
		} else
			// check that parameters are syntactically valid....
			for (int tn = 2; tn <= c.getNumberOfTokens(); tn++) {
				String oType = mSCommand.getOperandType(tn - 1);
				String token = c.getToken(tn);
				if (!SimpleOperandHandler.isValidToken(oType, token)) {
					errMsg.setMessage("Operand " + (tn - 1) + " does not match \"" + oType + "\".");
					return false;
				}
			}

		// no test has failed....
		return true;
	}

}
