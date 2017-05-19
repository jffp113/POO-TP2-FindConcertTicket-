import java.util.Iterator;
import java.util.List;

import exeptions.*;
import show.Entertainment;
import show.Ticket;
import users.User;

public interface ConcertManager {
	/**
	 * This method allows the creation of a new Administrator
	 * @param name Name of the administrator account
	 * @return Administrator password
	 * @throws UserLoggedInExeption
	 * @throws UserAlreadyExistExeption
	 */
	String newAdmin(String name) throws UserLoggedInExeption, UserAlreadyExistExeption;
	
	/**
	 * This method allows the creation of a new client
	 * @param name Name of the client account
	 * @return Client password
	 * @throws UserLoggedInExeption
	 * @throws UserAlreadyExistExeption
	 */
	String newClient(String name) throws UserLoggedInExeption, UserAlreadyExistExeption;
	
	/**
	 * This method allows the account logging
	 * @param name Name of the account to be logged
	 * @param password Account password
	 * @throws UserDoesNotExistExeption
	 * @throws UserAlreadyLoggedInExeption
	 * @throws UserPasswordMismatchExeption
	 * @throws UserLoggedInExeption
	 */
	void login(String name, String password) throws UserDoesNotExistExeption, UserAlreadyLoggedInExeption, UserPasswordMismatchExeption, UserLoggedInExeption;
	
	/**
	 * This method allows the logout of an active logged account
	 * @return the user that was logged in
	 * @throws NoUserLoggedInExeption
	 */
	User logout() throws NoUserLoggedInExeption;
	
	/**
	 * This method creates an Iterator organize the shows by clients
	 * @return Iterator to the shows
	 */
	Iterator<Entertainment> iteratorBySales();
	
	/**
	 * This method creates an iterator organize by Type  
	 * @param type Type of the show
	 * @return Iterator to the requested type of show
	 */
	iterators.Iterator<Entertainment> iteratorByEntertainmentType(String type);
	
	/**
	 * This method allows the creation of a new band 
	 * @param name Name of the band
	 * @param discography Band discography
	 * @param elements elements that are part of the band
	 * @throws PerformerAlreadyExists 
	 * @throws UserWithoutPrivilegesExeption
	 */
	void addBand(String name, String[] discography, String[] elements) throws PerformerAlreadyExists, UserWithoutPrivilegesExeption;
	
	/**
	 * This method allows the creation of a new artist (solo)
	 * @param name The name of the artist
	 * @param discography Artist discography
	 * @throws PerformerAlreadyExists 
	 * @throws UserWithoutPrivilegesExeption
	 */
	void addArtist(String name, String[] discography) throws PerformerAlreadyExists, UserWithoutPrivilegesExeption;
	
	/**
	 * This method allows the creation of a new Festival
	 * @param entertainemntName Entertainment name
	 * @param description festival description
	 * @param numberTickets festival number of tickets
	 * @param beginDate the beginning date of the festival
	 * @param endDate the end date of the festival
	 * @param prices price to buy a day in the festival
	 * @param elem artist that act
	 * @throws UserWithoutPrivilegesExeption
	 * @throws ShowAlreadyExistsExeption
	 * @throws PerformerDoesNotExistExeption
	 */
	void addFestival(String entertainemntName, String description, int numberTickets, String beginDate, String endDate,int[] prices, List<String> elem) throws UserWithoutPrivilegesExeption, ShowAlreadyExistsExeption, PerformerDoesNotExistExeption;
	
	/**
	 * This method allows to get the buffer of the users that could't be added in the festival
	 * @return Buffer Iterator for the users that could be added
	 */
	Iterator<String> inexistentArtistBueffer();
	
	/**
	 * This method creates a Iterator for all shows
	 * @return Entertainment Iterator
	 */
	Iterator<Entertainment> listShows();
	

	/**
	 * This method creates an iterator organize by Type
	 * @param name of the show  
	 * @param type Type of the show
	 * @return Iterator to the requested type of show
	 */
	iterators.Iterator<Entertainment> shows(String name, String type);
	
	/**
	 * This method allows to get a certain show by name
	 * @param name Name of the show
	 * @param date begin date of the show
	 * @return Entertainment (show)
	 * @throws NoEntertainmentExeption
	 */
	Entertainment getShow(String name, String date) throws NoEntertainmentExeption;
	
	/**
	 * This method allows the user to buy a concert ticket
	 * @param name Name of the show 
	 * @param date day of the show to be bought
	 * @param number number of tickets
	 * @return price of the ticket
	 * @throws NoEntertainmentExeption
	 * @throws NoTicketsAvailableExeption
	 * @throws UserWithoutPrivilegesExeption
	 */
	int buyConcertTicket(String name, String date, int number) throws NoEntertainmentExeption, NoTicketsAvailableExeption, UserWithoutPrivilegesExeption;
	
	/**
	 * This method allows the user to buy a festival ticket
	 * @param name Name of the festival
	 * @param date begin date of the festival
	 * @param buydate days to buy the ticket
	 * @return price of the ticket
	 * @throws NoEntertainmentExeption
	 * @throws UserWithoutPrivilegesExeption
	 * @throws NoTicketsAvailableExeption
	 */
	int buyFestivalTicket(String name, String date ,List<String> buydate) throws NoEntertainmentExeption, UserWithoutPrivilegesExeption, NoTicketsAvailableExeption;
	
	/**
	 * This method allows to list Client tickets
	 * @param type type of the ticket
	 * @return iterator to the tickets
	 * @throws UserWithoutPrivilegesExeption
	 */
	iterators.Iterator<Ticket> listTikets(String type) throws UserWithoutPrivilegesExeption;
	
	/**
	 * This method allows the creation of a new concert
	 * @param entertainemntName Name of the concert
	 * @param description description of the concert
	 * @param numberTickets number of ticket
	 * @param price price per ticket
	 * @param date begin date of the concert
	 * @param artist artist that will performer (as String)
	 * @throws UserWithoutPrivilegesExeption
	 * @throws ShowAlreadyExistsExeption
	 * @throws PerformerDoesNotExistExeption
	 */
	public void addConcert(String entertainemntName, String description, int numberTickets, int price, String date, String artist) throws UserWithoutPrivilegesExeption, ShowAlreadyExistsExeption, PerformerDoesNotExistExeption;
}
