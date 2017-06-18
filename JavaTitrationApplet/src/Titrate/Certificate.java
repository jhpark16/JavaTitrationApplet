package Titrate;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Certificate extends Panel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Titrate applet;
	private Button but1;
	private boolean laidOut = false;
	private Label textLabel, textLabel2, textLabel3, textLabel4;
	private Label textLabel5, textLabel6;

	public Image myImage;

	public Certificate(Titrate applet) {
		super();
		this.applet = applet;
		setLayout(null);
		Insets ins = this.getInsets();
		setFont(new Font("Helvetica", Font.PLAIN, 15));
		but1 = new Button("< Previous");
		but1.addActionListener(this);
		add(but1);
		textLabel = new Label("Certificate");
		textLabel.setFont(new Font("Helvetica", Font.PLAIN, 30));
		add(textLabel);
		textLabel2 = new Label("Name :");
		add(textLabel2);
		textLabel3 = new Label("");
		add(textLabel3);
		textLabel4 = new Label("Congratulation!!!");
		add(textLabel4);
		textLabel5 = new Label("");
		add(textLabel5);
		textLabel6 = new Label("Now you are one step closer to becoming a chemist.");
		add(textLabel6);
		// myImage = applet.getImage(applet.getCodeBase(),"certificate.gif");

		textLabel.setBounds(230 + ins.left, 50 + ins.top, 400, 40);

		textLabel2.setBounds(400 + ins.left, 120 + ins.top, 50, 20);
		textLabel3.setBounds(450 + ins.left, 120 + ins.top, 160, 20);

		textLabel4.setBounds(90 + ins.left, 170 + ins.top, 500, 20);
		textLabel5.setBounds(90 + ins.left, 220 + ins.top, 500, 20);
		textLabel6.setBounds(90 + ins.left, 270 + ins.top, 500, 20);
		but1.setBounds(440 + ins.left, 360 + ins.top, 80, 20);
	}

	public void paint(Graphics g) {
		if (!laidOut) {

			laidOut = true;
		}
		double precision1;
		precision1 = applet.chem.MErr;
		if (precision1 < 1.0) {
			textLabel5.setText("You've done an excellent job on the virtual acid-base titration lab.");
		} else if (precision1 < 5.0) {
			textLabel5.setText("You've done a good job on the virtual acid-base titration lab.");
		} else {
			textLabel5.setText("You finished the virtual acid-base titration lab.");
		}
		textLabel3.setText(applet.panel1.textfield1.getText());
		// g.drawImage(myImage, 400, 20, this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == but1) {
			((CardLayout) applet.cards.getLayout()).previous(applet.cards);
		}

	}
}
