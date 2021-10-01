/**
 * @author Lukas Runt
 * @version 1.0 (25-09-2021)
 */
public class Kun {
	
	/** Souradnice kone x*/
	private double x;
	/** Souradnice kone y*/
	private double y;
	/** hmotnost kone s vybavenim */
	private int m;
	/** doba nalozeni kone */
	private int n;

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
	}
	
	public double getx() {
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
	
	public String toString() {
		return String.format("Kun: x = %f, y = %f, m = %d, n = %d", x, y, m, n);
	}

}
