package control;
import java.util.Collections;
import java.util.List;

import model.Kun;
import model.Letoun;
import view.Main;

/**
 * Trida {@code Utils} je sbyrkou nastroju, ktere se volaju odkudkoliv z programu
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 2.0 (20-11-2021)
 */
public class Utils {
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
	public static void selekceLetadel(List<Letoun> letadla) {
		serazeniLetounu(letadla);
		Double nejRychlost = letadla.get(0).getV();
		for(int i = 0; i < letadla.size(); i++) {
			if(letadla.get(i).getV() < nejRychlost/KOLIKRAT_MENSI) {
				letadla.remove(i);
				i--;
				}
		}
	}
	
	/**
	 * Metoda seradi letouny podle cisla
	 */
	public static void serazeniPodleCisla(List<Letoun> letadla) {
		Collections.sort(letadla, (l1, l2) -> (int)(l1.getPoradi()- l2.getPoradi()));
	}
	
	/**
	 * Metoda seradi kone podle cisla
	 */
	public static void serazeniKonuPodleCisla(List<Kun> konici) {
		Collections.sort(konici, (k1, k2) -> (int)(k1.getPoradi() - k2.getPoradi()));
	}
	
	/**
	 * Metoda seradi letouny podle nosnosti
	 */
	public static void serazeniPodleNosnosti(List<Letoun> letadla) {
		Collections.sort(letadla, (l1,l2) -> (int)(l1.getM() - l2.getM()));
	}
	
	/**
	 * Metoda seradi kone podle hmotnosti
	 */
	public static void serazeniPodleHmotnosti(List<Kun> konici) {
		Collections.sort(konici, (k1,k2) -> (int)(k1.getM() - k2.getM()));
	}
	
	/**
	 * Metoda vypise pole koni do konzole
	 */
	public static void vypisKoni(List<Kun> konici) {
		konici.stream().forEach(s -> System.out.println(s));
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
	public static void vypisLetounu(List<Letoun> letadla) {
		letadla.stream().forEach(s -> System.out.println(s));
	}

	/**
	 * Metoda seradi letouny podle rychlosti
	 */
	public static void serazeniLetounu(List<Letoun> letadla) {
		Collections.sort(letadla, (l1,l2) -> (int)(l2.getV() * K - l1.getV() * K));
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
	
	/**
	 * Metoda zjistuje, zda je vstupni retezec cislo 
	 * @param input vstupni retezec
	 * @return true - je to cislo, false - neni to cislo
	 */
	public static boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			System.out.println("Zadane cislo neni cislo!");
			return false;
		}
	}
	
	/**
	 * Metoda zjistuje zda je vstupni retezec celym cislem
	 * @param input vstupni retezec
	 * @return true - je to cele cislo, false - neni to cele cislo
	 */
	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			System.out.println("Zadany retezec neni cele cislo!");
			return false;
		}
	}
	
	/**
	 * Metoda zarucuje, ze vstupni retezec bude cislo
	 * @param otazka navod pro uzivatele co ma zadavat
	 * @return cislo
	 */
	public static double inputDouble(String otazka) {
		String vstup;
		do {
			System.out.print(otazka);
			vstup = Main.sc.next();
		}while(!Utils.isDouble(vstup));
		return Double.parseDouble(vstup);
	}
	
	/**
	 * Metoda zarucuje, ze vstupni retezec bude cislo
	 * @param otazka navod pro uzivatele co ma zadavat
	 * @param podminka cislo ma byt vetsi nez podminka
	 * @return cislo
	 */
	public static double inputDouble(String otazka, double podminka) {
		double cislo = podminka - 1;
		String vstup;
		while(podminka > cislo) {
			do {
				System.out.print(otazka);
				vstup = Main.sc.next();
			}while(!Utils.isDouble(vstup));
			cislo = Double.parseDouble(vstup);
			if(podminka > cislo) {
				System.out.println("Cislo musi byt vetsi nez " + podminka);
			}
		}
		
		return cislo;
	}
	
	/**
	 * Metoda zarucuje, ze vstupni retezec bude cele cislo
	 * @param otazka navod pro uzivatele co ma zadavat
	 * @param podminka pomineno nez kolik ma byt cele cislo vetsi
	 * @return cele cislo
	 */
	public static int inputInteger(String otazka, int podminka) {
		String vstup;
		int celeCislo = podminka - 1;
		while(podminka > celeCislo) {
			do {
				System.out.print(otazka);
				vstup = Main.sc.next();
			}while(!Utils.isInteger(vstup));
			celeCislo = Integer.parseInt(vstup);
			if(podminka > celeCislo) {
				System.out.println("Cislo musi byt vetsi nez " + (podminka - 1));
			}
		}
		return celeCislo;	
		
	}

}
