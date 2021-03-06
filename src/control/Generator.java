/**
 * 
 */
package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Trida {@code Generator} zajistuje generovani souboru daneho formatu
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.2 (26-11-2021)
 */
public class Generator {
	/** Minimalni X-ova souradnice*/
	private final double maxX;
	/** Maximalni X-ova souradnice*/ 
	private final double minX;
	/** Minimalni Y-ova souradnice*/
	private final double maxY;
	/** Maximalni Y-ova souradnice*/
	private final double minY;
	/** Minimalni pocet koni*/
	private final int minPocK;
	/** Maximalni pocet koni*/
	private final int maxPocK;
	/** Minimalni pocet letounu*/
	private final int minPocL;
	/** Maximalni pocet letounu*/
	private final int maxPocL;
	/** Vygenerovany pocet koni*/
	private int pocetKoni;
	/** Vygenerovany pocet letounu*/
	private int pocetLetounu;
	/** Konstanta generovani hmotnosti koni*/
	private static final int MAX_M = 1500;
	/** Konstanta pro generovani nosnosti letounu*/
	private static final int MAX_N = 10000;
	/** Konstatnta pro generovani rychlosti letounu*/
	private static final int MAX_V = 100;
	/** Konstanta pro generovani doby nalozeni koni*/
	private static final int MAX_T = 1000;
	/** Vystup do generovaneho souboru*/
	private String vystup = "";
	/** Generator nahodnych cisel*/
	private static Random random = new Random();

	/**
	 * Konstruktor generatoru dat
	 * @param minX - Minimalni X-ova souradnice
	 * @param maxX - Maximalni X-ova souradnice
	 * @param minY - Minimalni Y-ova souradnice
	 * @param maxY - Maximalni Y-ova souradnice
	 * @param minPocK - Minimalni pocet koni
	 * @param maxPocK - Maximalni pocet koni
	 * @param minPocL - Minimalni pocet letounu
	 * @param maxPocL - Maximalni pocet letounu
	 */
	public Generator(double minX, double maxX, double minY, double maxY, int minPocK, int maxPocK, int minPocL, int maxPocL) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.minPocK = minPocK;
		this.maxPocK = maxPocK;
		this.minPocL = minPocL;
		this.maxPocL = maxPocL;
	}
	
	/**
	 * Metoda vygeneruje souradnice Parize
	 */
	public void generateParis() {
		vystup += String.format(":-) Pariz:\n");
		double a = Math.random() * (maxX - minX + 1) + minX;
		vystup += a + " "; 
		double b = Math.random() * (maxY - minY + 1) + minY;
		vystup += b + " \n";
	}
	
	/**
	 * Metoda vygeneruje kone uplne nahodne
	 */
	public void generateKone() {
		pocetKoni = (int)(Math.random() * (maxPocK - minPocK)) + minPocK;
		vystup += ":-) Pocet koni:\n";
		vystup += pocetKoni + " \n";
		for(int i = 0; i < pocetKoni; i++) {
			vystup += String.format(":-) Kun %d\n", i + 1);
			vystup += (Math.random() * (maxX - minX + 1) + minX) + " ";
			vystup += (Math.random() * (maxY - minY + 1) + minY) + " ";
			vystup += (int)(Math.random() * (MAX_M - 0 + 1)) + " ";
			vystup += (int)(Math.random() * (MAX_T - 0 + 1)) + "\n";
		}
	}
	
	/**
	 * Metoda vygeneruje kone
	 */
	public void generateKoneGaussian() {
		pocetKoni = (int)(Math.random() * (maxPocK - minPocK)) + minPocK;
		vystup += ":-) Pocet koni:\n";
		vystup += pocetKoni + " \n";
	    List<Double> souradniceX = IntStream.rangeClosed(1, pocetKoni).boxed()
	                .map(i -> random.nextGaussian() * (maxX - minX) / 5 + (maxX - minX) / 2 + minX)
	                .collect(Collectors.toList());
	    List<Double> souradniceY = IntStream.rangeClosed(1, pocetKoni).boxed()
                .map(i -> random.nextGaussian() * (maxX - minX) / 5 + (maxX - minX) / 2 + minX) 
                .collect(Collectors.toList());
	    List<Double> hmotnostKone = IntStream.rangeClosed(1, pocetKoni).boxed()
                .map(i -> random.nextGaussian() * MAX_M / 5 + MAX_M) 
                .collect(Collectors.toList());
	    List<Double> dobaNalozeni = IntStream.rangeClosed(1, pocetKoni).boxed()
                .map(i -> random.nextGaussian() * MAX_T / 5 + MAX_T) 
                .collect(Collectors.toList());
	    for(int i = 0; i < pocetKoni; i++) {
	    	vystup += String.format(":-) Kun %d\n", i + 1);
			vystup += souradniceX.get(i) + " ";
			vystup += souradniceY.get(i) + " ";
			vystup += Math.abs((int)hmotnostKone.get(i).doubleValue())  + " ";
			vystup += Math.abs((int)dobaNalozeni.get(i).doubleValue())  + "\n";
	    }
	}
	
	
	/**
	 * Metoda vygeneruje letouny
	 */
	public void generateLetouny() {
		pocetLetounu = (int)(Math.random() * (maxPocL - minPocL)) + minPocL;
		vystup += ":-) Pocet letounu:\n";
		vystup += pocetLetounu + " \n";
		for(int i = 0; i < pocetLetounu; i++) {
			vystup += String.format(":-) Letoun %d\n", i + 1);
			vystup += (Math.random() * (maxX - minX + 1) + minX) + " ";
			vystup += (Math.random() * (maxY - minY + 1) + minY) + " ";
			vystup += (int)(Math.random() * (MAX_M * 10 - MAX_M + 1)) + " ";
			vystup += (int)(Math.random() * (MAX_V - 0 + 1)) + "\n";
		}
	}
	
	/**
	 * Metoda vygeneruje letouny
	 */
	public void generateLetounyGaussian() {
		pocetLetounu = (int)(Math.random() * (maxPocL - minPocL)) + minPocL;
		vystup += ":-) Pocet letounu:\n";
		vystup += pocetLetounu + " \n";
	    List<Double> souradniceX = IntStream.rangeClosed(1, pocetLetounu).boxed()
	                .map(i -> random.nextGaussian() * (maxX - minX) / 5 + (maxX - minX) / 2 + minX) //Standard deviation 20, mean 50
	                .collect(Collectors.toList());
	    List<Double> souradniceY = IntStream.rangeClosed(1, pocetLetounu).boxed()
                .map(i -> random.nextGaussian() * (maxY - minY) / 5 + (maxY - minY) / 2 + minY) 
                .collect(Collectors.toList());
	    List<Double> nosnostLetounu = IntStream.rangeClosed(1, pocetLetounu).boxed()
                .map(i -> random.nextGaussian() * MAX_N / 5 + MAX_N) 
                .collect(Collectors.toList());
	    List<Double> rychlostLetounu = IntStream.rangeClosed(1, pocetLetounu).boxed()
                .map(i -> random.nextGaussian() * MAX_V /5 + MAX_V) 
                .collect(Collectors.toList());
	    for(int i = 0; i < pocetLetounu; i++) {
	    	vystup += String.format(":-) Letoun %d\n", i + 1);
			vystup += souradniceX.get(i) + " ";
			vystup += souradniceY.get(i) + " ";
			vystup += Math.abs((int)nosnostLetounu.get(i).doubleValue())  + " ";
			vystup += Math.abs((int)rychlostLetounu.get(i).doubleValue())  + "\n";
	    }
	}
	
	/**
	 * Generovani dat rovnomerneho rozdeleni
	 * @param jmenoSouboru jmeno generovaneho souboru
	 */
	public void generateData(String jmenoSouboru){
		generateParis();
		generateKone();
		generateLetouny();
		generateSoubor(jmenoSouboru);
	}
	
	/**
	 * Generovani dat normalniho rozdeleni
	 * @param jmenoSouboru jmeno generovaneho souboru
	 */
	public void generateDataGaussian(String jmenoSouboru) {
		generateParis();
		generateKoneGaussian();
		generateLetounyGaussian();
		generateSoubor(jmenoSouboru);
	}
	
	/**
	 * Metoda vygeneruje nahodne soubor s Parizi, konmi a letouny
	 * @param jmenoSouboru jmeno generovaneho souboru
	 */
	public void generateSoubor(String jmenoSouboru) {
		try {
			System.out.println("Probiha generovani souboru.");
			PrintWriter pw = new PrintWriter(
                    new BufferedWriter(
                    new FileWriter(new File("export/" + jmenoSouboru + ".txt"))));
			pw.print(vystup);
			pw.close();
			System.out.println("Soubor vygenerovan.");
		}catch(Exception ex) {
			System.err.println("JEMINKOTE! nepodarilo se vytvorit soubor: " + jmenoSouboru);
		}
	}

}
