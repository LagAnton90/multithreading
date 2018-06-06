package assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

/**
 * The GUI for assignment 2
 */
public class GUIMutex {
	/**
	 * These are the components you need to handle. You have to add listeners
	 * and/or code
	 */
	private JFrame frame; // The Main window
	private JLabel lblTrans; // The transmitted text
	private JLabel lblRec; // The received text
	private JRadioButton bSync; // The sync radiobutton
	private JRadioButton bAsync; // The async radiobutton
	private JTextField txtTrans; // The input field for string to transfer
	private JButton btnRun; // The run button
	private JButton btnClear; // The clear button

	private JPanel pnlRes; // The colored result area
	private JLabel lblStatus; // The status of the transmission
	private JTextArea listW; // The write logger pane
	private JTextArea listR; // The read logger pane
	private Writer writer;
	private Reader reader;
	private String textRec = "Start:", textTrans = "Start:";
	private int isFinished = 0;

	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 601, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Concurrent Read/Write");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		// First, create the static components
		// First the 4 static texts
		JLabel lab1 = new JLabel("Writer Thread Logger");
		lab1.setBounds(18, 29, 128, 13);
		frame.add(lab1);
		JLabel lab2 = new JLabel("Reader Thread Logger");
		lab2.setBounds(388, 29, 128, 13);
		frame.add(lab2);
		JLabel lab3 = new JLabel("Transmitted:");
		lab3.setBounds(13, 394, 100, 13);
		frame.add(lab3);
		JLabel lab4 = new JLabel("Received:");
		lab4.setBounds(383, 394, 100, 13);
		frame.add(lab4);
		// Then add the two lists (of string) for logging transfer
		listW = new JTextArea();
		listW.setBounds(13, 45, 197, 342);
		listW.setBorder(BorderFactory.createLineBorder(Color.black));
		listW.setEditable(false);
		frame.add(listW);
		listR = new JTextArea();
		listR.setBounds(386, 45, 183, 342);
		listR.setBorder(BorderFactory.createLineBorder(Color.black));
		listR.setEditable(false);
		frame.add(listR);
		// Next the panel that holds the "running" part
		JPanel pnlTest = new JPanel();
		pnlTest.setBorder(BorderFactory.createTitledBorder("Concurrent Tester"));
		pnlTest.setBounds(220, 45, 155, 342);
		pnlTest.setLayout(null);
		frame.add(pnlTest);
		lblTrans = new JLabel("Transmitted string goes here"); // Replace with
																// sent string
		lblTrans.setBounds(13, 415, 200, 13);
		frame.add(lblTrans);
		lblRec = new JLabel("Received string goes here"); // Replace with
															// received string
		lblRec.setBounds(383, 415, 200, 13);
		frame.add(lblRec);

		// These are the controls on the user panel, first the radiobuttons
		bSync = new JRadioButton("Syncronous Mode", false);
		bSync.setBounds(8, 37, 131, 17);
		pnlTest.add(bSync);
		bAsync = new JRadioButton("Asyncronous Mode", true);
		bAsync.setBounds(8, 61, 141, 17);
		pnlTest.add(bAsync);
		ButtonGroup grp = new ButtonGroup();
		grp.add(bSync);
		grp.add(bAsync);
		// then the label and textbox to input string to transfer
		JLabel lab5 = new JLabel("String to Transfer:");
		lab5.setBounds(6, 99, 141, 13);
		pnlTest.add(lab5);
		txtTrans = new JTextField();
		txtTrans.setBounds(6, 124, 123, 20);
		pnlTest.add(txtTrans);
		// The run button
		btnRun = new JButton("Run");
		btnRun.setBounds(26, 150, 75, 23);
		pnlTest.add(btnRun);
		JLabel lab6 = new JLabel("Running status:");
		lab6.setBounds(23, 199, 110, 13);
		pnlTest.add(lab6);
		// The colored rectangle holding result status
		pnlRes = new JPanel();
		pnlRes.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlRes.setBounds(26, 225, 75, 47);
		pnlRes.setBackground(Color.GREEN);
		pnlTest.add(pnlRes);
		// also to this text
		lblStatus = new JLabel("Status goes here");
		lblStatus.setBounds(23, 275, 100, 13);
		pnlTest.add(lblStatus);
		// The clear input button, starts disabled
		btnClear = new JButton("Clear");
		btnClear.setBounds(26, 303, 75, 23);
		btnClear.setEnabled(false);
		pnlTest.add(btnClear);

		// creates Action instance
		Action action = new Action();
		// add actionListeners here
		btnRun.addActionListener(action);
		btnClear.addActionListener(action);
		bSync.addActionListener(action);
		bAsync.addActionListener(action);

	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public void setReader(Reader reader) {

		this.reader = reader;
	}

	class Action implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnRun) {
				{
					SynchronizedBuffer synchronizedBuffer = new SynchronizedBuffer();
					UnsyncedBuffer unsyncedBufferFucked = new UnsyncedBuffer();
					writer = new Writer(synchronizedBuffer, unsyncedBufferFucked);
					reader = new Reader(synchronizedBuffer, unsyncedBufferFucked);
					writer.setGUI(GUIMutex.this);
					reader.setGUI(GUIMutex.this);
					reader.setBufferSynced(false);
					writer.setBufferSynced(false);
					

					if (bSync.isSelected()) {
						writer.setBufferSynced(true);
						reader.setBufferSynced(true);
					}

					else if (bAsync.isSelected()) {
						writer.setBufferSynced(false);
						reader.setBufferSynced(false);
					}

					writer.start();
					reader.start();

				}

			}

			if (e.getSource() == btnClear) {
				clearGUI();
			}

		}
	}

	public void updateWriter(char temp) {
		textTrans = textTrans + temp;
		listW.append("Written to buffer: " + temp + "\n");
	}

	public void updateReader(char temp) {
		textRec = textRec + temp;
		listR.append("Red from buffer: " + temp + "\n");

	}

	public void compareResult() {
		lblTrans.setText(textTrans);
		lblRec.setText(textRec);

		char[] chars1 = textTrans.toCharArray();
		char[] chars2 = textRec.toCharArray();
		Arrays.sort(chars1);
		Arrays.sort(chars2);

		if (Arrays.equals(chars1, chars2)) {
			pnlRes.setBackground(Color.green);
			lblStatus.setText("Strings matched!");
			btnClear.setEnabled(true);
		} else {
			pnlRes.setBackground(Color.RED);
			lblStatus.setText("Strings did not match.");
			btnClear.setEnabled(true);

		}

	}

	public void clearGUI() {
		listW.setText("");
		listR.setText("");
		pnlRes.setBackground(Color.green);
		lblStatus.setText("Status goes here");
		lblTrans.setText("Transmitted string goes here");
		lblRec.setText("Received string goes here");
		isFinished = 0;
		textTrans = "Start:";
		textRec = "Start:";

	}

	public String getUserInput() {
		return txtTrans.getText();

	}

	public void isFinished(int x) {
		isFinished++;

		if (isFinished == 2) {
			compareResult();
		}
	}

}
