import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReceiptFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;

	/**
	 * Create the frame.
	 */
	public ReceiptFrame(Customer customer, String receiptText) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel ReceiptFrame = new JLabel("Receipt");
		ReceiptFrame.setBounds(20, 22, 314, 55);
		ReceiptFrame.setFont(new Font("Arial", Font.PLAIN, 40));
		contentPane.add(ReceiptFrame);

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

		JPanel Receipt = new JPanel();
		Receipt.setBounds(20, 190, 877, 471);
		contentPane.add(Receipt);
		Receipt.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(197, 84, 506, 98);
		Receipt.add(scrollPane);

		JTextArea txtAreaReceipt = new JTextArea();
		txtAreaReceipt.setFont(new Font("Arial", Font.PLAIN, 22));
		scrollPane.setViewportView(txtAreaReceipt);
		txtAreaReceipt.setText(receiptText);

		JButton btnContinueShopping = new JButton("Continue Shopping");
		btnContinueShopping.addActionListener(new ActionListener() {  // When "Continue Shopping" button is clicked,
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustomerFrame cf = new CustomerFrame(customer);  // Open CustomerFrame.
				cf.setVisible(true);
			}
		});
		btnContinueShopping.setFont(new Font("Arial", Font.PLAIN, 17));
		btnContinueShopping.setBounds(359, 288, 174, 37);
		Receipt.add(btnContinueShopping);
	}
}
