package show;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import artists_band.Performer;

public class DayClass implements Day {
	//Constants
	
	//Variables
	private LocalDate date;
	private List<Performer> performers;
	private int numberOfTickets;
	
	//Constructor
	public DayClass(Performer[] per, LocalDate date , int counter, int tickets){
		this.date = date;
		performers = new ArrayList<Performer>();
		this.numberOfTickets = tickets;
		addPerformers(per, counter);
	}
	
	/**
	 * This method adds a performer to the day
	 * @param per performers
	 * @param counter amount of performers
	 */
	private void addPerformers(Performer[] per, int counter){
		for(int i = 0; i < counter; i++){
			performers.add(per[i]);
		}
	}
	
	public Iterator<Performer> getPerfomerIterator() {
		return performers.iterator();
	}
	
	public String getFirstPerformer() {
		return performers.get(0).getName();
	}
	
	public String getDate() {
		return date.toString();
	}
	
	public int getAvailableTickets(){
		return numberOfTickets;
	}
	
	public void sell() {
		numberOfTickets--;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) return true; 
		
		if (obj == null) return false;
		
		if (!(obj instanceof Day)) return false;
			
		Day other = (Day) obj; 
		
		if (date == null) {
			if (other.getDate() != null) return false;
			else return true;
		}
		else return date.toString().equals(other.getDate().toString());
	}
}
