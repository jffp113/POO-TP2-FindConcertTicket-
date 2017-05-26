package users;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import show.FestivalTicket;
import show.Ticket;

/**
 * This is the Class of a Client (type of user)
 * @author 49771
 * @author 50654
 */
public class ClientClass extends UserClass implements Client{

	//Variables
	private List<Ticket> festivalTickets;
	private List<Ticket> concertTickets;
	
	//Constructor
	/**
	 * This is the ClientClass Constructor
	 * @param userName User name
	 * @param password user password
	 */
	public ClientClass(String userName, String password) {
		super(userName, password);
		festivalTickets = new LinkedList<Ticket>();
		concertTickets = new LinkedList<Ticket>();
	}

	@Override
	public void addTicket(Ticket ticket) {
		if(ticket instanceof FestivalTicket)
			festivalTickets.add(ticket);
		else
			concertTickets.add(ticket);
	}
	
	public Iterator<Ticket> listTickets(String type) {
		if(type.equals("CONCERT"))
			return concertTickets.iterator();
		else
			return festivalTickets.iterator();
	}
	
}
