package iterators;

public interface Iterator<E> {
	
	/**
	 * This method allows the initialization of the Iterator
	 */
	void init();
	
	/**
	 * This method gets the next element
	 * @return E element
	 */
	E next();
	
	/**
	 * This method allows to see if there is next element to be iterated
	 * @return true if there is other element , otherwise returns false
	 */
	boolean hasNext();
}
