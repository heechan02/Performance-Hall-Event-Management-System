import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Admin extends User {

	public Admin(int userId, String userName, String name, Address address, String role) {
		super(userId, userName, name, address, role);
	}

	public void addEvent(String eventId, String eventCategory, String eventType, String eventName,
			String ageRestriction, String ticketQuantity, String performanceFee, String ticketPrice,
			String additionalInfo) throws DuplicateEventIdException, DigitNumberException {  // Add an event.
		HashMap<Integer, LiveEvent> events = DataLoader.loadStockData("Stock.txt");  // Load stock data.
		List<LiveEvent> eventList = new ArrayList<LiveEvent>(events.values());
		BufferedWriter bw = null;
		if (eventId.length() != 5 || !eventId.matches("\\d{5}")) {  // Check if event ID is 5-digit number.
			throw new DigitNumberException("The event ID should be 5-digit number.");
		}
		int newEventId = Integer.parseInt(eventId.trim());
		for (LiveEvent event : eventList) {
			if (event.getEventID() == newEventId) {  // Check if event ID already exists.
				throw new DuplicateEventIdException("You cannot add the same event ID.");
			}
		}
		try {
			String newEventName = eventName.trim();
			LiveEventCategory newEventCategory = LiveEventCategory.valueOf(eventCategory.toUpperCase().trim());
			AgeRestrictionCategory newAgeRestriction = AgeRestrictionCategory
					.valueOf(ageRestriction.trim().toUpperCase());
			int newTicketQuantity = Integer.parseInt(ticketQuantity.trim());
			double newPerformanceFee = Double.parseDouble(performanceFee.trim());
			double newTicketPrice = Double.parseDouble(ticketPrice.trim());
			if (eventCategory.equalsIgnoreCase("Performance")) {  // If the event category is performance,
				PerformanceEventType newPerformanceEventType = PerformanceEventType
						.valueOf(eventType.trim().toUpperCase().replace(" ", "_").replace("-", "_"));
				String newLanguage = additionalInfo.trim();
				if(newLanguage.matches("^\\d+$")) {  // Check if the language is valid.
					throw new IllegalArgumentException("Language should be a string.");
				}
				Performance newPerformanceEvent = new Performance(newEventId, newEventCategory, newPerformanceEventType,
						newEventName, newAgeRestriction, newTicketQuantity, newPerformanceFee, newTicketPrice,
						newLanguage);  // Create a new Performance object.
				events.put(newEventId, newPerformanceEvent);  // Add the performance to the events map.
			} else if (eventCategory.equalsIgnoreCase("Music")) {  // If the event category is music,
				MusicEventType newMusicEventType = MusicEventType
						.valueOf(eventType.trim().toUpperCase().replace(" ", "_"));
				int newNumberOfPerformers = Integer.parseInt(additionalInfo.trim());
				Music newMusicEvent = new Music(newEventId, newEventCategory, newMusicEventType, newEventName,
						newAgeRestriction, newTicketQuantity, newPerformanceFee, newTicketPrice, newNumberOfPerformers);  // Create a new Music object.
				events.put(newEventId, newMusicEvent);  // Add the music to the events map.
			}
			bw = new BufferedWriter(new FileWriter("Stock.txt", false));
			for (HashMap.Entry<Integer, LiveEvent> entry : events.entrySet()) {
				bw.write(entry.getValue() + "\n");  // Write the added event to the file.
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException i) {  // Handle invalid input.
			throw new IllegalArgumentException("Error adding event. \nPlease follow the input format in Stock.txt file.");
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<LiveEvent> viewAllEvents() {  // View all events.
		HashMap<Integer, LiveEvent> events = DataLoader.loadStockData("Stock.txt"); // Load stock data.
		List<LiveEvent> eventList = new ArrayList<LiveEvent>(events.values()); // Convert HashMap into List.
		// Sorting ascending order by ticket price using comparator.
		TicketPriceCompare eventTicketPriceCompare = new TicketPriceCompare();
		Collections.sort(eventList, eventTicketPriceCompare);
		return eventList;  // Return the sorted list of events.
	}
}
