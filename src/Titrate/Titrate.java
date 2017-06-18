package Titrate;

import java.applet.*;
import java.awt.*;

/**
 * This class reads PARAM tags from its HTML host page and sets the color and
 * label properties of the applet. Program execution begins with the init()
 * method.
 */

public class Titrate extends Applet implements Runnable {
	/**
	 * Serial Version UID for serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The base panel for the titration applet
	 */
	public Panel cards;
	/**
	 * 8 sequential pages of titration dialogs
	 */
	Introduction panel1;
	Flask panel2;
	Buret panel3;
	Indicator panel4;
	Titration panel5;
	pHCurve panel6;
	Results panel7;
	Certificate panel8;
	/**
	 * Variables related to the thread for animation.
	 */
	int frameNumber = -1;
	Thread animatorThread;
	int delay = 500;
	
	Label label1 = new Label();
	/**
	 * Some string variables
	 */
	private final String labelParam = "label";
	public final String labelParam2 = "label2";
	private final String backgroundParam = "background";
	private final String foregroundParam = "foreground";
	public String dlgLabel, dlgLabel2;
	public Color gbgColor;
	public boolean paintflag = true;

	private boolean laidOut = false;

	int nInterval = 1000;
	public Chem chem = new Chem(this, nInterval + 2);
	public boolean dflag = true, randflag = true;

	/**
	 * The entry point for the applet.
	 */
	public void init() {
		// add(new JLabel("Look ma! No Menu!"));
		Frame[] frames = Frame.getFrames();
		for (Frame frame : frames) {
			frame.setMenuBar(null);
			frame.pack();
		}
		usePageParams();
		initForm();

		chem.Mol1 = 0.2;
		chem.Molx = chem.Mol1 + Math.random() * chem.Mol1 / 2.0 - chem.Mol1 / 4.0;
		chem.Mol2 = 0.2;
		chem.Vol1 = 25.0;
		chem.Vol2 = 50.0;
		chem.IIndicator = 3;
		chem.AcidFlag1 = true;
		chem.K1 = 1e7;
		chem.AcidFlag2 = false;
		chem.K2 = 1.6;
		chem.InitTitration();
		// TODO: Add any constructor code after initForm call.
	}

	/**
	 * Reads parameters from the applet's HTML host and sets applet properties.
	 */
	private void usePageParams() {
		String defaultLabel = "Default label";
		String defaultLabel2 = "PaintMode";
		String defaultBackground = "C0C0C0";
		String defaultForeground = "000000";
		String labelValue, labelValue2;
		String backgroundValue;
		String foregroundValue;

		/**
		 * Read the <PARAM NAME="label" VALUE="some string">,
		 * <PARAM NAME="background" VALUE="rrggbb">, and
		 * <PARAM NAME="foreground" VALUE="rrggbb"> tags from the applet's HTML
		 * host.
		 */
		labelValue = getParameter(labelParam);
		labelValue2 = getParameter(labelParam2);
		backgroundValue = getParameter(backgroundParam);
		foregroundValue = getParameter(foregroundParam);

		if ((labelValue == null) || (backgroundValue == null) || (labelValue2 == null) || (foregroundValue == null)) {
			/**
			 * There was something wrong with the HTML host tags. Generate
			 * default values.
			 */
			labelValue = defaultLabel;
			labelValue2 = defaultLabel2;
			backgroundValue = defaultBackground;
			foregroundValue = defaultForeground;
		}
		if (labelValue2.equalsIgnoreCase("PaintMode")) {
			paintflag = true;
		} else {
			paintflag = false;
		}
		/**
		 * Set the applet's string label, background color, and foreground
		 * colors.
		 */
		gbgColor = stringToColor(backgroundValue);
		this.setBackground(stringToColor(backgroundValue));
		this.setForeground(stringToColor(foregroundValue));
	}

	/**
	 * Converts a string formatted as "rrggbb" to an awt.Color object
	 */
	private Color stringToColor(String paramValue) {
		int red;
		int green;
		int blue;

		red = Integer.parseInt(paramValue.substring(0, 2), 16);
		green = Integer.parseInt(paramValue.substring(2, 4), 16);
		blue = Integer.parseInt(paramValue.substring(4, 6), 16);

		return new Color(red, green, blue);
	}

	/**
	 * External interface used by design tools to show properties of an applet.
	 */
	public String[][] getParameterInfo() {
		String[][] info = { { labelParam, "String", "Label string to be displayed" },
				{ backgroundParam, "String", "Background color, format \"rrggbb\"" },
				{ foregroundParam, "String", "Foreground color, format \"rrggbb\"" }, };
		return info;
	}

	/**
	 * Intializes panels and variables for the applet and its components
	 */
	void initForm() {
		setLayout(null);
		int width = 620, height = 390;
		Insets ins = getInsets();
		Rectangle r = new Rectangle(ins.left, ins.top, width, height);
		cards = new Panel();
		cards.setLayout(new CardLayout());
		panel1 = new Introduction(this);
		panel2 = new Flask(this);
		panel3 = new Buret(this);
		panel4 = new Indicator(this);
		panel5 = new Titration(this);
		panel6 = new pHCurve(this);
		panel7 = new Results(this);
		panel8 = new Certificate(this);
		cards.setBounds(r);
		// cards.reshape(insets().left, insets().top, 620, 390);
		panel1.setBounds(r);
		panel2.setBounds(r);
		panel3.setBounds(r);
		panel4.setBounds(r);
		panel5.setBounds(r);
		panel6.setBounds(r);
		panel7.setBounds(r);
		panel8.setBounds(r);
		cards.add("P1", panel1);
		cards.add("P2", panel2);
		cards.add("P3", panel3);
		cards.add("P4", panel4);
		cards.add("P5", panel5);
		cards.add("P6", panel6);
		cards.add("P7", panel7);
		cards.add("P8", panel8);
		add(cards);
	}

	/**
	 * Start the thread for animation. The thread is used by run().  
	 */
	public void start() {
		// Start the animating thread.
		if (animatorThread == null) {
			animatorThread = new Thread(this);
		}
		animatorThread.start();
	}
	/**
	 * Stop the thread for animation.  
	 */
	public void stop() {
		// Stop the animating thread.
		animatorThread = null;
	}
	/**
	 * The animation thread calls this function.  
	 */
	public void run() {

		// Remember the starting time.
		long startTime = System.currentTimeMillis();

		// Remember which thread we are.
		Thread currentThread = Thread.currentThread();

		// Just to be nice, lower this thread's priority
		// so it can't interfere with other processing going on.
		currentThread.setPriority(Thread.MIN_PRIORITY);
		
		// This is the animation loop.
		while (currentThread == animatorThread) {
			// Advance the animation frame.
			frameNumber++;
			if (panel5 != null) {
				panel5.timer();
			}

			// Delay depending on how far we are behind.
			try {
				// Thread sleeps until the time reaches for the next frame.
				startTime += delay;
				Thread.sleep(Math.max(0, startTime - System.currentTimeMillis()));
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public void paint(Graphics g) {
		if (!laidOut) {
			laidOut = true;
		}
	}
}
