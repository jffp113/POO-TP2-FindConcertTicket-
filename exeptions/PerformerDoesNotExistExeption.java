package exeptions;

import java.util.Iterator;
import java.util.List;

import artists_band.Performer;

@SuppressWarnings("serial")
public class PerformerDoesNotExistExeption extends Exception {
	//Variabels
	List<String> performers;
	
	public PerformerDoesNotExistExeption(List<String> performers){
		super();
		this.performers = performers;
	}
	
	public Iterator<String> iterarater() {
		return performers.iterator();
	}
}
