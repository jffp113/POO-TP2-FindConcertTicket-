package iterators;

import java.util.List;
import show.*;

/**
 * This is Iterator allows to iterate Tickets by Type
 * @author 49771
 * @author 50654
 */
public class TicketTypeIterator implements Iterator<Ticket> {
		//Constants
		public static final String CONCERT = "CONCERT";
		public static final String FESTIVAL = "FESTIVAL";
		
		//Variables
		private List<Ticket> tickets;
		private int numberTickets;
		private String type;
		private int current;
		
		public TicketTypeIterator(List<Ticket> tickets, int numberTickets, String type) {
			this.tickets = tickets;
			this.numberTickets = numberTickets;
			this.type = type.toUpperCase();
			this.init();
		}
		
		@Override
		public void init() {
			current = 0;
			this.searchNext();
		}

		@Override
		public Ticket next() {
			Ticket enter = tickets.get(current++);
			this.searchNext();
			return enter;
		}

		@Override
		public boolean hasNext() {
			return current < numberTickets;
		}

		/**
		 * This method search for the next element of a certain type
		 */
		private void searchNext() {
			while((current < numberTickets) && !isType(type, tickets.get(current))){
				current++;
			}
		}
		
		/**
		 * This method checks if the element is instance of a type initialized in the constructor
		 * @param type type of the element to be checked
		 * @param showCheck ticket to be checked
		 * @return returns true if match
		 */
		private boolean isType(String type, Ticket showCheck){
			if(type.equals(FESTIVAL) && showCheck instanceof FestivalTicket)
				return true;
			if(type.equals(CONCERT) && showCheck instanceof ConcertTicket)
				return true;
			
			return false;
		}
}
