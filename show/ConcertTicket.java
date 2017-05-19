package show;

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
