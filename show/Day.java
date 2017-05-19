package show;

import java.util.Iterator;

import artists_band.Performer;

public interface Day {
	/**
	 * This method returns the Iterator for the days
	 * @return Iterator
	 */
	Iterator<Performer> getPerfomerIterator();
	
	/**
	 * This method returns the Date of the day
	 * @return date as String
	 */
	String getDate();
	
	/**
	 * This method gets the available tickets for the day
	 * @return available tickets
	 */
	int getAvailableTickets();
	
	/**
	 * This method sells a ticket for the day
	 */
	void sell();
	
	/**
	 * This method gets the first performer name for the day
	 * @return first performer name for the day
	 */
	String getFirstPerformer();
}
