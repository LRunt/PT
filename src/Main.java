import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

/**
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.2 (04-10-2021)
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
	
	/**
	 * Metoda na nacitani dat ze souboru :-) - oznaceni komentare -> necist data az
	 * do konce radku
	 * 
	 * @param jmenoSouboru nazev soubor, ze ktereho ziskame data
	 */
	public static void parser(String jmenoSouboru) {
		System.out.println("Zacina nacitani dat.");
		try {
			Parser ps= new Parser(jmenoSouboru);
			double[] souradnice = ps.getSouradnice();
			a = souradnice[0];
			b = souradnice[1];
			kone = ps.getKone();
			letouny = ps.getLetouny();
			ps.sc.close();
			System.out.println("Data uspesne nactena.");
		} catch (Exception ex) {
			System.out.println("Doslo k chybe pri cteni souboru: " + jmenoSouboru + "(" + ex.getMessage() + ")");
		}
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
	 * Vstupni metoda
	 * @param args
	 */
	public static void main(String[] args) {
	
		parser("data/random100.txt");	
		Utils.vypisKoni();
		System.out.println("----------------------------");
		Utils.vypisLetounu();
		System.out.println("----------------------------");
		Simulace sim = new Simulace();
		sim.greedySimulace();
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
