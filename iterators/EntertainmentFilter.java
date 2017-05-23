package iterators;

import show.Concert;
import show.Entertainment;
import show.Festival;

/**
 * This is the Class of a Filter to be used with the GenericFilterIterator
 * @author 49771
 * @author 50654
 */
public class EntertainmentFilter implements Filter<Entertainment> {
	//Constants
	public static final String CONCERT = "CONCERT";
	public static final String FESTIVAL = "FESTIVAL";
	
	
	//Variabels
	private String type;
	
	/**
	 * This is the constructor of the filter 
	 * @param type Type to filter
	 */
	public EntertainmentFilter(String type) {
		this.type = type;
	}
	
	@Override
	public boolean applyFilter(Entertainment o) {
		if(type.equals(FESTIVAL) && (o instanceof Festival))
			return true;
		if(type.equals(CONCERT) && (o instanceof Concert))
			return true;
		
		return false;
	}
	
}
