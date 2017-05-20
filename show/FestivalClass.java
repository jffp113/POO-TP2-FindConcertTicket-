package show;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import artists_band.*;
import exeptions.NoTicketsAvailableExeption;

public class FestivalClass extends EntertainmentAbstractClass implements Festival{
	//Constants
	
	//Variables
	private LocalDate beginDate;
	private LocalDate endDate;
	private List<Day> festivalDays;
	private int[] price;
	
	//Constructor
	/**
	 * This is the FestivalClass constructor 
	 * @param entertainemntName Show name
	 * @param description show description
	 * @param numberTickets number of tickets
	 * @param beginDate begin date of the show
	 * @param endDate end date of the show
	 * @param prices price of the show
	 */
	public FestivalClass(String entertainemntName, String description, int numberTickets, String beginDate, String endDate,int[] prices) {
		super(entertainemntName, description, numberTickets);
		this.beginDate = LocalDate.parse(beginDate);
		if(prices != null)
			this.endDate = this.beginDate.plusDays(prices.length -1);
		festivalDays = new ArrayList<Day>();
		this.price = prices;
	}

	@Override
	public String firstArtistName() {
		return festivalDays.get(0).getFirstPerformer();
	}

	@Override
	public Ticket buytickets(int numberTickets, List<String> dates) throws NoTicketsAvailableExeption {
		Iterator<String> it = dates.iterator();
		
		while(it.hasNext()){
			if(!canSell(1,it.next()))
				throw new NoTicketsAvailableExeption();
		}
		
		it = dates.iterator();
		
		while(it.hasNext()){
			sell(it.next());
		}
		
		super.buytickets(dates.size(), null);
		
		return (Ticket)(new FestivalTicketClass(super.getName(),price[dates.size()-1],dates));
	}

	/**
	 * This method checks if a ticket can be sold
	 * @param buy number of tickets to be bought
	 * @param date day to buy the ticket
	 * @return true if it is possible to buy the ticket
	 */
	private boolean canSell(int buy, String date) {
		int index = festivalDays.indexOf(new DayClass(null,LocalDate.parse(date),0,0));
		return festivalDays.get(index).getAvailableTickets() - buy >= 0;
	}
	
	/**
	 * This method sells a ticket
	 * @param date day to sell the ticket
	 */
	private void sell(String date){
		int index = festivalDays.indexOf(new DayClass(null,LocalDate.parse(date),0,0));
		festivalDays.get(index).sell();
	}

	public void addDay(Performer[] per, int counter){
		long days = (long)festivalDays.size();
		LocalDate dayDate = beginDate.plusDays(days);
		
		festivalDays.add(new DayClass(per,dayDate,counter,super.getInicialTickets()));
	}

	@Override
	public String getBeginDateAsString() {
		return beginDate.toString();
	}
	
	public String getEndDateAsString() {
		return endDate.toString();
	}
	
	public Iterator<Day> getDay(){
		return festivalDays.iterator();
	}
	
	public int[] getPriceIt() { 
		return price;
	}

	@Override
	public LocalDate getBegin() {
		return beginDate;
	}

}
