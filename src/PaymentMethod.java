
public interface PaymentMethod {
	public abstract Receipt processPayment(double amount, Address fullAddress);
}
