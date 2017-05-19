package show;

import java.time.LocalDate;

public class ConcertTicketClass extends ticketClass implements ConcertTicket{
	//Constants
	
	//Variabels
	private int quantitaty;
	private LocalDate date;
	
	//Constructor
	/**
	 * This is the Constructor of the ConcertTicketClass
	 * @param entretain Name of the entertainment
	 * @param price price of the entertainment
	 * @param quantitaty quantitaty of tickets for the day
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
