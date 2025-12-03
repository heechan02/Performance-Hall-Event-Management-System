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
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShoppingBasketFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTable tblBasket;
	private DefaultTableModel dtmBasket;
	private List<LiveEvent> shoppingBasketList;

	/**
	 * Create the frame.
	 */
	public ShoppingBasketFrame(Customer customer, List<LiveEvent> basketList) {
		shoppingBasketList = basketList;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblShoppingBasketFrame = new JLabel("Shopping Basket");
		lblShoppingBasketFrame.setBounds(20, 22, 314, 55);
		lblShoppingBasketFrame.setFont(new Font("Arial", Font.PLAIN, 40));
		contentPane.add(lblShoppingBasketFrame);

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

		JPanel basket = new JPanel();
		basket.setBounds(6, 165, 904, 496);
		contentPane.add(basket);
		basket.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 766, 484);
		basket.add(scrollPane);

		tblBasket = new JTable();
		scrollPane.setViewportView(tblBasket);
		dtmBasket = new DefaultTableModel();
		dtmBasket.setColumnIdentifiers(new Object[] { "ID", "Category", "Type", "Name", "Age Restriction",
				"Ticket Quantity", "Ticket Price", "Additional Information" });
		tblBasket.setModel(dtmBasket);
		dtmBasket.setRowCount(0); // clears the table.
		for (LiveEvent event : shoppingBasketList) {
			if (event instanceof Performance) { // If the event is Performance,
				Performance p = (Performance) event;
				Object[] rowData = new Object[] { p.getEventID(), p.getEventCategory(), p.getEventType(),
						p.getEventName(), p.getAgeRestriction(), p.getQuantityInStock(), p.getTicketPrice(),
						p.getLanguage() };
				dtmBasket.addRow(rowData); // Add row.
			} else if (event instanceof Music) { // If the event is Music,
				Music m = (Music) event;
				Object[] rowData = new Object[] { m.getEventID(), m.getEventCategory(), m.getEventType(),
						m.getEventName(), m.getAgeRestriction(), m.getQuantityInStock(), m.getTicketPrice(),
						m.getNumberOfPerformers() };
				dtmBasket.addRow(rowData); // Add row.
			}
		}

		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {  // When checkout button is clicked,
			public void actionPerformed(ActionEvent e) {
				if (shoppingBasketList == null || shoppingBasketList.isEmpty()) {  // If the shopping basket is empty,
					JOptionPane.showMessageDialog(null, "Please add an event in the shopping basket.", "Information",
							JOptionPane.WARNING_MESSAGE);
				} else {  // If the shopping basket is not empty,
					dispose();
					PaymentFrame pf = new PaymentFrame(customer, shoppingBasketList);  // Open PaymentFrame.
					pf.setVisible(true);
				}
			}
		});
		btnCheckout.setFont(new Font("Arial", Font.PLAIN, 17));
		btnCheckout.setBounds(781, 21, 117, 29);
		basket.add(btnCheckout);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() { // When cancel button is clicked,
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to cancel the basket?", "Warning",
						JOptionPane.YES_NO_OPTION);
				try {
					if (result == JOptionPane.YES_OPTION) {
						customer.cancelBasket(shoppingBasketList); // Cancels the basket.
						dtmBasket.setRowCount(0);
						JOptionPane.showMessageDialog(null, "Shopping basket has been cancelled.", "Information",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}
				} catch (EmptyBasketException eb) {
					JOptionPane.showMessageDialog(null, eb, "Information", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 17));
		btnCancel.setBounds(781, 62, 117, 29);
		basket.add(btnCancel);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustomerFrame cf = new CustomerFrame(customer);
				cf.setVisible(true);
			}
		});
		btnBack.setBounds(6, 124, 66, 29);
		contentPane.add(btnBack);
		btnBack.setFont(new Font("Arial", Font.PLAIN, 13));
	}
}
