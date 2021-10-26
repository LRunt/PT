import java.util.Collections;

/**
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0 (13-10-2021)
 */
public class Utils {
	
	/**
	 * Metoda spocita cas letu
	 * @param letoun - letoun ktery leti
	 * @param kun - kun pro ktereho se leti
	 * @return celkovy cas letu
	 */
	public static double spoctiCas(Letoun letoun, Kun kun)  {
		return spoctiVzdalenost(letoun,kun)/ letoun.getV() + kun.getN();
	}

	/**
	 * Metoda spocita cas letu
	 * @param rychlost - rychlost letounu
	 * @param kun1 - kun od ktereho se leti
	 * @param kun2 - kun ke teremu se leti
	 * @return celkovy cas letu
	 */
	public static double spoctiCas(double rychlost, Kun kun1, Kun kun2)  {
		return spoctiVzdalenost(kun1,kun2)/ rychlost + kun2.getN();
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
	
	public static double getNejvetsiXKone() {
		Collections.sort(Main.kone, (k1, k2) -> (int)(k2.getX() * 1000 - k1.getX() * 1000));
		return Main.kone.get(0).getX();
	}
	
	public static double getNejmensiXKone() {
		Collections.sort(Main.kone, (k1, k2) -> (int)(k1.getX() * 1000 - k2.getX() * 1000));
		return Main.kone.get(0).getX();
	}
	
	public static double getNejvetsiYKone() {
		Collections.sort(Main.kone, (k1, k2) -> (int)(k2.getY() * 1000 - k1.getY() * 1000));
		return Main.kone.get(0).getX();
	}
	
	public static double getNejmensiYKone() {
		Collections.sort(Main.kone, (k1, k2) -> (int)(k1.getY() * 1000 - k2.getY() * 1000));
		return Main.kone.get(0).getX();
	}

}
