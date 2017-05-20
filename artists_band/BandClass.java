package artists_band;

/**
 * This is the BandClass it allows the creation of a new Band
 * @author 49771
 * @author 50654
 */
public class BandClass extends PerformerAbstractClass implements Band{

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
