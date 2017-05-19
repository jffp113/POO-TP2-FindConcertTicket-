package show;
import java.time.LocalDate;
import java.util.Comparator;

public class ComparatorByDate implements Comparator<Entertainment> {
	

		@Override
		public int compare(Entertainment o1, Entertainment o2) {
			LocalDate dateO1 = o1.getBegin();
			LocalDate dateO2 = o2.getBegin();
			String nameO1 = o1.getName();
			String nameO2 = o2.getName();
			
			
			if(dateO1.isAfter(dateO2))
				return 1;
			else if(dateO1.isBefore(dateO2))
				return -1;
			else
				return nameO1.compareTo(nameO2);
		}

}

