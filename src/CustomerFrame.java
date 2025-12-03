import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblViewEvent;
	private DefaultTableModel dtmViewEvent;
	private JTextField txtUserName;
	private List<LiveEvent> eventList;
	private JTextField txtSearch;
	private JTable tblSearch;
	private DefaultTableModel dtmSearchResult;
	private List<LiveEvent> eventSearchList;
	private List<LiveEvent> basketList;

	/**
	 * Create the frame.
	 */
	public CustomerFrame(Customer customer) {
		eventList = customer.viewAllEvents();
		eventSearchList = null;
		basketList = null;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 177, 904, 484);
		contentPane.add(tabbedPane);

		JPanel search = new JPanel();
		tabbedPane.addTab("Search", null, search, null);
		search.setLayout(null);

		JComboBox<String> cbFilter = new JComboBox<>(new String[] { "Event ID", "Language" }); // Filter options.
		cbFilter.setBounds(244, 38, 107, 27);
		search.add(cbFilter);

		txtSearch = new JTextField();
		txtSearch.setText("53201");
		txtSearch.setBounds(363, 38, 149, 25);
		search.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() { // When Search button is clicked,
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText().trim();
				if (cbFilter.getSelectedItem().equals("Event ID")) {
					eventSearchList = customer.searchByEventId(search);
					dtmSearchResult.setRowCount(0); // Clears the table.
					for (LiveEvent event : eventSearchList) {
						if (event instanceof Performance) { // If the event is Performance,
							Performance p = (Performance) event;
							Object[] rowData = new Object[] { p.getEventID(), p.getEventCategory(), p.getEventType(),
									p.getEventName(), p.getAgeRestriction(), p.getQuantityInStock(), p.getTicketPrice(),
									p.getLanguage() };
							dtmSearchResult.addRow(rowData); // Add row.
						} else if (event instanceof Music) { // If the event is Music,
							Music m = (Music) event;
							Object[] rowData = new Object[] { m.getEventID(), m.getEventCategory(), m.getEventType(),
									m.getEventName(), m.getAgeRestriction(), m.getQuantityInStock(), m.getTicketPrice(),
									m.getNumberOfPerformers() };
							dtmSearchResult.addRow(rowData); // Add row.
						}
					}
				} else if (cbFilter.getSelectedItem().equals("Language")) {
					eventSearchList = customer.filterByLanguage(search);
					dtmSearchResult.setRowCount(0); // Clears the table.
					for (LiveEvent event : eventSearchList) {
						if (event instanceof Performance) { // If the event is Performance,
							Performance p = (Performance) event;
							Object[] rowData = new Object[] { p.getEventID(), p.getEventCategory(), p.getEventType(),
									p.getEventName(), p.getAgeRestriction(), p.getQuantityInStock(), p.getTicketPrice(),
									p.getLanguage() };
							dtmSearchResult.addRow(rowData); // Add row.
						}
					}
				}
			}
		});
		btnSearch.setBounds(524, 37, 117, 29);
		search.add(btnSearch);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 125, 773, 307);
		search.add(scrollPane_1);

		tblSearch = new JTable();
		scrollPane_1.setViewportView(tblSearch);
		tblSearch.setFont(new Font("Arial", Font.PLAIN, 12));

		dtmSearchResult = new DefaultTableModel();
		dtmSearchResult.setColumnIdentifiers(new Object[] { "ID", "Category", "Type", "Name", "Age Restriction",
				"Ticket Quantity", "Ticket Price", "Additional Information" });
		tblSearch.setModel(dtmSearchResult);

		tblSearch.getColumnModel().getColumn(0).setPreferredWidth(100); // ID
		tblSearch.getColumnModel().getColumn(1).setPreferredWidth(100); // Category
		tblSearch.getColumnModel().getColumn(2).setPreferredWidth(100); // Type
		tblSearch.getColumnModel().getColumn(3).setPreferredWidth(100); // Name
		tblSearch.getColumnModel().getColumn(4).setPreferredWidth(150); // Age Restriction
		tblSearch.getColumnModel().getColumn(5).setPreferredWidth(150); // Ticket Quantity
		tblSearch.getColumnModel().getColumn(6).setPreferredWidth(100); // Ticket Price
		tblSearch.getColumnModel().getColumn(7).setPreferredWidth(200); // Additional Information

		JButton btnAddBasket = new JButton("Add to basket");
		btnAddBasket.addActionListener(new ActionListener() {  // When Add to basket button is clicked,
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblSearch.getSelectedRow();
				if (selectedRow != -1) {
					String id = dtmSearchResult.getValueAt(selectedRow, 0).toString().trim();
					String quantity = dtmSearchResult.getValueAt(selectedRow, 5).toString().trim();
					try {
						basketList = customer.addToBasket(id, quantity);  // Adds the event to the basket.
						JOptionPane.showMessageDialog(null, "The event has been added to basket successfully!",
								"Confirm", JOptionPane.INFORMATION_MESSAGE);
					} catch (InvalidTicketQuantityException it) {
						JOptionPane.showMessageDialog(null, it, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (DuplicateEventIdException de) {
						JOptionPane.showMessageDialog(null, de, "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {  // If no event is selected,
					JOptionPane.showMessageDialog(null, "Please select an event to add.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddBasket.setFont(new Font("Arial", Font.PLAIN, 13));
		btnAddBasket.setBounds(782, 360, 95, 72);
		search.add(btnAddBasket);

		JLabel lblInstruction = new JLabel(
				"After changing the ticket quantity, please press the Enter key and then add the event to your basket.");
		lblInstruction.setFont(new Font("Arial", Font.PLAIN, 13));
		lblInstruction.setBounds(147, 84, 591, 16);
		search.add(lblInstruction);

		JPanel viewAllEvent = new JPanel();
		tabbedPane.addTab("View All Events", null, viewAllEvent, null);
		viewAllEvent.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 6, 804, 454);
		viewAllEvent.add(scrollPane_2);

		tblViewEvent = new JTable();
		scrollPane_2.setViewportView(tblViewEvent);
		// Create a table.
		dtmViewEvent = new DefaultTableModel();
		dtmViewEvent.setColumnIdentifiers(new Object[] { "ID", "Category", "Type", "Name", "Age Restriction",
				"Ticket Quantity", "Ticket Price", "Additional Information" });
		tblViewEvent.setModel(dtmViewEvent);

		JButton btnViewEvent = new JButton("View"); // Performance fee is excluded.
		btnViewEvent.setFont(new Font("Arial", Font.PLAIN, 13));
		btnViewEvent.addActionListener(new ActionListener() { // When View button is clicked,
			public void actionPerformed(ActionEvent e) {
				dtmViewEvent.setRowCount(0); // Clears the table.
				for (LiveEvent event : eventList) {
					if (event instanceof Performance) { // If the event is Performance,
						Performance p = (Performance) event;
						Object[] rowData = new Object[] { p.getEventID(), p.getEventCategory(), p.getEventType(),
								p.getEventName(), p.getAgeRestriction(), p.getQuantityInStock(), p.getTicketPrice(),
								p.getLanguage() };
						dtmViewEvent.addRow(rowData); // Add row.
					} else if (event instanceof Music) { // If the event is Music,
						Music m = (Music) event;
						Object[] rowData = new Object[] { m.getEventID(), m.getEventCategory(), m.getEventType(),
								m.getEventName(), m.getAgeRestriction(), m.getQuantityInStock(), m.getTicketPrice(),
								m.getNumberOfPerformers() };
						dtmViewEvent.addRow(rowData); // Add row.
					}
				}
			}
		});
		btnViewEvent.setBounds(816, 17, 67, 38);
		viewAllEvent.add(btnViewEvent);
		// Setting width of columns.
		tblViewEvent.getColumnModel().getColumn(0).setPreferredWidth(100); // ID
		tblViewEvent.getColumnModel().getColumn(1).setPreferredWidth(100); // Category
		tblViewEvent.getColumnModel().getColumn(2).setPreferredWidth(100); // Type
		tblViewEvent.getColumnModel().getColumn(3).setPreferredWidth(100); // Name
		tblViewEvent.getColumnModel().getColumn(4).setPreferredWidth(150); // Age Restriction
		tblViewEvent.getColumnModel().getColumn(5).setPreferredWidth(150); // Ticket Quantity
		tblViewEvent.getColumnModel().getColumn(6).setPreferredWidth(100); // Ticket Price
		tblViewEvent.getColumnModel().getColumn(7).setPreferredWidth(200); // Additional Information

		JLabel lblCustomerFrame = new JLabel("Customer");
		lblCustomerFrame.setFont(new Font("Arial", Font.PLAIN, 40));
		lblCustomerFrame.setBounds(20, 22, 188, 55);
		contentPane.add(lblCustomerFrame);

		JButton btnSignout = new JButton("Sign out");
		btnSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame lf = new LoginFrame();
				lf.setVisible(true);
			}
		});
		btnSignout.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSignout.setBounds(812, 93, 87, 26);
		contentPane.add(btnSignout);

		JLabel lblHello = new JLabel("Hello,");
		lblHello.setFont(new Font("Arial", Font.PLAIN, 18));
		lblHello.setBounds(717, 50, 51, 16);
		contentPane.add(lblHello);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Arial", Font.PLAIN, 18));
		txtUserName.setBounds(769, 45, 130, 26);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		txtUserName.setText(customer.getName()); // Display the current customer user name.

		JButton btnBasket = new JButton("Basket"); // When Basket button is clicked,
		btnBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (basketList == null) {
					basketList = customer.getBasket();
				}
				dispose();
				ShoppingBasketFrame sbf = new ShoppingBasketFrame(customer, basketList);
				sbf.setVisible(true);
			}
		});
		btnBasket.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBasket.setBounds(793, 132, 117, 41);
		contentPane.add(btnBasket);
	}
}
