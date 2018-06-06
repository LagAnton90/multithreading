package assignment4;

import javax.swing.JLabel;

/**
 * Class represent a waiting queue for the entrance to both the Common- and
 * Adventure pool
 * 
 * @author Anton
 *
 */

public class EntranceWaitingQueue {
	private int numberOfWaiting = 0;
	private JLabel l;

	public EntranceWaitingQueue(JLabel receptionQueue) {
		l = receptionQueue;

	}

	/**
	 * increases variable numberOfwaiting which represent a waiting customer
	 */
	public void addToQueue() {
		numberOfWaiting++;
		l.setText(Integer.toString(numberOfWaiting));

	}

	/**
	 * decreases the number of waiting customers and returns true, else returns
	 * false if no customer is currently waiting
	 * 
	 * @return
	 */
	public boolean getWaiting() {

		if (numberOfWaiting > 0) {
			numberOfWaiting--;
			l.setText(Integer.toString(numberOfWaiting));
			return true;
		}

		else {
			System.out.println("Nobody was waiting!");
			return false;

		}
	}

}
