package assignment4;

import java.util.Random;

/**
 * Class implements a thread that when started inputs Customers to the adventure
 * pool and transfers customers with VIP status from the common- to the
 * adventure pool
 * 
 * @author Anton
 *
 */

public class AdventurePoolInputQueue extends Thread {
	private AdventurePool aPool;
	private CommonPool cPool;
	private EntranceWaitingQueue entranceQueue;
	private Random random;

	public AdventurePoolInputQueue(AdventurePool aPool, CommonPool cPool, EntranceWaitingQueue entranceQueue) {
		this.aPool = aPool;
		this.cPool = cPool;
		this.entranceQueue = entranceQueue;
		random = new Random();
		start();

	}

	/**
	 * Uses a Random object to either input a customer from the entranceQueue or
	 * input from the other pool
	 */
	public void run() {
		Customer cust;

		while (true) {

			if (random.nextInt(2) == 0) {
				if (entranceQueue.getWaiting()) {
					aPool.entry(new Customer(true));

				}

			}

			else {
				if ((cust = cPool.getCustomerToAdventure()) != null) {
					aPool.entry(cust);
				}

			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
