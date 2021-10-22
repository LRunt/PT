/**
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0 (13-10-2021)
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
	 * Metoda spocte vzdalenost mezi konmi
	 * @param Xx - x-ova souradnice kam leti ledadlo
	 * @param Yy - y-ova souradnice kam leti letadlo
	 * @return prima vzdalenost
	 */
	public static double spoctiVzdalenost(Kun kun1, Kun kun2) {
		double x = Math.abs(kun1.getX() - kun2.getX());
		double y = Math.abs(kun1.getY() - kun2.getY());
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Metoda spocte vzdalenost do mista kam leti letadlo
	 * @param letoun - letoun od ktereho pocitame vzdalenost
	 * @param X1 - x-ova souradnice kam leti ledadlo
	 * @param Y2 - y-ova souradnice kam leti letadlo
	 * @return prima vzdalenost
	 */
	public static double spoctiVzdalenost(Letoun letoun, double X1, double Y1) {
		double x = Math.abs(letoun.getX() - X1);
		double y = Math.abs(letoun.getY() - Y1);
		return Math.sqrt(x * x + y * y);
	}

}
