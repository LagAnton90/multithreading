package assignment2;

import java.util.LinkedList;

public class SynchronizedBuffer implements Buffer {

	private final int max = 1;
	private LinkedList<Character> buffer = new LinkedList<Character>();

	public synchronized void write(char c) throws InterruptedException {
		if (buffer.size() == max) {

			System.out.println("Data exists. Writer waits.");
			wait();
		}
		buffer.addLast(c);
		notifyAll();

	}

	public synchronized char read() throws InterruptedException {
		{
			if (buffer.isEmpty()) {
				System.out.println("No data. Reader waits.");
				wait();
			}
			char removed = buffer.removeFirst();
			notifyAll();
			return removed;

		}
	}

	public synchronized int size() {
		return buffer.size();
	}

	public void clearBuffer() {
		buffer.clear();
	}

}
