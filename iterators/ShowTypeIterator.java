package iterators;

import java.util.List;

import show.*;

/**
 * This is a Iterator that allows to iterate Entertainments by Type
  * @author 49771
 *	@author 50654
 */
public class ShowTypeIterator implements Iterator<Entertainment> {
	//Constants
	public static final String CONCERT = "CONCERT";
	public static final String FESTIVAL = "FESTIVAL";
	
	//Variables
	private List<Entertainment> shows;
	private int numberShows;
	private String type;
	private int current;
	
	public ShowTypeIterator(List<Entertainment> shows, int numberShows, String type) {
		this.shows = shows;
		this.numberShows = numberShows;
		this.type = type.toUpperCase();
		this.init();
	}
	
	@Override
	public void init() {
		current = 0;
		this.searchNext();
	}

	@Override
	public Entertainment next() {
		Entertainment enter = shows.get(current++);
		this.searchNext();
		return enter;
	}

	@Override
	public boolean hasNext() {
		return current < numberShows;
	}

	/**
	 * This method search for the next element of a certain type
	 */
	private void searchNext() {
		while((current < numberShows) && !isType(type, shows.get(current))){
			current++;
		}
	}
	
	/**
	 * This method checks if the element is instance of a type initialized in the constructor
	 * @param type type of the element
	 * @param showCheck element to be checked
	 * @return true if s matches the type
	 */
	private boolean isType(String type, Entertainment showCheck){
		if(type.equals(FESTIVAL) && (showCheck instanceof Festival))
			return true;
		if(type.equals(CONCERT) && (showCheck instanceof Concert))
			return true;
		
		return false;
	}
}
