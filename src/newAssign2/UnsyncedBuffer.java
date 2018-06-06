package newAssign2;

/**
 * An unsynchronized buffer with a single char
 * 
 * @author Anton
 *
 */
public class UnsyncedBuffer implements Buffer {

	private char charBuffer;

	public void write(char c) {

		charBuffer = c;

	}

	public char read() {

		return charBuffer;

	}

}
