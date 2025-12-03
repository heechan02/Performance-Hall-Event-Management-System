import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<User> cbUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setFont(new Font("Arial", Font.PLAIN, 30));
		lblWelcome.setBounds(6, 6, 194, 47);
		contentPane.add(lblWelcome);

		List<User> users = DataLoader.loadUserData("UserAccounts.txt");  // Load user data from the file.
		cbUser = new JComboBox<User>(users.toArray(new User[0]));
		cbUser.setFont(new Font("Arial", Font.PLAIN, 13));
		cbUser.setBounds(138, 123, 148, 27);
		contentPane.add(cbUser);

		JLabel lblUserSelect = new JLabel("Please select the user you would like to sign in:");
		lblUserSelect.setBounds(67, 85, 342, 16);
		contentPane.add(lblUserSelect);

		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() { // When "Sign in" button is clicked,
			public void actionPerformed(ActionEvent e) {
				User user = (User) cbUser.getSelectedItem();
				if (user.getRole().equals("admin")) { // If the role is 'admin',
					Admin admin = (Admin) user;
					dispose(); // Close the LoginFrame.
					AdminFrame adf = new AdminFrame(admin); // Open AdminFrame.
					adf.setVisible(true);
				} else if (user.getRole().equals("customer")) { // If the role is 'customer',
					Customer customer = (Customer) user;
					dispose(); // Close the LoginFrame.
					CustomerFrame cuf = new CustomerFrame(customer); // Open CustomerFrame.
					cuf.setVisible(true);
				}
			}

		});
		btnSignIn.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSignIn.setBounds(153, 190, 117, 29);
		contentPane.add(btnSignIn);
	}
}
