package view;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import control.Generator;
import control.Parser;
import control.Simulace;
import control.Utils;
import model.Kun;
import model.Letoun;

/**
 * @author Lukas Runt, Martina Mlezivova
 * @version 2.0 (11-11-2021)
 */
public class Main {
	
	/** Souradnice Parize X*/
	public static double a;
	/** Souradnice Parize Y*/
	public static double b;
	/** Pocet koni*/
	public static int K;
	/** Pocet letounu*/
	public static int L;
	/** Pole koni */
	public static List<Kun> kone;
	/** Pole letounu */
	public static List<Letoun> letouny;
	/** Celkovy retezec */
	public static String retezec = "";
	/** Scanner pro vstup z klavesnice */
	public static Scanner sc = new Scanner(System.in);
	/** cesta k souboru */
	public static String cesta = null;
	
	/**
	 * Metoda na nacitani dat ze souboru :-) - oznaceni komentare -> necist data az
	 * do konce radku
	 * 
	 * @param jmenoSouboru nazev soubor, ze ktereho ziskame data
	 * @throws IOException 
	 */
	public static void parser(String jmenoSouboru) throws IOException{
		kone = null;
		letouny = null;
		Letoun.pocetLetounu = 0;
		Kun.setPocetKoni(0);
		System.out.println("Zacina nacitani dat.");
		Parser ps= new Parser(jmenoSouboru);
		double[] souradnice = ps.getSouradnice();
		a = souradnice[0];
		b = souradnice[1];
		kone = ps.getKone();
		letouny = ps.getLetouny();
		ps.sc.close();
		System.out.println("Data uspesne nactena.");
	}
	
	/**
	 * Metoda vypise celou simulaci do souboru
	 */
	public static void vypisDoSouboru() {
		try {
			 PrintWriter pw = new PrintWriter(
                     new BufferedWriter(
                     new FileWriter(new File("Vystup.txt"))));
			 pw.print(retezec);
			 pw.close();
		}catch(Exception ex) {
			 ex.printStackTrace();
		}
	}
	
	/**
	 * Metoda vypisuje menu
	 */
	public static void menu() {
		System.out.println(
				  "-------------------------------------------------------------------------------------------------\r\n"
				+ "|######  ######  ####### ######  ######   #####  ##    ##  #####    ##   ##  #####  ###   ## ## |\r\n"
				+ "|##   ## ##   ## ##      ##   ## ##   ## ##   ## ##    ## ##   ##   ##  ##  ##   ## ####  ## ## |\r\n"
				+ "|######  ######  #####   ######  ######  #######  ##  ##  #######   #####   ##   ## ## ## ## ## |\r\n"
				+ "|##      ##   ## ##      ##      ##   ## ##   ##   ####   ##   ##   ##  ##  ##   ## ##  #### ## |\r\n"
				+ "|##      ##   ## ####### ##      ##   ## ##   ##    ##    ##   ##   ##   ##  #####  ##   ### ## |\r\n"
				+ "-------------------------------------------------------------------------------------------------\r\n"
				+ "|   1 - Start   |   2 - Generace dat   |   3 - Uprava dat   |   4 - Jiny soubor   |  5 - Konec  |\r\n"
				+ "-------------------------------------------------------------------------------------------------");
		volba();
	}
	
	/**
	 * Metoda pro menu k uprave dat
	 */
	public static void upravaDat() {
		do {
			System.out.println(
				  "-------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "|                        ##    ## ######  ######   #####  ##    ##  #####    ######   #####  ########                         |\r\n"
				+ "|                        ##    ## ##   ## ##   ## ##   ## ##    ## ##   ##   ##   ## ##   ##    ##                            |\r\n"
				+ "|                        ##    ## ######  ######  #######  ##  ##  #######   ##   ## #######    ##                            |\r\n"
				+ "|                        ##    ## ##      ##   ## ##   ##   ####   ##   ##   ##   ## ##   ##    ##                            |\r\n"
				+ "|                         ######  ##      ##   ## ##   ##    ##    ##   ##   ######  ##   ##    ##                            |\r\n"
				+ "-------------------------------------------------------------------------------------------------------------------------------\r\n"
				+ "| 1 - Pridej kone | 2 - Odeber kone | 3 - Vypis kone | 4 - Pridej letadlo | 5 - Odeber letadlo | 6 - Vypis letadla | 7 - Zpet |\r\n"
				+ "-------------------------------------------------------------------------------------------------------------------------------");
		}while(volba2());
		}
	
	/**
	 * Metoda ve ktere si uzivatel voli co se ma stat
	 */
	public static void volba() {
		int volba;
		try {
			sc = new Scanner(System.in);
			volba = sc.nextInt();
			if(volba < 1 || volba > 6) {
				throw new IllegalArgumentException();
			}
			switch(volba) {
		  case 1:
			Simulace sim = new Simulace();
			sim.greedySimulace();
			System.out.println("Pro pokracovani zmackni ENTER.");
			try{System.in.read();}
			catch(Exception e){System.out.print("");}
		    break;
		  case 2:
			generovaniDat();
		    break;
		  case 3:
			upravaDat();
			break;
		  case 4:
			 vstupDat();
			 break;
		  case 5:
			 System.exit(0);
			 break;
		  default:
			  System.out.println();
			  break;
		}	
		}catch(IllegalArgumentException e) {
			System.out.println("Nevalidni volba");
		}catch(InputMismatchException exc) {
			System.out.println("Nevalidni volba");
		}
	}
	
	/**
	 * Metoda, ve ktere uzivatel voli jak se maji data zmenit
	 * @return true - uzivatel bude pokracovat v uprave, false - uzivatel se chce vratit do hlavniho menu
	 */
	public static boolean volba2() {
		int volba;
		try {
			sc = new Scanner(System.in);
			volba = sc.nextInt();
			if(volba < 1 || volba > 7) {
				throw new IllegalArgumentException();
			}
			switch(volba) {
		  case 1:
			tvorbaKone();
		    break;
		  case 2:
			 System.out.print("Zadej cislo kone, ktery ma byt odstranen: ");
			volba = sc.nextInt();
			if(volba > 0 && volba <= kone.size()) {
				kone.remove(volba - 1);
				System.out.printf("Kun %d byl smazan.\n", volba);
			} else {
				throw new IllegalArgumentException();
			}
		    break;
		  case 3:
			Utils.vypisKoni(kone);
			break;
		  case 4:
			tvorbaLetounu();
			break;
		  case 5:
			System.out.print("Zadej cislo letounu, ktery ma byt odstranen: ");
			volba = sc.nextInt();
			if(volba > 0 && volba < letouny.size()) {
				letouny.remove(volba - 1);
				System.out.printf("Letoun %d byl smazan.\n", volba);
			} else {
				throw new IllegalArgumentException();
			}
			break;
		  case 6:
			Utils.vypisLetounu(letouny);
			break;
		  case 7:
			return false;
		  default:
			  System.out.println();
		  	  break;
		}	
		}catch(IllegalArgumentException e) {
			System.out.println("Nevalidni volba");
		}catch(InputMismatchException exc) {
			System.out.println("Nevalidni volba");
		}catch(Exception ex) {
			System.out.println("Random chyba: " + ex.getMessage());
		}
		return true;
	}
	
	/**
	 * Metoda vytvari noveho kone
	 */
	public static void tvorbaKone() {
		double x, y;
		int m, n;
		x = Utils.inputDouble("Zadej souradnici x: ");
		y = Utils.inputDouble("Zadej souradnici y: ");
		m = Utils.inputInteger("Zadej hmotnost kone: ", 0);
		n = Utils.inputInteger("Zadej dobu nalozeni kone: ", 0);
		kone.add(new Kun(x, y, m, n));
	}
	
	/**
	 * Metoda vytvari nove letadlo
	 */
	public static void tvorbaLetounu() {
		double x, y, v;
		int m;
		x = Utils.inputDouble("Zadej souradnici x: ");
		y = Utils.inputDouble("Zadej souradnici y: ");
		m = Utils.inputInteger("Zadej nosnost letounu: ", 0);
		v = Utils.inputDouble("Zadej rychlost letounu: ", 0.0);
		letouny.add(new Letoun(x, y, m, v));
	}
	
	/**
	 * Metoda pro generovani dat
	 */
	public static void generovaniDat() {
		String jmenoSouboru = "";
		int minPocK, maxPocK, minPocL, maxPocL, volba = 0;
		double minX, maxX, minY, maxY;
		System.out.print("Zadej jmeno exportovaneho souboru: ");
		jmenoSouboru = sc.next();
		minPocK = Utils.inputInteger("Zadej minimalni pocet koni: ", 0);
		maxPocK = Utils.inputInteger("Zadej maximalni pocet koni: ", minPocK);
		minPocL = Utils.inputInteger("Zadej minimalni pocet letounu: ", 0);
		maxPocL = Utils.inputInteger("Zadej maximalni pocet letounu: ", minPocL);
		minX = Utils.inputDouble("Zadej minimalni X: ");
		maxX = Utils.inputDouble("Zadej maximalni X: ", minX);
		minY = Utils.inputDouble("Zadej minimalni Y: ");
		maxY = Utils.inputDouble("Zadej maximalni Y: ", minY);
		Generator gen = new Generator(minX, maxX, minY, maxY,minPocK, maxPocK, minPocL, maxPocL);
		while(volba < 1 || volba > 2) {
			volba = Utils.inputInteger("Vyber zpusob generovani: 1 - Rovnomerne | 2 - Normalni\n" , 1);
		}
		if(volba == 1) {
			gen.generateData(jmenoSouboru);
		}
		if(volba == 2) {
			gen.generateDataGausian(jmenoSouboru);
		}
	}
	
	/**
	 * Matoda pro vstup dat
	 * @return true - data byla nactena, false - pri nacitani doslo k chybe
	 */
	public static boolean vstupDat() {
		String vstup = null;
		try {
			System.out.print("Zadej cestu vstupnich dat: ");
			vstup = sc.next();
			parser(vstup);
			cesta = vstup;
			return true;
		}catch(Exception ex) {
			System.err.println("HUPSÍK DUPSÍK!");
			System.out.println("Doslo k chybe pri cteni souboru: " + vstup);
			return false;
		}
	}
	
	/**
	 * Vstupni metoda
	 * @param args
	 */
	public static void main(String[] args) {
		do {
		}while(!vstupDat());
		while(true) {
			menu();
		}
	}

}
