package iterators;

/**
 * This is a Iterator that allows to iterate Entertainments by Type
  * @author 49771
 *	@author 50654
 */
public class FilterIterator<E> implements Iterator<E> {
	//Constants
	
	//Variables
	
	private Filter<E> filter;
	private java.util.Iterator<E> it;
	private E nextIterable;
	private boolean hasNext;
	
	public FilterIterator(java.util.Iterator<E> iterator, Filter<E> filter) {
		this.filter = filter;
		it = iterator;
		nextIterable = null;
		hasNext = true;
		this.init();
	}
	
	@Override
	public void init() {
		this.searchNext();
	}

	@Override
	public E next() {
		E next = nextIterable;
		this.searchNext();
		return next;
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}

	/**
	 * This method search for the next element of a certain type
	 */
	private void searchNext() {
		hasNext = it.hasNext();
		
		while(it.hasNext()){
			nextIterable = it.next();
			if(filter.applyFilter(nextIterable)){
				return;
			}
		}
		nextIterable = null;
		this.hasNext = false;
	}
}
