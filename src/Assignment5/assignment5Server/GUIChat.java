
package assignment5Server;

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
	private Server server;

	/**
	 * Starts the application
	 * 
	 * @throws IOException
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multi Chat Server");
		InitializeGUI();

	}

	/**
	 * Sets up the GUI with components
	 * 
	 * @throws IOException
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
		frame.setVisible(true);
		frame.setResizable(false);
		btnSend.addActionListener(new Listener());
		try {
			server = new Server(1500, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		GUIChat chat = new GUIChat();
		chat.Start();

	}
	/**
	 * Updates the GUI with the passed String object 
	 * @param messageRecieved
	 */

	public void updateGUI(String messageRecieved) {

		lstMsg.append(messageRecieved + "\n");

	}

	/**
	 * Class handling button push 
	 * Sends message in Server GUI to all clients 
	 * @author Anton
	 *
	 */
	class Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String message = txt.getText();
			server.pushMessage(message);

		}

	}

}
