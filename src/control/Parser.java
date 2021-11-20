package control;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import model.Kun;
import model.Letoun;
import view.Main;

/**
 * Trida, ktera se stara o nacitani souboru
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0 (04-10-2021)
 */
public class Parser {
	
	/** Scanner souboru */
	public Scanner sc = Main.sc;
	/** Promenna, do ktere se uklada vstupni retezec */
	private String retezec;
	

	/**
	 * Konstruktor
	 */
	public Parser(String jmenoSouboru) throws IOException{
		sc = new Scanner(Paths.get(jmenoSouboru));
	}
	
	/**
	 * Metoda ziska ze souboru souradnice Parize
	 * @return souradnice
	 */
	public double[] getSouradnice() {
		double[] souradnice = new double[2];
		int tmp = 0;
		while (sc.hasNext()) {
			retezec = sc.next();
			if(isKomentar(retezec))
				continue;
			else if(tmp == 0) {
				souradnice[0] = Double.parseDouble(retezec);
				tmp++;
			}
			else {
				souradnice[1] = Double.parseDouble(retezec);
				break;
			}
		}
		return souradnice;
	}
	
	
	/**
	 * Metoda vytvori ze souboru kone
	 * @return pole koni
	 */
	public ArrayList<Kun> getKone() {
		ArrayList<Kun> kone = new ArrayList<>();
		Kun kun;
		int pocetK = 1, aktK = 0, tmp = 0, m = 0, n = 0;
		double x = 0, y = 0;
		while (sc.hasNext() && aktK < pocetK) {
			retezec = sc.next();
			if(isKomentar(retezec))
				continue;
			switch (tmp) {
				case 0: 
					pocetK = Integer.parseInt(retezec);
					tmp++;
					break;
				case 1:
					x = Double.parseDouble(retezec);
					tmp++;
					break;
				case 2:
					y = Double.parseDouble(retezec);
					tmp++;
					break;
				case 3:
					m = Integer.parseInt(retezec);
					tmp++;
					break;
				case 4:
					n = Integer.parseInt(retezec);
					kun = new Kun(x, y, m, n);
					kone.add(kun);
					aktK++;
					tmp = 1;
					break;	
			}
		}
		return kone;
	}
	
	/**
	 * Metoda vytvori ze souboru letouny na dopravu koni
	 * @return pole letounu
	 */
	public ArrayList<Letoun> getLetouny() {
		ArrayList<Letoun> letouny = new ArrayList<>();
		Letoun let;
		int pocetL = 1, aktL = 0, tmp = 0, m = 0;
		double x = 0, y = 0, v = 0;
		while (sc.hasNext() && aktL < pocetL) {
			retezec = sc.next();
			if(isKomentar(retezec))
				continue;
			switch (tmp) {
				case 0: 
					pocetL = Integer.parseInt(retezec);
					tmp++;
					break;
				case 1:
					x = Double.parseDouble(retezec);
					tmp++;
					break;
				case 2:
					y = Double.parseDouble(retezec);
					tmp++;
					break;
				case 3:
					m = Integer.parseInt(retezec);
					tmp++;
					break;
				case 4:
					v = Double.parseDouble(retezec);
					let = new Letoun(x, y, m, v);
					letouny.add(let);
					aktL++;
					tmp = 1;
					break;	
			}
		}
		return letouny;
	}
	
	/**
	 * Metoda rozhoduje, zda se jedna o komentar
	 * @param retezec vstupni retezec
	 * @return true - je to komentar, false - neni to komentar
	 */
	private boolean isKomentar(String retezec) {
		if(retezec.equals(":-)")) {
			sc.nextLine();
			return true;
		}
		return false;
	}
}
