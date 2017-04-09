package stack;

import exceptions.EmptyStackException;
import exceptions.FullStackException;

/**
 * Represent IntStack objects, a similar implementation of Stack
 * 
 * @author Pedro I. Rivera Vega
 *
 */
public class IntStack {
	private static final int DS = 10; // stack's default capacity
	private int[] element; // the stack's content
	private int top;

	/**
	 * Default constructor
	 */
	public IntStack() {
		element = new int[DS];
		top = -1;
	}

	/**
	 * IntStack constructor with parameter size
	 * 
	 * @param s
	 *            size to set to stack
	 */
	public IntStack(int s) {
		if (s <= 0)
			s = DS;
		element = new int[s];
		top = -1;
	}

	/**
	 * Determines if "stack" is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return top == -1;
	}

	/**
	 * Returns current size of "stack"
	 * 
	 * @return current size
	 */
	public int size() {
		return top + 1;
	}

	/**
	 * Removes top element
	 * 
	 * @return element at top
	 * @throws EmptyStackException
	 *             thrown when working with an empty stack
	 */
	public int pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return element[top--];
	}

	/**
	 * Push new element
	 * 
	 * @param n
	 *            element to push
	 * @throws FullStackException
	 *             thrown when working with full stack
	 */
	public void push(int n) throws FullStackException {
		if (top == element.length - 1)
			throw new FullStackException("Full stack in push...");
		else
			element[++top] = n;
	}

	/**
	 * Returns top element
	 * 
	 * @return top element
	 * @throws EmptyStackException
	 *             thrown when working with empty stack
	 */
	public int top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return element[top];
	}

}
