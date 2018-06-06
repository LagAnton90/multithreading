package assignment5Client;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Class instantiates Clients and pass them a ThreadPoolExecutor object for execution 
 * @author Anton
 *
 */
public class ClientHandler {
	private ThreadPoolExecutor executor;

	public ClientHandler() {
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
		createClients();

	}

	/**
	 * Choose the number of Clients you wish to instantiate 
	 */
	public void createClients() {

		executor.execute(new Client());
		executor.execute(new Client());
		executor.execute(new Client());
		executor.execute(new Client());
	

	}

	public static void main(String[] args) {
		new ClientHandler();
	}
}