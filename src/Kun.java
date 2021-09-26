/**
 * @author Lukas Runt
 * @version 1.0 (25-09-2021)
 */
public class Kun {
	
	/** Souradnice kone x*/
	private int x;
	/** Souradnice kone y*/
	private int y;
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
	public Kun(int x, int y, int m, int n) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.n = n;
	}
	
	public int getx() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
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
		return String.format("Kun: x = %d, y = %d, m = %d, n = %d", x, y, m, n);
	}

}
