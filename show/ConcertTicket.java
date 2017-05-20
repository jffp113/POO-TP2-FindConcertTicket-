package show;

/**
 * This is the interface of a ConcertTicket
 * @author 49771
 * @author 50654
 */
public interface ConcertTicket extends Ticket{
	/**
	 * Get the amount of tickets for a concert day
	 * @return amount of tickets
	 */
	 int getQuantitaty();
	 
	 /**
	  * Get the ticket date
	  * @return date as String
	  */
	 String getDate();
}
