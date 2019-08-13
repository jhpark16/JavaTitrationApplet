package Titrate;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The calculation of the concentration of unknown solution (#1)
 * 
 * @author Jungho Park
 *
 */
class Results extends Panel implements ActionListener {
	/**
	 * control variables
	 */
	private static final long serialVersionUID = 1L;
	Main applet;
	private Button but1, but2, but3;
	private boolean laidOut = false;
	private Label textLabel, textLabel2, textLabel3, textLabel4;
	private Label textLabel5, textLabel6, textLabel7, textLabel8;
	private Label textLabel9, textLabel10, textLabel11, textLabel12;
	private Label textLabel13, textLabel14, textLabel15, textLabel16;
	private Label textLabel17, textLabel18, textLabel19, textLabel20;
	public Label textLabel21, textLabel22, textLabel23, textLabel24;
	public Label textLabel25, textLabel26, textLabel27, textLabel28;
	private Label textLabel29, textLabel30, textLabel31, textLabel32;
	private Label textLabel33, textLabel34, textLabel35;

	private TextField textfield1;

	public Image myImage;

	/**
	 * manually places controls
	 * 
	 * @param applet
	 */
	public Results(Main applet) {
		super();
		this.applet = applet;
		setLayout(null);
		Insets ins = getInsets();
		setFont(new Font("Helvetica", Font.PLAIN, 15));
		but1 = new Button("< Previous");
		but1.addActionListener(this);
		add(but1);
		but2 = new Button("Next >");
		but2.addActionListener(this);
		add(but2);
		but3 = new Button("Calculate");
		but3.addActionListener(this);
		add(but3);
		textLabel = new Label(
				"Using the volume at equivalence point obtained from performing titrations, scientists can");
		add(textLabel);
		textLabel2 = new Label("calculate the concentration of the unknown solution. The equation required to");
		add(textLabel2);
		textLabel3 = new Label("do so is shown below.");
		add(textLabel3);
		textLabel4 = new Label("C");
		add(textLabel4);
		textLabel5 = new Label("V");
		add(textLabel5);
		textLabel6 = new Label("=");
		add(textLabel6);
		textLabel7 = new Label("C");
		add(textLabel7);
		textLabel8 = new Label("V");
		add(textLabel8);
		textLabel9 = new Label("acid");
		add(textLabel9);
		textLabel10 = new Label("acid");
		add(textLabel10);
		textLabel11 = new Label("base");
		add(textLabel11);
		textLabel12 = new Label("base");
		add(textLabel12);
		textLabel33 = new Label("Here, C is the concentration");
		add(textLabel33);
		textLabel34 = new Label("and V is the volume.");
		add(textLabel34);
		textLabel13 = new Label(
				"Step 6. Enter the volume used in the text box and click \"Calculate\"");
		add(textLabel13);
		textLabel14 = new Label("to get the concentration of the acid-base solution. Then, compare your");
		add(textLabel14);
		textLabel35 = new Label("answer with the real value.");
		add(textLabel35);
		textLabel15 = new Label("Your data");
		add(textLabel15);
		textLabel16 = new Label("Real value");
		add(textLabel16);
		textLabel17 = new Label("Error %");
		add(textLabel17);
		textLabel18 = new Label("Concentration of solution 1");
		add(textLabel18);
		textLabel19 = new Label(" ");
		textLabel19.setAlignment(Label.RIGHT);
		add(textLabel19);
		textLabel20 = new Label("M");
		add(textLabel20);
		textLabel21 = new Label(" ");
		textLabel21.setAlignment(Label.RIGHT);
		add(textLabel21);
		textLabel22 = new Label(" ");
		textLabel22.setAlignment(Label.RIGHT);
		add(textLabel22);
		textLabel23 = new Label("Volume of solution 1");
		add(textLabel23);
		textLabel24 = new Label(" ");
		textLabel24.setAlignment(Label.RIGHT);
		add(textLabel24);
		textLabel25 = new Label("mL");
		add(textLabel25);
		textLabel26 = new Label("Concentration of solution 2");
		add(textLabel26);
		textLabel27 = new Label(" ");
		textLabel27.setAlignment(Label.RIGHT);
		add(textLabel27);
		textLabel28 = new Label("M");
		add(textLabel28);
		textLabel29 = new Label("Volume of solution 2");
		add(textLabel29);
		textfield1 = new TextField("", 5);
		textfield1.setBackground(new Color(255, 255, 255));
		add(textfield1);

		textLabel30 = new Label("mL");
		add(textLabel30);
		textLabel31 = new Label(" ");
		textLabel31.setAlignment(Label.RIGHT);
		add(textLabel31);
		textLabel32 = new Label(" ");
		textLabel32.setAlignment(Label.RIGHT);
		add(textLabel32);

		textLabel.setBounds(20 + ins.left, 10 + ins.top, 580, 20);
		textLabel2.setBounds(20 + ins.left, 30 + ins.top, 580, 20);
		textLabel3.setBounds(20 + ins.left, 50 + ins.top, 580, 20);
		textLabel4.setBounds(130 + ins.left, 80 + ins.top, 15, 20);
		textLabel5.setBounds(180 + ins.left, 80 + ins.top, 15, 20);
		textLabel6.setBounds(230 + ins.left, 80 + ins.top, 15, 20);
		textLabel7.setBounds(250 + ins.left, 80 + ins.top, 15, 20);
		textLabel8.setBounds(310 + ins.left, 80 + ins.top, 15, 20);
		textLabel9.setBounds(145 + ins.left, 90 + ins.top, 36, 20);
		textLabel10.setBounds(195 + ins.left, 90 + ins.top, 36, 20);
		textLabel11.setBounds(265 + ins.left, 90 + ins.top, 36, 20);
		textLabel12.setBounds(325 + ins.left, 90 + ins.top, 36, 20);
		textLabel33.setBounds(400 + ins.left, 70 + ins.top, 200, 20);
		textLabel34.setBounds(400 + ins.left, 90 + ins.top, 200, 20);

		textLabel13.setBounds(20 + ins.left, 120 + ins.top, 580, 20);
		textLabel14.setBounds(60 + ins.left, 140 + ins.top, 560, 20);
		textLabel35.setBounds(60 + ins.left, 160 + ins.top, 560, 20);

		textLabel15.setBounds(320 + ins.left, 190 + ins.top, 70, 20);
		textLabel16.setBounds(420 + ins.left, 190 + ins.top, 70, 20);
		textLabel17.setBounds(525 + ins.left, 190 + ins.top, 70, 20);

		textLabel18.setBounds(20 + ins.left, 220 + ins.top, 300, 20);
		textLabel19.setBounds(320 + ins.left, 220 + ins.top, 40, 20);
		textLabel20.setBounds(380 + ins.left, 220 + ins.top, 25, 20);
		textLabel21.setBounds(425 + ins.left, 220 + ins.top, 50, 20);
		textLabel22.setBounds(520 + ins.left, 220 + ins.top, 45, 20);

		textLabel23.setBounds(20 + ins.left, 250 + ins.top, 300, 20);
		textLabel24.setBounds(320 + ins.left, 250 + ins.top, 40, 20);
		textLabel25.setBounds(380 + ins.left, 250 + ins.top, 50, 20);

		textLabel26.setBounds(20 + ins.left, 300 + ins.top, 300, 20);
		textLabel27.setBounds(320 + ins.left, 300 + ins.top, 40, 20);
		textLabel28.setBounds(380 + ins.left, 300 + ins.top, 50, 20);

		textLabel29.setBounds(20 + ins.left, 330 + ins.top, 290, 20);
		textLabel30.setBounds(380 + ins.left, 330 + ins.top, 25, 20);
		textLabel31.setBounds(425 + ins.left, 330 + ins.top, 50, 20);
		textLabel32.setBounds(520 + ins.left, 330 + ins.top, 45, 20);

		textfield1.setBounds(310 + ins.left, 330 + ins.top, 55, 20);
		but1.setBounds(440 + ins.left, 360 + ins.top, 80, 20);
		but2.setBounds(530 + ins.left, 360 + ins.top, 80, 20);
		but3.setBounds(420 + ins.left, 275 + ins.top, 80, 20);
	}

	/**
	 * paint sets the locations of some controls
	 */
	public void paint(Graphics g) {
		if (!laidOut) {
			laidOut = true;
		}
		textLabel18.setText("Concentration of " + applet.panel2.cbg.getSelectedCheckbox().getLabel());
		textLabel23.setText("Volume of " + applet.panel2.cbg.getSelectedCheckbox().getLabel());
		textLabel26.setText("Concentration of " + applet.panel3.cbg.getSelectedCheckbox().getLabel());
		textLabel29.setText("Volume of " + applet.panel3.cbg.getSelectedCheckbox().getLabel());

		textLabel24.setText(String.valueOf(((int) (applet.chem.Vol1 * 100.0)) / 100.0));
		textLabel27.setText(String.valueOf(((int) (applet.chem.Mol2 * 100.0)) / 100.0));
		g.drawRect(110, 75, 260, 40);
	}

	/**
	 * an event handler for buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == but2) {
			((CardLayout) applet.cards.getLayout()).next(applet.cards);
		} else if (source == but1) {
			((CardLayout) applet.cards.getLayout()).previous(applet.cards);
		} else if (source == but3 || source == textfield1) {
			double TVal, TVol, tmp;
			tmp = Double.valueOf(textfield1.getText()).doubleValue();
			if (tmp > 0) {
				TVal = applet.chem.Mol2 * tmp / applet.chem.Vol1;
				TVol = applet.chem.Molx * applet.chem.Vol1 / applet.chem.Mol2;
				textLabel19.setText(String.valueOf(((int) (TVal * 1000.0)) / 1000.0));
				textLabel21.setText(String.valueOf(((int) (applet.chem.Molx * 1000.0)) / 1000.0));
				applet.chem.MErr = Math.abs(TVal - applet.chem.Molx) / applet.chem.Molx * 100.0;
				textLabel22.setText(String.valueOf(((int) (applet.chem.MErr * 10.0)) / 10.0));
				textLabel31.setText(String.valueOf(((int) (TVol * 100.0)) / 100.0));
				textLabel32.setText(String.valueOf(((int) (Math.abs(TVol - tmp) / TVol * 10000.0)) / 100.0));
			} else {
				// Beep
			}
		}
	}
}
