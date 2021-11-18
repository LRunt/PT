package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import model.Kun;
import model.Letoun;
import view.Main;

public class Tisk {

	/**
	 * Metoda vytiskne vsechny soubory se statistikami
	 * @param casSimulace cas, ktery je potrebny k prepraveni koni
	 */
	public static void tiskni(double casSimulace) {
		tiskniLetouny();
		tiskniKone(casSimulace);
		tiskniStatistiku(casSimulace);
	}

	/**
	 * Metoda vytiskne statistiku letounu
	 */
	private static void tiskniLetouny() {
		String jmenoSouboru = "Letouny";
		Utils.serazeniPodleCisla();
		try {
			PrintWriter pw = new PrintWriter(
							 new BufferedWriter(
							 new FileWriter(
							 new File("export/statistika/" + jmenoSouboru + ".csv"))));
			for(Letoun letoun : Main.letouny) {
				pw.print(letoun.statistika);
				pw.print(String.format("Letadlo stravilo ve vzduchu %.0f, doba prostoju je %.0f\n", letoun.celkDobaLetu, letoun.getCas() - letoun.celkDobaLetu));
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
	private static void tiskniKone(double casSimulace) {
		String jmenoSouboru = "Kone";
		Utils.serazeniKonuPodleCisla();
		try {
			PrintWriter pw = new PrintWriter(
							 new BufferedWriter(
							 new FileWriter(
							 new File("export/statistika/" + jmenoSouboru + ".csv"))));
			for(Kun kun : Main.kone) {
				pw.print(kun.statistika);
				pw.print(String.format("Kun byl v Parizi %.0f pred zacatkem olympiady\n", casSimulace - kun.cas));
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
	private static void tiskniStatistiku(double casSimulace) {
		String jmenoSouboru = "Statistika";
		Utils.serazeniKonuPodleCisla();
		try {
			double celkovaDobaLetu = Main.letouny.stream().mapToDouble(l -> l.celkDobaLetu).sum();
			double celkovaDobaProstoju = Main.letouny.stream().mapToDouble(l -> l.getCas() - l.celkDobaLetu).sum();
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
