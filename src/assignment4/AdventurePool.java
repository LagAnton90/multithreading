package assignment4;

import java.util.LinkedList;
import java.util.Random;
import javax.swing.JLabel;

/**
 * Class represent the adventure pool in the application. It has a LinkedList
 * with Customer objects that occupy the pool and methods for Customer objects
 * to enter, exit and swap pools Observe that the methods are synchronized due
 * to protection of the shared resources in this class
 * 
 * @author Anton
 *
 */
public class AdventurePool {
	private LinkedList<Customer> guests;
	private final int maxGuests;
	private int currentGuests;
	private Random random;
	private JLabel lblAdvNr;

	public AdventurePool(int maxGuests, JLabel lblAdvLim, JLabel lblAdvNr) {
		this.lblAdvNr = lblAdvNr;
		this.maxGuests = maxGuests;
		guests = new LinkedList<Customer>();
		random = new Random();
		lblAdvLim.setText(Integer.toString(maxGuests));

	}

	/**
	 * IF pool is full the thread goes to sleep ELSE the passed Customer objects
	 * is added to the list and GUI is updated
	 * 
	 * @param cust
	 */
	public synchronized void entry(Customer cust) {

		if (currentGuests == maxGuests) {
			try {
				System.out.println("Thread is sleeping!");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		guests.addLast(cust);
		currentGuests++;
		lblAdvNr.setText(Integer.toString(currentGuests));
		System.out.println("Customer just entered the adventure pool!");

	}

	/**
	 * Checks if there is a customer with VIP status in the list, breaks loop at
	 * end or at found then removes and returns this object. If no Customer
	 * object with VIP status could be found null is returned
	 * 
	 * @return
	 */
	public synchronized Customer getCustomerToCommon() {
		Customer temp = null;

		for (Customer c : guests) {
			if (c.getStatus()) {
				temp = c;
				break;
			}

		}

		if (temp != null) {
			guests.remove(temp);
			System.out.println("Customer just switched to the common pool!");
			lblAdvNr.setText(Integer.toString(currentGuests));
			currentGuests--;

		}

		return temp;

	}

	/**
	 * Removes a random customer from the list and notifies the thread waiting
	 * on this objects monitor that a new customer can enter the pool
	 * 
	 * @return null if there are no objects in the list else true
	 */
	public synchronized boolean removeCustomer() {

		if (currentGuests != 0) {
			guests.remove(random.nextInt(currentGuests));
			currentGuests--;
			lblAdvNr.setText(Integer.toString(currentGuests));
			System.out.println("Customer REMOVED from adventure pool");
			notify();
			return true;

		}

		return false;

	}

}
