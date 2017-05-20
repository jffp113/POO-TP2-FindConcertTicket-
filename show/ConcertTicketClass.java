package show;

import java.time.LocalDate;

/**
 * This is a class of the ConcertTicket
 * It allows the creation of a Ticket
 * @author 49771
 * @author 50654
 */
public class ConcertTicketClass extends ticketClass implements ConcertTicket{
	//Constants
	
	//Variables
	private int quantitaty;
	private LocalDate date;
	
	//Constructor
	/**
	 * This is the Constructor of the ConcertTicketClass
	 * @param entretain Name of the entertainment
	 * @param price price of the entertainment
	 * @param quantitaty amount of tickets for the day
	 * @param date Begin date
	 */
	public ConcertTicketClass(String entretain, int price, int quantitaty, String date) {
		super(entretain, price);
		this.quantitaty = quantitaty;
		this.date = LocalDate.parse(date);
	}
	
	public int getQuantitaty(){
		return quantitaty;
	}
	
	public String getDate() {
		return date.toString();
	}
}
