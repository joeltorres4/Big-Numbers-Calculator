package systemGeneralClasses;

import java.util.ArrayList;

import theSystem.IOCommandsProcessor;
import theSystem.SystemCommandsProcessor;

/**
 * Represents System Controller objects used in system
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public class SystemController {
	private static final int PROCMODE = 0;
	private static final int OUTPUTMODE = 1;
	private SystemCommandsProcessor sys;
	private IOCommandsProcessor io;
	private CommandProcessor actualProcessor;
	private ArrayList<String> outputList;
	private int currentMode;

	/**
	 * Default constructor for this type of objects
	 */
	public SystemController() {
		sys = new SystemCommandsProcessor();
		io = new IOCommandsProcessor();
		currentMode = PROCMODE;
	}

	/**
	 * Starts execution of system's controller object.
	 */
	public void start() {
		CommandLine cLine;
		while (!sys.inShutdownMode()) {
			// read next command line
			cLine = io.readCommandLine();

			// process the attempted command -- the content of cLine...
			processCommand(cLine);

		} // end execution cycle --while

	} // end start

	/**
	 * Initializes command by first first processing them
	 * 
	 * @param cLine
	 */
	private void processCommand(CommandLine cLine) {
		// the following is to carry error messages if detected during
		// the preprocessing of the input given by the user
		ErrMsg errMsg = new ErrMsg();

		// Set the current mode -- either processing or output
		if (currentMode == PROCMODE)
			actualProcessor = sys;
		else
			actualProcessor = io;

		actualProcessor.preProcessCommand(cLine, errMsg);
		if (cLine.getSc() != null) {

			// here, we need to determine if the command is
			// of fixed length, or if it is of variable length.....
			Command cmd = makeCommand(cLine);

			// initiate execution of command
			SystemCommand matchingSC = cLine.getSc();
			outputList = actualProcessor.executeCommand(matchingSC, cmd);
			// show results...
			if (outputList != null && !outputList.isEmpty()) {
				currentMode = OUTPUTMODE;
				io.setResultsList(outputList);
				io.processOutput();
			}
			if (!io.hasMoreOutputToProcess())
				currentMode = PROCMODE;
		} else {
			// the output here is direct since it is due to invalid commands
			outputList = null;
			if (!errMsg.isEmpty()) {
				System.out.println(errMsg.getMessage());
			} else
				System.out.println("Invalid command for the current system mode. ");
		}

	}

	/**
	 * Validates command and returns it as Command object
	 * 
	 * @param cLine
	 *            command to validate
	 * @return cLine as Command object
	 */
	private Command makeCommand(CommandLine cLine) {
		SystemCommand sc = cLine.getSc();
		CommandValidator cv = sc.getCommandValidator();
		if (cv instanceof FixedLengthCommandValidator)
			return new FixedLengthCommand(cLine);
		else {
			VariableLengthCommandValidator scv = (VariableLengthCommandValidator) cv;
			return new VariableLengthCommand(scv.getLastItemsLists());
		}
	}
}
