import java.util.Comparator;

public class TicketPriceCompare implements Comparator<LiveEvent> {
	public int compare(LiveEvent event1, LiveEvent event2) {
		return Double.valueOf(event1.getTicketPrice()).compareTo(Double.valueOf(event2.getTicketPrice()));
	}
}
