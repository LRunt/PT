import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0 (13-10-2021)
 */
public class Utils {
	/** Typedef letounu z mainu*/
	private static ArrayList<Letoun> letouny = Main.letouny;
	/** Typedef koni z mainu*/
	private static ArrayList<Kun> kone = Main.kone;
	/** Odstranuje chybu */
	private static final int K = 1000;
	/** Kolikrat muze byt mensi rychlost letadla */
	private static final double KOLIKRAT_MENSI = 6.0;
	
	/**
	 * Metoda spocita cas letu
	 * @param letoun - letoun, ktery leti
	 * @param kun - kun, pro ktereho se leti
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
	 * @param Xx - x-ova souradnice, kam leti ledadlo
	 * @param Yy - y-ova souradnice, kam leti letadlo
	 * @return prima vzdalenost
	 */
	public static double spoctiVzdalenost(Letoun letoun, Kun kun) {
		double x = Math.abs(letoun.getX() - kun.getX());
		double y = Math.abs(letoun.getY() - kun.getY());
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Metoda spocte vzdalenost mezi konmi
	 * @param Xx - x-ova souradnice, kam leti ledadlo
	 * @param Yy - y-ova souradnice, kam leti letadlo
	 * @return prima vzdalenost
	 */
	public static double spoctiVzdalenost(Kun kun1, Kun kun2) {
		double x = Math.abs(kun1.getX() - kun2.getX());
		double y = Math.abs(kun1.getY() - kun2.getY());
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Metoda spocte vzdalenost do mista, kam leti letadlo
	 * @param letoun - letoun, od ktereho pocitame vzdalenost
	 * @param X1 - x-ova souradnice, kam leti ledadlo
	 * @param Y2 - y-ova souradnice, kam leti letadlo
	 * @return prima vzdalenost
	 */
	public static double spoctiVzdalenost(Letoun letoun, double X1, double Y1) {
		double x = Math.abs(letoun.getX() - X1);
		double y = Math.abs(letoun.getY() - Y1);
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Metoda vyradi letadla, ktere maji malou rychlost, ktera by zpomalovala simulaci
	 */
	public static void selekceLetadel() {
		serazeniLetounu();
		Double nejRychlost = letouny.get(0).getV();
		for(int i = 0; i < letouny.size(); i++) {
			if(letouny.get(i).getV() < nejRychlost/KOLIKRAT_MENSI) {
				letouny.remove(i);
				i--;
				}
		}
	}
	
	/**
	 * Metoda seradi letouny podle cisla
	 */
	public static void serazeniPodleCisla() {
		Collections.sort(letouny, (l1,l2) -> (int)(l2.getPoradi()- l1.getPoradi()));
	}
	
	/**
	 * Metoda vypise pole koni do konzole
	 */
	public static void vypisKoni() {
		kone.stream().forEach(s -> System.out.println(s));
	}
	
	/**
	 * Metoda vypise pozici Parize
	 */
	public static void vypisParize() {
		System.out.printf("Pariz: x = %f; y = %f\n");
	}
	
	/**
	 * Metoda vypise pole letounu do konzole
	 */
	public static void vypisLetounu() {
		letouny.stream().forEach(s -> System.out.println(s));
	}

	/**
	 * Metoda seradi letouny podle rychlosti
	 */
	public static void serazeniLetounu() {
		Collections.sort(letouny, (l1,l2) -> (int)(l2.getV() * K - l1.getV() * K));
	}
	
	/**
	 * Metoda vrati nejvetsi X-ovou souradnici kone
	 * @return Nejvetsi X-ova souradnice kone
	 */
	public static double getNejvetsiXKone() {
		Collections.sort(Main.kone, (k1, k2) -> (int)(k2.getX() * K - k1.getX() * K));
		return Main.kone.get(0).getX();
	}
	
	/**
	 * Metoda vrati nejmensi X-ovou souradnici kone
	 * @return Nejmensi X-ova souradnice kone
	 */
	public static double getNejmensiXKone() {
		Collections.sort(Main.kone, (k1, k2) -> (int)(k1.getX() * K - k2.getX() * K));
		return Main.kone.get(0).getX();
	}
	
	/**
	 * Metoda vrati nejvetsi Y-ovou souradnici kone
	 * @return Nejvetsi Y-ova souradnice kone 
	 */
	public static double getNejvetsiYKone() {
		Collections.sort(Main.kone, (k1, k2) -> (int)(k2.getY() * K - k1.getY() * K));
		return Main.kone.get(0).getX();
	}
	
	/**
	 * Metoda vrati nejmensi Y-ovou souradnici kone
	 * @return Nejmensi Y-ova souradnice kone
	 */
	public static double getNejmensiYKone() {
		Collections.sort(Main.kone, (k1, k2) -> (int)(k1.getY() * K - k2.getY() * K));
		return Main.kone.get(0).getX();
	}

}
