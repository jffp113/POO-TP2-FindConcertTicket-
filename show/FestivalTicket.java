package show;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * This is the interface of a ConcertTicket
 * @author 49771
 * @author 50654
 */
public interface FestivalTicket extends Ticket {
	
	/**
	 * This method allows to get the day iterator of a festival
	 * @return a Iterator for localDates
	 */
	Iterator<LocalDate> listDay();
}
