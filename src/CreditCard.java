
public class CreditCard implements PaymentMethod {
	private final String cardNumber;
	private final String securityCode;

	public CreditCard(String cardNumber, String securityCode) throws DigitNumberException {
		if (cardNumber.length() != 6 || !cardNumber.matches("\\d{6}")) {  // Check if card number is 6 digits.
			throw new DigitNumberException("Please enter 6-digit card number.");
		}
		if (securityCode.length() != 3 || !securityCode.matches("\\d{3}")) {  // Check if security code is 3 digits.
			throw new DigitNumberException("Please enter 3-digit security code.");
		}
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}
	
	public String getSecurityCode() {
		return this.securityCode;
	}

	@Override
	public Receipt processPayment(double amount, Address fullAddress) {  // Process the payment and return a receipt.
		return new Receipt(amount, fullAddress);
	}
}
