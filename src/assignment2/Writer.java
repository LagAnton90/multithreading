package assignment2;

import java.util.Random;

public class Writer implements Runnable {

	private SynchronizedBuffer synchronizedBuffer;
	private UnsyncedBuffer unsyncedBufferFucked;
	private Buffer currentBuffer;
	private Random random;
	public Thread writerThread;
	private String string;
	private GUIMutex GUI;


	public Writer(SynchronizedBuffer synchronizedBuffer, UnsyncedBuffer unsyncedBufferFucked) {
		this.unsyncedBufferFucked = unsyncedBufferFucked;
		this.synchronizedBuffer = synchronizedBuffer;
		random = new Random();
		writerThread = new Thread(this);
	}

	@Override
	public void run() {
		string = GUI.getUserInput();
		int i = 0;
		char temp;
		while (i <= string.length() - 1) {
			try {
				temp = string.charAt(i);
				Thread.sleep(random.nextInt(300));
				currentBuffer.write(temp);
				GUI.updateWriter(temp);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;

		}
		GUI.isFinished(1);
	}

	public void start() {
		writerThread.start();
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
		currentBuffer.clearBuffer();

	}
	
	
}
