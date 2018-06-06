package assignment5Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 * Class Client attemps to set up a socket and out-/inputStreams to a Server at "localhost" port 1500.
 * Client implements Runnable and has a thread listening for messages being recieved through this socket.
 * @author Anton
 *
 */
public class Client implements Runnable {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private GUIChat GUI;
	private String server = "localhost";
	private int port = 1500;

	
	
	/**
	 * Creates a GUI and sets this object as its client 
	 * Also creates the sockets and streams needed for communication with the server 
	 */
	public Client() {
		GUI = new GUIChat();
		GUI.Start();
		GUI.setClient(this);

		try {

			socket = new Socket(server, port);
			dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "Server is not running, shutting down.(Try again later!)");
			System.exit(0);
		}

		

	}

	/**
	 * Sends a message through the socket to Server
	 * @param messageToSend
	 */
	public void sendMessage(String messageToSend) throws IOException {
		dos.writeUTF(messageToSend);
		dos.flush();

	}

	/**
	 * Thread listening for messages being recieved through the socket 
	 */
	public void run() {
		boolean running = true;
		String messageToRecieve;

		while (running) {

			try {

				messageToRecieve = dis.readUTF();
				GUI.updateGUI(messageToRecieve);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

}
