package Titrate;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 
 * @author Jungho Park
 *
 */
class Solution2 extends Panel implements ActionListener, AdjustmentListener, ItemListener, FocusListener {
	/**
	 * version UID to verify the object data during deserialization
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * control variables
	 */
	Main applet;
	private Button but1, but2;
	private boolean laidOut = false;
	private Scrollbar slider, slider2;
	private Label textLabel, textLabel2, textLabel3, textLabel4;
	private Label textLabel5, textLabel6, textLabel7, textLabel8;
	private Label textLabel9, textLabel10, textLabel11;
	private Label textLabel13, textLabel14, textLabel15, textLabel16;
	public Checkbox cb1, cb2, cb3;
	public Checkbox cb4, cb5, cb6;
	public CheckboxGroup cbg;
	private TextField textfield1, textfield2;

	public Image myImage;

	/**
	 * Solution #2 in the burette manually places controls
	 * 
	 * @param applet
	 */
	public Solution2(Main applet) {
		super();
		this.applet = applet;
		setLayout(null);
		Insets ins = this.getInsets();
		setFont(new Font("Helvetica", Font.PLAIN, 15));
		but1 = new Button("< Previous");
		but1.addActionListener(this);
		add(but1);
		but2 = new Button("Next >");
		but2.addActionListener(this);
		add(but2);
		textLabel = new Label("A burette is used to determinate the equivalence point in acid-base titrations.");
		add(textLabel);
		textLabel2 = new Label("You can accurately measure the volume of solution used using a burette.");
		add(textLabel2);
		textLabel3 = new Label("The solution with a known concentration should be placed in the burette.");
		add(textLabel3);
		textLabel4 = new Label("Step 2. Choose a solution you want to place in the burette and choose what");
		add(textLabel4);
		textLabel8 = new Label("the concentration and volume of the solution will be.");
		add(textLabel8);
		textLabel5 = new Label(" Acid solutions ");
		add(textLabel5);
		textLabel6 = new Label(" Base solutions ");
		add(textLabel6);
		textLabel7 = new Label("Concentration of the solution");
		add(textLabel7);
		textLabel9 = new Label("M (mol/L)");
		add(textLabel9);
		textLabel10 = new Label("Volume of the solution");
		add(textLabel10);
		textLabel11 = new Label("mL");
		add(textLabel11);

		textLabel13 = new Label(" Strong acid ");
		add(textLabel13);
		textLabel14 = new Label(" Strong base ");
		add(textLabel14);
		textLabel15 = new Label(" Weak acid ");
		add(textLabel15);
		textLabel16 = new Label(" Weak base ");
		add(textLabel16);

		cbg = new CheckboxGroup();
		cb1 = new Checkbox("Hydrochloric acid (HCl)", cbg, false);
		cb1.setBackground(applet.gbgColor);
		cb1.addItemListener(this);
		add(cb1);
		cb2 = new Checkbox("Nitric acid (HNO3)", cbg, false);
		cb2.setBackground(applet.gbgColor);
		cb2.addItemListener(this);
		add(cb2);
		cb3 = new Checkbox("Acetic acid (CH3COOH)", cbg, false);
		cb3.setBackground(applet.gbgColor);
		cb3.addItemListener(this);
		add(cb3);
		cb4 = new Checkbox("Sodium hydroxide (NaOH)", cbg, true);
		cb4.setBackground(applet.gbgColor);
		cb4.addItemListener(this);
		add(cb4);
		cb5 = new Checkbox("Potassium hydroxide (KOH)", cbg, false);
		cb5.setBackground(applet.gbgColor);
		cb5.addItemListener(this);
		add(cb5);
		cb6 = new Checkbox("Ammonia (NH3)", cbg, false);
		cb6.setBackground(applet.gbgColor);
		cb6.addItemListener(this);
		add(cb6);
		slider = new Scrollbar(Scrollbar.HORIZONTAL, 4, 1, 1, 21);
		slider.addAdjustmentListener(this);
		add(slider);
		slider2 = new Scrollbar(Scrollbar.HORIZONTAL, 50, 1, 5, 51);
		slider2.addAdjustmentListener(this);
		add(slider2);
		textfield1 = new TextField("0.20", 4);
		textfield1.setBackground(new Color(255, 255, 255));
		textfield1.addActionListener(this);
		add(textfield1);
		textfield2 = new TextField("50.00", 5);
		textfield2.setBackground(new Color(255, 255, 255));
		textfield2.addActionListener(this);
		add(textfield2);

		textLabel.setBounds(20 + ins.left, 10 + ins.top, 580, 20);
		textLabel2.setBounds(20 + ins.left, 30 + ins.top, 580, 20);
		textLabel3.setBounds(20 + ins.left, 50 + ins.top, 580, 20);
		textLabel4.setBounds(20 + ins.left, 75 + ins.top, 580, 20);
		textLabel8.setBounds(60 + ins.left, 95 + ins.top, 560, 20);
		textLabel5.setBounds(30 + ins.left, 120 + ins.top, 110, 20);
		textLabel6.setBounds(330 + ins.left, 120 + ins.top, 110, 20);
		textLabel13.setBounds(30 + ins.left, 145 + ins.top, 110, 20);
		textLabel14.setBounds(330 + ins.left, 145 + ins.top, 110, 20);
		textLabel15.setBounds(30 + ins.left, 215 + ins.top, 110, 20);
		textLabel16.setBounds(330 + ins.left, 215 + ins.top, 110, 20);
		cb1.setBounds(50 + ins.left, 165 + ins.top, 210, 20);
		cb2.setBounds(50 + ins.left, 185 + ins.top, 210, 20);
		cb3.setBounds(50 + ins.left, 235 + ins.top, 210, 20);
		cb4.setBounds(350 + ins.left, 165 + ins.top, 210, 20);
		cb5.setBounds(350 + ins.left, 185 + ins.top, 210, 20);
		cb6.setBounds(350 + ins.left, 235 + ins.top, 210, 20);
		textLabel7.setBounds(20 + ins.left, 290 + ins.top, 240, 20);
		textfield1.setBounds(260 + ins.left, 290 + ins.top, 50, 20);
		slider.setBounds(310 + ins.left, 290 + ins.top, 70, 20);
		textLabel9.setBounds(390 + ins.left, 290 + ins.top, 70, 20);
		textLabel10.setBounds(20 + ins.left, 330 + ins.top, 240, 20);
		textfield2.setBounds(260 + ins.left, 330 + ins.top, 50, 20);
		slider2.setBounds(310 + ins.left, 330 + ins.top, 70, 20);
		textLabel11.setBounds(390 + ins.left, 330 + ins.top, 40, 20);
		but1.setBounds(440 + ins.left, 360 + ins.top, 80, 20);
		but2.setBounds(530 + ins.left, 360 + ins.top, 80, 20);

		// myImage = applet.getImage(applet.getCodeBase(),"bigimg.gif");
	}

	/**
	 * draw rectangles to separate the area
	 */
	public void paint(Graphics g) {
		if (!laidOut) {

			laidOut = true;
		}
		g.drawRect(20, 130, 260, 140);
		g.drawRect(320, 130, 260, 140);
		// g.drawImage(myImage, 400, 20, this);
	}

	/**
	 * an event handler for buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		double f;
		if (source == but2) {
			if (applet.chem.AcidFlag1 != applet.chem.AcidFlag2) {
				((CardLayout) applet.cards.getLayout()).next(applet.cards);
			} else {
				if (applet.dflag) {
					applet.dflag = false;
					applet.dlgLabel = new String("You should select one acid and");
					applet.dlgLabel2 = new String("one base for titration.");
					DlgW dlg = new DlgW(applet);
					dlg.setSize(300, 200);
					dlg.setVisible(true);
				}
			}
		} else if (source == but1) {
			((CardLayout) applet.cards.getLayout()).previous(applet.cards);
		} else if (source == textfield1) {
			try {
				f = (double) Double.valueOf(textfield1.getText()).doubleValue();
				slider.setValue((int) (f * 20));
			} catch (java.lang.NumberFormatException e2) {
				f = 0.2 / 20.0;
			}
			applet.chem.Mol2 = f;
		} else if (source == textfield2) {
			try {
				f = (double) Double.valueOf(textfield2.getText()).doubleValue();
				slider2.setValue((int) f);
			} catch (java.lang.NumberFormatException e2) {
				f = 50.0;
			}
			applet.chem.Vol2 = f;
		}
		applet.chem.InitTitration();
	}

	/**
	 * an event handler for sliders
	 */
	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == slider) {
			textfield1.setText(String.valueOf(slider.getValue() / 20.0));
			applet.chem.Mol2 = (double) Double.valueOf(textfield1.getText()).doubleValue();
		} else if (source == slider2) {
			textfield2.setText(String.valueOf(slider2.getValue() / 1.0));
			applet.chem.Vol2 = (double) Double.valueOf(textfield2.getText()).doubleValue();
		}
		applet.chem.InitTitration();
	}

	/**
	 * focus gained
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * an event handler when focus is lost from controls updates the variables
	 * attached to the controls
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		double f;
		if (source == textfield1) {
			try {
				f = (double) Double.valueOf(textfield1.getText()).doubleValue();
				slider.setValue((int) (f * 20));
			} catch (java.lang.NumberFormatException e2) {
				f = 0.2 / 20.0;
			}
			applet.chem.Mol2 = f;
		} else if (source == textfield2) {
			try {
				f = (double) Double.valueOf(textfield2.getText()).doubleValue();
				slider2.setValue((int) f);
			} catch (java.lang.NumberFormatException e2) {
				f = 50.0;
			}
			applet.chem.Vol2 = f;
		} else
			applet.chem.InitTitration();
	}

	/**
	 * an event handler for combo boxes
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == cb1) {
			applet.chem.K2 = 1e7;
			applet.chem.AcidFlag2 = true;
		} else if (source == cb2) {
			applet.chem.K2 = 20.0;
			applet.chem.AcidFlag2 = true;
		} else if (source == cb3) {
			applet.chem.K2 = 1.76e-5;
			applet.chem.AcidFlag2 = true;
		} else if (source == cb4) {
			applet.chem.K2 = 1.6;
			applet.chem.AcidFlag2 = false;
		} else if (source == cb5) {
			applet.chem.K2 = 3.15;
			applet.chem.AcidFlag2 = false;
		} else if (source == cb6) {
			applet.chem.K2 = 1.8e-5;
			applet.chem.AcidFlag2 = false;
		}
		applet.chem.InitTitration();
	}
}
