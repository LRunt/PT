package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import model.Kun;
import model.Letoun;
import view.Main;

public class Tisk {

	public static void tiskni(double casSimulace) {
		tiskniLetouny();
		tiskniKone(casSimulace);
	}

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
			System.err.println("TO JE MRZUTE! nepodarilo se vytvorit soubor: " + jmenoSouboru);
		}
	}
}
