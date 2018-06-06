package newAssign2;

/**
 * Interface implemented by classes SynchronizedBuffer, UnsyncedBuffer
 * 
 * @author Anton
 *
 */

public interface Buffer {

	public char read() throws InterruptedException;

	public void write(char c) throws InterruptedException;

}
