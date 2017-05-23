package artists_band;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import iterators.EntertainmentFilter;
import iterators.FilterIterator;
import show.ComparatorByDate;
import show.Entertainment;

/**
 * This is the PerformerAbstractClass it allows to unify common parts between
 * All Performers based classes
 * @author 49771
 * @author 50654
 */
public abstract class PerformerAbstractClass implements Performer{
	
	//Constants
	
	
	//Variables
	private String name;
	@SuppressWarnings("unused")
	private String[] discography;
	private List<Entertainment> agenda;
	
	//Constructor
	public PerformerAbstractClass(String name, String[] discography) {
		this.name = name;
		this.discography = discography;
		agenda = new ArrayList<Entertainment>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addEvent(Entertainment newEvent){ 
		if(!agenda.contains(newEvent))
			agenda.add(newEvent);
		
		agenda.sort(new ComparatorByDate()); //When a new show is added sort all
	}
	
	public Entertainment getShow(String name, String date){
		Iterator<Entertainment> entIt = agenda.iterator();
		Entertainment ent = null;
		
		while(entIt.hasNext()){
			ent = entIt.next();
			if(ent.getBeginDateAsString().equals(date))
				return ent;
		}
		
		return null;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) return true; 
		
		if (obj == null) return false;
		
		if (!(obj instanceof PerformerAbstractClass)) return false;
			
		PerformerAbstractClass other = (PerformerAbstractClass) obj; 
		
		if (name == null) {
			if (other.getName() != null) return false;
			else return true;
		}
		else return name.equals(other.getName());
	}

	public iterators.Iterator<Entertainment> getShowIterator(String type) {
		
		return new FilterIterator<Entertainment>(agenda.iterator(),new EntertainmentFilter(type));
	}
}
