package assignment1;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class instantiates a thread to animate a String reappearing on panel passed in constructor. 
 * Multiple threads can be created to manipulate the Jpanel
 * This class implements Runnable 
 * 
 * @author Anton
 *
 */
public class Word extends JPanel implements Runnable {
	private boolean running = false;
	private Thread displayThread;
	private JPanel panelAnimation;
	private JLabel labelAnimation;
	private int xLocation, yLocation;

	Word(JPanel panelAnimation, JLabel labelAnimation) {

		this.panelAnimation = panelAnimation;
		this.labelAnimation = labelAnimation;

	}

	/**
	 * method loops and prints a string in a random location on the JPanel
	 * panelAnimation
	 */
	public void run() {
		panelAnimation.setLayout(null);
		panelAnimation.add(labelAnimation);

		running = true;
		while (running) {
			showTextRandomly();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("Thread just died");
	}

	/**
	 * Creates and starts a new thread for this object
	 */
	public void initiate() {
		
		displayThread = new Thread(this);
		displayThread.start();
	}

	{

	}

	/**
	 * sets boolean flag "running" to false which stops animation
	 */
	public void stopDisplay() {
		if (running) {
			running = false;
		}
	}

	/**
	 * This method uses Class Random to decide the next location of the string
	 * to be printed in JPanel The coordinates are decided from 0-129 for x and
	 * y.
	 */
	public void showTextRandomly() {
		Random location = new Random();
		Insets insets = panelAnimation.getInsets();
		Dimension size = labelAnimation.getPreferredSize();
		//labelAnimation.setBounds(100 + insets.left, 150 + insets.top, size.width, size.height);
		xLocation = location.nextInt(130);
		yLocation = location.nextInt(130);
		labelAnimation.setBounds(xLocation + insets.left, yLocation + insets.top, size.width, size.height);
	}
}
