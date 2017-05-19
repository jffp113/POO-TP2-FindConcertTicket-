package show;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class FestivalTicketClass extends ticketClass implements FestivalTicket {

	//Constants
	
	
	//Variables
	private List<LocalDate> date;
	
	//Cnstructor
	/**
	 * This is the construcotr of the festival ticket
	 * @param entretain show name
	 * @param price show price
	 * @param days days of the show
	 */
	public FestivalTicketClass(String entretain, int price, List<String> days) {
		super(entretain, price);
		date = new LinkedList<LocalDate>();
		addDay(days);
	}

	/**
	 * add a day to a festival ticket
	 * @param days
	 */
	private void addDay(List<String> days) {
		Iterator<String> it = days.iterator();
		while(it.hasNext()){
			date.add(LocalDate.parse(it.next()));
		}
	}
	
	public Iterator<LocalDate> listDay() {
		return date.iterator();
	}
	
}
