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
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.net.URL;

/**
 * A burette and a flask setup for acid-base titration.
 * 
 * @author Jungho Park
 *
 */
class Titration extends Panel implements ActionListener, AdjustmentListener {
	/**
	 * local control variables
	 */
	private static final long serialVersionUID = 1L;
	Main applet;
	private Button but1, but2, but3, but4;
	private boolean laidOut = false;
	private Scrollbar slider;
	private Label textLabel, textLabel2, textLabel3, textLabel4;
	private Label textLabel5, textLabel6, textLabel7, textLabel8;
	private Label textLabel9, textLabel10, textLabel11, textLabel12;
	private Label textLabel13, textLabel14, textLabel15, textLabel16;
	private Label textLabel17, textLabel18, textLabel19, textLabel20;
	private Label textLabel21;
	// Image for titration setup
	public Image mFlask;

	double TotalVol;
	// Flash shape in 2D
	static double[][] Flask2 = { { 39, 74 }, { 37, 76 }, { 36, 77 }, { 35, 78 }, { 35, 78 }, { 34, 79 }, { 34, 79 },
			{ 34, 79 }, { 34, 79 }, { 34, 79 }, { 34, 79 }, { 35, 79 }, { 35, 78 }, { 35, 78 }, { 35, 78 }, { 35, 78 },
			{ 36, 78 }, { 36, 77 }, { 36, 77 }, { 36, 77 }, { 37, 77 }, { 37, 76 }, { 37, 76 }, { 38, 76 }, { 38, 75 },
			{ 38, 75 }, { 38, 75 }, { 39, 75 }, { 39, 75 }, { 39, 74 }, { 39, 74 }, { 40, 74 }, { 40, 73 }, { 40, 73 },
			{ 40, 73 }, { 41, 72 }, { 41, 72 }, { 41, 72 }, { 42, 72 }, { 42, 71 }, { 42, 71 }, { 42, 71 }, { 43, 70 },
			{ 43, 70 }, { 43, 70 }, { 43, 70 }, { 43, 70 }, { 44, 69 }, { 44, 69 }, { 44, 69 }, { 45, 68 }, { 45, 68 },
			{ 45, 68 }, { 46, 67 }, { 46, 67 }, { 46, 67 }, { 46, 67 }, { 46, 67 }, { 46, 67 }, { 47, 66 },
			{ 47, 66 } };
	Color IColor;

	/**
	 * Calculate water reaction
	 * 
	 * @param ch:
	 *            chemistry class object
	 */
	private void SolveH2O(Chem ch) {
		double x, sumhoh;
		sumhoh = ch.COH + ch.CH;
		x = (sumhoh - Math.sqrt(sumhoh * sumhoh - 4.0 * (ch.CH * ch.COH - 1e-14))) / 2.0;
		ch.COH = ch.COH - x;
		ch.CH = ch.CH - x;
		if (ch.COH > ch.CH) {
			if (ch.CH < 1e-11) {
				ch.CH = 1e-14 / ch.COH;
			}
		} else {
			if (ch.COH < 1e-11) {
				ch.COH = 1e-14 / ch.CH;
			}
		}
	}

	/**
	 * Simulate acid/base reactions and evaluate the remaining amount of H+ and
	 * OH- ions.
	 * 
	 * @param ch:
	 *            chemistry obj
	 * @param iflag:
	 *            if it is 1, solution #1 is acid
	 */
	private void SolveAcidBase(Chem ch, int iflag) {
		double C, CIon, CH_OH, KEq;
		double X, X1, X2, sumIon;
		if (iflag == 1) {
			C = ch.CAcid;
			CIon = ch.CAcidIon;
			CH_OH = ch.CH;
			KEq = ch.KA;
		} else {
			C = ch.CBase;
			CIon = ch.CBaseIon;
			CH_OH = ch.COH;
			KEq = ch.KB;
		}
		if ((C == 0) && (CIon == 0)) {
			return;
		}
		sumIon = CIon + CH_OH + KEq;
		X = sumIon * sumIon - 4.0 * (CIon * CH_OH - KEq * C);
		X = Math.sqrt(X);
		X1 = (sumIon - X) / 2.0;
		X2 = (sumIon + X) / 2.0;
		if ((C + X1) >= 0 && (CIon - X1) >= 0 && (CH_OH - X1) >= 0) {
			C = C + X1;
			CIon = CIon - X1;
			CH_OH = CH_OH - X1;
		} else if ((C + X2) >= 0 && (CIon - X2) >= 0 && (CH_OH - X2) >= 0) {
			C = C + X2;
			CIon = CIon - X2;
			CH_OH = CH_OH - X2;
		} else {
			if (X1 < 0) {
				CIon = CIon - C;
				CH_OH = CH_OH - C;
				C = 0;
			} else {
				// MsgBox ("Error in Acid Base calculation");
			}
		}
		if (iflag == 1) {
			ch.CAcid = C;
			ch.CAcidIon = CIon;
			ch.CH = CH_OH;
		} else {
			ch.CBase = C;
			ch.CBaseIon = CIon;
			ch.COH = CH_OH;
		}
	}

	/**
	 * Calculate the pH of the solution in a chemistry object
	 * 
	 * @param ch:
	 *            chemistry class object
	 * @return: calculated pH of the chemical system
	 */
	private double CalcPH(Chem ch) {
		int i;
		boolean Flag1;
		double tolconc;
		double PConcH, PConcOH, PConcAcidIon, PConcAcid;
		double PConcBaseIon, PConcBase;

		tolconc = 1e-15;
		for (i = 1; i <= 10000; i++) {
			PConcH = ch.CH;
			PConcOH = ch.COH;
			PConcAcidIon = ch.CAcidIon;
			PConcAcid = ch.CAcid;
			PConcBaseIon = ch.CBaseIon;
			PConcBase = ch.CBase;
			SolveH2O(ch);
			SolveAcidBase(ch, 1);
			SolveAcidBase(ch, 2);
			Flag1 = (Math.abs(PConcH - ch.CH) < tolconc) && (Math.abs(PConcOH - ch.COH) < tolconc)
					&& (Math.abs(PConcAcid - ch.CAcid) < tolconc) && (Math.abs(PConcAcidIon - ch.CAcidIon) < tolconc)
					&& (Math.abs(PConcBase - ch.CBase) < tolconc) && (Math.abs(PConcBaseIon - ch.CBaseIon) < tolconc);
			if (Flag1) {
				break;
			}
		}
		return (-Math.log(ch.CH) / Math.log(10.0));
	}

	/**
	 * Set the variable IColor to the indicator colour as a function of pH
	 * 
	 * @param tpH
	 */
	private void SetIColor(double tpH) {
		double tmp;
		switch (applet.chem.IIndicator) {
		case 0:
			if (tpH < 1.2)
				IColor = new Color(255, 0, 0);
			else if ((tpH >= 1.2) && (tpH < 2.8)) {
				tmp = (tpH - 1.2) / 1.6;
				IColor = new Color(255, (int) (255 * tmp), 0);
			} else if ((tpH >= 2.8) && (tpH < 8.0))
				IColor = new Color(255, 255, 0);
			else if ((tpH >= 8.0) && (tpH < 9.6)) {
				tmp = (tpH - 8.0) / 1.6;
				IColor = new Color((int) (255.0 * (1.0 - tmp)), (int) (255.0 * (1.0 - tmp)), (int) (255 * tmp));
			} else
				IColor = new Color(0, 0, 255);
			break;
		case 1:
			if (tpH < 3.2)
				IColor = new Color(255, 0, 0);
			else if ((tpH >= 3.2) && (tpH < 4.4)) {
				tmp = (tpH - 3.2) / 1.2;
				IColor = new Color(255, (int) (255 * tmp), 0);
			} else
				IColor = new Color(255, 255, 0);
			break;
		case 2:
			if (tpH < 6.0)
				IColor = new Color(255, 255, 0);
			else if ((tpH >= 6.0) && (tpH < 7.0)) {
				tmp = (tpH - 6.0) / 1.0;
				IColor = new Color((int) (255.0 * (1.0 - tmp)), (int) (255.0 * (1.0 - tmp)), (int) (255 * tmp));
			} else
				IColor = new Color(0, 0, 255);
			break;
		case 3:
			if (tpH < 8.2)
				IColor = new Color(210, 210, 210);
			else if ((tpH >= 8.2) && (tpH < 10.0)) {
				tmp = (tpH - 8.2) / 1.8;
				IColor = new Color((int) (210 + tmp * 45), (int) (210.0 * (1.0 - tmp)), (int) (210.0 * (1.0 - tmp)));
			} else
				IColor = new Color(255, 0, 0);
		}
	}

	/**
	 * Draw the liquid level of the solution #1 and #2
	 */
	private void Picture1_Paint(Graphics g) {
		int i, ix, iy;
		Color savedColor;
		savedColor = g.getColor();
		// If paintflag is true, the lines will be drawn on the image.
		// If it is false, the lines will be combined (XORed) with the image.
		if (applet.paintflag) {
			// g.setPaintMode();
			g.setColor(new Color(210, 210, 210));
		} else {
			g.setXORMode(new Color(210, 210, 210));
			g.setColor(new Color(255, 255, 255));
		}
		ix = 480;
		iy = 10;
		for (i = 200; i >= (int) (200.0 - applet.chem.CurrentVol2 * 3.5); i--) {
			g.drawLine(53 + ix, i + iy, 57 + ix, i + iy);
		}
		if (applet.paintflag) {
			// g.setPaintMode();
			g.setColor(IColor);
		} else {
			g.setXORMode(IColor);
			g.setColor(new Color(255, 255, 255));
		}
		for (i = 298; i >= (int) (298.0 - TotalVol * 0.6); i--) {
			g.drawLine((int) Flask2[298 - i][0] + ix, i + iy, (int) Flask2[298 - i][1] + ix - 1, i + iy);
		}
		g.setColor(savedColor);
	}

	/**
	 * Setup all controls and manually place them.
	 */
	public Titration(Main applet) {
		super();
		this.applet = applet;
		Insets ins = getInsets();
		// Absolute positioning
		setLayout(null);
		setFont(new Font("Helvetica", Font.PLAIN, 15));

		but1 = new Button("< Previous");
		but1.addActionListener(this);
		add(but1);
		but2 = new Button("Next >");
		but2.addActionListener(this);
		add(but2);
		but3 = new Button("Start");
		but3.addActionListener(this);
		add(but3);
		but4 = new Button("Titrate again");
		but4.addActionListener(this);
		add(but4);
		textLabel = new Label("By adding one solution to the other, you can find");
		add(textLabel);
		textLabel2 = new Label("the equivalence point of the acid-base titration.");
		add(textLabel2);
		textLabel3 = new Label("Step 4. Adjust the speed at which the solution in the burette");
		add(textLabel3);
		textLabel4 = new Label("is added and find the equivalence point. Record the volume of");
		add(textLabel4);
		textLabel5 = new Label("solution used at the equivalence point.");
		add(textLabel5);
		textLabel6 = new Label("Speed at which solution is added");
		add(textLabel6);
		textLabel7 = new Label("Slow");
		add(textLabel7);
		textLabel8 = new Label("Fast");
		add(textLabel8);
		textLabel9 = new Label("Amount of solution in the burette");
		add(textLabel9);
		textLabel10 = new Label("Initial volume");
		add(textLabel10);
		textLabel11 = new Label("50.00");
		textLabel11.setAlignment(Label.RIGHT);
		add(textLabel11);
		textLabel12 = new Label("mL");
		add(textLabel12);
		textLabel13 = new Label("Current volume");
		add(textLabel13);
		textLabel14 = new Label("50.00");
		textLabel14.setAlignment(Label.RIGHT);
		add(textLabel14);
		textLabel15 = new Label("mL");
		add(textLabel15);
		textLabel16 = new Label("Solution used");
		add(textLabel16);
		textLabel17 = new Label("0.00");
		textLabel17.setFont(new Font("Helvetica", Font.BOLD, 15));
		textLabel17.setAlignment(Label.RIGHT);
		add(textLabel17);
		textLabel18 = new Label("mL");
		add(textLabel18);
		textLabel19 = new Label("pH");
		add(textLabel19);
		textLabel20 = new Label("7");
		add(textLabel20);
		textLabel21 = new Label("");
		add(textLabel21);

		slider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 41);
		slider.addAdjustmentListener(this);
		add(slider);
		URL t1 = applet.getCodeBase();
		mFlask = applet.getImage(t1, "resources/titration.gif");

		textLabel.setBounds(30 + ins.left, 20 + ins.top, 400, 20);
		textLabel2.setBounds(30 + ins.left, 40 + ins.top, 400, 20);
		textLabel3.setBounds(30 + ins.left, 70 + ins.top, 410, 20);
		textLabel4.setBounds(70 + ins.left, 90 + ins.top, 380, 20);
		textLabel5.setBounds(70 + ins.left, 110 + ins.top, 380, 20);
		textLabel6.setBounds(45 + ins.left, 140 + ins.top, 190, 20);
		textLabel7.setBounds(75 + ins.left, 165 + ins.top, 50, 20);
		textLabel8.setBounds(205 + ins.left, 165 + ins.top, 50, 20);
		slider.setBounds(80 + ins.left, 185 + ins.top, 150, 20);
		but3.setBounds(110 + ins.left, 210 + ins.top, 80, 20);
		but4.setBounds(320 + ins.left, 200 + ins.top, 100, 20);
		textLabel9.setBounds(45 + ins.left, 250 + ins.top, 230, 20);
		textLabel10.setBounds(55 + ins.left, 280 + ins.top, 110, 20);
		textLabel11.setBounds(170 + ins.left, 280 + ins.top, 40, 20);
		textLabel12.setBounds(220 + ins.left, 280 + ins.top, 50, 20);
		textLabel13.setBounds(55 + ins.left, 300 + ins.top, 110, 20);
		textLabel14.setBounds(170 + ins.left, 300 + ins.top, 40, 20);
		textLabel15.setBounds(220 + ins.left, 300 + ins.top, 50, 20);
		textLabel16.setBounds(55 + ins.left, 320 + ins.top, 110, 20);
		textLabel17.setBounds(170 + ins.left, 320 + ins.top, 40, 20);
		textLabel18.setBounds(220 + ins.left, 320 + ins.top, 50, 20);
		textLabel19.setBounds(470 + ins.left, 315 + ins.top, 30, 20);
		textLabel20.setBounds(520 + ins.left, 315 + ins.top, 40, 20);
		textLabel21.setBounds(380 + ins.left, 315 + ins.top, 40, 20);
		but1.setBounds(440 + ins.left, 360 + ins.top, 80, 20);
		but2.setBounds(530 + ins.left, 360 + ins.top, 80, 20);
	}

	/**
	 * Draws the image of titration equipment and the liquid level
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		if (!laidOut) {
			laidOut = true;
		}
		textLabel11.setText(String.valueOf(((int) (applet.chem.Vol2 * 100.0)) / 100.0));
		// Draw boxes around the scrollbar and input box
		g.drawRect(30, 150, 255, 95);
		g.drawRect(30, 260, 255, 95);
		// Draw the image of a burette and flask
		g.drawImage(mFlask, 480, 10, this);
		// Draw the liquid level
		Picture1_Paint(g);
	}

	/**
	 * The animation thread will call this function every 500 msec.
	 */
	public void timer() {
		double tpH, PVol, DiffVol;
		int tmp;
		tmp = slider.getValue();
		if (but3.getLabel() != "Stop" && tmp > 0) {
			but3.setLabel("Stop");
		} else if (but3.getLabel() == "Stop" && tmp == 0) {
			but3.setLabel("Continue");
		}
		if ((tmp == 0 || applet.chem.CurrentVol2 == 0) && applet.chem.initflag) {
			return;
		} else {
			applet.chem.initflag = true;
			tmp = (int) (tmp * tmp);
			applet.chem.CurrentVol2 = applet.chem.CurrentVol2 - ((double) tmp) / 1000.0;
			if (applet.chem.CurrentVol2 < 0) {
				applet.chem.CurrentVol2 = 0;
				// Timer1.Enabled = False;
			}
			TotalVol = applet.chem.Vol1 + applet.chem.Vol2 - applet.chem.CurrentVol2;
			PVol = applet.chem.Vol1 + applet.chem.Vol2 - applet.chem.PreviousVol2;
			DiffVol = applet.chem.PreviousVol2 - applet.chem.CurrentVol2;
			if (applet.chem.AcidFlag1) {
				applet.chem.CBase = applet.chem.CBase * PVol / TotalVol;
				applet.chem.CBaseIon = applet.chem.CBaseIon * PVol / TotalVol + applet.chem.Mol2 * DiffVol / TotalVol;
				applet.chem.COH = applet.chem.COH * PVol / TotalVol + applet.chem.Mol2 * DiffVol / TotalVol;
				applet.chem.CAcid = applet.chem.CAcid * PVol / TotalVol;
				applet.chem.CAcidIon = applet.chem.CAcidIon * PVol / TotalVol;
				applet.chem.CH = applet.chem.CH * PVol / TotalVol;
				tpH = CalcPH(applet.chem);
			} else {
				applet.chem.CBase = applet.chem.CBase * PVol / TotalVol;
				applet.chem.CBaseIon = applet.chem.CBaseIon * PVol / TotalVol;
				applet.chem.COH = applet.chem.COH * PVol / TotalVol;
				applet.chem.CAcid = applet.chem.CAcid * PVol / TotalVol;
				applet.chem.CAcidIon = applet.chem.CAcidIon * PVol / TotalVol + applet.chem.Mol2 * DiffVol / TotalVol;
				applet.chem.CH = applet.chem.CH * PVol / TotalVol + applet.chem.Mol2 * DiffVol / TotalVol;
				tpH = CalcPH(applet.chem);
			}
			SetIColor(tpH);
			if ((applet.chem.Vol2 - applet.chem.CurrentVol2) > applet.chem.NpH * applet.chem.pHInterval) {
				applet.chem.Vol[applet.chem.NpH] = applet.chem.CurrentVol2;
				applet.chem.pH[applet.chem.NpH] = tpH;
				applet.chem.NpH = applet.chem.NpH + 1;
			} else if (applet.chem.Vol2 == applet.chem.CurrentVol2) {
				applet.chem.Vol[0] = applet.chem.CurrentVol2;
				applet.chem.pH[0] = tpH;
				applet.chem.NpH = 1;
			}
			textLabel14.setText(String.valueOf(((int) (applet.chem.CurrentVol2 * 100.0)) / 100.0));
			textLabel17.setText(String.valueOf(((int) ((applet.chem.Vol2 - applet.chem.CurrentVol2) * 100.0)) / 100.0));
			textLabel20.setText(String.valueOf(((int) (tpH * 100.0)) / 100.0));
			repaint();
			applet.chem.PreviousVol2 = applet.chem.CurrentVol2;
		}
	}

	/**
	 * Handle adjustment event for a slider
	 */
	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (source == slider) {
			// textfield1.setText(String.valueOf(slider.getValue() / 20.0));
			// applet.chem.Mol2 = (double)
			// Double.valueOf(textfield1.getText()).doubleValue();
		}
	}

	/*
	 * Process button and control clicks
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == but2) {
			slider.setValue(0);
			((CardLayout) applet.cards.getLayout()).next(applet.cards);
		} else if (source == but1) {
			slider.setValue(0);
			((CardLayout) applet.cards.getLayout()).previous(applet.cards);
		} else if (source == but3) {
			if (slider.getValue() > 0)
				slider.setValue(0);
			else
				slider.setValue(4);
		} else if (source == but4) {
			slider.setValue(0);
			but3.setLabel("Start");
			applet.chem.InitTitration();
			timer();
		}
	}
}
