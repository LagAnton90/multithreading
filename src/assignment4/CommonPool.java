package assignment4;

import java.util.LinkedList;
import java.util.Random;
import javax.swing.JLabel;

/**
 * Class represent the common pool in the application. It has a LinkedList with
 * Customer objects that occupy the pool and methods for Customer objects to
 * enter, exit and swap pools Observe that the methods are synchronized due to
 * protection of the shared resources in this class
 * 
 * @author Anton
 *
 */
public class CommonPool {
	private LinkedList<Customer> guests;
	private final int maxGuests;
	private int currentGuests;
	private Random random;
	private JLabel lblComNr;

	public CommonPool(int maxGuests, JLabel l1, JLabel l2) {

		this.lblComNr = l2;
		this.maxGuests = maxGuests;
		guests = new LinkedList<Customer>();
		random = new Random();
		l1.setText(Integer.toString(maxGuests));

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
		lblComNr.setText(Integer.toString(currentGuests));
		System.out.println("Customer just entered the common pool!");

	}

	/**
	 * Checks if there is a customer with VIP status in the list, breaks loop at
	 * end or at found then removes and returns this object. If no Customer
	 * object with VIP status could be found null is returned
	 * 
	 * @return
	 */
	public synchronized Customer getCustomerToAdventure() {
		Customer temp = null;

		for (Customer c : guests) {
			if (c.getStatus()) {
				temp = c;
				break;
			}

		}

		if (temp != null) {
			guests.remove(temp);
			System.out.println("Customer just switched to the adventure pool!");
			lblComNr.setText(Integer.toString(currentGuests));
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
			lblComNr.setText(Integer.toString(currentGuests));
			System.out.println("Customer removed from common pool");
			notify();
			return true;
		}
		return false;

	}

}
