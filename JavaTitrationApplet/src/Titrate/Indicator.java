package Titrate;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

class Indicator extends Panel implements ActionListener, ItemListener {
	Titrate applet;
	private Button but1, but2;
	private boolean laidOut = false;
	private Label textLabel, textLabel2, textLabel3, textLabel4;
	private Label textLabel5, textLabel6, textLabel7, textLabel8;
	private Label textLabel9, textLabel10;
	private Checkbox cb1, cb2, cb3, cb4;
	private CheckboxGroup cbg;

	public Image myImage;

	public Indicator(Titrate applet) {
		super();
		this.applet = applet;
		setLayout(null);
		setFont(new Font("Helvetica", Font.PLAIN, 15));
		but1 = new Button("< Previous");
		but1.addActionListener(this);
		add(but1);
		but2 = new Button("Next >");
		but2.addActionListener(this);
		add(but2);
		textLabel = new Label("An indicator is a soluble dye that changes its color noticeably over a fairly");
		add(textLabel);
		textLabel2 = new Label("short range of pH. Different indicators show color changes at different pH");
		add(textLabel2);
		textLabel3 = new Label("values and it is important to determine which indicator to be used according");
		add(textLabel3);
		textLabel4 = new Label("to the expected equivalence point.");
		add(textLabel4);
		textLabel5 = new Label("Step 3. Choose an indicator which is apporopriate for your titration.");
		add(textLabel5);

		cbg = new CheckboxGroup();
		cb1 = new Checkbox("Thymol blue", cbg, false);
		cb1.setBackground(applet.gbgColor);
		cb1.addItemListener(this);
		add(cb1);
		cb2 = new Checkbox("Methyl orange", cbg, false);
		cb2.setBackground(applet.gbgColor);
		cb2.addItemListener(this);
		add(cb2);
		cb3 = new Checkbox("Bromothymol blue", cbg, false);
		cb3.setBackground(applet.gbgColor);
		cb3.addItemListener(this);
		add(cb3);
		cb4 = new Checkbox("Phenolphthalein", cbg, true);
		cb4.setBackground(applet.gbgColor);
		cb4.addItemListener(this);
		add(cb4);
		// String test2 = getClass().getResource("indicator.gif").getPath();
		// URL test =
		// getClass().getResource("/Titration/resources/indicator.gif");
		// URL url =
		// getClass().getClass().getClassLoader().getResource("resources/indicator.gif");
		// if (url != null)
		// myImage = Toolkit.getDefaultToolkit().getImage(url);
		// URL t = Titrate.class.getResource();
		// myImage = applet.getImage(t);
		// myImage =
		// applet.getImage(Titrate.class.getResource("Titration/resources/indicator.gif"));
		// myImage = applet.getImage(applet.getCodeBase(),"");
		URL t1 = applet.getCodeBase();
		myImage = applet.getImage(t1, "resources/indicator.gif");
		Insets ins = this.getInsets();

		textLabel.setBounds(20 + ins.left, 20 + ins.top, 500, 20);
		textLabel2.setBounds(20 + ins.left, 40 + ins.top, 500, 20);
		textLabel3.setBounds(20 + ins.left, 60 + ins.top, 500, 20);
		textLabel4.setBounds(20 + ins.left, 80 + ins.top, 500, 20);
		textLabel5.setBounds(20 + ins.left, 120 + ins.top, 500, 20);
		cb1.setBounds(35 + ins.left, 160 + ins.top, 150, 20);
		cb2.setBounds(35 + ins.left, 202 + ins.top, 150, 20);
		cb3.setBounds(35 + ins.left, 244 + ins.top, 150, 20);
		cb4.setBounds(35 + ins.left, 286 + ins.top, 150, 20);
		but1.setBounds(440 + ins.left, 360 + ins.top, 80, 20);
		but2.setBounds(530 + ins.left, 360 + ins.top, 80, 20);
	}

	public void paint(Graphics g) {
		if (!laidOut) {
			laidOut = true;
		}
		g.drawImage(myImage, 185, 160, this);
	}

	// public boolean imageUpdate(Image theimg,
	// int infoflags,int x, int y, int w, int h)
	// {
	// if ((infoflags & (ERROR)) != 0)
	// {
	// errored = true;
	// }
	// if ((infoflags & (WIDTH | HEIGHT)) != 0)
	// {
	// positionImages();
	// }
	// boolean done = ((infoflags & (ERROR | FRAMEBITS | ALLBITS)) != 0);
	// Repaint immediately if we are done, otherwise batch up
	// repaint requests every 100 milliseconds
	// repaint(done ? 0 : 100);
	// return !done; //If done, no further updates required.
	// }

	/*
	 * public boolean action(Event e, Object obje) { Object source = e.target;
	 * if (source == but2) { ((CardLayout)
	 * applet.cards.getLayout()).next(applet.cards); return super.action(e,
	 * obje); } else if (source == but1) { ((CardLayout)
	 * applet.cards.getLayout()).previous(applet.cards); return super.action(e,
	 * obje); } else if (source == cb1) { applet.chem.IIndicator = 0; } else if
	 * (source == cb2) { applet.chem.IIndicator = 1; } else if (source == cb3) {
	 * applet.chem.IIndicator = 2; } else if (source == cb4) {
	 * applet.chem.IIndicator = 3; } else { return super.action(e, obje); }
	 * applet.chem.InitTitration(); return super.action(e, obje); }
	 */

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == cb1) {
			applet.chem.IIndicator = 0;
		} else if (source == cb2) {
			applet.chem.IIndicator = 1;
		} else if (source == cb3) {
			applet.chem.IIndicator = 2;
		} else if (source == cb4) {
			applet.chem.IIndicator = 3;
		}
		applet.chem.InitTitration();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == but2) {
			((CardLayout) applet.cards.getLayout()).next(applet.cards);
		} else if (source == but1) {
			((CardLayout) applet.cards.getLayout()).previous(applet.cards);
		}
		applet.chem.InitTitration();

	}
}
