package Titrate;

import java.applet.*;
import java.awt.*;
//import java.awt.image.*;

/**
 * This class reads PARAM tags from its HTML host page and sets the color and
 * label properties of the applet. Program execution begins with the init()
 * method.
 */

public class Titrate extends Applet implements Runnable {
	/**
	 * The entry point for the applet.
	 */
	int nInterval = 1000;
	public Chem chem = new Chem(this, nInterval + 2);
	public String dlgLabel, dlgLabel2;
	public boolean dflag = true, randflag = true;

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

	Label label1 = new Label();
	private final String labelParam = "label";
	public final String labelParam2 = "label2";
	private final String backgroundParam = "background";
	private final String foregroundParam = "foreground";
	public Color gbgColor;
	public boolean paintflag = true;

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
		// label1.setText(labelValue);
		// label1.setBackground(stringToColor(backgroundValue));
		// label1.setForeground(stringToColor(foregroundValue));
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

	public Panel cards;
	Introduction pan1;
	Flask pan2;
	Buret pan3;
	Indicator pan4;
	Titration pan5;
	pHCurve pan6;
	Results pan7;
	Certificate pan8;

	private boolean laidOut = false;

	/**
	 * Intializes values for the applet and its components
	 */
	void initForm() {
		this.setLayout(null);
		int width = 620, height = 390;
		Insets ins = getInsets();
		Rectangle r = new Rectangle(ins.left, ins.top, width, height);
		cards = new Panel();
		cards.setLayout(new CardLayout());
		pan1 = new Introduction(this);
		pan2 = new Flask(this);
		pan3 = new Buret(this);
		pan4 = new Indicator(this);
		pan5 = new Titration(this);
		pan6 = new pHCurve(this);
		pan7 = new Results(this);
		pan8 = new Certificate(this);
		cards.setBounds(r);
		// cards.reshape(insets().left, insets().top, 620, 390);
		pan1.setBounds(r);
		pan2.setBounds(r);
		pan3.setBounds(r);
		pan4.setBounds(r);
		pan5.setBounds(r);
		pan6.setBounds(r);
		pan7.setBounds(r);
		pan8.setBounds(r);
		// pan1.reshape(insets().left, insets().top, 620, 390);
		// pan2.reshape(insets().left, insets().top, 620, 390);
		// pan3.reshape(insets().left, insets().top, 620, 390);
		// pan4.reshape(insets().left, insets().top, 620, 390);
		// pan5.reshape(insets().left, insets().top, 620, 390);
		// pan6.reshape(insets().left, insets().top, 620, 390);
		// pan7.reshape(insets().left, insets().top, 620, 390);
		// pan8.reshape(insets().left, insets().top, 620, 390);
		cards.add("P1", pan1);
		cards.add("P2", pan2);
		cards.add("P3", pan3);
		cards.add("P4", pan4);
		cards.add("P5", pan5);
		cards.add("P6", pan6);
		cards.add("P7", pan7);
		cards.add("P8", pan8);
		add(cards);
	}

	int frameNumber = -1;
	Thread animatorThread;
	int delay = 500;

	public void start() {
		if (animatorThread == null) {
			animatorThread = new Thread(this);
		}
		animatorThread.start();
	}

	public void stop() {
		// Stop the animating thread.
		animatorThread = null;
	}

	/*
	 * public void methForApplet(){ AccessController .doPrivileged(new
	 * PrivilegedExceptionAction<Object>() { public Object run() {
	 * actualMethToBeExecuted(); return null; } }); }
	 */
	public void run() {
		// Just to be nice, lower this thread's priority
		// so it can't interfere with other processing going on.
		// Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

		// Remember the starting time.
		long startTime = System.currentTimeMillis();

		// Remember which thread we are.
		Thread currentThread = Thread.currentThread();

		// This is the animation loop.
		while (currentThread == animatorThread) {
			// Advance the animation frame.
			frameNumber++;
			if (pan5 != null) {
				pan5.timer();
			}

			// Delay depending on how far we are behind.
			try {
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
