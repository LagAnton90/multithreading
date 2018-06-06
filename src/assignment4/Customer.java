package assignment4;

/**
 * Represents a customer with a boolean VIPStatus that is either true or false
 * 
 * @author Anton
 *
 */
public class Customer {
	private boolean VIPStatus;

	public Customer(boolean status) {
		VIPStatus = status;

	}

	public boolean getStatus() {

		return VIPStatus;
	}

}
