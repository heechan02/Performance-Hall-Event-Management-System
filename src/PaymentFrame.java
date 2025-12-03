import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class PaymentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtEmail;
	private JTextField txtCreditCardNumber;
	private JTextField txtSecurityCode;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public PaymentFrame(Customer customer, List<LiveEvent> basketList) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPaymentFrame = new JLabel("Payment Method");
		lblPaymentFrame.setBounds(20, 22, 314, 55);
		lblPaymentFrame.setFont(new Font("Arial", Font.PLAIN, 40));
		contentPane.add(lblPaymentFrame);

		JButton btnSignout = new JButton("Sign out");
		btnSignout.setBounds(812, 93, 87, 26);
		btnSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame lf = new LoginFrame();
				lf.setVisible(true);
			}
		});
		btnSignout.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(btnSignout);

		JLabel lblHello = new JLabel("Hello,");
		lblHello.setBounds(717, 50, 51, 16);
		lblHello.setFont(new Font("Arial", Font.PLAIN, 18));
		contentPane.add(lblHello);

		txtUserName = new JTextField();
		txtUserName.setBounds(769, 45, 130, 26);
		txtUserName.setFont(new Font("Arial", Font.PLAIN, 18));
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		txtUserName.setText(customer.getName()); // Display the current customer user name.

		JPanel paymentMethod = new JPanel();
		paymentMethod.setBounds(22, 163, 877, 518);
		contentPane.add(paymentMethod);
		paymentMethod.setLayout(null);

		JLabel lblSelectPaymentMethod = new JLabel("Please select a payment method:");
		lblSelectPaymentMethod.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSelectPaymentMethod.setBounds(280, 19, 312, 36);
		paymentMethod.add(lblSelectPaymentMethod);

		JRadioButton rdbtnPayPal = new JRadioButton("PayPal");
		buttonGroup.add(rdbtnPayPal);
		rdbtnPayPal.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtnPayPal.setBounds(202, 100, 108, 23);
		paymentMethod.add(rdbtnPayPal);

		JRadioButton rdbtnCreditCard = new JRadioButton("Credit Card");
		buttonGroup.add(rdbtnCreditCard);
		rdbtnCreditCard.setFont(new Font("Arial", Font.PLAIN, 20));
		rdbtnCreditCard.setBounds(202, 230, 141, 23);
		paymentMethod.add(rdbtnCreditCard);

		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {  // When "Pay" button is clicked,
			public void actionPerformed(ActionEvent e) {
				double total = customer.calculateTotal(basketList);  // Calculate the total price of the items in the basket.
				String receiptText = "";
				if (rdbtnPayPal.isSelected()) {  // Check if PayPal is selected
					try {
						String email = txtEmail.getText().trim();  
						PayPal paypal = new PayPal(email);
						receiptText = customer.pay("Stock.txt", basketList, paypal, total);  // Process payment using PayPal.
						dispose();
						ReceiptFrame rf = new ReceiptFrame(customer, receiptText);  // Create a new ReceiptFrame with the receipt text.
						rf.setVisible(true);
					} catch (EmailAddressException ea) {
						JOptionPane.showMessageDialog(null, ea, "Error", JOptionPane.ERROR_MESSAGE);
						System.err.println(ea.getMessage());
						ea.printStackTrace();
					}
				} else if (rdbtnCreditCard.isSelected()) {  // Check if Credit Card is selected
					try {
						String cardNumber = txtCreditCardNumber.getText().trim();
						String securityCode = txtSecurityCode.getText().trim();
						CreditCard creditCard = new CreditCard(cardNumber, securityCode);
						receiptText = customer.pay("Stock.txt", basketList, creditCard, total);  // Process payment using Credit Card.
						dispose();
						ReceiptFrame rf = new ReceiptFrame(customer, receiptText);  // Create a new ReceiptFrame with the receipt text.
						rf.setVisible(true);
					} catch (DigitNumberException dn) {
						JOptionPane.showMessageDialog(null, dn, "Error", JOptionPane.ERROR_MESSAGE);
						System.err.println(dn.getMessage());
						dn.printStackTrace();
					}
				} else {  // If neither payment method is selected,
					JOptionPane.showMessageDialog(null, "Please select a payment method.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPay.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPay.setBounds(730, 432, 108, 42);
		paymentMethod.add(btnPay);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEmail.setBounds(282, 162, 61, 16);
		paymentMethod.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setText("abc123@gmail.com");
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEmail.setBounds(342, 157, 201, 26);
		paymentMethod.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblCreditCardNumber = new JLabel("Credit Card Number:");
		lblCreditCardNumber.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCreditCardNumber.setBounds(278, 304, 191, 16);
		paymentMethod.add(lblCreditCardNumber);

		JLabel lblSecurityCode = new JLabel("Security Code:");
		lblSecurityCode.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSecurityCode.setBounds(280, 384, 141, 23);
		paymentMethod.add(lblSecurityCode);

		txtCreditCardNumber = new JTextField();
		txtCreditCardNumber.setText("6-digit");
		txtCreditCardNumber.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCreditCardNumber.setBounds(466, 301, 130, 26);
		paymentMethod.add(txtCreditCardNumber);
		txtCreditCardNumber.setColumns(10);

		txtSecurityCode = new JTextField();
		txtSecurityCode.setText("3-digit");
		txtSecurityCode.setFont(new Font("Arial", Font.PLAIN, 20));
		txtSecurityCode.setBounds(413, 381, 130, 26);
		paymentMethod.add(txtSecurityCode);
		txtSecurityCode.setColumns(10);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ShoppingBasketFrame sf = new ShoppingBasketFrame(customer, basketList);
				sf.setVisible(true);
			}
		});
		btnBack.setBounds(6, 124, 66, 29);
		contentPane.add(btnBack);
		btnBack.setFont(new Font("Arial", Font.PLAIN, 13));
	}
}
