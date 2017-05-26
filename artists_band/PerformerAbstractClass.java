package artists_band;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import show.ComparatorByDate;
import show.Entertainment;
import show.Festival;

/**
 * This is the PerformerAbstractClass it allows to unify common parts between
 * All Performers based classes
 * @author 49771
 * @author 50654
 */
public abstract class PerformerAbstractClass implements Performer{
	
	//Constants
	public static final String CONCERT = "CONCERT";
	public static final String FESTIVAL = "FESTIVAL";
	
	//Variables
	private String name;
	@SuppressWarnings("unused")
	private String[] discography;
	private Set<Entertainment> festivalAgenda;
	private Set<Entertainment> concertAgenda;
	
	//Constructor
	public PerformerAbstractClass(String name, String[] discography) {
		this.name = name;
		this.discography = discography;
		festivalAgenda = new TreeSet<Entertainment>(new ComparatorByDate());
		concertAgenda = new TreeSet<Entertainment>(new ComparatorByDate());
		
	}
	
	public String getName() {
		return name;
	}
	
	public void addEvent(Entertainment newEvent){ 
		if(newEvent instanceof Festival)
			festivalAgenda.add(newEvent);
		else
			concertAgenda.add(newEvent);
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

	public Iterator<Entertainment> getShowIterator(String type) {
		if(type.equals(FESTIVAL))
			return festivalAgenda.iterator();
		else
			return concertAgenda.iterator();
	}
}
