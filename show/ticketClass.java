package show;

/**
 * This is a Ticket Class
 * @author 49771
 * @author 50654
 */
public class ticketClass implements Ticket {
	//Constants
	
	//Variables
	private int price;
	private String entretain;
	
	//Constructor
	/**
	 * This is the constructor of ticketClass
	 * @param entretain show name
	 * @param price price of the ticket
	 */
	public ticketClass(String entretain, int price) {
		this.price = price;
		this.entretain = entretain;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getEntretainment() {
		return this.entretain;
	}
}
