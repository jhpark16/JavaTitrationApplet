package Titrate;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgW extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean laidOut = false;
	Panel cPanel;
	Label label1, label2;
	Button but1;
	Titrate obj1;

	public DlgW(Titrate obj1) {
		this.obj1 = obj1;
		setLayout(null);
		Insets ins = this.getInsets();
		label1 = new Label(obj1.dlgLabel);
		label2 = new Label(obj1.dlgLabel2);
		but1 = new Button("OK");
		but1.addActionListener(this);
		cPanel = new Panel();
		label1.setBounds(ins.left + 20, ins.top + 10, 280, 20);
		label2.setBounds(ins.left + 20, ins.top + 30, 280, 20);
		cPanel.setLayout(null);
		cPanel.add(label1);
		cPanel.add(label2);
		cPanel.add(but1);
		add(cPanel);
	}

	public void paint(Graphics g) {
		if (!laidOut) {
			Insets ins = this.getInsets();
			Rectangle rect = new Rectangle(g.getClipBounds().x, g.getClipBounds().y, g.getClipBounds().width,
					g.getClipBounds().height);
			but1.setBounds(ins.left + rect.width / 2 - 20, ins.top + 70, 40, 20);
			cPanel.setBounds(ins.left, ins.top, rect.width, rect.height);
			laidOut = true;
		}
	}

	/*
	 * public boolean action(Event e, Object obje) { Object source = e.target;
	 * if (source == but1) { dispose(); obj1.dflag =true; } return
	 * super.action(e,obje); }
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == but1) {
			dispose();
			obj1.dflag = true;
		}
	}
}
