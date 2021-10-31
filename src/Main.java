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
	/** Zjistuje jestli je letoun v Parizi*/
	public static boolean jeVeFrancii = false;
	/*/** Cas od zacatku simulace 
	public static double cas;*/
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
	 * Zakladni simulace pomoci greedy algoritmu
	 */
	public static void simulace() {
		System.out.println("Zacatek simulace:");
		ArrayList<Kun> koneKPreprave = kone;
		letouny.get(0).start();
		Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(letouny.get(0), k1) *  100 - Utils.spoctiVzdalenost(letouny.get(0), k2) * 100));
		while(koneKPreprave.size() > 0) {
			Kun nasledujiciKun = koneKPreprave.get(0);
			Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(nasledujiciKun, k1) * 1000 - Utils.spoctiVzdalenost(nasledujiciKun, k2) * 1000));
			if(koneKPreprave.size() == 1) {
				letouny.get(0).letDoFrancie(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				jeVeFrancii = true;
				//leti z parize
			} else if(jeVeFrancii) {
				letouny.get(0).letZFrancieKeKoni(koneKPreprave.get(0));
				jeVeFrancii = false;
				//leti do parize
			} else if(letouny.get(0).getM() < letouny.get(0).getAktNakl() + koneKPreprave.get(0).getM() + koneKPreprave.get(1).getM() ) {
				letouny.get(0).letDoFrancie(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				jeVeFrancii = true;
			} else{
				letouny.get(0).letKeKoni(koneKPreprave.get(0), koneKPreprave.get(1));
				koneKPreprave.remove(0);
			}
			
		}
		letouny.get(0).letounPristal();
		System.out.println("Konec simulace");
	}
	
	/**
	 * Simulace s vice letadly
	 */
	public static void simulace2() {
		int pocetKoni = kone.size(), prevezenoKoni = 0;
		System.out.println("Zacatek simulace:");
		ArrayList<Kun> koneKPreprave = kone;
		//serazeniLetounu();
		selekceLetadel();
		while(pocetKoni >= prevezenoKoni) {
			//System.out.println(prevezenoKoni + "; " + pocetKoni);
			Collections.sort(letouny,(l1, l2) -> (int)(l1.getCas() - l2.getCas()));
			Letoun aktLet = letouny.get(0);
			if(letouny.get(0).getNasledujiciKun() == null && koneKPreprave.size() >= 2) {
				letouny.get(0).start();
				Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(aktLet, k1) * 100 - Utils.spoctiVzdalenost(aktLet, k2) * 100));
				letouny.get(0).setNasledujiciKun(koneKPreprave.get(0));
				koneKPreprave.remove(0);
			} else if(koneKPreprave.size() > 1) {
				Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(aktLet.getNasledujiciKun(), k1) * 1000 - Utils.spoctiVzdalenost(aktLet.getNasledujiciKun(), k2) * 1000));
			}
			if(koneKPreprave.size() == 0) {
				if(letouny.get(0).getNasledujiciKun() == null) {
					prevezenoKoni++;
				} else if(letouny.get(0).getNasledujiciKun().prevezen == false) {
					letouny.get(0).letDoFrancie(letouny.get(0).getNasledujiciKun());
					prevezenoKoni++;
					jeVeFrancii = true;
					letouny.get(0).getNasledujiciKun().prevezen = true;
				} else {
					prevezenoKoni++;
				}
				//leti z parize
			} else if(jeVeFrancii) {
				if(koneKPreprave.size() == 0 || letouny.get(0).getNasledujiciKun() == null) {
					letouny.get(0).letounPristal();
				} else {
					letouny.get(0).letZFrancieKeKoni(letouny.get(0).getNasledujiciKun());
					jeVeFrancii = false;
				}
				//leti do parize
			} else if(letouny.get(0).getM() < letouny.get(0).getAktNakl() + letouny.get(0).getNasledujiciKun().getM() + koneKPreprave.get(0).getM() ) {
				letouny.get(0).letDoFrancie(letouny.get(0).getNasledujiciKun());
				//koneKPreprave.remove(0);
				letouny.get(0).setNasledujiciKun(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				prevezenoKoni++;
				jeVeFrancii = true;
			} else{
				letouny.get(0).letKeKoni(letouny.get(0).getNasledujiciKun(), koneKPreprave.get(0));
				letouny.get(0).setNasledujiciKun(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				prevezenoKoni++;
			}
		}
		letouny.stream().filter(l -> l.getNasledujiciKun() != null).forEach(l -> l.letounPristal());
		System.out.println("Konec simulace");
		Collections.sort(letouny, (l1, l2) -> (int)(l2.getCas() - l1.getCas()));
		System.out.printf("Simulace trvala: %.0f",letouny.get(0).getCas());
	}	
			
			
			/*Kun nasledujiciKun = koneKPreprave.get(0);
			Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(nasledujiciKun, k1) - Utils.spoctiVzdalenost(nasledujiciKun, k2)));
			if(koneKPreprave.size() == 1) {
				letouny.get(0).letDoFrancie(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				jeVeFrancii = true;
				//leti z parize
			} else if(jeVeFrancii) {
				letouny.get(0).letZFrancieKeKoni(koneKPreprave.get(0));
				jeVeFrancii = false;
				//leti do parize
			} else if(letouny.get(0).getM() < letouny.get(0).getAktNakl() + koneKPreprave.get(0).getM() + koneKPreprave.get(1).getM() ) {
				letouny.get(0).letDoFrancie(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				jeVeFrancii = true;
			} else{
				letouny.get(0).letKeKoni(koneKPreprave.get(0), koneKPreprave.get(1));
				koneKPreprave.remove(0);
			}
			
		}
		letouny.get(0).letounPristal();
		System.out.println("Konec simulace");
	}*/
	
	public static void selekceLetadel() {
		serazeniLetounu();
		Double nejRychlost = letouny.get(0).getV();
		System.out.println(nejRychlost/5.0);
		for(int i = 0; i < letouny.size(); i++) {
			if(letouny.get(i).getV() < nejRychlost/10.0) {
				letouny.remove(i);
				i--;
				}
		}
	}
	
	public static void vypisCelkovehoCasu() {
		letouny.stream().forEach(l -> System.out.println(l));
		//letouny.stream().max((l1, l2) -> (int)(l1.getCas() - l2.getCas()));
	}
	
	public static void serazeniPodleCisla() {
		Collections.sort(letouny, (l1,l2) -> (int)(l2.getPoradi()- l1.getPoradi()));
		//System.out.
	}
	
	/**
	 * Metoda vypise pole koni do konzole
	 */
	public static void vypisKoni() {
		kone.stream().forEach(s -> System.out.println(s));
	}
	
	/**
	 * Metoda vypise pozici Parize
	 */
	public static void vypisParize() {
		System.out.printf("Pariz: x = %f; y = %f\n");
	}
	
	/**
	 * Metoda vypise pole letounu do konzole
	 */
	public static void vypisLetounu() {
		letouny.stream().forEach(s -> System.out.println(s));
	}

	/**
	 * Metoda seradi letouny podle rychlosti
	 */
	public static void serazeniLetounu() {
		Collections.sort(letouny, (l1,l2) -> (int)(l2.getV() * 1000 - l1.getV() * 1000));
	}
	
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
		parser("data/random10000.txt");
		//vypisKoni();
		vypisLetounu();
		simulace2();
		/*Simulace sim = new Simulace(kone);
		sim.createGraf();*/
		vypisDoSouboru();
		
		
		
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
