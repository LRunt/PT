/**
 * @author Lukas Runt
 * @version 1.0 (25-09-2021)
 */
public class Letoun {
	
	/** Souradnice letadla*/
	private int X;
	/** Souradnice letadla*/
	private int Y;
	/** Maximalni nosnost letounu*/
	private int M;
	/** Rychlost letu*/
	private double V;

	/**
	 * Konstruktor letadla
	 * @param X - x-ova souradnice
	 * @param Y - y-ova souradnice
	 * @param M - nosnost letounu
	 * @param V - rychlost letu
	 */
	public Letoun(int X, int Y, int M, double V) {
		this.X = X;
		this.Y = Y;
		this.M = M;
		this.V = V;
	}
	
	public int getX() {
		return X;
	}
	
	public void setX(int X) {
		this.X = X;
	}
	
	public int getY() {
		return Y;
	}
	
	public void setY(int Y) {
		this.Y = Y;
	}
	
	public int getM() {
		return M;
	}
	
	public void setM(int M) {
		this.M = M;
	}
	
	public double getV() {
		return V;
	}
	
	public void setV(double V) {
		this.V = V;
	}

}
