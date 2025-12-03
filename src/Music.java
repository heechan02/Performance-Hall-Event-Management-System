
public class Music extends LiveEvent {
	private MusicEventType eventType;
	private int numberOfPerformers;

	public Music(int eventID, LiveEventCategory liveEventCategory, MusicEventType eventType, String eventName,
			AgeRestrictionCategory restriction, int quantityInStock, double performanceFee, double ticketPrice,
			int numberOfPerformers) {
		super(eventID, liveEventCategory, eventName, restriction, quantityInStock, performanceFee, ticketPrice);
		this.eventType = eventType;
		this.numberOfPerformers = numberOfPerformers;
	}

	public MusicEventType getEventType() {
		return this.eventType;
	}

	public int getNumberOfPerformers() {
		return this.numberOfPerformers;
	}

	@Override
	public String toString() {  // Override toString method to return a string representation of the Music object
		return getEventID() + ", " + getEventCategory() + ", " + getEventType() + ", " + getEventName() + ", "
				+ getAgeRestriction() + ", " + getQuantityInStock() + ", " + getPerformanceFee() + ", "
				+ getTicketPrice() + ", " + getNumberOfPerformers();
	}
}