package iterators;

import show.*;

/**
 * This is the Class of a Filter to be used with the GenericFilterIterator
 * @author 49771
 * @author 50654
 */
public class TicketFilter implements Filter<Ticket> {

	
	//Constants
	public static final String CONCERT = "CONCERT";
	public static final String FESTIVAL = "FESTIVAL";
	
	
	//Variables
	private String type;
	
	/**
	 * This is the Constructor of the filter
	 * @param type type to filter
	 */
	public TicketFilter(String type) {
		this.type = type;
	}
	
	@Override
	public boolean applyFilter(Ticket o) {
		if(type.equals(FESTIVAL) && o instanceof FestivalTicket)
			return true;
		if(type.equals(CONCERT) && o instanceof ConcertTicket)
			return true;
		
		return false;
	}

}
