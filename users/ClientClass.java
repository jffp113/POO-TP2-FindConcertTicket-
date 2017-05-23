package users;

import java.util.LinkedList;
import java.util.List;

import iterators.*;
import show.Ticket;

/**
 * This is the Class of a Client (type of user)
 * @author 49771
 * @author 50654
 */
public class ClientClass extends UserClass implements Client{

	//Variables
	private List<Ticket> tickets;
	
	//Constructor
	/**
	 * This is the ClientClass Constructor
	 * @param userName User name
	 * @param password user password
	 */
	public ClientClass(String userName, String password) {
		super(userName, password);
		tickets = new LinkedList<Ticket>();
	}

	@Override
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}
	
	public iterators.Iterator<Ticket> listTickets(String type) {
		return new FilterIterator<Ticket>(tickets.iterator(),new TicketFilter(type));
	}
	
}
