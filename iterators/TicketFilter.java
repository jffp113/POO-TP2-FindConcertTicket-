package iterators;

import show.*;

public class TicketFilter implements Filter<Ticket> {

	
	//Constants
	public static final String CONCERT = "CONCERT";
	public static final String FESTIVAL = "FESTIVAL";
	
	
	//Variabels
	private String type;
	
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
