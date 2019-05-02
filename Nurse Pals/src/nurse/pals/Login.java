package nurse.pals;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Login extends JFrame implements ActionListener {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField userTxt;
	private JPasswordField passwordField;
	private JButton loginBtn;
	private ArrayList<User> users = new ArrayList<User>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	
	

	public Login() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNursePals = new JLabel("Nurse Pals");
		lblNursePals.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNursePals.setBounds(191, 26, 91, 16);
		contentPane.add(lblNursePals);

		setUserTxt(new JTextField());
		getUserTxt().setBounds(191, 91, 130, 26);
		contentPane.add(getUserTxt());
		getUserTxt().setColumns(10);

		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setBounds(108, 96, 71, 16);
		contentPane.add(usernameLbl);

		passwordField = new JPasswordField();
		passwordField.setBounds(191, 129, 130, 26);
		contentPane.add(passwordField);
		passwordField.addActionListener(this);
		passwordField.addKeyListener(Enter);

		JLabel passLbl = new JLabel("Password");
		passLbl.setBounds(108, 134, 71, 16);
		contentPane.add(passLbl);

		loginBtn = new JButton("Login");
		loginBtn.setBounds(40, 205, 117, 29);
		contentPane.add(loginBtn);
		loginBtn.addActionListener(this);

		JButton resetBtn = new JButton("Reset");
		resetBtn.setBounds(165, 205, 117, 29);
		contentPane.add(resetBtn);
		resetBtn.addActionListener(this);

		JButton exitBtn = new JButton("Exit");
		exitBtn.setBounds(289, 205, 117, 29);
		contentPane.add(exitBtn);
		exitBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnCall = e.getActionCommand();

		loadUser();

		String username = getUserTxt().getText();
		String password = getPassword();

		if (btnCall.equals("Login")) {

			User login = new User(username, password);

			if (users.contains(login)) {

				getUserTxt().setText(null);
				passwordField.setText(null);
				NursePalsGui np = new NursePalsGui(username);
				frame.setVisible(false);

				// NursePalsGui.main(new String[] {username});

			} else {
				JOptionPane.showMessageDialog(null, "Invalid Login Details!");
				getUserTxt().setText(null);
				passwordField.setText(null);
			}

		} else if (btnCall.equals("Reset")) {
			getUserTxt().setText(null);
			passwordField.setText(null);

		} else if (btnCall.equals("Exit")) {
			
			int reply = JOptionPane.showConfirmDialog(
				    frame,
				    "Are you sure you want to exit the program?",
				    "Exit",
				    JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				Login login = new Login();

				System.exit(0);
				
			}
			

			

		}

		// TODO Auto-generated method stub

	}

	// Key Adapter for reads key pressed then sends action to login button
	KeyAdapter Enter = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				loginBtn.doClick();
			}
		}
	};

	public String getPassword() {

		String password = null;
		if (this.passwordField == null)
			return null;

		char[] buffer = this.passwordField.getPassword();
		password = new String();

		for (char c : buffer) {
			password += c;
		}

		return password;
	}

	public void loadUser() {

		String fileName = "nursePalsUsers.csv";
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			String userData;
			in.readLine();
			while ((userData = in.readLine()) != null) {

				// String userStr = dataTxt.nextLine();

				if (userData.isEmpty()) {
					continue;
				}

				String[] userTokens = userData.split(",");
				String username = userTokens[1];
				String password = userTokens[2];

				User u = new User(username, password);

				users.add(u);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception ex) {
			}
		}
	}

	public JTextField getUserTxt() {
		return userTxt;
	}

	public void setUserTxt(JTextField userTxt) {
		this.userTxt = userTxt;
	}

}
