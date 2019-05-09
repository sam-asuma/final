package nurse.pals;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import edu.century.pa5.Product;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class NursePalsGui implements ActionListener {

	private JFrame frame;
	private JTextField fName;
	private JTextField lName;
	private JTextField ssnTxt;
	private JTextField addressTxt;
	JTextField rxSig;
	private JTextField rxNameIn;
	private JTextField rxStrenghtIn;
	private JTextField rxDispenseQtyIn;
	private JTextField rxPrescriberNmIn;
	private JTextField rxPrescriberDeaIn;
	private JTextField rxPrescriberAddrIn;
	private JTextField rxPresciberPhIn;
	private JFormattedTextField rxDateIn;
	private JTextField rxRefillQty;
	private JTable table;
	private JTextArea txtrPatienSummary;
	private static String user;
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private JComboBox rxDosageForm;
	private JTextArea displayRx;
	private JScrollPane displayPane;
	private Date date = new Date();
	ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();
	private JComboBox selectRx;
	private String firstName, lastName, address;
	private DefaultTableModel tableModel;
	private String fristN = null;
	private String lastN = null;
	private String addr = null;
	private String ssn = null;
	private TableRowSorter<TableModel> sorter;
	private JButton searchBtn;
	private JButton rxDisplayBtn;
	private JTextArea rxDisplayArea;
	private JScrollPane rxDisplayAreaPane;
	private String translate = "";
	private Date displayDate;
	private String patientSum = "";
	private JButton addMedBtn;
	private JComboBox doseComboBx;
	private JTextArea notesTxt;
	private JButton startBtn;
	private JButton stopBtn;
	private SimpleDateFormat dformat;
	private SimpleDateFormat dtformat;
	private Date d1, d2;
	private JTextArea dataConsole;
	private JComboBox dataCombo;
	private MedAdmin event;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NursePalsGui window = new NursePalsGui(user);
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NursePalsGui(String s) {
		this.user = s;

		initialize();
		frame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Nurse Pals");
		frame.setBounds(100, 100, 930, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(27, 67, 193, 510);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

/// Scrollable text area that displays summary of current patient record
		txtrPatienSummary = new JTextArea();
		JScrollPane scrollPatientSummary = new JScrollPane(txtrPatienSummary);
		txtrPatienSummary.setText("Patient Summary\n");
		scrollPatientSummary.setBounds(6, 6, 181, 498);
		panel.add(scrollPatientSummary);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(278, 67, 646, 510);
		frame.getContentPane().add(tabbedPane);
/// Patient search panel
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Patient Search", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblPatientSearch = new JLabel("Patient Search");
		lblPatientSearch.setBounds(272, 14, 88, 16);
		panel_1.add(lblPatientSearch);

		fName = new JTextField();
		fName.setBounds(47, 80, 130, 26);
		panel_1.add(fName);
		fName.setColumns(10);
		fName.addKeyListener(Enter);

		lName = new JTextField();
		lName.setBounds(189, 80, 130, 26);
		panel_1.add(lName);
		lName.setColumns(10);
		lName.addKeyListener(Enter);

		ssnTxt = new JTextField();
		ssnTxt.setBounds(331, 80, 130, 26);
		panel_1.add(ssnTxt);
		ssnTxt.setColumns(10);
		ssnTxt.addKeyListener(Enter);

		addressTxt = new JTextField();
		addressTxt.setBounds(47, 140, 272, 26);
		panel_1.add(addressTxt);
		addressTxt.setColumns(10);
		addressTxt.addKeyListener(Enter);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(47, 55, 77, 13);
		panel_1.add(lblNewLabel);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(194, 53, 77, 16);
		panel_1.add(lblLastName);

		JLabel lblSsn = new JLabel("SSN");
		lblSsn.setBounds(333, 53, 61, 16);
		panel_1.add(lblSsn);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(47, 112, 61, 16);
		panel_1.add(lblAddress);

		searchBtn = new JButton("Search");
		searchBtn.setBounds(483, 80, 117, 29);
		panel_1.add(searchBtn);
		searchBtn.addActionListener(this);

		/// Table
		String col[] = { "ID", "First Name", "Last Name", "Midde Initial", "SSN", "DOB", "Height", "Weight", "Sex", "Address",
				"Primay Physician" };

		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setBounds(47, 194, 548, 244);
		panel_1.add(table);
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setBounds(47, 194, 548, 244);
		panel_1.add(tableScroll);
		sorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(sorter);
		table.getAutoCreateRowSorter();

		JButton clearBtn = new JButton("Clear");
		clearBtn.setBounds(483, 112, 117, 29);
		panel_1.add(clearBtn);
		clearBtn.addActionListener(this);
		// table.setFillsViewportHeight(true);

		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					// Double clicked
					txtrPatienSummary.setText(" ");
					txtrPatienSummary.setText("Patient Summary\n");
					;
					String tableData = tableModel.getDataVector()
							.elementAt(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow())).toString();
					tableData = tableData.replace(",", "\n");
					tableData = tableData.replace("[", " ");
					tableData = tableData.replace("]", " ");
					String row[] = tableData.split("\n");
					
					for (int i = 0; i < col.length; i++) {
						patientSum = patientSum + col[i] + ":" + row[i] + "\n";
					}
					txtrPatienSummary.append(patientSum);
					
				}

			}
		});

// RX panel
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("RX", null, panel_2, null);
		panel_2.setLayout(null);

		JButton rxReviewBtn = new JButton("Review");
		rxReviewBtn.setBounds(23, 242, 117, 29);
		panel_2.add(rxReviewBtn);
		rxReviewBtn.addActionListener(this);

		rxSig = new JTextField();
		rxSig.setText("Enter sig");
		rxSig.setBounds(165, 208, 440, 94);
		panel_2.add(rxSig);
		rxSig.setColumns(10);
		
		rxSig.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	rxSig.setText("");
		    }
		});

		Prescription p = new Prescription();
		rxDosageForm = new JComboBox(p.getDosageForm());
		rxDosageForm.setBounds(165, 88, 130, 29);
		panel_2.add(rxDosageForm);

		JLabel lblChooseRxType = new JLabel("Dosage Form");
		lblChooseRxType.setBounds(23, 93, 110, 16);
		panel_2.add(lblChooseRxType);

		JLabel lblRxName = new JLabel("RX Name");
		lblRxName.setBounds(23, 38, 61, 16);
		panel_2.add(lblRxName);

		rxNameIn = new JTextField();
		rxNameIn.setBounds(165, 33, 130, 26);
		panel_2.add(rxNameIn);
		rxNameIn.setColumns(10);

		JLabel lblRxStrenght = new JLabel("Strength");
		lblRxStrenght.setBounds(23, 66, 61, 16);
		panel_2.add(lblRxStrenght);

		rxStrenghtIn = new JTextField();
		rxStrenghtIn.setColumns(10);
		rxStrenghtIn.setBounds(165, 61, 130, 26);
		panel_2.add(rxStrenghtIn);

		JLabel lblQty = new JLabel("Dispense Qty");
		lblQty.setBounds(23, 123, 92, 16);
		panel_2.add(lblQty);

		rxDispenseQtyIn = new JTextField();
		rxDispenseQtyIn.setColumns(10);
		rxDispenseQtyIn.setBounds(165, 118, 130, 26);
		panel_2.add(rxDispenseQtyIn);

		JLabel lblPersname = new JLabel("Prescriber Name");
		lblPersname.setBounds(341, 35, 110, 16);
		panel_2.add(lblPersname);

		rxPrescriberNmIn = new JTextField();
		rxPrescriberNmIn.setColumns(10);
		rxPrescriberNmIn.setBounds(480, 30, 130, 26);
		panel_2.add(rxPrescriberNmIn);

		JLabel lblDea = new JLabel("DEA #");
		lblDea.setBounds(341, 63, 61, 16);
		panel_2.add(lblDea);

		rxPrescriberDeaIn = new JTextField();
		rxPrescriberDeaIn.setColumns(10);
		rxPrescriberDeaIn.setBounds(480, 58, 130, 26);
		panel_2.add(rxPrescriberDeaIn);

		JLabel lblPrescriberAddress = new JLabel("Prescriber Address");
		lblPrescriberAddress.setBounds(341, 96, 130, 16);
		panel_2.add(lblPrescriberAddress);

		rxPrescriberAddrIn = new JTextField();
		rxPrescriberAddrIn.setColumns(10);
		rxPrescriberAddrIn.setBounds(480, 91, 130, 26);
		panel_2.add(rxPrescriberAddrIn);

		JLabel lblPhone = new JLabel("Phone #");
		lblPhone.setBounds(341, 128, 61, 16);
		panel_2.add(lblPhone);

		rxPresciberPhIn = new JTextField();
		rxPresciberPhIn.setColumns(10);
		rxPresciberPhIn.setBounds(480, 123, 130, 26);
		panel_2.add(rxPresciberPhIn);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(341, 161, 130, 16);
		panel_2.add(lblDate);

		DateFormat sformat = new SimpleDateFormat("dd/MM/yyyy");
		rxDateIn = new JFormattedTextField();
		rxDateIn.setText(sformat.format(date));
		rxDateIn.setColumns(10);
		rxDateIn.setBounds(480, 156, 130, 26);
		panel_2.add(rxDateIn);

		JLabel lblSig = new JLabel("Sig");
		lblSig.setBounds(23, 208, 61, 16);
		panel_2.add(lblSig);

		JLabel lblRefill = new JLabel("Refill");
		lblRefill.setBounds(23, 161, 61, 16);
		panel_2.add(lblRefill);

		rxRefillQty = new JTextField();
		rxRefillQty.setColumns(10);
		rxRefillQty.setBounds(165, 156, 130, 26);
		panel_2.add(rxRefillQty);

		displayRx = new JTextArea();
		displayRx.setText(" ");
		displayRx.setBounds(26, 314, 573, 124);
		panel_2.add(displayRx);

		displayPane = new JScrollPane(displayRx);
		displayPane.setBounds(26, 314, 573, 124);
		panel_2.add(displayPane);

		JButton rxEnterBtn = new JButton("Enter");
		rxEnterBtn.setBounds(23, 273, 117, 29);
		panel_2.add(rxEnterBtn);
		rxEnterBtn.addActionListener(this);

		JLabel lblRx = new JLabel("RX");
		lblRx.setBounds(310, 6, 16, 16);
		panel_2.add(lblRx);

// Administer Tab
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Administer RX", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblPrescriptions = new JLabel("Prescription");
		lblPrescriptions.setBounds(45, 44, 82, 16);
		panel_3.add(lblPrescriptions);
		
		MedAdmin med = new MedAdmin();
		///Object[] arr = prescriptions.toArray();
		selectRx = new JComboBox(med.getScript());
		//selectRx.addItem(prescriptions.get(prescriptions.size()-1).getName());

		selectRx.setBounds(167, 40, 122, 27);
		panel_3.add(selectRx);
		

		rxDisplayArea = new JTextArea();
		rxDisplayArea.setText(
				"RX Sumary");
		rxDisplayArea.setBounds(2, 42, 490, 41);
		panel_3.add(rxDisplayArea);
		rxDisplayAreaPane = new JScrollPane(rxDisplayArea);
		rxDisplayAreaPane.setBounds(45, 86, 494, 85);
		panel_3.add(rxDisplayAreaPane);

		JLabel admimisterRx = new JLabel("Administer Dose");
		admimisterRx.setBounds(53, 201, 122, 16);
		panel_3.add(admimisterRx);
		
		//RX Display button
		rxDisplayBtn = new JButton("Display RX");
		rxDisplayBtn.setBounds(313, 39, 117, 29);
		panel_3.add(rxDisplayBtn);
		
		
		
		doseComboBx = new JComboBox(med.getDose());
		doseComboBx.setBounds(45, 229, 156, 27);
		panel_3.add(doseComboBx);
		
		startBtn = new JButton("Start");
		startBtn.setBounds(45, 263, 72, 41);
		panel_3.add(startBtn);
		startBtn.addActionListener(this);
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setBounds(45, 309, 61, 16);
		panel_3.add(lblNotes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 337, 494, 109);
		panel_3.add(scrollPane);
		
		notesTxt = new JTextArea();
		notesTxt.setText(" ");
		scrollPane.setViewportView(notesTxt);
		
		addMedBtn = new JButton("Add");
		addMedBtn.setBounds(546, 337, 73, 109);
		panel_3.add(addMedBtn);
		addMedBtn.addActionListener(this);
		
		stopBtn = new JButton("Stop");
		stopBtn.setBounds(129, 263, 72, 41);
		panel_3.add(stopBtn);
		stopBtn.addActionListener(this);
		rxDisplayBtn.addActionListener(this);

// Event Summary tab
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Event Summary", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblDisplayEventData = new JLabel("Display Event Data");
		lblDisplayEventData.setBounds(245, 6, 126, 16);
		panel_4.add(lblDisplayEventData);
		
		dataCombo = new JComboBox(med.getEventData());
		dataCombo.setBounds(229, 35, 161, 27);
		panel_4.add(dataCombo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 74, 541, 355);
		panel_4.add(scrollPane_1);
		
		dataConsole = new JTextArea();
		dataConsole.setText("Data");
		scrollPane_1.setViewportView(dataConsole);

		JLabel lblCurrentDate = new JLabel("Current Date & Time:");
		displayDate = new Date();
		dformat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		lblCurrentDate.setBounds(746, 589, 178, 16);
		frame.getContentPane().add(lblCurrentDate);
		lblCurrentDate.setText(dformat.format(displayDate));

		JLabel lblCurrentUser = new JLabel("Current User: " + user);
		lblCurrentUser.setBounds(731, 6, 163, 16);
		frame.getContentPane().add(lblCurrentUser);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(807, 20, 94, 29);
		frame.getContentPane().add(btnLogOut);
		btnLogOut.addActionListener(this);
	}

	public JTextField getTxtSigText() {
		return rxSig;
	}

	public void translate() {
		// TODO Auto-generated method stub

	}
	
	private void Paint(Graphics g) {
		
	}

//Button Action listeners
	@Override
	public void actionPerformed(ActionEvent e) {

		String btnCall = e.getActionCommand();

		if (btnCall.equals("Search")) {

			firstName = fName.getText();
			lastName = lName.getText();
			address = addressTxt.getText();
			ssn= ssnTxt.getText();

			// If our table is empty addPatients
			
			patient();
			tableModel.setRowCount(0);
			addPatients();

			if (firstName.length() != 0) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" +firstName));

			} else if (lastName.length() != 0) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" +lastName));

			}else if (address.length() != 0) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" +address));

			}else if (ssn.length() != 0) {
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" +ssn));

			} else {
				sorter.setRowFilter(null);

			}

			// Patient searchP = new Patient(firstName, lastName, address);

			/*
			 * for (Patient i : patients) { if (i.getFirstName().equals(firstName)) {
			 * txtrPatienSummary.append(i.toString()); } }
			 */

		}

		if (btnCall.equals("Clear")) {
			tableModel.setRowCount(0);
			fName.setText(" ");
			lName.setText(" ");
			ssnTxt.setText(" ");
			addressTxt.setText(" ");

		}

		if (btnCall.equals("Log Out")) {

			int reply = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Log Out",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				Login login = new Login();

				frame.setVisible(false);

			}

			// System.exit(0);

		}
		Prescription rx = null;
		
		if (btnCall.equals("Review")) {

			try {
				String name = rxNameIn.getText();
				String strength = rxStrenghtIn.getText();
				String form = rxDosageForm.getSelectedItem().toString();
				int qty = Integer.parseInt(rxDispenseQtyIn.getText());
				String prescriber = rxPrescriberNmIn.getText();
				String dea = rxPrescriberDeaIn.getText();
				String addr = rxPrescriberAddrIn.getText();
				long phone = Long.parseLong(rxPresciberPhIn.getText());

				String date = rxDateIn.getText();

				int refill = Integer.parseInt(rxRefillQty.getText());
				rx = new Prescription(name, strength, form, qty, refill, prescriber, dea, addr, phone, date);
				
				prescriptions.add(rx);
				
				displayRx.setText(rx.toString() + "\n");

				String sig = rxSig.getText();
				MedAdmin admin = new MedAdmin();
				translate = admin.translate(sig);
				displayRx.append("Sig: " + translate);

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Please fill out all data fields");
				// TODO: handle exception
			}
			
			

		}
		if (btnCall.equals("Enter")) {
			if (displayRx.getText().equals(" ")) {
				JOptionPane.showMessageDialog(null, "Please 'Review' data first.");
			}
			displayRx.setText(" ");
			displayRx.setText("Commit!");
			
		}
		
		
		if (btnCall.equals("Display RX")) {
			
			rxDisplayArea.setText(" ");
			
			 rxDisplayArea.append(prescriptions.toString());
	
			 rxDisplayArea.append("\nSig: " + translate);

		}
		dtformat = new SimpleDateFormat("mm:ss");
		
		if (btnCall.equals("Start")) {
			
			startBtn.setForeground(Color.green);
			stopBtn.setForeground(null);
			
			d1 = new Date();
			dtformat.format(d1);
			
		}
		
		if (btnCall.equals("Stop")) {
				
				
				stopBtn.setForeground(Color.red);
				startBtn.setForeground(null);
				
			
			
			d2 = new Date();
			dtformat.format(d2);
			
		}
		
		
		
		
		if (btnCall.equals("Add")) {
			
			
			
		
			
			try {
				
				String dose = doseComboBx.getSelectedItem().toString();
				String notes = notesTxt.getText();
				String duration = dtformat.format(new Date(d2.getTime() - d1.getTime()));
				if (duration == null) {
					duration = dtformat.format(d1.getTime());
					
				}
				String script = prescriptions.toString();
				event = new MedAdmin(user,displayDate,patientSum,script, translate,
						dose, duration, notes );
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			
			
			
			
		}
		
		if (dataCombo.getSelectedItem().equals("Current Session")) {
			
			try {
				dataConsole.setText(event.toString());
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		String fileName = "nursepalsEventData.csv";
			
			try {
				PrintWriter dataTxt = new PrintWriter(new FileOutputStream(fileName));
				
					
						dataTxt.println(event);
					
					
					
				
				
		
				
				dataTxt.close();
				
			} catch (FileNotFoundException z) {
				
				z.printStackTrace();
			}
			
		}
		if (dataCombo.getSelectedItem().equals("Daily")) {
			try {
				dataConsole.setText(event.toString());
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		
			
		}
		

	}
	public void patient() {
		
		
		Patient p1 = new Patient(00001, "John", "Doe", 'H', 889097654, "05/02/1988", 180, 160, 'M', "123 Main St",
				"Dr.Stien");
		Patient p2 = new Patient(00002, "David", "Green", 'S', 433097654, "02/05/1998", 163, 140, 'M', "223 Dalton St",
				"Dr.Ogaden");
		Patient p3 = new Patient(00003, "Kenya", "Davis", 'L', 889097654, "06/22/1999", 180, 120, 'F',
				"622 Greensburg St", "Dr.Olsen");
		Patient p4 = new Patient(00004, "Author", "Olumade", 'A', 889097654, "12/02/1990", 183, 160, 'M', "543 Main St",
				"Dr.Oluwolu");
		patients.add(p1);
		patients.add(p2);
		patients.add(p3);
		patients.add(p4);



		}

//Method that adds patients to our table
	public void addPatients() {


		
		/*
		 * long id = 0; String fristN = null; String lastN= null; char ini= ' '; long
		 * social= 0; String dob= null; double weight= 0.0; double height= 0.0; char
		 * sex= ' '; String addr= null; String doc= null;
		 */
		Object[] data = null;
		tableModel.setRowCount(0);
		for (int i = 0; i < patients.size(); i++) {
			long id = patients.get(i).getId();
			fristN = patients.get(i).getFirstName();
			String lastN = patients.get(i).getLastName();
			char ini = patients.get(i).getMiddleInitial();
			long social = patients.get(i).getSSN();
			String dob = patients.get(i).getDOB();
			double weight = patients.get(i).getWeight();
			double height = patients.get(i).getHeight();
			char sex = patients.get(i).getSex();
			String addr = patients.get(i).getAddress();
			String doc = patients.get(i).getPrimaryDoctor();
			data = new Object[] { id, fristN, lastN, ini, social, dob, weight, height, sex, addr, doc };
			tableModel.addRow(data);

		}

	}

	// Key Adapter for reads key pressed then sends action to login button
	KeyAdapter Enter = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				searchBtn.doClick();
			}
		}
	};
}
