package iterators;

/**
 * This is the Interface of a Generic Filter to be used with the GenericFilterIterator
 * @author 49771
 * @author 50654
 * @param <E> Type of the Filter
 */
public interface Filter<E> {
	
	/**
	 * This method allows to see if a certain object match the filter options
	 * @param o Object to evaluate
	 * @return true if is from that type, false if not
	 */
	boolean applyFilter(E o);
}
