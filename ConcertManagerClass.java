import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import artists_band.*;
import exeptions.*;
import iterators.EntertainmentFilter;
import iterators.FilterIterator;
import show.*;
import users.*;

/**
 * This is the TOPCLASS, so all methods that are need to the user interact with
 * the program are all situated here
 * The ConcertManager all to Manage a DataBase(in java), of concert/Manager companies
 * @author 49771
 * @author 50654
 */
public class ConcertManagerClass implements ConcertManager{
	//Constants
	
	//Variables
	private Map<String,User> users;
	private User loggedUser;
	private Map<String,Performer> performers;
	private List<Entertainment> shows;
	private int adminCounter;
	private int clientCounter;
	
	//Constructor
	/**
	 * This is the Constructor of the ConcertManagerClass
	 */
	public ConcertManagerClass() {
		users = new HashMap<String,User>(50);//Constants
		performers = new HashMap<String,Performer>(50);
		shows = new ArrayList<Entertainment>();
		loggedUser = null;
		
		adminCounter = 0;
		clientCounter = 0;
	}

	/**
	 * this method sees if there is a User or Not
	 * @param name Name of the user
	 * @return true if there is the user otherwise it returns false
	 */
	private boolean isThereUser(String name){
		return users.containsKey(name);
	}
	
	/**
	 * this method sees if there is a Performer or Not
	 * @param name the name of the performer
	 * @return true if there is the performer otherwise it returns false
	 */
	private boolean isTherePerformer(String name){
		return performers.containsKey(name);
	}
	
	/**
	 * this method sees if there is a show or not
	 * @param name the name of the show
	 * @param date the beginning date of the show as String
	 * @return true if there is the show otherwise it returns false
	 */
	private boolean isThereShow(String name, String date){
		return shows.contains(new ConcertClass(name,null,0,0, date, null));
	}
	
	/**
	 * This method generates a administrator password
	 * @return administrator password as String
	 */
	private String generateAdminPassword() {
		return "admin" + adminCounter;
	}
	
	/**
	 * This method generates a client password
	 * @return client password as String
	 */
	private String generateClientPassword(){
		return "client" + clientCounter;
	}
	
	public String newAdmin(String name) throws UserLoggedInExeption, UserAlreadyExistExeption {
		String password = null;
		
		if(loggedUser != null)
			throw new UserLoggedInExeption();
		else if(isThereUser(name))
			throw new UserAlreadyExistExeption();
		
		adminCounter++;
		password = generateAdminPassword();
		users.put(name,new AdminClass(name,password));
		
		return password; 
	}
	
	public String newClient(String name) throws UserLoggedInExeption, UserAlreadyExistExeption {
		String password = null;
		
		if(loggedUser != null)
			throw new UserLoggedInExeption();
		else if(isThereUser(name))
			throw new UserAlreadyExistExeption();
		
		clientCounter++;
		password = generateClientPassword();
		users.put(name,new ClientClass(name,password));
		
		return password; 
	}
	
	public void login(String name, String password) throws UserDoesNotExistExeption, UserAlreadyLoggedInExeption, UserPasswordMismatchExeption, UserLoggedInExeption{

		if(!isThereUser(name))
			throw new UserDoesNotExistExeption();
		else if(loggedUser != null && loggedUser.getUserName().equals(name))
			throw new UserAlreadyLoggedInExeption();
		else if(loggedUser != null)
			throw new UserLoggedInExeption();
		else if(!users.get(name).getPassword().equals(password))
			throw new UserPasswordMismatchExeption();
		
		loggedUser = users.get(name);
	}
	
	public User logout() throws NoUserLoggedInExeption{
		User tmp = null;
		if(loggedUser == null)
			throw new NoUserLoggedInExeption();
		
		tmp = loggedUser;
		loggedUser = null;
		return tmp;
	}
	
	public void addBand(String name, String[] discography, String[] elements) throws PerformerAlreadyExists, UserWithoutPrivilegesExeption{
		if(!(loggedUser instanceof AdminClass))
			throw new UserWithoutPrivilegesExeption();
		if(this.isTherePerformer(name)){
			throw new PerformerAlreadyExists();
		}
		
		performers.put(name, new BandClass(name, discography, elements));
	}
	
	public void addArtist(String name, String[] discography) throws PerformerAlreadyExists, UserWithoutPrivilegesExeption{
		if(!(loggedUser instanceof AdminClass))
			throw new UserWithoutPrivilegesExeption();
		if(this.isTherePerformer(name)) {
			throw new PerformerAlreadyExists();
		}
		
		performers.put(name, new ArtistClass(name, discography));
	}
	
	public void addConcert(String entertainemntName, String description, int numberTickets, int price, String date, String artist) throws UserWithoutPrivilegesExeption, ShowAlreadyExistsExeption, PerformerDoesNotExistExeption{
		List<String> artistInexistent = new ArrayList<String>(10);
		
		if(!(loggedUser instanceof AdminClass)){
			throw new UserWithoutPrivilegesExeption();
		}
		else if(isThereShow(entertainemntName,date))
			throw new ShowAlreadyExistsExeption();
		else if(!isTherePerformer(artist)){
			artistInexistent.add(artist);
			throw new PerformerDoesNotExistExeption(artistInexistent);
		}
		Entertainment show = new ConcertClass(entertainemntName,description,numberTickets,price,date,performers.get(artist));
		shows.add(show);
		performers.get(artist).addEvent(show);
	}
	
	public void addFestival(String entertainemntName, String description, int numberTickets, String beginDate, String endDate,int[] prices, List<String> elem) throws UserWithoutPrivilegesExeption, ShowAlreadyExistsExeption, PerformerDoesNotExistExeption{
		List<String> artistInexistent = new ArrayList<String>(10);
		if(!(loggedUser instanceof AdminClass)){
			throw new UserWithoutPrivilegesExeption();
		}
		else if(isThereShow(entertainemntName,beginDate))
			throw new ShowAlreadyExistsExeption();
		
		Iterator<String> it = elem.iterator();
		String element;
		FestivalClass show = null;
		Performer[] per = new Performer[elem.size()];
		int counter = 0;
		boolean artistExist = false;
		artistInexistent = new ArrayList<String>(10);
		
		while(it.hasNext()){
			element = it.next();
			if(!element.equals("\0")){
				if(!isTherePerformer(element)){
					artistExist = true;
					artistInexistent.add(element);
				}
			}
		}
		if(artistExist)
			throw new PerformerDoesNotExistExeption(artistInexistent);
		
		show = new FestivalClass(entertainemntName,description,numberTickets, beginDate,endDate,prices);
		it = elem.iterator();
		
		while(it.hasNext()){
			element = it.next();
			if(!element.equals("\0")){
				per[counter++] = performers.get(element);
			}
			else{
				show.addDay(per, counter);
				counter = 0;
			}
		}
		shows.add(show);
		addAgenda(show);
	}

	/**
	 * Add a determined festival to a performer agenda
	 * @param fest Festival to be added
	 */
	private void addAgenda(Festival fest){
		Iterator<Day> dayIt = fest.getDay();
		Iterator<Performer> perIt = null;
		Performer per = null;
		Day agendaDay = null;
		
		while(dayIt.hasNext()){
			agendaDay = dayIt.next();
			perIt = agendaDay.getPerfomerIterator();
			
			while(perIt.hasNext()){
				per = perIt.next();
				per.addEvent(fest);
			}
		}
		
	}
	
	public Iterator<Entertainment> listShows(){
		return shows.iterator();
	}
	
	public iterators.Iterator<Entertainment> shows(String name, String type){//Throw exeption confirmar se need
		Performer per = performers.get(name);
		if(per == null){
			return null;
		}
		return performers.get(name).getShowIterator(type);
	}
	
	public Entertainment getShow(String name, String date) throws NoEntertainmentExeption{
		Entertainment enter = null;
		int festivalIndex = shows.indexOf(new FestivalClass(name,null,0,date,date,null));
		
		if(festivalIndex == -1)
			throw new NoEntertainmentExeption();
		
			enter = shows.get(festivalIndex);
		
		return enter;
	}

	public int buyConcertTicket(String name, String date, int number) throws NoEntertainmentExeption, NoTicketsAvailableExeption, UserWithoutPrivilegesExeption{
		int index = shows.indexOf((new ConcertClass(name,null,0,0, date, null)));
		Ticket ticket = null;
		Client cl;
		List<String> dates = new ArrayList<String>();
		dates.add(date);
		if((loggedUser == null) || (!(loggedUser instanceof Client)))
			throw new UserWithoutPrivilegesExeption();
		if(index == -1)
			throw new NoEntertainmentExeption();
		
		ticket = shows.get(index).buytickets(number, dates);
		cl = (Client)loggedUser;
		cl.addTicket(ticket);
		return ticket.getPrice();
	}
	
	public int buyFestivalTicket(String name, String date ,List<String> buydate) throws NoEntertainmentExeption, UserWithoutPrivilegesExeption, NoTicketsAvailableExeption{
		int index = shows.indexOf((new ConcertClass(name,null,0,0, date, null)));
		Ticket ticket = null;
		Client cl;
		
		if((loggedUser == null) || (!(loggedUser instanceof Client)))
			throw new UserWithoutPrivilegesExeption();
		if(index == -1)
			throw new NoEntertainmentExeption();
		
		ticket = (Ticket)shows.get(index).buytickets(1, buydate);
		cl = (Client)loggedUser;
		cl.addTicket(ticket);
		return ticket.getPrice();
	}
	
	public iterators.Iterator<Ticket> listTikets(String type) throws UserWithoutPrivilegesExeption {
		Client cl;
		
		if((loggedUser == null) || !(loggedUser instanceof Client))
			throw new UserWithoutPrivilegesExeption();
		
		cl = (Client) loggedUser;
		return cl.listTickets(type);
	}
	
	public Iterator<Entertainment> iteratorBySales() {
		Iterator<Entertainment> it = null;
		List<Entertainment> enter = shows.subList(0, shows.size());
		enter.sort(new ComparatorBySales());
		it = enter.iterator();
		return it;
	}
	
	public iterators.Iterator<Entertainment> iteratorByEntertainmentType(String type) {
		
		List<Entertainment> enter = shows.subList(0, shows.size());
		enter.sort(new ComparatorByDate());
		
		return new FilterIterator<Entertainment>(enter.iterator(), new EntertainmentFilter(type));
	}
}


