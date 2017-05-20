package show;

import java.time.LocalDate;
import java.util.List;

import exeptions.*;

/**
 * This is the Abstract for the Entertainments so it allows to unify equal
 * methods and variables
 */
public abstract class EntertainmentAbstractClass implements Entertainment {
	//Constants
	
	//Variabels
	private String entertainmentName;
	private String description;
	private int numberTickets;
	private int soldTickets;
	
	//Constructor
	/**
	 * This is the constructor of the EntertainmentClass
	 * @param entertainemntName show name
	 * @param description show description
	 * @param numberTickets show number of tickets
	 */
	public EntertainmentAbstractClass(String entertainemntName, String description, int numberTickets) {
		this.entertainmentName = entertainemntName;
		this.description = description;
		this.numberTickets = numberTickets;
	}
	
	
	public String getName() {
		return this.entertainmentName;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getInicialTickets() {
		return this.numberTickets;
	}
	
	public int getSoldTickets() {
		return soldTickets;
	}
	
	public Ticket buytickets(int numberTickets, List<String> dates) throws NoTicketsAvailableExeption{
		soldTickets += numberTickets;
		return null;
	}
	
	public abstract String getBeginDateAsString();
	
	public abstract LocalDate getBegin();
	
	public abstract String firstArtistName();
	
	public boolean equals(Object obj) {
		if (this == obj) return true; 
		
		if (obj == null) return false;
		
		if (!(obj instanceof EntertainmentAbstractClass)) return false;
			
		Entertainment other = (Entertainment) obj; 
		
		if (entertainmentName == null && getBeginDateAsString() == null) {
			if (other.getName() != null && other.getBeginDateAsString() == null) return false;
			else return true;
		}
		else return (entertainmentName.equals(other.getName()) && getBeginDateAsString().equals(other.getBeginDateAsString()));
	}
}
