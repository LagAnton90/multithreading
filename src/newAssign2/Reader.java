package newAssign2;

import java.util.Random;

/**
 * Class reads one char at a time from synchronized/unsynchronized buffer and
 * sends the char to the GUI
 * 
 * @author Anton
 *
 */

public class Reader implements Runnable {

	private SynchronizedBuffer synchronizedBuffer;
	private UnsyncedBuffer unsyncedBuffer;
	private Buffer currentBuffer;

	private Thread readerThread;
	private GUIMutex GUI;

	/**
	 * 
	 * recieved a synhronized and a unsynchronized buffer creates a thread for
	 * this object
	 */
	public Reader(SynchronizedBuffer synchronizedBuffer, UnsyncedBuffer unsyncedBuffer) {
		this.unsyncedBuffer = unsyncedBuffer;
		this.synchronizedBuffer = synchronizedBuffer;
		readerThread = new Thread(this);

	}

	/**
	 * Thread reads from buffer, updates GUI and then sleeps a random period of
	 * milisec This is done until all chars have been red and thread notifies
	 * the UI that it has finished executing
	 */
	public void run() {
		char currentChar;
		int charsRed = 0;
		int allCharsToRead = GUI.getUserInput().length();
		Random randMilisec = new Random();

		while (charsRed <= allCharsToRead - 1) {
			try {
				Thread.sleep(randMilisec.nextInt(300));
				currentChar = currentBuffer.read();
				GUI.updateReader(currentChar);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			charsRed++;
		}
		//notifies that thread is finished 
		GUI.isFinished(1);

	}

	/**
	 * Starts the thread
	 */
	public void start() {
		readerThread.start();

	}

	/**
	 * Sets the GUI for this Writer instance
	 */
	public void setGUI(GUIMutex GUI) {
		this.GUI = GUI;
	}

	/**
	 * Changes the buffer used by this writer by boolean setBufferedSynced
	 */
	public void setBufferSynced(boolean setBufferSynced) {
		if (setBufferSynced) {
			currentBuffer = synchronizedBuffer;
		}

		else {
			currentBuffer = unsyncedBuffer;

		}

	}

}
