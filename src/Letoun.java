/**
 * 
 */

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

}
