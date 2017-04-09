package theSystem;

import java.util.ArrayList;

import stack.IntStack;
import systemGeneralClasses.Command;
import systemGeneralClasses.CommandActionHandler;
import systemGeneralClasses.CommandProcessor;
import systemGeneralClasses.FixedLengthCommand;
import systemGeneralClasses.SystemCommand;
import utils.integerUtils;
import variablesManagementClasses.Variable;
import variablesManagementClasses.VariableManager;

/**
 * Contains commands for system
 * 
 * @author Pedro I. Rivera-Vega
 *
 */
public class SystemCommandsProcessor extends CommandProcessor {

	// NOTE: The HelpProcessor is inherited...

	// To initially place all lines for the output produced after a
	// command is entered. The results depend on the particular command.
	private ArrayList<String> resultsList;

	SystemCommand attemptedSC;
	// The system command that looks like the one the user is
	// trying to execute.

	boolean stopExecution;
	// This field is false whenever the system is in execution
	// Is set to true when in the "administrator" state the command
	// "shutdown" is given to the system.

	// //////////////////////////////////////////////////////////////
	// The following are references to objects needed for management
	// of data as required by the particular options of the command-set..
	// The following represents the object that will be capable of
	// managing the different lists that are created by the system
	// to be implemented as a lab exercise.
	private VariableManager vm = new VariableManager();

	/**
	 * Initializes the list of possible commands for each of the states the
	 * system can be in.
	 */
	public SystemCommandsProcessor() {

		// stack of states
		currentState = new IntStack();
		currentState.push(GENERALSTATE);

		// Maximum number of states for the moment is assumed to be 1
		// this may change depending on the types of commands the system
		// accepts in other instances......
		createCommandList(1); // only 1 state -- GENERALSTATE

		add(GENERALSTATE,
				SystemCommand.getFLSC("var var_name", new CreateProcessor()));
		add(GENERALSTATE,
				SystemCommand.getFLSC("delete var_name", new DeleteProcessor()));
		add(GENERALSTATE, SystemCommand.getFLSC("add var_name value value",
				new AddProcessor()));
		add(GENERALSTATE, SystemCommand.getFLSC("sub var_name value value",
				new SubtractProcessor()));
		add(GENERALSTATE, SystemCommand.getFLSC("mult var_name value value",
				new MultiplyProcessor()));
		add(GENERALSTATE, SystemCommand.getFLSC("factorial var_name value",
				new FactorialProcessor()));
		add(GENERALSTATE,
				SystemCommand.getFLSC("factors value", new FactorsProcessor()));
		add(GENERALSTATE,
				SystemCommand.getFLSC("prime value", new PrimeProcessor()));
		add(GENERALSTATE,
				SystemCommand.getFLSC("load file_name", new LoadProcessor()));
		add(GENERALSTATE,
				SystemCommand.getFLSC("save file_name", new SaveProcessor()));
		add(GENERALSTATE,
				SystemCommand.getFLSC("show var_name", new ShowProcessor()));
		add(GENERALSTATE,
				SystemCommand.getFLSC("exit", new ShutDownProcessor()));
		add(GENERALSTATE, SystemCommand.getFLSC("help", new HelpProcessor()));

		// set to execute....
		stopExecution = false;

	}

	/**
	 * Results list getter
	 * 
	 * @return resultsList
	 */
	public ArrayList<String> getResultsList() {
		return resultsList;
	}

	// INNER CLASSES -- ONE FOR EACH VALID COMMAND --
	/**
	 * The following are inner classes. Notice that there is one such class for
	 * each command. The idea is that enclose the implementation of each command
	 * in a particular unique place. Notice that, for each command, what you
	 * need is to implement the internal method "execute(Command c)". In each
	 * particular case, your implementation assumes that the command received as
	 * parameter is of the type corresponding to the particular inner class. For
	 * example, the command received by the "execute(...)" method inside the
	 * "LoginProcessor" class must be a "login" command.
	 *
	 */

	/**
	 * Variable creator processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class CreateProcessor implements CommandActionHandler {

		/**
		 * Calls create method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {

			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String var_name = fc.getOperand(1);
			vm.createVar(var_name);
			return resultsList;
		}

	}

	/**
	 * Addition processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class AddProcessor implements CommandActionHandler {

		/**
		 * Calls addition method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {

			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String var_name = fc.getOperand(1);
			String value1 = fc.getOperand(2);
			String value2 = fc.getOperand(3);

			Variable v = vm.searchVariable(var_name);
			if (v == null)
				resultsList.add("Name of variable given doesn't exist");
			else
				v.setValue(vm.convertToPL(integerUtils.removeZeroes(vm.add(
						value1, value2))));

			return resultsList;

		}

	}

	/**
	 * Subtract processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class SubtractProcessor implements CommandActionHandler {

		/**
		 * Calls subtract method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String var_name = fc.getOperand(1);
			String value1 = fc.getOperand(2);
			String value2 = fc.getOperand(3);
			Variable v = vm.searchVariable(var_name);
			if (v == null)
				resultsList.add("Name of variable given doesn't exist");
			else
				v.setValue(vm.convertToPL(integerUtils.removeZeroes(vm
						.subtract(value1, value2))));
			return resultsList;
		}

	}

	/**
	 * Multiplication processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class MultiplyProcessor implements CommandActionHandler {

		/**
		 * Calls multiply method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String var_name = fc.getOperand(1);
			String value1 = fc.getOperand(2);
			String value2 = fc.getOperand(3);
			Variable v = vm.searchVariable(var_name);
			if (v == null)
				resultsList.add("Name of variable given doesn't exist");
			else
				v.setValue(vm.convertToPL(vm.multiply(value1, value2)));
			return resultsList;
		}

	}

	/**
	 * Factorial processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class FactorialProcessor implements CommandActionHandler {

		/**
		 * Calls factorial method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String var_name = fc.getOperand(1);
			String value1 = fc.getOperand(2);
			Variable v = vm.searchVariable(var_name);
			if (v == null)
				resultsList.add("Name of variable given doesn't exist");
			else {
				if (value1.charAt(0) != '-') {
					v.setValue(vm.convertToPL(vm.factorial(value1)));
				} else
					resultsList
							.add("Unable to compute factorial of non-positive values");
			}

			return resultsList;
		}

	}

	/**
	 * Factorization processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class FactorsProcessor implements CommandActionHandler {

		/**
		 * Calls factors method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String number = fc.getOperand(1);
			if (number.charAt(0) == '+')
				number = number.substring(1);
			else if (!integerUtils.isNumeric(number)) {
				resultsList.add(number + " is not valid");
				return resultsList;
			} else
				vm.factors(number);
			return resultsList;
		}

	}

	/**
	 * Primes processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class PrimeProcessor implements CommandActionHandler {

		/**
		 * Calls prime method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String number = fc.getOperand(1);
			if (number.charAt(0) == '+')
				number = number.substring(1);
			else if (!integerUtils.isNumeric(number)) {
				resultsList.add(number + " is not valid");
				return resultsList;
			}

			// number is valid here...
			if (vm.prime(number))
				resultsList.add(number + " is prime");
			else
				resultsList.add(number + " is not prime");
			return resultsList;
		}

	}

	/**
	 * File load processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class LoadProcessor implements CommandActionHandler {

		/**
		 * Calls load method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String file_name = fc.getOperand(1);
			vm.load(file_name);
			return resultsList;
		}

	}

	/**
	 * File save processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class SaveProcessor implements CommandActionHandler {

		/**
		 * Calls save method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String file_name = fc.getOperand(1);
			vm.save(file_name);
			return resultsList;
		}

	}

	/**
	 * Variable show processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class ShowProcessor implements CommandActionHandler {

		/**
		 * Calls show method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {
			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String var_name = fc.getOperand(1);
			Variable v = vm.searchVariable(var_name);
			if (v == null)
				resultsList.add("Name of variable given doesn't exist");
			else
				resultsList.add(vm.show(var_name));
			return resultsList;
		}

	}

	/**
	 * Shutdown processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class ShutDownProcessor implements CommandActionHandler {

		/**
		 * Stops system execution
		 */
		public ArrayList<String> execute(Command c) {

			resultsList = new ArrayList<String>();
			stopExecution = true;
			return resultsList;
		}
	}

	/**
	 * Variable delete processor for system
	 * 
	 * @author Joel Torres
	 *
	 */
	private class DeleteProcessor implements CommandActionHandler {

		/**
		 * Calls delete method in variable manager class
		 */
		public ArrayList<String> execute(Command c) {

			resultsList = new ArrayList<>();
			FixedLengthCommand fc = (FixedLengthCommand) c;
			String var_name = fc.getOperand(1);
			Variable v = vm.searchVariable(var_name);
			if (v == null)
				resultsList.add("Name of variable given doesn't exist");
			else
				vm.deleteVar(var_name);
			return resultsList;
		}

	}

	/**
	 * Determines if system is in shutdown mode
	 * 
	 * @return true if in shutdown mode, false otherwise
	 */
	public boolean inShutdownMode() {
		return stopExecution;
	}

}
