package assignment4;

import java.util.Random;

/**
 * Class runs a thread that invokes method addToQueue of two instances of
 * EntranceWaitingQueue
 * 
 * @author Anton
 *
 */

public class Reception extends Thread {
	private EntranceWaitingQueue entrWaitingCommon, entrWaitingAdv;
	private Random random;
	private boolean running = true;

	public Reception(EntranceWaitingQueue entrWaitingAdv, EntranceWaitingQueue entrWaitingCommon) {

		this.entrWaitingCommon = entrWaitingCommon;
		this.entrWaitingAdv = entrWaitingAdv;
		random = new Random();
		start();

	}

	public void run() {
		int i;
		while (true) {

			while (running == false) {
				synchronized (this) {
					try {
						System.out.println("Reception Closed!");
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

			i = random.nextInt(2);

			if (i == 0) {
				entrWaitingCommon.addToQueue();

				System.out.println("customer added to common queue");
			}

			else if (i == 1) {

				entrWaitingAdv.addToQueue();

				System.out.println("customer added to adventure queue");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	/**
	 * Pauses the thread
	 */
	public void pauseRunning() {
		running = false;

	}

	/**
	 * Resumes thread by altering boolean running and notifying the sleeping
	 * thread
	 */
	public synchronized void resumeRunning() {
		running = true;
		notify();
	}

	/**
	 * Returns running status
	 * 
	 */
	public boolean getRunning() {
		return running;
	}

}
