import java.util.List;

public abstract class User {
	private final int userId;
	private String userName;
	private String name;
	private Address address;
	private String role;

	public User(int userId, String userName, String name, Address address, String role) {
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.address = address;
		this.role = role;
	}

	public int getUserId() {
		return this.userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getName() {
		return this.name;
	}

	public Address getAddress() {
		return this.address;
	}

	public String getRole() {
		return this.role;
	}

	@Override
	public String toString() {  // Override toString method to return a string representation of the User object
		return getName() + " - " + getRole();
	}

	public abstract List<LiveEvent> viewAllEvents();  // Abstract method to be implemented by Admin and User classes.
}
