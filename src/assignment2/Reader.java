package assignment2;

import java.util.Random;

public class Reader implements Runnable {

	private Thread readerThread;
	private SynchronizedBuffer synchronizedBuffer;
	private UnsyncedBuffer unsyncedBufferFucked;
	private Buffer currentBuffer;
	private Random random;
	private char temp;
	private GUIMutex GUI;

	public Reader(SynchronizedBuffer synchronizedBuffer, UnsyncedBuffer unsyncedBufferFucked2) {
		this.unsyncedBufferFucked = unsyncedBufferFucked2;
		this.synchronizedBuffer = synchronizedBuffer;
		random = new Random();
		readerThread = new Thread(this);

	}

	@Override
	public void run() {
		int i = 0;
		int length = GUI.getUserInput().length();
		while (i <= length - 1) {
			try {
				Thread.sleep(random.nextInt(300));
				temp = currentBuffer.read();
				GUI.updateReader(temp);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			i++;
		}
		System.out.println("This happens now");
		GUI.isFinished(1);

	}

	public void start() {
		readerThread.start();

	}

	public void setGUI(GUIMutex GUI) {
		this.GUI = GUI;
	}

	public void setBufferSynced(boolean b) {
		if (b) {
			currentBuffer = synchronizedBuffer;
		}

		else {
			currentBuffer = unsyncedBufferFucked;

		}

	}
	

}
