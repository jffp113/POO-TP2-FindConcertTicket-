package show;

import java.time.LocalDate;
import java.util.List;

import exeptions.*;

/**
 * This is the Interface of a Entertainment
 * @author 49771
 * @author 50654
 */
public interface Entertainment {
	/**
	 * This methods gets the show name
	 * @return show name
	 */
	String getName();
	
	/**
	 * This method gets the show description
	 * @return show description
	 */
	String getDescription();
	
	/**
	 * This method gets the initial tickets of the show
	 * @return initial tickets of the show
	 */
	int getInicialTickets();
	
	/**
	 * This method gets the amount of tickets sold
	 * @return tickets sold
	 */
	int getSoldTickets();
	
	/**
	 * This method allows user to buy a ticket
	 * @param numberTickets number of tickets to be bought
	 * @param dates day to buy
	 * @return a Ticket
	 * @throws NoTicketsAvailableExeption Exeption when there is no tickets to sell
	 */
	Ticket buytickets(int numberTickets, List<String> dates) throws NoTicketsAvailableExeption;
	
	/**
	 * This method returns the first artist to perform
	 * @return first artist name
	 */
	public abstract String firstArtistName();
	
	/**
	 * This method allows to get the begin date of the show
	 * @return begin date as String
	 */
	public abstract String getBeginDateAsString();
	
	/**
	 * This method allows to get the begin date
	 * @return Begin Date as a LocalDate
	 */
	abstract LocalDate getBegin();
	
	}
