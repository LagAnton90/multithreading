package newAssign2;

import java.util.LinkedList;

/**
 * Class represent a synchornized buffer with room for three chars
 * simultaneously
 * 
 * @author Anton
 *
 */

public class SynchronizedBuffer implements Buffer {

	private char removedChar;
	private final int max = 3;
	private LinkedList<Character> buffer = new LinkedList<Character>();

	/**
	 * If buffer is already full the thread waits, else the char is added to the
	 * buffer and threads waiting on this objects lock are notified
	 */
	public synchronized void write(char c) throws InterruptedException {
		if (buffer.size() == max) {

			System.out.println("Data exists. Writer waits.");
			wait();
		}
		buffer.addLast(c);
		notifyAll();

	}

	/**
	 * If buffer is empty the thread waits, else char is removed, returned and
	 * thereafter threads are notified
	 */
	public synchronized char read() throws InterruptedException {
		{
			if (buffer.isEmpty()) {
				System.out.println("No data. Reader waits.");
				wait();
			}
			removedChar = buffer.removeFirst();
			notifyAll();
			return removedChar;

		}
	}

}
