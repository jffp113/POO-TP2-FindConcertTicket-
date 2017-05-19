package artists_band;

import show.Entertainment;

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
	iterators.Iterator<Entertainment> getShowIterator(String type);
	
	/**
	 * This method gets a specific show from the agenda
	 * @param name Name of the show
	 * @param date Date of the show to be retrieved
	 * @return
	 */
	Entertainment getShow(String name, String date);
}
