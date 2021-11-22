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
	 * @param letoun Letoun, ktery leti
	 * @param kun Kun, pro ktereho se leti
	 * @return Celkovy cas letu
	 */
	public static double spoctiCas(Letoun letoun, Kun kun)  {
		return spoctiVzdalenost(letoun,kun)/ letoun.getV() + kun.getN();
	}

	/**
	 * Metoda spocita cas letu
	 * @param rychlost Rychlost letounu
	 * @param kun1 Kun od ktereho se leti
	 * @param kun2 Kun ke teremu se leti
	 * @return Celkovy cas letu
	 */
	public static double spoctiCas(double rychlost, Kun kun1, Kun kun2)  {
		return spoctiVzdalenost(kun1,kun2)/ rychlost + kun2.getN();
	}
	
	/**
	 * Metoda spocte vzdalenost do mista kam leti letadlo
	 * @param letoun Letoun od ktereho se pocita vzdalenost
	 * @param kun Kun ke kteremu se pocita vzdalenost
	 * @return Prima vzdalenost
	 */
	public static double spoctiVzdalenost(Letoun letoun, Kun kun) {
		double x = Math.abs(letoun.getX() - kun.getX());
		double y = Math.abs(letoun.getY() - kun.getY());
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Metoda spocte vzdalenost mezi konmi
	 * @param kun1 Kun od ktereho se pocita vzdalenost
	 * @param kun2 Kun ke kteremu se pocita vzdalenost
	 * @return Prima vzdalenost
	 */
	public static double spoctiVzdalenost(Kun kun1, Kun kun2) {
		double x = Math.abs(kun1.getX() - kun2.getX());
		double y = Math.abs(kun1.getY() - kun2.getY());
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Metoda spocte vzdalenost do mista, kam leti letadlo
	 * @param letoun Letoun, od ktereho pocitame vzdalenost
	 * @param X1 x-ova souradnice, kam leti ledadlo
	 * @param Y1 y-ova souradnice, kam leti letadlo
	 * @return Prima vzdalenost
	 */
	public static double spoctiVzdalenost(Letoun letoun, double X1, double Y1) {
		double x = Math.abs(letoun.getX() - X1);
		double y = Math.abs(letoun.getY() - Y1);
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Metoda vyradi letadla, ktere maji malou rychlost, ktera by zpomalovala simulaci
	 * @param letadla Pole letadel, ze ktereho se dela selekce
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
	 * @param letadla Pole letadel, ktere se ma seradit podle cisla
	 */
	public static void serazeniPodleCisla(List<Letoun> letadla) {
		Collections.sort(letadla, (l1, l2) -> (int)(l1.getPoradi()- l2.getPoradi()));
	}
	
	/**
	 * Metoda seradi kone podle cisla
	 * @param konici Pole koni, ktere se ma seradit podle cisla
	 */
	public static void serazeniKonuPodleCisla(List<Kun> konici) {
		Collections.sort(konici, (k1, k2) -> (int)(k1.getPoradi() - k2.getPoradi()));
	}
	
	/**
	 * Metoda seradi letouny podle nosnosti
	 * @param letadla Pole letadel, ktere se ma seradit podle nosnosti
	 */
	public static void serazeniPodleNosnosti(List<Letoun> letadla) {
		Collections.sort(letadla, (l1,l2) -> (int)(l1.getM() - l2.getM()));
	}
	
	/**
	 * Metoda seradi kone podle hmotnosti
	 * @param konici Pole koni, ktere se ma seradit podle hmotnosti
	 */
	public static void serazeniPodleHmotnosti(List<Kun> konici) {
		Collections.sort(konici, (k1,k2) -> (int)(k1.getM() - k2.getM()));
	}
	
	/**
	 * Metoda vypise pole koni do konzole
	 * @param konici Pole koni, ktere se ma vypsat do konzole
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
	 * @param letadla Pole letadel, ktere se ma vypsat do konzole
	 */
	public static void vypisLetounu(List<Letoun> letadla) {
		letadla.stream().forEach(s -> System.out.println(s));
	}

	/**
	 * Metoda seradi letouny podle rychlosti
	 * @param letadla Pole letadel, ktere se ma seradit podle rychlosti
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
	 * @param input Vstupni retezec
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
	 * @param input Vstupni retezec
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
	 * @param otazka Navod pro uzivatele co ma zadavat
	 * @return Cislo
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
	 * @param otazka Navod pro uzivatele co ma zadavat
	 * @param podminka Cislo ma byt vetsi nez podminka
	 * @return Cislo
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
	 * @param otazka Navod pro uzivatele co ma zadavat
	 * @param podminka Podmineno nez kolik ma byt cele cislo vetsi
	 * @return Cele cislo
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
