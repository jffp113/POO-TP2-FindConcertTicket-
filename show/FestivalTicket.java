package show;

import java.time.LocalDate;
import java.util.Iterator;

public interface FestivalTicket extends Ticket {
	
	/**
	 * This method allows to get the day iterator of a festival
	 * @return
	 */
	Iterator<LocalDate> listDay();
}
