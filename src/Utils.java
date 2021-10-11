/**
 * 
 */

/**
 * @author Lukáš
 *
 */
public class Utils {

	/**
	 * 
	 */
	public Utils() {
		
	}
	
	/**
	 * Metoda spocte vzdalenost do mista kam leti letadlo
	 * @param Xx - x-ova souradnice kam leti ledadlo
	 * @param Yy - y-ova souradnice kam leti letadlo
	 * @return prima vzdalenost
	 */
	public static double spoctiVzdalenost(Letoun letoun, Kun kun) {
		double x = Math.abs(letoun.getX() - kun.getX());
		double y = Math.abs(letoun.getY() - kun.getY());
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Metoda spocte vzdalenost do mista kam leti letadlo
	 * @param Xx - x-ova souradnice kam leti ledadlo
	 * @param Yy - y-ova souradnice kam leti letadlo
	 * @return prima vzdalenost
	 */
	public static double spoctiVzdalenost(Letoun letoun, double X1, double Y1) {
		double x = Math.abs(letoun.getX() - X1);
		double y = Math.abs(letoun.getY() - Y1);
		return Math.sqrt(x * x + y * y);
	}

}
