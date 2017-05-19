package users;


import show.Ticket;

public interface Client extends User {
	
	/**
	 * This method adds tickets to the the client bag
	 * @param ticket ticket bought
	 */
	void addTicket(Ticket ticket);
	
	/**
	 * This method allows the creation of a iterator for the user tickets
	 * @param type type of the ticket
	 * @return Iterator
	 */
	iterators.Iterator<Ticket> listTickets(String type);
	
}
