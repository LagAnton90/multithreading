package assignment5Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Class represent a client on the server side and handles communication with a client through socket communication
 * @author Anton
 *
 */
public class User implements Runnable {
	
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	boolean userOnline;
	Server server;

	public User(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;
		try {
			dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			userOnline = true;
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
/**
 * Thread reads from client and send message recieved to server 
 */
	public void run() {
		String recievedMessage = null;
		while (userOnline) {
			try {
				recievedMessage = dis.readUTF();
				server.pushMessage(recievedMessage);

			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}

	}
	/**
	 * Method sends a String object to Client connected to this socket 
	 *
	 */
	public void sendMessage(String messageToSend) {
		try {
			dos.writeUTF(messageToSend);
			dos.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
