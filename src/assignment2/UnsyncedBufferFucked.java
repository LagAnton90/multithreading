package assignment2;

import java.util.LinkedList;

public class UnsyncedBufferFucked implements Buffer {

	private final int max = 5;
	private LinkedList<Character> buffer = new LinkedList<Character>();

	public void write(char c) throws InterruptedException {
		if (buffer.size() == max) {

			System.out.println("Data exists. Writer waits.");
		
		}
		buffer.addLast(c);
	
	}

	public char read() throws InterruptedException {
		{
			char removed = buffer.removeFirst();
			return removed;

		}
	}

	public synchronized int size() {
		return buffer.size();
	}
	
	public void clearBuffer(){
		buffer.clear();
	}

}
