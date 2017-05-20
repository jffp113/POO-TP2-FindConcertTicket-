package show;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * This comparable allows to sort Entertainment by the number of sold Tickets
 * If they have the some quantity of sort tickets it will try sort by dates,
 * if the some happens it will sort by names(It is impossible two Entertainments having the some name 
 * on the some date because it will be a clone)
 * @author 49771
 * @author 50654
 */
public class ComparatorBySales implements Comparator<Entertainment> {

	@Override
	public int compare(Entertainment o1, Entertainment o2) {
		LocalDate dateO1 = o1.getBegin();
		LocalDate dateO2 = o2.getBegin();
		int ticketsO1 = o1.getSoldTickets();
		int ticketsO2 = o2.getSoldTickets();
		String nameO1 = o1.getName();
		String nameO2 = o2.getName();
		
		
		if(ticketsO1 > ticketsO2)
			return -1;
		else if (ticketsO1 < ticketsO2)
			return 1;
		else if(dateO1.isAfter(dateO2))
			return 1;
		else if(dateO1.isBefore(dateO2))
			return -1;
		else
			return nameO1.compareTo(nameO2);
	}

}
