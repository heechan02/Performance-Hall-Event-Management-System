
public abstract class LiveEvent {
	private int eventID;
	private String eventName;
	private LiveEventCategory liveEventCategory;
	private double performanceFee;
	private int quantityInStock;
	private AgeRestrictionCategory restriction;
	private double ticketPrice;

	public LiveEvent(int eventID, LiveEventCategory liveEventCategory, String eventName,
			AgeRestrictionCategory restriction, int quantityInStock, double performanceFee, double ticketPrice) {
		this.eventID = eventID;
		this.liveEventCategory = liveEventCategory;
		this.eventName = eventName;
		this.restriction = restriction;
		this.quantityInStock = quantityInStock;
		this.performanceFee = performanceFee;
		this.ticketPrice = ticketPrice;
	}

	public int getEventID() {
		return this.eventID;
	}

	public String getEventName() {
		return this.eventName;
	}

	public LiveEventCategory getEventCategory() {
		return this.liveEventCategory;
	}

	public double getPerformanceFee() {
		return this.performanceFee;
	}

	public int getQuantityInStock() {
		return this.quantityInStock;
	}

	public AgeRestrictionCategory getAgeRestriction() {
		return this.restriction;
	}

	public double getTicketPrice() {
		return this.ticketPrice;
	}

	public void setQuantityInStock(int newQuantityInStock) {
		this.quantityInStock = newQuantityInStock;
	}

	public abstract String toString();

}
