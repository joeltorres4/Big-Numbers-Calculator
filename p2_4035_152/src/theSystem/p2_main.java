/**
 * 
 */
package theSystem;

import java.io.IOException;

import systemGeneralClasses.SystemController;

/**
 * Main class where execution begins
 * 
 * @author Pedro I. Rivera-Vega
 *
 */
public class p2_main {

	/**
	 * System's main method
	 * 
	 * @param args
	 *            array of input
	 * @throws IOException
	 *             thrown if input error
	 */
	public static void main(String[] args) throws IOException {
		SystemController system = new SystemController();
		system.start();
	}

}
