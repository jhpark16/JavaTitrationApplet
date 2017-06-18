package Titrate;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Introduction extends Panel implements ActionListener {
	Titrate applet;
	private Button but2;
	private boolean laidOut = false;
	private Label textLabel, textLabel2, textLabel3, textLabel4;
	private Label textLabel5, textLabel6, textLabel7, textLabel8;
	public TextField textfield1;

	public Introduction(Titrate applet) {
		super();
		this.applet = applet;
		setLayout(null);
		Insets ins = getInsets();
		setFont(new Font("Helvetica", Font.PLAIN, 15));
		but2 = new Button("Next >");
		but2.addActionListener(this);
		add(but2);
		textLabel = new Label("Acid-Base Titration Lab");
		textLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
		add(textLabel);
		textLabel2 = new Label("Welcome to the virtual chemistry lab.  You are going to explore");
		add(textLabel2);
		textLabel3 = new Label("what would happen when acids and bases get mixed and decide");
		add(textLabel3);
		textLabel4 = new Label("which point is equivalence point of acid-base titration.  Please");
		add(textLabel4);
		textLabel5 = new Label("read and follow the instruction given carefully.  And enjoy your");
		add(textLabel5);
		textLabel6 = new Label("virtual experiment.");
		add(textLabel6);
		textLabel7 = new Label("Your name");
		add(textLabel7);
		textfield1 = new TextField("", 30);
		textfield1.setBackground(new Color(255, 255, 255));
		add(textfield1);
		textLabel.setBounds(insets().left + 180, insets().top + 70, 400, 30);
		// textLabel.reshape(insets().left+180, insets().top+70, 400, 30);
		textLabel2.setBounds(insets().left + 80, insets().top + 130, 500, 20);
		// textLabel2.reshape(insets().left+80, insets().top+130, 500, 20);
		textLabel3.setBounds(insets().left + 80, insets().top + 150, 500, 20);
		// textLabel3.reshape(insets().left+80, insets().top+150, 500, 20);
		textLabel4.setBounds(insets().left + 80, insets().top + 170, 500, 20);
		// textLabel4.reshape(insets().left+80, insets().top+170, 500, 20);
		textLabel5.setBounds(insets().left + 80, insets().top + 190, 500, 20);
		// textLabel5.reshape(insets().left+80, insets().top+190, 500, 20);
		textLabel6.setBounds(insets().left + 80, insets().top + 210, 300, 20);
		// textLabel6.reshape(insets().left+80, insets().top+210, 300, 20);
		textLabel7.setBounds(insets().left + 170, insets().top + 260, 100, 20);
		// textLabel7.reshape(insets().left+170, insets().top+260, 100, 20);
		textfield1.setBounds(insets().left + 270, insets().top + 260, 160, 20);
		// textfield1.reshape(insets().left+270, insets().top+260, 160, 20);
		but2.setBounds(530 + insets().left, 360 + insets().top, 80, 20);
		// but2.reshape(530 + insets().left, 360 + insets().top, 80, 20);
	}

	public void paint(Graphics g) {
		if (!laidOut) {
			laidOut = true;
		}
	}
/*
	public boolean action(Event e, Object obje) {
		Object source = e.target;
		if (source == but2) {
			if (textfield1.getText().trim().length() != 0) {
				((CardLayout) applet.cards.getLayout()).next(applet.cards);
			} else {
				if (applet.dflag) {
					applet.dflag = false;
					applet.dlgLabel = new String("Please enter your name.");
					applet.dlgLabel2 = new String("");
					DlgW dlg = new DlgW(applet);
					dlg.setSize(300, 200);
					dlg.setVisible(true);
				}
			}
		}
		return super.action(e, obje);
	}
*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == but2) {
			if (textfield1.getText().trim().length() != 0) {
				((CardLayout) applet.cards.getLayout()).next(applet.cards);
			} else {
				if (applet.dflag) {
					applet.dflag = false;
					applet.dlgLabel = new String("Please enter your name.");
					applet.dlgLabel2 = new String("");
					DlgW dlg = new DlgW(applet);
					dlg.setSize(300, 200);
					dlg.setVisible(true);
				}
			}
		}
	}
}
