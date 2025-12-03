import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataLoader {  // This class is responsible for loading data from files and updating stock data.
	private DataLoader() {
	}

	public static HashMap<Integer, LiveEvent> loadStockData(String stockFileName) { // Loads stock data from a file.
		HashMap<Integer, LiveEvent> events = new HashMap<Integer, LiveEvent>();
		Scanner sc = null;
		try {
			File stockFile = new File(stockFileName);
			sc = new Scanner(stockFile);
			while (sc.hasNextLine()) {
				String[] stockArr = sc.nextLine().split(",");
				int eventID = Integer.parseInt(stockArr[0].trim());
				String eventCategory = stockArr[1].trim();
				String eventName = stockArr[3].trim();
				AgeRestrictionCategory ageRestriction = AgeRestrictionCategory
						.valueOf(stockArr[4].trim().toUpperCase());
				int ticketQuantity = Integer.parseInt(stockArr[5].trim());
				double performanceFee = Double.parseDouble(stockArr[6].trim());
				double ticketPrice = Double.parseDouble(stockArr[7].trim());
				if (eventCategory.equalsIgnoreCase("Performance")) {  // If the event category is performance,
					LiveEventCategory liveEventCategory = LiveEventCategory.valueOf(stockArr[1].toUpperCase().trim());
					PerformanceEventType performanceEvent = PerformanceEventType
							.valueOf(stockArr[2].trim().toUpperCase().replace(" ", "_").replace("-", "_"));
					String language = stockArr[8].trim();
					Performance performance = new Performance(eventID, liveEventCategory, performanceEvent, eventName,
							ageRestriction, ticketQuantity, performanceFee, ticketPrice, language);  // Create a new Performance object.
					events.put(eventID, performance);  // Add the performance to the events map.
				} else if (eventCategory.equalsIgnoreCase("Music")) {  // If the event category is music,
					LiveEventCategory liveEventCategory = LiveEventCategory.valueOf(stockArr[1].toUpperCase().trim());
					MusicEventType musicEvent = MusicEventType
							.valueOf(stockArr[2].trim().toUpperCase().replace(" ", "_"));
					int numberOfPerformers = Integer.parseInt(stockArr[8].trim());
					Music music = new Music(eventID, liveEventCategory, musicEvent, eventName, ageRestriction,
							ticketQuantity, performanceFee, ticketPrice, numberOfPerformers);  // Create a new Music object.
					events.put(eventID, music);  // Add the music to the events map.
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException i) {
			System.out.println(i.getMessage());
			i.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		return events;  // Return the events map.
	}

	public static List<User> loadUserData(String userFileName) {  // Loads user data from a file.
		List<User> users = new ArrayList<>();
		Scanner sc = null;
		try {
			File userFile = new File(userFileName);
			sc = new Scanner(userFile);
			while (sc.hasNextLine()) {
				String[] userArr = sc.nextLine().split(",");
				int userId = Integer.parseInt(userArr[0].trim());
				String userName = userArr[1].trim();
				String name = userArr[2].trim();
				Address address = new Address(Integer.parseInt(userArr[3].trim()), userArr[4].trim(),
						userArr[5].trim());
				String role = userArr[6].trim();
				if (role.equalsIgnoreCase("Admin")) {  // If the role is admin,
					Admin admin = new Admin(userId, userName, name, address, role);  // Create a new Admin object.
					users.add(admin);  // Add the admin to the users list.
				} else if (role.equalsIgnoreCase("Customer")) {  // If the role is customer,
					Customer customer = new Customer(userId, userName, name, address, role);  // Create a new Customer object.
					users.add(customer);  // Add the customer to the users list.	
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		return users;  // Return the users list.
	}

	public static void updateTicketQuantity(String stockFileName, List<LiveEvent> basketList) {  // Updates the ticket quantity in stock data.
		BufferedWriter bw = null;
		HashMap<Integer, LiveEvent> stockData = loadStockData(stockFileName); // Load current stock data
		for (LiveEvent basket : basketList) {
			if (stockData.containsKey(basket.getEventID())) {  //If the event ID from the basket exists in the stock data,
				LiveEvent stockEvent = stockData.get(basket.getEventID());
				int updatedQuantity = stockEvent.getQuantityInStock() - basket.getQuantityInStock();
				stockEvent.setQuantityInStock(updatedQuantity); // Update the quantity in stock
			}
		}
		try {
			bw = new BufferedWriter(new FileWriter(stockFileName, false));
			for (LiveEvent event : stockData.values()) {
				bw.write(event.toString() + "\n"); // Write the updated stock data to the file
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
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
}