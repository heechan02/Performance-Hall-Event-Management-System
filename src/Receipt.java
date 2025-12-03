import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Receipt {
	private double amount;
	private String paymentMethod;
	private Address address;
	private LocalDate date = LocalDate.now();

	public Receipt(double amount, Address address) {
		this.amount = amount;
		this.address = address;
	}

	public double getAmount() {
		return this.amount;
	}

	public String paymentMethod() {
		return this.paymentMethod;
	}

	public Address getAddress() {
		return this.address;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public String createPayPalReceipt(String email) {  // Create a receipt for PayPal payments.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "£" + getAmount() + " paid via PayPal using " + email + "\non " + getDate().format(formatter)
				+ ", and the billing address is\n" + getAddress() + ".";
	}

	public String createCreditCardReceipt(String cardNumber) {  // Create a receipt for Credit Card payments.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "£" + getAmount() + " paid by Credit Card using " + cardNumber + "\non " + getDate().format(formatter)
				+ ", and the billing address is\n " + getAddress() + ".";
	}
}
