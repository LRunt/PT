package model;
/**
 * Instance tridy Kun reprezentuje kone, ktery ma byt prevezen do Parize
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.2 (28-10-2021)
 */
public class Kun{
	
	/** Uchovava zaznam kolik koni existuje*/
	private static int pocetKoni = 0;
	/** Cislo kone (ID)*/
	private final int PORADI = ++pocetKoni;
	/** Souradnice kone x*/
	private double x;
	/** Souradnice kone y*/
	private double y;
	/** Hmotnost kone s vybavenim */
	private int m;
	/** Doba nalozeni kone */
	private int n;
	/** Kun je prevezen */
	private boolean stav;
	/** Uchovava udaje o navstivenych mistech a casu transportu*/
	public String statistika = String.format("Kun %d\nCas;X;Y\n", PORADI);
	/** Cas kdy se kun dostal do parize */
	private double cas;

	/**
	 * Konstruktor kone
	 * @param x - x-ova souradnice
	 * @param y - y-ova souradnice
	 * @param m - hmotnost kone
	 * @param n - doba nalozeni
	 */
	public Kun(double x, double y, int m, int n) {
		setX(x);
		setY(y);
		setM(m);
		setN(n);
		setStav(false);
	}
	
	/**
	 * Vrati X-ovou souradnici kone
	 * @return X-ova souradnice kone
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Nastaveni X-ove souradnice kone
	 * @param x nova X-ova souradnice kone
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Vrati Y-ovou souradnici kone
	 * @return Y-ova souradnice kone
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Nastaveni Y-ove souradnice kone
	 * @param y nova Y-ova souradnice kone
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Vrati hmotnost kone
	 * @return hmotnost kone
	 */
	public int getM() {
		return m;
	}
	
	/**
	 * Nastaveni hmotnosti kone
	 * @param m nova hmotnost kone
	 */
	public void setM(int m) {
		if(m < 0) {
			System.out.println("Hmotnost kone nemuze byt zaporna!");
		} else {
			this.m = m;
		}
	}
	
	/**
	 * Vrati dobu nalozeni kone
	 * @return doba nalozeni kone
	 */
	public int getN() {
		return n;
	}
	
	/**
	 * Nastaveni doby nalozeni kone
	 * @param n nova doba nalozeni kone
	 */
	public void setN(int n) {
		if(n < 0) {
			System.out.println("Doba nalozeni kone nemuze byt zaporna!");
		}else {
			this.n = n;
		}
	}
	
	/**
	 * Vrati poradi kone
	 * @return poradi kone
	 */
	public int getPoradi() {
		return PORADI;
	}
	
	/**
	 * Nastaveni postu koni
	 * @param pocet pocet koni
	 */
	public static void setPocetKoni(int pocet) {
		if(pocet < 0) {
			System.out.println("Pocet koni nemuze byt zaporny!");
		} else {
			pocetKoni = pocet;
		}
	}
	
	/**
	 * Vrati stav kone
	 * @return true - prevezen, false - neprevezen
	 */
	public boolean isStav() {
		return stav;
	}

	/**
	 * Nastavi stav kone
	 * @param stav novy stav kone
	 */
	public void setStav(boolean stav) {
		this.stav = stav;
	}
	
	/**
	 * Metoda vraci cas, kdy byl kun vylozen v Parizi
	 * @return cas, kdy se kun dostal do parize
	 */
	public double getCas() {
		return cas;
	}

	/**
	 * Nastaveni casu, kdy se kun dostal do Parize
	 * @param cas novy cas
	 */
	public void setCas(double cas) {
		if (cas < 0) {
			System.out.println("Cas nesmi byt zaporny!");
		}else {
			this.cas = cas;
		}
	}
	
	/**
	 * Textova reprezentace instance kone
	 * @return textova reprezentace instance kone
	 */
	public String toString() {
		return String.format("Kun %d: x = %.02f, y = %.02f, m = %d, n = %d",PORADI, x, y, m, n);
	}
}
