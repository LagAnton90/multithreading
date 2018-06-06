package assignment4;

import java.util.Random;
import javax.swing.JLabel;

/**
 * Class has a thread that loops and removes Customer objects from the swimming
 * pools common and adventure.
 * 
 * @author Anton
 *
 */

public class CustomerExit extends Thread {
	private AdventurePool adventurePool;
	private CommonPool commonPool;
	private JLabel lblAexit, lblCexit;
	private Random random;
	private int adventureCounter, commonCounter;

	public CustomerExit(AdventurePool adventurePool, CommonPool commonPool, JLabel lblAexit, JLabel lblCexit) {
		this.adventurePool = adventurePool;
		this.commonPool = commonPool;
		random = new Random();
		this.lblAexit = lblAexit;
		this.lblCexit = lblCexit;
		start();
	}

	public void run() {
		int i;

		while (true) {

			i = random.nextInt(2);

			if (i == 0) {

				if (adventurePool.removeCustomer()) {
					lblAexit.setText(Integer.toString(adventureCounter++));
				}

			}

			else if (i == 1) {
				if (commonPool.removeCustomer()) {
					lblCexit.setText(Integer.toString(commonCounter++));
				}

			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

}
