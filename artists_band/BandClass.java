package artists_band;

public class BandClass extends PerformerClass {

	//Variables
	private String[] elementsName;
	
	
	//Constructor
	public BandClass(String name, String[] discography,String[] elementsName) {
		super(name, discography);
		this.elementsName = elementsName;
	}
	
	public String getFirstElement(){
		return elementsName[0];
	} 
	
	

}
