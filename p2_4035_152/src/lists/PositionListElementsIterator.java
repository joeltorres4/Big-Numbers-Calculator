package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

import exceptions.EmptyListException;

/**
 * Iterator class for PositionList implementation
 * 
 * @author Joel Torres
 *
 * @param <T>
 *            generic param
 */
public class PositionListElementsIterator<T> implements Iterator<T> {

	private Position<T> current;
	private PositionList<T> theList;

	/**
	 * Constructor to initialize list and current
	 * 
	 * @param list
	 *            list to initialize
	 */
	public PositionListElementsIterator(PositionList<T> list) {
		theList = list;
		try {
			current = theList.first();
		} catch (EmptyListException e) {
			current = null;
		}
	}

	/**
	 * Determine if iterator has next element
	 * 
	 * @return true if more to iterate over, false otherwise
	 */
	public boolean hasNext() {
		return current != null;
	}

	/**
	 * Determines next value in iteration
	 * 
	 * @return next value
	 */
	public T next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException("Iterator has past the end.");
		Position<T> ptr = current;
		try {
			current = theList.next(current);
		} catch (Exception e) {
			current = null;
		}
		return ptr.element();
	}

	/**
	 * Removes from list: not implemented
	 */
	public void remove() {
		// not implemented

	}
}
