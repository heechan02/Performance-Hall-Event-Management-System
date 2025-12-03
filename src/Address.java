
public class Address {
	private int houseNumber;
	private String postcode;
	private String city;

	public Address(int houseNumber, String postcode, String city) {
		this.houseNumber = houseNumber;
		this.postcode = postcode;
		this.city = city;
	}
	
	@Override
	public String toString() {  // Override the toString method to return a string representation of the Address object.
		return (this.houseNumber + " " + this.postcode + " " + this.city);
	}
}