
public class PayPal implements PaymentMethod {
	private String email;
	private static final String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	public PayPal(String email) throws EmailAddressException {
		if (email == null || !email.matches(regex)) {  // Check if email is null or does not match the regex.
			throw new EmailAddressException("Invalid Email Address: " + email);  // Throw exception if email is invalid.
		}
		this.email = email;

	}

	public String getEmail() {
		return this.email;
	}

	@Override
	public Receipt processPayment(double amount, Address fullAddress) {  // Process the payment and return a receipt.
		return new Receipt(amount, fullAddress);
	}
}
