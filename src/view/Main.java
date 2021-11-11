package view;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFrame;

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
	public static ArrayList<Kun> kone;
	/** Pole letounu */
	public static ArrayList<Letoun> letouny;
	/** Celkovy retezec */
	public static String retezec = "";
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * Metoda na nacitani dat ze souboru :-) - oznaceni komentare -> necist data az
	 * do konce radku
	 * 
	 * @param jmenoSouboru nazev soubor, ze ktereho ziskame data
	 */
	public static void parser(String jmenoSouboru) throws Exception{
		System.out.println("Zacina nacitani dat.");
		Parser ps= new Parser(jmenoSouboru);
		double[] souradnice = ps.getSouradnice();
		a = souradnice[0];
		b = souradnice[1];
		kone = ps.getKone();
		letouny = ps.getLetouny();
		ps.sc.close();
		System.out.println("Data uspesne nactena.");
			//System.err.println("HUPSÍK DUPSÍK!");
			//System.out.println("Doslo k chybe pri cteni souboru: " + jmenoSouboru);
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
				+ "|██████╗░██████╗░███████╗██████╗░██████╗░░█████╗░██╗░░░██╗░█████╗░  ██╗░░██╗░█████╗░███╗░░██╗██╗|\r\n"
				+ "|██╔══██╗██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔══██╗██║░░░██║██╔══██╗  ██║░██╔╝██╔══██╗████╗░██║██║|\r\n"
				+ "|██████╔╝██████╔╝█████╗░░██████╔╝██████╔╝███████║╚██╗░██╔╝███████║  █████═╝░██║░░██║██╔██╗██║██║|\r\n"
				+ "|██╔═══╝░██╔══██╗██╔══╝░░██╔═══╝░██╔══██╗██╔══██║░╚████╔╝░██╔══██║  ██╔═██╗░██║░░██║██║╚████║██║|\r\n"
				+ "|██║░░░░░██║░░██║███████╗██║░░░░░██║░░██║██║░░██║░░╚██╔╝░░██║░░██║  ██║░╚██╗╚█████╔╝██║░╚███║██║|\r\n"
				+ "|╚═╝░░░░░╚═╝░░╚═╝╚══════╝╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░╚═╝  ╚═╝░░╚═╝░╚════╝░╚═╝░░╚══╝╚═╝|\r\n"
				+ "-------------------------------------------------------------------------------------------------\r\n"
				+ "|  1 - Start  |  2 - Generace dat  |  3 - Ovladani  |  4 - Debug  | 5 - Jiny soubor | 6 - Konec |\r\n"
				+ "-------------------------------------------------------------------------------------------------");
		volba();
	}
	
	/**
	 * Metoda ve ktere si uzivatel voli co se ma stat
	 */
	public static void volba() {
		String vstup = null;
		int volba;
		try {
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
			catch(Exception e){}
		    break;
		  case 2:
			generovaniDat();
		    break;
		  case 3:
			System.out.println("Work in progress");
			break;
		  case 4:
			 System.out.print("Neimplementovano");
			 System.out.println("Zatim to nic nedela :-(");
			 break;
		  case 5:
			 vstupDat();
			 break;
		  case 6:
			 System.exit(0);
			 break;
		}	
		}catch(IllegalArgumentException e) {
			System.out.println("Nevalidni volba");
		}
		catch(Exception ex) {
			System.err.println("HUPSÍK DUPSÍK! Doslo k chybe pri cteni souboru: " + vstup);
		}
		
	}
	
	public static void generovaniDat() {
		String jmenoSouboru;
		System.out.print("Zadej jmeno exportovaneho souboru: ");
		jmenoSouboru = sc.next();
		Generator gen = new Generator(-200, 200, -200, 200);
		gen.generateSoubor(jmenoSouboru);
	}
	
	public static boolean vstupDat() {
		String vstup = null;
		try {
			System.out.print("Zadej cestu vstupnich dat: ");
			vstup = sc.next();
			parser(vstup);
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
		/*parser("data/random100.txt");	
		Utils.vypisKoni();
		System.out.println("----------------------------");
		Utils.vypisLetounu();
		System.out.println("----------------------------");
		Simulace sim = new Simulace();
		sim.greedySimulace();*/
		//vypisDoSouboru();
		
		
		//graficka vzualizace simulace 1
		/*JFrame okno = new JFrame();
		okno.setTitle("Semestralni prace - PT");
		okno.setResizable(false);
		
		okno.add(new DrawingPanel());//pridani komponenty
		okno.pack(); //udela resize okna dle komponent
		
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//skonceni po zavreni okna
		okno.setLocationRelativeTo(null);//vycentrovat na obrazovce
		okno.setVisible(true);*/
	}

}
