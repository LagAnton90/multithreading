package assignment2;

public interface Buffer {

	public char read() throws InterruptedException;

	public void write(char c) throws InterruptedException;
	
	public void clearBuffer();

}
