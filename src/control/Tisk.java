package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import model.Kun;
import model.Letoun;
import view.Main;

/**
 * Trida {@code Tisk} zajistuje tisknuti statistik do souboru
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0(20-11-2021)
 */
public class Tisk {

	/**
	 * Metoda vytiskne vsechny soubory se statistikami
	 * @param casSimulace cas, ktery je potrebny k prepraveni koni
	 */
	public void tiskni(double casSimulace) {
		tiskniLetouny();
		tiskniKone(casSimulace);
		tiskniStatistiku(casSimulace);
	}

	/**
	 * Metoda vytiskne statistiku letounu
	 */
	private void tiskniLetouny() {
		String jmenoSouboru = "Letouny";
		Utils.serazeniPodleCisla(Main.letouny);
		try {
			PrintWriter pw = new PrintWriter(
							 new BufferedWriter(
							 new FileWriter(
							 new File("export/statistika/" + jmenoSouboru + ".csv"))));
			for(Letoun letoun : Main.letouny) {
				pw.print(letoun.statistika);
				pw.print(String.format("Letadlo stravilo ve vzduchu %.0f, doba prostoju je %.0f\n", letoun.getCelkDobaLetu(), letoun.getCas() - letoun.getCelkDobaLetu()));
			}
			pw.close();	
			System.out.println("Soubor se statistikou letounu vygenerovan.");
		}catch(Exception ex) {
			System.err.println("TO JE MRZUTE! nepodarilo se vytvorit soubor: " + jmenoSouboru);
		}
	}
	
	/**
	 * Metoda vytiskne statistiku koni
	 * @param casSimulace cas, ktery je potrebny k prepraveni koni
	 */
	private void tiskniKone(double casSimulace) {
		String jmenoSouboru = "Kone";
		Utils.serazeniKonuPodleCisla(Main.kone);
		try {
			PrintWriter pw = new PrintWriter(
							 new BufferedWriter(
							 new FileWriter(
							 new File("export/statistika/" + jmenoSouboru + ".csv"))));
			for(Kun kun : Main.kone) {
				pw.print(kun.statistika);
				pw.print(String.format("Kun byl v Parizi %.0f pred zacatkem olympiady\n", casSimulace - kun.getCas()));
			}
			pw.close();	
			System.out.println("Soubor se statistikou koni vygenerovan.");
		}catch(Exception ex) {
			System.err.println("TO JE ALE SMULA! nepodarilo se vytvorit soubor: " + jmenoSouboru);
		}
	}
	
	/**
	 * Matoda vytiskne celkovou statistiku
	 * @param casSimulace cas, ktery je potrebny k prepraveni koni
	 */
	private void tiskniStatistiku(double casSimulace) {
		String jmenoSouboru = "Statistika";
		Utils.serazeniKonuPodleCisla(Main.kone);
		try {
			double celkovaDobaLetu = Main.letouny.stream().mapToDouble(l -> l.getCelkDobaLetu()).sum();
			double celkovaDobaProstoju = Main.letouny.stream().mapToDouble(l -> l.getCas() - l.getCelkDobaLetu()).sum();
			PrintWriter pw = new PrintWriter(
							 new BufferedWriter(
							 new FileWriter(
							 new File("export/statistika/" + jmenoSouboru + ".txt"))));
			pw.write(String.format("Celkova doba letu: %.0f\n", celkovaDobaLetu));
			pw.write(String.format("Celkova doba prostoju: %.0f\n", celkovaDobaProstoju));
			pw.write(String.format("Celkova doba simulace: %.0f\n", casSimulace));
			pw.close();	
			System.out.println("Soubor se statistikou vygenerovan.");
		}catch(Exception ex) {
			System.err.println("HEHE nepodarilo se vytvorit soubor: " + jmenoSouboru);
		}
	}
}
