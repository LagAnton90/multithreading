
package assignment5Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

/**
 * The GUI for assignment 5
 */
public class GUIChat {
	/**
	 * These are the components you need to handle. You have to add listeners
	 * and/or code
	 */
	private JFrame frame; // The Main window
	private JTextField txt; // Input for text to send
	private JButton btnSend; // Send text in txt
	private JTextArea lstMsg; // The logger listbox
	private Client client;

	/**
	 * Constructor
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multi Chat Client"); // Change to "Multi Chat Server" on
												// server part and vice versa
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		txt = new JTextField();
		txt.setBounds(13, 13, 177, 23);
		frame.add(txt);
		btnSend = new JButton("Send");
		btnSend.setBounds(197, 13, 75, 23);
		frame.add(btnSend);
		lstMsg = new JTextArea();
		lstMsg.setEditable(false);
		JScrollPane pane = new JScrollPane(lstMsg);
		pane.setBounds(12, 51, 260, 199);
		pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.add(pane);
		btnSend.addActionListener(new Listener());

	}

	/**
	 * Updates the GUI with message with String object passed
	 * 
	 */

	public void updateGUI(String messageRecieved) {

		lstMsg.append(messageRecieved + "\n");

	}

	/**
	 * Class handles button "Send" being pushed by the user Sends the message
	 * entered by user to the Client for processing
	 * 
	 * @author Anton
	 *
	 */
	class Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			try {
				String message = txt.getText();
				client.sendMessage(message);

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

}
