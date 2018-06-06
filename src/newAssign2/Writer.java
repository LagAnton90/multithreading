package newAssign2;

import java.util.Random;

/**
 * Class writes one char at a time to a synchronized/unsynchronized buffer and
 * sends the char to the GUI
 * 
 * @author Anton
 *
 */

public class Writer implements Runnable {

	private SynchronizedBuffer synchronizedBuffer;
	private UnsyncedBuffer unsyncedBuffer;
	private Buffer currentBuffer;

	public Thread writerThread;
	private GUIMutex GUI;

	/**
	 * recieved a synhronized and a unsynchronized buffer creates a thread for
	 * this object
	 */
	public Writer(SynchronizedBuffer synchronizedBuffer, UnsyncedBuffer unsyncedBuffer) {
		this.unsyncedBuffer = unsyncedBuffer;
		this.synchronizedBuffer = synchronizedBuffer;
		writerThread = new Thread(this);
	}

	/**
	 * Thread writes to buffer, updates GUI and then sleeps a random period of
	 * milisec This is done until all chars have been written and thread
	 * notifies the UI that it has finished executing
	 */

	public void run() {
		Random randMilisec = new Random();
		String allInputToTransfer = GUI.getUserInput();
		int position = 0;
		char charToTransfer;

		while (position <= allInputToTransfer.length() - 1) {
			try {
				charToTransfer = allInputToTransfer.charAt(position);
				Thread.sleep(randMilisec.nextInt(300));

				currentBuffer.write(charToTransfer);
				GUI.updateWriter(charToTransfer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			position++;

		}
		//notifies that thread is finished 
		GUI.isFinished(1);
	}

	/**
	 * Starts the thread
	 */
	public void start() {
		writerThread.start();
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
		} else {
			currentBuffer = unsyncedBuffer;

		}

	}

}
