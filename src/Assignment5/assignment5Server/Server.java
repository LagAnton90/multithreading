package assignment5Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The server listens for a connection attemp being made by a client, creates a User object 
 * to handle communication with this client and adds the object to a list of users
 * The User objects are executed by workers in the ThreadPool 'executor'
 * @author Anton
 *
 */
public class Server extends Thread {
	private ArrayList<User> clientList;
	private ServerSocket serverSocket;
	private boolean serverIsUp;
	private ThreadPoolExecutor executor;
	private GUIChat GUI;

	public Server(int port, GUIChat GUI) throws IOException {
		this.GUI = GUI;
		clientList = new ArrayList<User>();
		serverSocket = new ServerSocket(port);
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
		start();
	}

	public void run() {

		Socket socket;
		User user;
		serverIsUp = true;
		GUI.updateGUI("Server is running...");

		while (serverIsUp) {

			try {
				GUI.updateGUI("Waiting for connection...");
				socket = serverSocket.accept();
				synchronized (this) {
					user = new User(socket, this);
					clientList.add(user);
					System.out.println("Added user: " + user.toString());
					executor.execute(user);
					GUI.updateGUI("A new user connected!");

				}

			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}
	/**
	 * Takes a String object and sends it through the socket of 
	 * all User objects in ArrayList and therefore is recieved by all clients connected
	 * @param recievedMessage
	 */
	public synchronized void pushMessage(String recievedMessage) {
		for (User user : clientList) {
			user.sendMessage(recievedMessage);

		}
		GUI.updateGUI(recievedMessage);
	}

}
