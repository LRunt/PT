/**
 * 
 */
package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0 (11-11-2021)
 */
public class Generator {
	public double maxX;
	public double minX;
	public double maxY;
	public double minY;
	public double a;
	public double b;
	public int pocetKoni;
	public int pocetLetounu;
	public static final int MAX_POCET = 100;
	public static final int MAX_M = 2000;
	public static final int MAX_N = 10000;
	public static final int MAX_V = 10;
	public String vystup = "";

	/**
	 * 
	 */
	public Generator(double minX, double maxX, double minY, double maxY) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
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
		System.out.printf("%.2f; %.2f\n", a, b);
	}
	
	/**
	 * Metoda vygeneruje kone
	 */
	public void generateKone() {
		pocetKoni = (int)(Math.random() * MAX_POCET) + 1;
		vystup += ":-) Pocet koni:\n";
		vystup += pocetKoni + " \n";
		for(int i = 0; i < pocetKoni; i++) {
			vystup += String.format(":-) Kun %d\n:", i + 1);
			vystup += (Math.random() * (maxX - minX + 1) + minX) + " ";
			vystup += (Math.random() * (maxY - minY + 1) + minY) + " ";
			vystup += (int)(Math.random() * (MAX_M - 0 + 1)) + " ";
			vystup += (int)(Math.random() * (MAX_N - 0 + 1)) + "\n";
		}
	}
	
	/**
	 * Metoda vygeneruje letouny
	 */
	public void generateLetouny() {
		pocetLetounu = (int)(Math.random() * MAX_POCET) + 1;
		vystup += ":-) Pocet letounu:\n";
		vystup += pocetLetounu + " \n";
		for(int i = 0; i < pocetLetounu; i++) {
			vystup += String.format(":-) Letoun %d\n:", i + 1);
			vystup += (Math.random() * (maxX - minX + 1) + minX) + " ";
			vystup += (Math.random() * (maxY - minY + 1) + minY) + " ";
			vystup += (int)(Math.random() * (MAX_M * 10 - 0 + 1)) + " ";
			vystup += (int)(Math.random() * (MAX_V - 0 + 1)) + "\n";
		}
	}
	
	/**
	 * 
	 * @param jmenoSouboru
	 */
	public void generateSoubor(String jmenoSouboru) {
		generateParis();
		generateKone();
		generateLetouny();
		try {
			PrintWriter pw = new PrintWriter(
                    new BufferedWriter(
                    new FileWriter(new File("data/" + jmenoSouboru + ".txt"))));
			pw.print(vystup);
			pw.close();
		}catch(Exception ex) {
			System.err.println("JEMINKOTE! nepodarilo se vytvorit soubor: " + jmenoSouboru);
		}
	}

}
