package show;

import java.util.Iterator;

import artists_band.Performer;

public interface Festival extends Entertainment {
	/**
	 * This method allows to add a day to the festival
	 * @param per Performs
	 * @param counter number of performers
	 */
	void addDay(Performer[] per, int counter);
	
	/**
	 * This method allows to get the day iterator
	 * @return day iterator
	 */
	Iterator<Day> getDay();
	
	/**
	 * This method allows to get the last day of the festival
	 * @return Last day of the festival as a String
	 */
	String getEndDateAsString();
	
	/**
	 * This method allows to get the array of prices
	 * @return prices as an integer[]
	 */
	int[] getPriceIt();
}
