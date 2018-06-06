
package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI for assignment 1, DualThreads
 */
public class GUIDualThreads {
	/**
	 * These are the components you need to handle. You have to add listeners
	 * and/or code
	 */
	private JFrame frame; // The Main window
	private JButton btnDisplay; // Start thread moving display
	private JButton btnDStop; // Stop moving display thread
	private JButton btnRectangle;// Start moving graphics thread
	private JButton btnTStop; // Stop moving graphics thread
	private JPanel pnlMove; // The panel to move display in
	private JPanel pnlCircle; // The panel to move graphics in
	private Listeners listeners; // listeners
	private Word animationW; // Class that prints word to JPanel pnlMove
	private Circle animationC;// Class that prints Circle to Jpanel pnlCircle
	private JLabel animationLabel; // label used by word class

	/**
	 * Constructor
	 */
	public GUIDualThreads() {
	}

	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 494, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		listeners = new Listeners();
		frame.setTitle("Multiple Thread Demonstrator");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
		animationLabel = new JLabel("Hallå!");
		animationW = new Word(pnlMove, animationLabel);
		animationC = new Circle(pnlCircle);
		listeners = new Listeners();

	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 12, 222, 269);
		pnlDisplay.setLayout(null);

		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		pnlDisplay.add(btnDisplay);

		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		pnlDisplay.add(btnDStop);

		pnlMove = new JPanel();
		pnlMove.setBounds(10, 19, 200, 200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
		// the animationLabel
		pnlDisplay.add(pnlMove);
		// Then add this to main window
		frame.add(pnlDisplay);

		// The moving graphics outer panel
		JPanel pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Rectangle Thread");
		pnlTriangle.setBorder(b3);
		pnlTriangle.setBounds(240, 12, 222, 269);
		pnlTriangle.setLayout(null);

		// Add buttons and drawing panel to this panel
		btnRectangle = new JButton("Start Rotate");
		btnRectangle.setBounds(10, 226, 121, 23);
		pnlTriangle.add(btnRectangle);

		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		pnlTriangle.add(btnTStop);

		pnlCircle = new JPanel();
		pnlCircle.setBounds(10, 19, 200, 200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlCircle.setBorder(b31);
		pnlTriangle.add(pnlCircle);
		// Add this to main window
		frame.add(pnlTriangle);

		// adding Listeners to buttons
		btnDisplay.addActionListener(listeners);
		btnDStop.addActionListener(listeners);
		btnRectangle.addActionListener(listeners);
		btnTStop.addActionListener(listeners);

	}

	/**
	 * Class for handling buttons
	 * 
	 * @author Anton
	 *
	 */
	private class Listeners implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnDisplay) {
				animationW.initiate();

			}
			if (e.getSource() == btnDStop) {
				animationW.stopDisplay();

			}

			if (e.getSource() == btnRectangle) {
				animationC.initiate();

			}
			if (e.getSource() == btnTStop) {
				animationC.stopCircle();

			}
		}
	}

}
