
public class Performance extends LiveEvent {
	private PerformanceEventType eventType;
	private String language;

	public Performance(int eventID, LiveEventCategory liveEventCategory, PerformanceEventType eventType,
			String eventName, AgeRestrictionCategory restriction, int quantityInStock, double performanceFee,
			double ticketPrice, String language) {
		super(eventID, liveEventCategory, eventName, restriction, quantityInStock, performanceFee, ticketPrice);
		this.eventType = eventType;
		this.language = language;
	}

	public PerformanceEventType getEventType() {
		return this.eventType;
	}

	public String getLanguage() {
		return this.language;
	}

	@Override
	public String toString() {  // Override toString method to return a string representation of the Performance object
		return getEventID() + ", " + getEventCategory() + ", " + getEventType() + ", " + getEventName() + ", "
				+ getAgeRestriction() + ", " + getQuantityInStock() + ", " + getPerformanceFee() + ", "
				+ getTicketPrice() + ", " + getLanguage();
	}
}
