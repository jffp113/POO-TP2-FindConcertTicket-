package show;

import artists_band.Performer;
import exeptions.NoTicketsAvailableExeption;

import java.time.LocalDate;
import java.util.List;

public class ConcertClass extends EntertainmentClass implements Concert {
	//Constants
	
	//Variables
	private Performer artist;
	private int price;
	private LocalDate beginDate;
	
	
	//Constructor
	/**
	 * This is the constructor of the concertClass
	 * @param entertainemntName Concert Name
	 * @param description Concert Description
	 * @param numberTickets Number of tickets
	 * @param price ticket price
	 * @param date begin date
	 * @param artist
	 */
	public ConcertClass(String entertainemntName, String description, int numberTickets, int price, String date, Performer artist) {
		super(entertainemntName, description, numberTickets);
		this.price = price;
		this.artist = artist;
		this.beginDate = LocalDate.parse(date);
	}

	
	@Override
	public String firstArtistName() {
		return artist.getName();
	}

	public int getPrice(){
		return this.price;
	}
	
	@Override
	public Ticket buytickets(int numberTickets, List<String> dates) throws NoTicketsAvailableExeption {
		if(!canSell(numberTickets))
			throw new NoTicketsAvailableExeption();
		
		super.buytickets(numberTickets, dates);
		
		return (Ticket)new ConcertTicketClass(super.getName(),(numberTickets*this.price),numberTickets, dates.get(0));
	}

	/**
	 * This method allows the creation of a ticket
	 * @param buy
	 * @return
	 */
	private boolean canSell(int buy) {
		return ((super.getInicialTickets() - (super.getSoldTickets() + buy)) >= 0);
	}


	@Override
	public String getBeginDateAsString() {
		return beginDate.toString();
	}


	@Override
	public LocalDate getBegin() {
		return beginDate;
	}
	
	
}
