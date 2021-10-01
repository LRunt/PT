/**
 * @author Lukas Runt
 * @version 1.0 (25-09-2021)
 */
public class Letoun {
	
	/** Souradnice letadla*/
	private double X;
	/** Souradnice letadla*/
	private double Y;
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
	public Letoun(double X, double Y, int M, double V) {
		this.X = X;
		this.Y = Y;
		this.M = M;
		this.V = V;
	}
	
	public double getX() {
		return X;
	}
	
	public void setX(double X) {
		this.X = X;
	}
	
	public double getY() {
		return Y;
	}
	
	public void setY(double Y) {
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
	
	public String toString() {
		return String.format("Letoun: x = %f, y = %f, m = %d, v = %f", X, Y, M, V);
	}

}
