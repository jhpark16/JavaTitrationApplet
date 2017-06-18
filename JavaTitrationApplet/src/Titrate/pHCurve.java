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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class pHCurve extends Panel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Titrate applet;
	private Button but1, but2;
	private boolean laidOut = false;
	private Label textLabel, textLabel2, textLabel3, textLabel4;
	private Label textLabel5, textLabel6, textLabel7, textLabel8;
	private Label textLabel9, textLabel11, textLabel12;
	private Label textLabel13, textLabel14, textLabel15, textLabel16;
	private Label textLabel17, textLabel18;

	public Image myImage;

	public pHCurve(Titrate applet) {
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
		textLabel = new Label("A titration curve is a graph of the");
		add(textLabel);
		textLabel2 = new Label("pH versus the volume of titrating");
		add(textLabel2);
		textLabel3 = new Label("solution. Its shape depends on");
		add(textLabel3);
		textLabel4 = new Label("the strength and concentrations of");
		add(textLabel4);
		textLabel5 = new Label("the acid and base reacting. The");
		add(textLabel5);
		textLabel6 = new Label("titration curver can be used to");
		add(textLabel6);
		textLabel7 = new Label("evaluate the equivalence point");
		add(textLabel7);
		textLabel8 = new Label("and to select a suitable indicaoter");
		add(textLabel8);
		textLabel9 = new Label("for the equivalence point.");
		add(textLabel9);
		textLabel11 = new Label("Step 5. This titration curve is");
		add(textLabel11);
		textLabel12 = new Label("made from your lab data.");
		add(textLabel12);
		textLabel13 = new Label("Compare your data with this");
		add(textLabel13);
		textLabel14 = new Label("titration curve and examine");
		add(textLabel14);
		textLabel17 = new Label("if it is steep enough to show");
		add(textLabel17);
		textLabel18 = new Label("a proper equivalence point.");
		add(textLabel18);
		textLabel15 = new Label("pH");
		add(textLabel15);
		textLabel16 = new Label("mL");
		add(textLabel16);
		textLabel.setBounds(20 + ins.left, 10 + ins.top, 250, 20);
		textLabel2.setBounds(20 + ins.left, 30 + ins.top, 250, 20);
		textLabel3.setBounds(20 + ins.left, 50 + ins.top, 250, 20);
		textLabel4.setBounds(20 + ins.left, 70 + ins.top, 250, 20);
		textLabel5.setBounds(20 + ins.left, 90 + ins.top, 250, 20);
		textLabel6.setBounds(20 + ins.left, 110 + ins.top, 250, 20);
		textLabel7.setBounds(20 + ins.left, 130 + ins.top, 250, 20);
		textLabel8.setBounds(20 + ins.left, 150 + ins.top, 250, 20);
		textLabel9.setBounds(20 + ins.left, 170 + ins.top, 250, 20);
		textLabel11.setBounds(20 + ins.left, 230 + ins.top, 250, 20);
		textLabel12.setBounds(60 + ins.left, 250 + ins.top, 230, 20);
		textLabel13.setBounds(60 + ins.left, 270 + ins.top, 230, 20);
		textLabel14.setBounds(60 + ins.left, 290 + ins.top, 230, 20);
		textLabel17.setBounds(60 + ins.left, 310 + ins.top, 230, 20);
		textLabel18.setBounds(60 + ins.left, 330 + ins.top, 230, 20);
		textLabel15.setBounds(275 + ins.left, 150 + ins.top, 24, 20);
		textLabel16.setBounds(460 + ins.left, 320 + ins.top, 40, 20);
		but1.setBounds(440 + ins.left, 360 + ins.top, 80, 20);
		but2.setBounds(530 + ins.left, 360 + ins.top, 80, 20);
	}

	private int X(double x) {
		return ((int) (x * 5.5 + 325.0));
	}

	private int Y(double y) {
		return ((int) (-y * 20.0 + 300.0));
	}

	public void paint(Graphics g) {
		if (!laidOut) {
			laidOut = true;
		}
		int i;
		for (i = 0; i <= 50; i = i + 10) {
			g.setColor(new Color(0, 0, 0));
			g.drawString(String.valueOf((int) (i)), X(i) - 5, Y(0) + 15);
			g.setColor(new Color(255, 0, 0));
			g.drawLine(X(i), Y(0), X(i), Y(14));
		}
		for (i = 0; i <= 14; i++) {
			g.setColor(new Color(255, 0, 0));
			g.drawLine(X(0.0), Y(i), X(50.0), Y(i));
		}
		g.setColor(new Color(0, 0, 0));
		g.drawString("0", X(0) - 15, Y(0) + 5);
		g.drawString("4", X(0) - 15, Y(4) + 5);
		g.drawString("7", X(0) - 15, Y(7) + 5);
		g.drawString("10", X(0) - 20, Y(10) + 5);
		g.drawString("14", X(0) - 20, Y(14) + 5);
		for (i = 0; i < applet.chem.NpH - 1; i++) {
			g.drawLine(X(applet.chem.Vol2 - applet.chem.Vol[i]), Y(applet.chem.pH[i]),
					X(applet.chem.Vol2 - applet.chem.Vol[i + 1]), Y(applet.chem.pH[i + 1]));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == but2) {
			((CardLayout) applet.cards.getLayout()).next(applet.cards);
		} else if (source == but1) {
			((CardLayout) applet.cards.getLayout()).previous(applet.cards);
		}
	}
}
