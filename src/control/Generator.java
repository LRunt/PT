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
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0 (11-11-2021)
 */
public class Generator {
	public double maxX;
	public double minX;
	public double maxY;
	public double minY;
	public int minPocK;
	public int maxPocK;
	public int minPocL;
	public int maxPocL;
	public double a;
	public double b;
	public int pocetKoni;
	public int pocetLetounu;
	public static final int MAX_POCET = 100;
	public static final int MAX_M = 2000;
	public static final int MAX_N = 10000;
	public static final int MAX_V = 10;
	public String vystup = "";
	public Random random = new Random();

	/**
	 * 
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
		a = Math.random() * (maxX - minX + 1) + minX;
		vystup += a + " "; 
		b = Math.random() * (maxY - minY + 1) + minY;
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
			vystup += (int)(Math.random() * (MAX_N - 0 + 1)) + "\n";
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
	                .map(i -> random.nextGaussian() * 100 + 0) //Standard deviation 20, mean 50
	                .collect(Collectors.toList());
	    List<Double> souradniceY = IntStream.rangeClosed(1, pocetKoni).boxed()
                .map(i -> random.nextGaussian() * 100 + 0) //Standard deviation 20, mean 50
                .collect(Collectors.toList());
	    List<Double> hmotnostKone = IntStream.rangeClosed(1, pocetKoni).boxed()
                .map(i -> random.nextGaussian() * 100000 + 1000) //Standard deviation 20, mean 50
                .collect(Collectors.toList());
	    List<Double> dobaNalozeni = IntStream.rangeClosed(1, pocetKoni).boxed()
                .map(i -> random.nextGaussian() * 1000 + 10000) //Standard deviation 20, mean 50
                .collect(Collectors.toList());
	    /*System.out.println("X");
	    souradniceX.stream().forEach(a -> System.out.println(a));
	    System.out.println("Y");
	    souradniceY.stream().forEach(a -> System.out.println(a));
	    System.out.println("Vaha");
	    hmotnostKone.stream().forEach(a -> System.out.println(a));
	    System.out.println("Nalozeni");
	    dobaNalozeni.stream().forEach(a -> System.out.println(a));*/
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
	                .map(i -> random.nextGaussian() * 100 + 0) //Standard deviation 20, mean 50
	                .collect(Collectors.toList());
	    List<Double> souradniceY = IntStream.rangeClosed(1, pocetLetounu).boxed()
                .map(i -> random.nextGaussian() * 100 + 0) //Standard deviation 20, mean 50
                .collect(Collectors.toList());
	    List<Double> nosnostLetounu = IntStream.rangeClosed(1, pocetLetounu).boxed()
                .map(i -> random.nextGaussian() * 100000 + 1000) //Standard deviation 20, mean 50
                .collect(Collectors.toList());
	    List<Double> rychlostLetounu = IntStream.rangeClosed(1, pocetLetounu).boxed()
                .map(i -> random.nextGaussian() * 1000 + 10000) //Standard deviation 20, mean 50
                .collect(Collectors.toList());
	    for(int i = 0; i < pocetKoni; i++) {
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
	public void generateDataGausian(String jmenoSouboru) {
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
			PrintWriter pw = new PrintWriter(
                    new BufferedWriter(
                    new FileWriter(new File("export/" + jmenoSouboru + ".txt"))));
			pw.print(vystup);
			pw.close();
		}catch(Exception ex) {
			System.err.println("JEMINKOTE! nepodarilo se vytvorit soubor: " + jmenoSouboru);
		}
	}

}
