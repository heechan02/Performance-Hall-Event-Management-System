import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Customer extends User {
	private List<LiveEvent> shoppingBasket;

	public Customer(int userId, String userName, String name, Address address, String role) {
		super(userId, userName, name, address, role);
		this.shoppingBasket = new ArrayList<>();
	}

	@Override
	public List<LiveEvent> viewAllEvents() {  // View all events without performance fee and sort by ticket price in ascending order.
		HashMap<Integer, LiveEvent> events = DataLoader.loadStockData("Stock.txt"); // Load stock data.
		List<LiveEvent> eventList = new ArrayList<LiveEvent>(events.values()); // Convert HashMap into List.
		eventList.remove(6); // Hiding performance fee from customer view.
		// Sorting ascending order by ticket price using comparator.
		TicketPriceCompare eventTicketPriceCompare = new TicketPriceCompare();
		Collections.sort(eventList, eventTicketPriceCompare);
		return eventList;
	}

	public List<LiveEvent> searchByEventId(String eventId) {  // Search event by event ID.
		HashMap<Integer, LiveEvent> events = DataLoader.loadStockData("Stock.txt"); // Load stock data.
		List<LiveEvent> eventList = new ArrayList<LiveEvent>();
		try {
			int id = Integer.parseInt(eventId);
			if (events.containsKey(id)) {  // Check if the event ID exists in the HashMap.
				eventList.add(events.get(id));  // Add the event to the list.
			}
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return eventList.isEmpty() ? null : eventList;  // Return null if the event list is empty.
	}

	public List<LiveEvent> filterByLanguage(String language) {  // Filter events by language.
		HashMap<Integer, LiveEvent> events = DataLoader.loadStockData("Stock.txt"); // Load stock data.
		List<LiveEvent> eventList = new ArrayList<LiveEvent>();
		try {
			for (HashMap.Entry<Integer, LiveEvent> entry : events.entrySet()) {
				LiveEvent event = entry.getValue();
				if (event instanceof Performance) {
					Performance performance = (Performance) event;
					if (performance.getLanguage().equalsIgnoreCase(language)) {  // Check if the event language matches the input.
						eventList.add(performance);  // Add the event to the list.
					}
				}
			}
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		// Sorting ascending order by ticket price using comparator.
		TicketPriceCompare eventTicketPriceCompare = new TicketPriceCompare();
		Collections.sort(eventList, eventTicketPriceCompare);
		return eventList.isEmpty() ? null : eventList; // Return null if the event list is empty.
	}

	public List<LiveEvent> addToBasket(String eventId, String ticketQuantity)  // Add event to shopping basket.
			throws InvalidTicketQuantityException, DuplicateEventIdException {
		HashMap<Integer, LiveEvent> events = DataLoader.loadStockData("Stock.txt"); // Load stock data.
		try { 
			if(!ticketQuantity.matches("^\\d+$")) {  // Check if the ticket quantity is a digit.
				throw new InvalidTicketQuantityException("Ticket quantity should be number.");
			}
			int id = Integer.parseInt(eventId);
			int quantity = Integer.parseInt(ticketQuantity);
			if (quantity < 1) {  // If the quantity is less than 1, throw an exception.
				throw new InvalidTicketQuantityException("Please add more than 1 ticket.");
			}
			LiveEvent event = events.get(id);
			if (event != null) {  // Check if the event is not null.
				if (event.getQuantityInStock() < quantity && event.getQuantityInStock() != 0) {  // Check if the quantity is more than available.
					throw new InvalidTicketQuantityException(
							"You are adding too many tickets than there are available.");
				} else if (event.getQuantityInStock() < quantity && event.getQuantityInStock() == 0) {  // Check if the event is sold out.
					throw new InvalidTicketQuantityException(
							"The event is sold out. \nCheck the number of remaining tickets.");
				}
				for (LiveEvent basket : shoppingBasket) {
					if (id == basket.getEventID()) {  // Check if the event is already in the shopping basket.
						throw new DuplicateEventIdException("The event is already in your shopping basket. "
								+ "\nPlease cancel the basket and add the event again.");
					}
				}
				event.setQuantityInStock(quantity);  // Update the ticket quantity in stock.
				shoppingBasket.add(event);  // Add the event to the shopping basket.
			} else {
				throw new IllegalArgumentException("Event cannot be null.");
			}
			// Sorting ascending order by ticket price using comparator.
			TicketPriceCompare eventTicketPriceCompare = new TicketPriceCompare();
			Collections.sort(shoppingBasket, eventTicketPriceCompare);
			return shoppingBasket.isEmpty() ? null : shoppingBasket;
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return shoppingBasket;  // Return shopping basket.
	}

	public List<LiveEvent> getBasket() {  // Get shopping basket.
		return this.shoppingBasket;
	}

	public List<LiveEvent> cancelBasket(List<LiveEvent> basketList) throws EmptyBasketException {  // Cancel shopping basket.
		if (basketList.isEmpty() || basketList == null) {  // Check if the basket is already empty.
			throw new EmptyBasketException("Shopping basket is already empty.");
		}
		basketList.clear();  // Clear the shopping basket.
		basketList = null;  // Set the basket list to null.
		return basketList;	// Return the basket list.
	}

	public double calculateTotal(List<LiveEvent> basketList) {  // Calculate total price of the shopping basket.
		double total = 0;
		for (LiveEvent basket : basketList) {
			total += basket.getTicketPrice() * basket.getQuantityInStock();  // Calculate total price (ticket x quantity).
		}
		return total;  // Return total price.
	}

	public String pay(String stockFileName, List<LiveEvent> basketList, PaymentMethod paymentMethod, double total) {  // Pay for the shopping basket.
		DataLoader.updateTicketQuantity(stockFileName, basketList);
		String receiptTxt = "";
		try {
			cancelBasket(basketList); // Cancels the basket.
		} catch (EmptyBasketException eb) {  
			return null;
		}
		Receipt receipt;
		if (paymentMethod instanceof PayPal) {  // Check if the payment method is PayPal.
			PayPal paypal = (PayPal) paymentMethod;
			receipt = paypal.processPayment(total, getAddress());  // Process payment using PayPal.
			receiptTxt = receipt.createPayPalReceipt(paypal.getEmail());  // Create PayPal receipt text.
		} else if (paymentMethod instanceof CreditCard) {  // Check if the payment method is CreditCard.
			CreditCard creditCard = (CreditCard) paymentMethod;
			receipt = creditCard.processPayment(total, getAddress());  // Process payment using CreditCard.
			receiptTxt = receipt.createCreditCardReceipt(creditCard.getCardNumber());  // Create CreditCard receipt text.
		}
		return receiptTxt;  // Return receipt text.
	}

}