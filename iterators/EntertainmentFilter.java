package iterators;

import show.Concert;
import show.Entertainment;
import show.Festival;

public class EntertainmentFilter implements Filter<Entertainment> {
	//Constants
	public static final String CONCERT = "CONCERT";
	public static final String FESTIVAL = "FESTIVAL";
	
	
	//Variabels
	private String type;
	
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
