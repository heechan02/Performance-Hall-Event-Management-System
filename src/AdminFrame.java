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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEventId;
	private JTextField txtEventCategory;
	private JTextField txtEventType;
	private JTextField txtEventName;
	private JTextField txtAgeRestriction;
	private JTextField txtTicketQuantity;
	private JTextField txtPerformanceFee;
	private JTextField txtTicketPrice;
	private JTextField txtAdditionalInfo;
	private JTable tblViewEvent;
	private DefaultTableModel dtmViewEvent;
	private JTextField txtUserName;
	private List<LiveEvent> eventList;

	/**
	 * Create the frame.
	 */
	public AdminFrame(Admin admin) {
		eventList = admin.viewAllEvents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 149, 904, 512);
		contentPane.add(tabbedPane);

		JPanel addEvent = new JPanel();
		tabbedPane.addTab("Add Event", null, addEvent, null);
		addEvent.setLayout(null);

		JLabel lblEventId = new JLabel("Event ID:");
		lblEventId.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEventId.setBounds(80, 30, 87, 23);
		addEvent.add(lblEventId);

		txtEventId = new JTextField();
		txtEventId.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEventId.setText("12345");
		txtEventId.setBounds(170, 27, 87, 26);
		addEvent.add(txtEventId);
		txtEventId.setColumns(10);

		JLabel lblEventCategory = new JLabel("Event Category:");
		lblEventCategory.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEventCategory.setBounds(465, 31, 157, 21);
		addEvent.add(lblEventCategory);

		txtEventCategory = new JTextField();
		txtEventCategory.setText("Performance");
		txtEventCategory.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEventCategory.setBounds(617, 28, 130, 26);
		addEvent.add(txtEventCategory);
		txtEventCategory.setColumns(10);

		JLabel lblEventType = new JLabel("Event Type:");
		lblEventType.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEventType.setBounds(80, 124, 112, 23);
		addEvent.add(lblEventType);

		txtEventType = new JTextField();
		txtEventType.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtEventType.setText("Magic");
		txtEventType.setBounds(193, 116, 149, 37);
		addEvent.add(txtEventType);
		txtEventType.setColumns(10);

		JLabel lblEventName = new JLabel("Event Name:");
		lblEventName.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEventName.setBounds(465, 127, 124, 23);
		addEvent.add(lblEventName);

		txtEventName = new JTextField();
		txtEventName.setFont(new Font("Arial", Font.PLAIN, 20));
		txtEventName.setText("Spooky Dreams");
		txtEventName.setBounds(585, 122, 191, 37);
		addEvent.add(txtEventName);
		txtEventName.setColumns(10);

		JLabel lblAgeRestriction = new JLabel("Age Restriction:");
		lblAgeRestriction.setFont(new Font("Arial", Font.PLAIN, 20));
		lblAgeRestriction.setBounds(80, 204, 170, 37);
		addEvent.add(lblAgeRestriction);

		txtAgeRestriction = new JTextField();
		txtAgeRestriction.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtAgeRestriction.setText("Adults");
		txtAgeRestriction.setBounds(230, 208, 91, 26);
		addEvent.add(txtAgeRestriction);
		txtAgeRestriction.setColumns(10);

		JLabel lblTicketQuantity = new JLabel("Ticket Quantity:");
		lblTicketQuantity.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTicketQuantity.setBounds(465, 211, 149, 23);
		addEvent.add(lblTicketQuantity);

		txtTicketQuantity = new JTextField();
		txtTicketQuantity.setFont(new Font("Arial", Font.PLAIN, 20));
		txtTicketQuantity.setText("1000");
		txtTicketQuantity.setBounds(613, 209, 93, 26);
		addEvent.add(txtTicketQuantity);
		txtTicketQuantity.setColumns(10);

		JLabel lblPerformanceFee = new JLabel("Performance Fee:");
		lblPerformanceFee.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPerformanceFee.setBounds(80, 298, 170, 45);
		addEvent.add(lblPerformanceFee);

		txtPerformanceFee = new JTextField();
		txtPerformanceFee.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtPerformanceFee.setText("2500");
		txtPerformanceFee.setBounds(249, 307, 76, 26);
		addEvent.add(txtPerformanceFee);
		txtPerformanceFee.setColumns(10);

		JLabel lblTicketPrice = new JLabel("Ticket Price:");
		lblTicketPrice.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTicketPrice.setBounds(464, 309, 138, 23);
		addEvent.add(lblTicketPrice);

		txtTicketPrice = new JTextField();
		txtTicketPrice.setFont(new Font("Arial", Font.PLAIN, 20));
		txtTicketPrice.setText("30");
		txtTicketPrice.setBounds(584, 307, 103, 26);
		addEvent.add(txtTicketPrice);
		txtTicketPrice.setColumns(10);

		JLabel lblAdditionalInfo = new JLabel("Additional Information:");
		lblAdditionalInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblAdditionalInfo.setBounds(78, 399, 206, 26);
		addEvent.add(lblAdditionalInfo);

		txtAdditionalInfo = new JTextField();
		txtAdditionalInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txtAdditionalInfo.setText("English");
		txtAdditionalInfo.setBounds(280, 395, 124, 32);
		addEvent.add(txtAdditionalInfo);
		txtAdditionalInfo.setColumns(10);

		JButton btnAddEvent = new JButton("Add");
		btnAddEvent.addActionListener(new ActionListener() {  // When Add button is clicked,
			public void actionPerformed(ActionEvent e) {
				List<JTextField> textFields = new ArrayList<>(); // Grouping text fields.
				textFields.add(txtEventId);
				textFields.add(txtEventCategory);
				textFields.add(txtEventType);
				textFields.add(txtEventName);
				textFields.add(txtAgeRestriction);
				textFields.add(txtTicketQuantity);
				textFields.add(txtPerformanceFee);
				textFields.add(txtTicketPrice);
				textFields.add(txtAdditionalInfo);
				for (JTextField textField : textFields) { // Send error messages if a text field is empty.
					if (textField.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				String eventId = txtEventId.getText().trim();
				String eventCategory = txtEventCategory.getText().trim();
				String eventType = txtEventType.getText().trim();
				String eventName = txtEventName.getText().trim();
				String ageRestriction = txtAgeRestriction.getText().trim();
				String ticketQuantity = txtTicketQuantity.getText().trim();
				String performanceFee = txtPerformanceFee.getText().trim();
				String ticketPrice = txtTicketPrice.getText().trim();
				String additionalInfo = txtAdditionalInfo.getText().trim();
				// Admin adds an event what the user typed.
				try {
					admin.addEvent(eventId, eventCategory, eventType, eventName, ageRestriction, ticketQuantity,
							performanceFee, ticketPrice, additionalInfo);  // Add event.
					// Update the eventList.
					eventList = admin.viewAllEvents();
					// Confirmation message.
					JOptionPane.showMessageDialog(null, "A " + eventCategory + " event has been successfully added!",
							"Confirm", JOptionPane.INFORMATION_MESSAGE);

					for (JTextField textField : textFields) { // Empties the text fields once add button is clicked.
						textField.setText("");
					}
				} catch (DuplicateEventIdException d) {
					JOptionPane.showMessageDialog(null, d, "Error", JOptionPane.ERROR_MESSAGE);
					System.err.println(d.getMessage());
					d.printStackTrace();
				} catch (DigitNumberException dn) {
					JOptionPane.showMessageDialog(null, dn, "Error", JOptionPane.ERROR_MESSAGE);
					System.err.println(dn.getMessage());
					dn.printStackTrace();
				} catch (IllegalArgumentException i) {
					JOptionPane.showMessageDialog(null, i, "Error", JOptionPane.ERROR_MESSAGE);
					System.err.println(i.getMessage());
					i.printStackTrace();
				}
			}
		});
		btnAddEvent.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAddEvent.setBounds(750, 398, 101, 45);
		addEvent.add(btnAddEvent);
		
		JLabel lblEventCategoryGuide = new JLabel("(Performance or Music)");
		lblEventCategoryGuide.setFont(new Font("Arial", Font.PLAIN, 13));
		lblEventCategoryGuide.setBounds(465, 62, 149, 16);
		addEvent.add(lblEventCategoryGuide);
		
		JLabel lblPerformanceEventTypeGuide = new JLabel("(Performance: Stand-up Comedy, Theatre, Magic)");
		lblPerformanceEventTypeGuide.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPerformanceEventTypeGuide.setBounds(80, 154, 298, 16);
		addEvent.add(lblPerformanceEventTypeGuide);
		
		JLabel lblMusicEventTypeGuide = new JLabel("(Music: Live Concert, DJ Set)");
		lblMusicEventTypeGuide.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMusicEventTypeGuide.setBounds(80, 176, 178, 16);
		addEvent.add(lblMusicEventTypeGuide);
		
		JLabel lblAgeGuide = new JLabel("(Adults or All)");
		lblAgeGuide.setFont(new Font("Arial", Font.PLAIN, 13));
		lblAgeGuide.setBounds(80, 238, 87, 21);
		addEvent.add(lblAgeGuide);
		
		JLabel lblLanguage = new JLabel("(Performance: Language)");
		lblLanguage.setFont(new Font("Arial", Font.PLAIN, 13));
		lblLanguage.setBounds(78, 423, 157, 16);
		addEvent.add(lblLanguage);
		
		JLabel lblNumberOfPeople = new JLabel("(Music: Number of bands / DJs)");
		lblNumberOfPeople.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNumberOfPeople.setBounds(78, 444, 204, 16);
		addEvent.add(lblNumberOfPeople);

		JPanel viewEvent = new JPanel();
		tabbedPane.addTab("View All Events", null, viewEvent, null);
		viewEvent.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 6, 804, 454);
		viewEvent.add(scrollPane);

		tblViewEvent = new JTable();
		scrollPane.setViewportView(tblViewEvent);
		// Create a table.
		dtmViewEvent = new DefaultTableModel();
		dtmViewEvent.setColumnIdentifiers(new Object[] { "ID", "Category", "Type", "Name", "Age Restriction",
				"Ticket Quantity", "Performance Fee", "Ticket Price", "Additional Information" });
		tblViewEvent.setModel(dtmViewEvent);

		JButton btnViewEvent = new JButton("View");
		btnViewEvent.setFont(new Font("Arial", Font.PLAIN, 13));
		btnViewEvent.addActionListener(new ActionListener() { // When View button is clicked,
			public void actionPerformed(ActionEvent e) {
				dtmViewEvent.setRowCount(0); // Clears the table.
				for (LiveEvent event : eventList) {
					if (event instanceof Performance) { // If the event is Performance,
						Performance p = (Performance) event;
						Object[] rowData = new Object[] { p.getEventID(), p.getEventCategory(), p.getEventType(),
								p.getEventName(), p.getAgeRestriction(), p.getQuantityInStock(), p.getPerformanceFee(),
								p.getTicketPrice(), p.getLanguage() };
						dtmViewEvent.addRow(rowData); // Add row.
					} else if (event instanceof Music) { // If the event is Performance,
						Music m = (Music) event;
						Object[] rowData = new Object[] { m.getEventID(), m.getEventCategory(), m.getEventType(),
								m.getEventName(), m.getAgeRestriction(), m.getQuantityInStock(), m.getPerformanceFee(),
								m.getTicketPrice(), m.getNumberOfPerformers() };
						dtmViewEvent.addRow(rowData); // Add row.
					}
				}
			}
		});
		btnViewEvent.setBounds(816, 17, 67, 38);
		viewEvent.add(btnViewEvent);
		// Setting width of columns.
		tblViewEvent.getColumnModel().getColumn(0).setPreferredWidth(100); // ID
		tblViewEvent.getColumnModel().getColumn(1).setPreferredWidth(100); // Category
		tblViewEvent.getColumnModel().getColumn(2).setPreferredWidth(100); // Type
		tblViewEvent.getColumnModel().getColumn(3).setPreferredWidth(100); // Name
		tblViewEvent.getColumnModel().getColumn(4).setPreferredWidth(150); // Age Restriction
		tblViewEvent.getColumnModel().getColumn(5).setPreferredWidth(150); // Ticket Quantity
		tblViewEvent.getColumnModel().getColumn(6).setPreferredWidth(150); // Performance Fee
		tblViewEvent.getColumnModel().getColumn(7).setPreferredWidth(100); // Ticket Price
		tblViewEvent.getColumnModel().getColumn(8).setPreferredWidth(200); // Additional Information

		JLabel lblAdminFrame = new JLabel("Admin");
		lblAdminFrame.setFont(new Font("Arial", Font.PLAIN, 40));
		lblAdminFrame.setBounds(20, 22, 166, 55);
		contentPane.add(lblAdminFrame);

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
		txtUserName.setText(admin.getName()); // Display the current admin user name.
	}
}
