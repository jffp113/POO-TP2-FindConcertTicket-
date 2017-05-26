package artists_band;

import java.util.Iterator;

import show.Entertainment;

/**
 * This is the Interface of the PerformerClass
 * @author 49771
 * @author 50654
 */
public interface Performer {
	/**
	 * This method retrieves the artist Name 
	 * @return artist name
	 */
	String getName();
	
	/**
	 * This method adds a new event to the performer agenda
	 * @param newEvent event to be added to the agenda
	 */
	void addEvent(Entertainment newEvent);
	
	/**
	 * This method retrieves a iterator to the agenda
	 * @param type type of the show to be iterated from  the agenda
	 * @return Show iterator
	 */
	Iterator<Entertainment> getShowIterator(String type);
	
}
