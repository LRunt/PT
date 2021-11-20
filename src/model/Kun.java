package model;
/**
 * Instance tridy Kun reprezentuje kone, ktery ma byt prevezen do Parize
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.2 (28-10-2021)
 */
public class Kun{
	
	/** Uchovava zaznam kolik koni existuje*/
	public static int pocetKoni = 0;
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
	public boolean prevezen;
	/** Uchovava udaje o navstivenych mistech a casu transportu*/
	public String statistika = String.format("Kun %d\nCas;X;Y\n", PORADI);
	/** Cas kdy se kun dostal do parize */
	public double cas;

	/**
	 * Konstruktor kone
	 * @param x - x-ova souradnice
	 * @param y - y-ova souradnice
	 * @param m - hmotnost kone
	 * @param n - doba nalozeni
	 */
	public Kun(double x, double y, int m, int n) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.n = n;
		prevezen = false;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public int getM() {
		return m;
	}
	
	public void setM(int m) {
		this.m = m;
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public int getPoradi() {
		return PORADI;
	}
	
	/**
	 * Textova reprezentace instance kone
	 * @return textova reprezentace instance kone
	 */
	public String toString() {
		return String.format("Kun %d: x = %.02f, y = %.02f, m = %d, n = %d",PORADI, x, y, m, n);
	}

}
