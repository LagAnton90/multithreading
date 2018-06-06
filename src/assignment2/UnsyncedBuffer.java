package assignment2;

public class UnsyncedBuffer implements Buffer {

	private char charBuffer;

	public void write(char c) {

		charBuffer = c;

	}

	public char read() {

		return charBuffer;

	}

	@Override
	public void clearBuffer() {
		// TODO Auto-generated method stub
		
	}

}
