package assignment1;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Class instantiates a thread to animate a circle moving across panel passed in
 * constructor. 
 * Multiple threads can be created to manipulate the JPanel
 * This class implements Runnable
 * 
 * @author Anton
 *
 */

public class Circle implements Runnable {
	private boolean running = false;
	private Thread CircleThread;
	private JPanel CirclePanel;
	private int xLocation = 0, yLocation = 0;
	private Graphics g;

	/**
	 * Constructor does: saves passed JPanel in instance variable and gets
	 * graphics from same JPanel
	 * 
	 * @param pnlRotate
	 *            panel to show animation in
	 */
	Circle(JPanel pnlRotate) {
		CirclePanel = pnlRotate;
		g = CirclePanel.getGraphics();

	}

	/**
	 * run method loops and creates/paints a circle on the Jpanel while boolean
	 * running is true
	 * 
	 */
	public void run() {
		boolean positive = true;
		running = true;
		while (running) {

			if (positive) {
				xLocation++;
				yLocation++;

				g.drawOval(xLocation, yLocation, 30, 30);
				g.fillOval(xLocation, yLocation, 30, 30);

				if (xLocation == 180) {
					positive = false;

				}

			}

			else {

				xLocation--;
				yLocation--;

				g.drawOval(xLocation, yLocation, 30, 30);
				g.fillOval(xLocation, yLocation, 30, 30);
				paint();
				if (xLocation == 0) {
					positive = true;
				}

			}
			paint();

		}

	}

	/**
	 * Creates and starts a new thread for this object
	 */
	public void initiate() {
		CircleThread = new Thread(this);
		CircleThread.start();

	}

	/**
	 * sets boolean flag running to false which stops animation
	 */
	public void stopCircle() {
		if (running) {
			running = false;
		}
	}

	/**
	 * repaints JPanel and revalidates in a synchronized manner
	 */
	private synchronized void paint() {
		CirclePanel.repaint();
		CirclePanel.revalidate();

	}

}
