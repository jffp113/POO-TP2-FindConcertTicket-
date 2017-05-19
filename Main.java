import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import artists_band.Performer;
import exeptions.*;
import iterators.ShowTypeIterator;
import iterators.TicketTypeIterator;
import show.Concert;
import show.ConcertTicket;
import show.Day;
import show.Entertainment;
import show.Festival;
import show.FestivalClass;
import show.FestivalTicket;
import show.Ticket;
import users.User;

public class Main {
	
	//Menu constants
	public static final String EXIT 			= "EXIT";
	public static final String REGISTER_USER 	= "REGISTER";
	public static final String LOGIN_USER 		= "LOGIN";
	public static final String LOGOUT_USER 		= "LOGOUT";
	public static final String CREATE_PERFORMER = "ADDARTIST";
	public static final String CREATE_SHOW 		= "ADDSHOW";
	public static final String LIST_SHOWS 		= "SHOWS";
	public static final String LIST_AGENDA 		= "SEARCH";
	public static final String FIND_SHOW 		= "SHOW";
	public static final String BUY_TICKETS 		= "BUYTICKET";
	public static final String LIST_TICKETS 	= "MYTICKETS";
	public static final String LIST_SALES 		= "SHOWSBYCLIENTS";
	public static final String LIST_TYPE 		= "SHOWSBYTYPE";
	
	//Choice Constants
	public static final String ADMIN 			= "ADMIN";
	public static final String CLIENT 			= "CLIENT";
	public static final String USER_REGIST_SUC	= "User was registered: %s\n";
	public static final String USER_ALREADY_LOG	= "User already logged in.";
	public static final String USER_ALREADY_EXI	= "User already exists.";
	public static final String WELCOME			= "Welcome %s\n";
	public static final String NO_USER			= "User does not exist.";
	public static final String WRONG_PASS		= "Wrong password.";
	public static final String OTHER_USER_LOG	= "Another user is logged in.";
	public static final String GOODBYE			= "Goodbye %s\n";
	public static final String NO_USER_LOG		= "No user is logged in.";
	public static final String ARTIST_ADD		= "Artist added.\n";
	public static final String ARTIST_NAME_EXI	= "Artist name already exists.";
	public static final String USER_NO_RIGHTS	= "User cannot execute this command.";
	public static final String CONCERT_FESTIVAL	= "CONCERT OR FESTIVAL?";
	public static final String CONCERT			= "CONCERT";
	public static final String FESTIVAL			= "FESTIVAL";
	public static final String SHOW_ADDED		= "Show added.";
	public static final String SHOW_ALREADY_EX	= "Show already exists.";
	public static final String NO_ARTISTS		= "Artist name(s) do(es) not exist(s):";
	public static final String ALL_SHOWS		= "All shows:";
	public static final String TICKETS_BOUGHT	= "Ticket bought with cost %d.\n";
	public static final String NO_SHOW			= "Show does not exist.";
	public static final String NO_TICKETS		= "There are not sufficient seats for the request.";
	public static final String MY_TICKETS		= "My tickets:";
	public static final String ARTIST_CONCERT	= "Concerts of %s:\n";
	public static final String ARTIST_FESTIVAL	= "Festivals where %s will play:\n";
	public static final String ENTER_OUTPUT		= "%s on %s:\n";
	public static final String CONCERT_TITLE	= "Concerts:";
	public static final String FESTIVAL_TITLE	= "Festivals:";
	public static final String UNKNOW_TYPE		= "Unknown type of show.";
	public static final String MOST_SOLD_TITLE	= "Most sold shows:";
	
	//Output Message Constants
	public static final String EXITING 			= "Exiting.\n";
	
	
	public static void main(String[] args) {

		menu();
	}

	/**
	 * This method allows the creation of the menu
	 */
	public static void menu() {
		ConcertManager mg = new ConcertManagerClass();
		
		
		Scanner in = new Scanner(System.in);
		String option = "";

		option = in.nextLine();
		while(!option.toUpperCase().equals(EXIT)){
			switch(option.toUpperCase()){
				case REGISTER_USER: register(in,mg); break;
				case LOGIN_USER: login(in,mg); break;
				case LOGOUT_USER: logout(in,mg); break;
				case CREATE_PERFORMER:addPerformer(in,mg); break; 
				case CREATE_SHOW : addShow(in,mg); break;
				case LIST_SHOWS: listShows(in,mg);break;
				case LIST_AGENDA: listPerformerShows(in,mg); break;
				case FIND_SHOW: getShowStatus(in,mg); break;
				case BUY_TICKETS: buyTickets(in,mg); break; 
				case LIST_TICKETS: listTickets(in,mg); break;
				case LIST_SALES: listShowsClient(mg); break;
				case LIST_TYPE: listShowbyType(in,mg);break;
			}
			System.out.println("");
			option = in.nextLine();
		}
		System.out.println(EXITING);
		in.close();
	}
	
	/**
	 * This method allows the registration of a new user
	 * @param in Scanner to read input
	 * @param mg ConcertManager
	 */
	public static void register(Scanner in, ConcertManager mg){
		String accountType = in.nextLine();
		String accountEmail = in.nextLine();
		String accountPassword = null;
		try{
			switch(accountType.toUpperCase()){
				case ADMIN : accountPassword = mg.newAdmin(accountEmail); break;
				case CLIENT : accountPassword = mg.newClient(accountEmail); break;
			}
			System.out.printf(USER_REGIST_SUC, accountPassword);
		}
		catch(UserLoggedInExeption e){
			System.out.println(USER_ALREADY_LOG);
		}
		catch(UserAlreadyExistExeption e){
			System.out.println(USER_ALREADY_EXI);
		}
	}

	/**
	 * This method allows user to login
	 * @param in Scanner to read input
	 * @param mg ConcertManager
	 */
	public static void login(Scanner in, ConcertManager mg){
		String accountEmail = in.nextLine();
		String accountPassword = in.nextLine();
		
		try{
			mg.login(accountEmail, accountPassword);
			System.out.printf(WELCOME, accountEmail);
		}
		catch(UserDoesNotExistExeption e) {
			System.out.println(NO_USER);
		}
		catch(UserAlreadyLoggedInExeption e){
			System.out.println(USER_ALREADY_LOG);
		}
		catch(UserPasswordMismatchExeption e){
			System.out.println(WRONG_PASS);
		}
		catch(UserLoggedInExeption e){
			System.out.println(OTHER_USER_LOG);
		}
	}

	/**
	 * This method allows the user to logout 
	 * @param in Scanner
	 * @param mg ConcertManager 
	 */
	public static void logout(Scanner in, ConcertManager mg){
		User tmp = null;
		try{
			
			tmp = mg.logout();
			System.out.printf(GOODBYE, tmp.getUserName());
			
		}
		catch(NoUserLoggedInExeption e) {
			System.out.println(NO_USER_LOG);
		}
	}
	
	/**
	 * This method allows the creation of a new Performer
	 * @param in Scanner to read input
	 * @param mg ConcertManager
	 */
	public static void addPerformer(Scanner in, ConcertManager mg){
		String name = in.nextLine();
		int discoNumber = in.nextInt(); in.nextLine();
		String[] discography = new String[discoNumber];
		//String elem;
		int elementNumbers;
		String[] elements;
		
		for(int i = 0; i < discoNumber; i++){
			discography[i] = in.nextLine();
		}
		
		elementNumbers = in.nextInt(); in.nextLine();
		try{
			if(elementNumbers > 1){

				elements = new String[elementNumbers];
				
				for(int i = 0; i < elementNumbers; i++){
					elements[i] = in.nextLine();
				}
				
				mg.addBand(name, discography, elements);
			}
			else{
				mg.addArtist(name, discography);
			}
			System.out.printf(ARTIST_ADD);
			
		}
		catch(PerformerAlreadyExists e) {
			System.out.println(ARTIST_NAME_EXI);
		}
		catch(UserWithoutPrivilegesExeption e) {
			System.out.println(USER_NO_RIGHTS);
		}
		
	}
	
	/**
	 * This method allows the creation of a new Show
	 * @param in Scanner to read input
	 * @param mg ConcertManager
	 */
	public static void addShow(Scanner in, ConcertManager mg){
		String name = in.nextLine();
		String description = in.nextLine();
		int tickets = in.nextInt(); in.nextLine();
		String type = null;
		Iterator<String> bufferIt;
		
		System.out.println(CONCERT_FESTIVAL);
		type = in.nextLine();
		try{
			if(type.toUpperCase().equals(CONCERT)){
				String artist = in.nextLine();
				String date = in.nextLine();
				int price = in.nextInt(); in.nextLine();
				
					mg.addConcert(name, description, tickets, price, date, artist);
			}
			else{
				int numberDays = in.nextInt(); in.nextLine();
				String beginDate = in.nextLine();
				List<String> elem = new ArrayList<String>();
				int[] prices = new int[numberDays];
				int counter = 0;
				
				for(int i = 0; i < numberDays; i++){
					int numbElem = in.nextInt(); in.nextLine();
					for(int j = 0 ; j < numbElem; j++){
						elem.add(in.nextLine());
					}
					elem.add("\0");
				}
				for(int p = 0; p < numberDays; p++){
					in.nextInt(); prices[counter++] = in.nextInt(); in.nextLine();
				}
				
					mg.addFestival(name, description,tickets, beginDate, null, prices ,elem);
			}
			System.out.println(SHOW_ADDED);
		}
		catch(UserWithoutPrivilegesExeption e) {
			System.out.println(USER_NO_RIGHTS);
		}
		catch(ShowAlreadyExistsExeption e) {
			System.out.println(SHOW_ALREADY_EX);
		}
		catch(PerformerDoesNotExistExeption e) {
			bufferIt = mg.inexistentArtistBueffer();
			System.out.println(NO_ARTISTS);
			while(bufferIt.hasNext()){
				System.out.println(bufferIt.next());
			}
		}
	
		
	}
	
	/**
	 * This method allows users to list shows
	 * @param in Scanners
	 * @param mg ConcertManager
	 */
	public static void listShows(Scanner in, ConcertManager mg){ 
		Iterator<Entertainment> entIT = mg.listShows();
		Entertainment entertain;
		Festival fest;
		Concert concert;
		
		System.out.println(ALL_SHOWS);
		while(entIT.hasNext()){
			entertain = entIT.next();
			
			if(entertain instanceof FestivalClass){
				fest = (Festival)entertain;
				FestivalStatus(fest);
			}
			else{
				concert = (Concert)entertain;
				
				ConcertStatus(concert);
			}
		}
	}
	
	/**
	 * this method allows the client to buy tickets
	 * @param in Scanner to read input
	 * @param mg ConcertManager
	 */
	public static void buyTickets(Scanner in, ConcertManager mg){
		String name = in.nextLine();
		String date = in.nextLine();
		System.out.println(CONCERT_FESTIVAL);
		String type = in.nextLine().toUpperCase();
		int numberTickets = 0;
		int price = 0;
		List<String> dates = new LinkedList<String>();
		String dateToBuy = null;
		try{
			if(type.equals(CONCERT)){
				numberTickets = in.nextInt(); in.nextLine();
				price = mg.buyConcertTicket(name, date, numberTickets);
			}
			else{
				numberTickets = in.nextInt(); in.nextLine();
				for(int i = 0; i < numberTickets; i++){
					dateToBuy = in.nextLine();
					dates.add(dateToBuy);
				}
				price = mg.buyFestivalTicket(name, date, dates);
			}
			System.out.printf(TICKETS_BOUGHT, price);
		}
		catch(NoEntertainmentExeption e) {
			System.out.println(NO_SHOW);
		}
		catch(NoTicketsAvailableExeption e) {
			System.out.println(NO_TICKETS);
		}
		catch(UserWithoutPrivilegesExeption e) {
			System.out.println(USER_NO_RIGHTS);
		}
	}

	/**
	 * This method allows clients to list their tickets
	 * @param in
	 * @param mg
	 */
	public static void listTickets(Scanner in, ConcertManager mg){
		iterators.Iterator<Ticket> ticketit = null;
		FestivalTicket festT = null;
		ConcertTicket concT = null;
		Ticket ticket = null;
		Iterator<LocalDate> localIt = null;
		try{
			ticketit = mg.listTikets(TicketTypeIterator.CONCERT);
			System.out.println(MY_TICKETS);
			while(ticketit.hasNext()) {
				ticket = ticketit.next();
					concT = (ConcertTicket)ticket;
					System.out.println(concT.getEntretainment());
					System.out.println(concT.getDate());
					System.out.println(concT.getQuantitaty());
					System.out.println(concT.getPrice());;
				}
				ticketit = mg.listTikets(TicketTypeIterator.FESTIVAL);
				while(ticketit.hasNext()){
					ticket = ticketit.next();
					festT = (FestivalTicket)ticket;
					System.out.println(festT.getEntretainment());
					localIt = festT.listDay();
					while(localIt.hasNext())
						System.out.println(localIt.next().toString());
						System.out.println(festT.getPrice());
					}
		}
		catch(UserWithoutPrivilegesExeption e) {
			System.out.println("");
		}
	}
	
	/**
	 * This method allows to list a performer show
	 * @param in Scanner to read input
	 * @param mg ConcertManager
	 */
	public static void listPerformerShows(Scanner in, ConcertManager mg){
		String name = in.nextLine();
		iterators.Iterator<Entertainment> showit = mg.shows(name, iterators.ShowTypeIterator.CONCERT);
		Concert conc = null;
		Festival fest = null;
		
		System.out.printf(ARTIST_CONCERT,name);
		if(showit != null){
			while(showit.hasNext()){
				conc = (Concert) showit.next();
				ConcertStatus(conc);
			}
		}
		
		System.out.printf(ARTIST_FESTIVAL, name);
		
		showit = mg.shows(name, iterators.ShowTypeIterator.FESTIVAL);
		if(showit != null){
			while(showit.hasNext()){
				fest = (Festival) showit.next();
				FestivalStatus(fest);
			}
		}
	}
	
	/**
	 * This method allows users to know the information of a certain show
	 * @param in Scanner to read the show name
	 * @param mg ConcertManager
	 */
	public static void getShowStatus(Scanner in, ConcertManager mg) {
		String name = in.nextLine();
		String date = in.nextLine();
		Entertainment enter = null;
		Festival fest = null;
		Concert conc = null;
		try{
			enter = mg.getShow(name, date);
			
			if(enter instanceof Concert){
				conc = (Concert)enter;
				System.out.printf(ENTER_OUTPUT, conc.getName(), conc.getBeginDateAsString());
				ConcertStatus(conc);
			}
			else{
				fest  = (Festival)enter;
				System.out.printf(ENTER_OUTPUT, fest.getName(), fest.getBeginDateAsString());
				FestivalStatus(fest);
			}
		}
		catch(NoEntertainmentExeption e){
			System.out.println(NO_SHOW);
		}
	}
	
	/**
	 * This method prints the information of a certain Festival
	 * @param fest Festival which the information will be shown
	 */
	public static void FestivalStatus(Festival fest) {
		Iterator<Day> dayIt = null;
		Iterator<Performer> perIt = null;
		Performer per = null;
		Day day = null;
		int[] price = null;
		
		dayIt = fest.getDay();
		System.out.println(fest.getName());
		while(dayIt.hasNext()){
			day = dayIt.next();
			System.out.println(day.getDate());
			perIt = day.getPerfomerIterator();
			while(perIt.hasNext()){
				per = perIt.next();
				System.out.println(per.getName());
			}
		}
			System.out.println(fest.getBeginDateAsString());
			System.out.println(fest.getEndDateAsString());
			price = fest.getPriceIt();
			for(int i = 0; i < price.length; i++){
				System.out.printf("%d %d\n", i + 1 , price[i]);
			}
			
			dayIt = fest.getDay();
			
			while(dayIt.hasNext()){
				day = dayIt.next();
				System.out.printf("%s %d\n", day.getDate(), day.getAvailableTickets());
			}
	}

	/**
	 * This method prints the information of a certain Concert
	 * @param conc Concert which the information will be shown
	 */
	public static void ConcertStatus(Concert conc){
		int tickets = conc.getInicialTickets() - conc.getSoldTickets();
		System.out.println(conc.getName());
		System.out.println(conc.firstArtistName());
		System.out.println(conc.getBeginDateAsString());
		System.out.println(conc.getPrice());
		System.out.println(tickets);
	}
	
	/**
	 * This method allows the show listing by number of clients
	 * @param mg ConcertManager
	 */
	public static void listShowsClient(ConcertManager mg) {
		System.out.println(MOST_SOLD_TITLE);
		Iterator<Entertainment> it = mg.iteratorBySales();
		Entertainment enter = null;
		while(it.hasNext()){
			enter = it.next();
			
			if(enter instanceof Concert){
				ConcertStatus((Concert)enter);
			}
			else{
				FestivalStatus((Festival)enter);
			}
		}
	}
	
	/**
	 * This method allows the show listing by type 
	 * @param in
	 * @param mg
	 */
	public static void listShowbyType(Scanner in, ConcertManager mg) {
		String type = in.nextLine().toUpperCase();
		iterators.Iterator<Entertainment> it = null;
		
		it = mg.iteratorByEntertainmentType(type);
		
		if(type.equals(ShowTypeIterator.CONCERT)){
			System.out.println(CONCERT_TITLE);
			while(it.hasNext()){
				ConcertStatus((Concert)it.next());
			}
		}
		else if (type.equals(ShowTypeIterator.FESTIVAL)){
			System.out.println(FESTIVAL_TITLE);
			while(it.hasNext()){
				FestivalStatus((Festival)it.next());
			}
		}
		else{
			System.out.println(UNKNOW_TYPE);
		}
			
	}
}
