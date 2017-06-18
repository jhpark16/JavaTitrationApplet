package Titrate;
class Chem
{
	Titrate applet;
	public double K1, K2, Mol1, Molx, Mol2, Vol1, Vol2, CurrentVol2;
	public boolean AcidFlag1, AcidFlag2, initflag=false;
	public int NpH, IIndicator;
	public double pH[], Vol[];
	public double PreviousVol2, pHInterval;
	public double CAcid, CAcidIon;
	public double CBase, CBaseIon;
	public double KA, KB;
	public double CH, COH;
	public double MErr;
	int nInterval;
		
	public Chem(Titrate applet, int iarray)
	{
		this.applet = applet;
		pH = new double[iarray];
		Vol = new double[iarray];
		nInterval = iarray-2;
	}
	
	public void InitTitration()
	{
	  initflag = false;
	  CurrentVol2 = Vol2;
	  PreviousVol2 = Vol2;
	  NpH = 0;
	  pHInterval = Vol2 / nInterval;
      MErr = 50;
	  if (!applet.randflag) 
	  {
		  Molx = Mol1;
	  }
	  if (AcidFlag1)
	  {
	    CAcid = 0;
	    CAcidIon = Molx;
	    CH = Molx;
	    CBase = 0;
	    CBaseIon = 0;
	    COH = 0;
		KA = K1;
		KB = K2;
	  }
	  else
	  {
	    CBase = 0;
	    CBaseIon = Molx;
	    COH = Molx;
	    CAcid = 0;
	    CAcidIon = 0;
	    CH = 0;
		KA = K2;
		KB = K1;
	  }
	}
}
