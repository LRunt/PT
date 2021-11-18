package control;
import java.util.ArrayList;
import java.util.Collections;

import model.Kun;
import model.Letoun;
import view.Main;

/**
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.2 (02-11-2021)
 */
public class Simulace {
	/** Typedef koni z mainu*/
	private ArrayList<Kun> kone = Main.kone;
	/** Typedef letounu z mainu*/
	private ArrayList<Letoun> letouny = Main.letouny;
	/** Graf konu*/
	private Graf graf;
	/** Index kone v seznamu */ 
	private int index = 0;
	/** Odstranuje chybu */
	private static final int K = 1000;
	/** Kolikrat muze byt kun dal, nez letoun poleti do Parize */
	private static final int MAX_VZDALENOST = 2;
	private static Tisk tiskarna = new Tisk();

	/**
	 * 
	 */
	public Simulace() {
		//graf = createGraf();
		//greedySimulace();
	}

	public Graf createGraf() {
		@SuppressWarnings("unchecked")
		ArrayList<Kun> konici = (ArrayList<Kun>) kone.clone(); 
		Graf graf = new Graf(konici.size());
		for(int i = 0; i < konici.size(); i++) {
			Kun kun = Main.kone.get(i);
			Collections.sort(konici, (k1, k2) -> (int)(Utils.spoctiVzdalenost(kun, k1) - Utils.spoctiVzdalenost(kun, k2)));
			//double nejblizsiVzdalenost = Utils.spoctiVzdalenost(kun, konici.get(1)), 
			double aktualniVzdalenost = 0;
			for(int j = 1; j < konici.size(); j++) {
				aktualniVzdalenost = Utils.spoctiVzdalenost(kun, konici.get(j));
				graf.addEdge(i, j, aktualniVzdalenost);
				
			}
		}
		return graf;
	}
	
	public void simulace() {
		@SuppressWarnings("unchecked")
		ArrayList<Kun> konici = (ArrayList<Kun>) kone.clone(); 
		boolean jeVeFrancii = false;
		Utils.serazeniLetounu();
		Collections.sort(konici, (k1, k2) -> (int)(Utils.spoctiVzdalenost(letouny.get(0), k1) - Utils.spoctiVzdalenost(letouny.get(0), k2)));
		letouny.get(0).start();
		int aktKun, nasKun, prevezeniKone = 0, pocetKoni = konici.size();
		while(prevezeniKone <= pocetKoni) {
			aktKun = konici.get(0).getPoradi();
			nasKun = graf.edges[aktKun].neighbour;
			if(prevezeniKone == pocetKoni - 1) {
				letouny.get(0).letDoFrancie(Main.kone.get(aktKun));
				jeVeFrancii = true;
				prevezeniKone++;
				//leti z parize
			} else if(jeVeFrancii) {
				letouny.get(0).letZFrancieKeKoni(Main.kone.get(aktKun));
				//leti do parize
			} else if(letouny.get(0).getM() < letouny.get(0).getAktNakl() + Main.kone.get(aktKun).getM() + Main.kone.get(nasKun).getM()) {
				letouny.get(0).letDoFrancie(Main.kone.get(aktKun));
			} else{
				letouny.get(0).letKeKoni(Main.kone.get(aktKun), Main.kone.get(nasKun));
			}
		}
	}
	
	/**
	 * Metoda provede simulaci s jedním nebo vice letadly pomoci greedy algoritmu
	 */
	public void greedySimulace() {
		int pocetKoni = kone.size(), prevezenoKoni = 0;
		System.out.println("Zacatek simulace:");
		@SuppressWarnings("unchecked")
		ArrayList<Kun> koneKPreprave = (ArrayList<Kun>) kone.clone();
		Utils.selekceLetadel();
		Utils.serazeniPodleHmotnosti();
		Utils.serazeniPodleNosnosti();
		while(kone.get(0).getM() > letouny.get(0).getM()) {
			System.out.print(kone.get(0) + " se nevejde do zadneho letadla a tedy bezi sam do Parize.");
			kone.remove(0);
		}
		Utils.serazeniLetounu();
		while(pocetKoni >= prevezenoKoni) {
			Collections.sort(letouny,(l1, l2) -> (int)(l1.getCas() * K - l2.getCas() * K));
			Letoun aktLet = letouny.get(0);
			if(letouny.get(0).getNasledujiciKun() == null && koneKPreprave.size() >= 1) {
				letouny.get(0).start();
				Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(aktLet, k1) * K - Utils.spoctiVzdalenost(aktLet, k2) * K));
				letouny.get(0).setNasledujiciKun(koneKPreprave.get(0));
				koneKPreprave.remove(0);
			} else if(koneKPreprave.size() > 1) {
				Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(aktLet.getNasledujiciKun(), k1) * K - Utils.spoctiVzdalenost(aktLet.getNasledujiciKun(), k2) * K));
			}
			if(koneKPreprave.size() == 0) {
				if(letouny.get(0).getNasledujiciKun() == null) {
					prevezenoKoni++;
				} else if(letouny.get(0).getNasledujiciKun().prevezen == false) {
					letouny.get(0).letDoFrancie(letouny.get(0).getNasledujiciKun());
					prevezenoKoni++;
					letouny.get(0).getNasledujiciKun().prevezen = true;
				} else {
					prevezenoKoni++;
				}
				//leti z parize
			} else if(letouny.get(0).getJeVParizi()) {
				if(koneKPreprave.size() == 0 || letouny.get(0).getNasledujiciKun() == null) {
					letouny.get(0).letounPristal();
				} else {
					letouny.get(0).letZFrancieKeKoni(letouny.get(0).getNasledujiciKun());
				}
				//leti do parize
			} else if(letetDoParize(koneKPreprave, letouny.get(0))) {
				letouny.get(0).letDoFrancie(letouny.get(0).getNasledujiciKun());
				//koneKPreprave.remove(0);
				letouny.get(0).setNasledujiciKun(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				prevezenoKoni++;
			} else{
				letouny.get(0).letKeKoni(letouny.get(0).getNasledujiciKun(), koneKPreprave.get(index));
				letouny.get(0).setNasledujiciKun(koneKPreprave.get(index));
				koneKPreprave.remove(index);
				prevezenoKoni++;
			}
		}
		letouny.stream().filter(l -> l.getNasledujiciKun() != null).forEach(l -> l.letounPristal());
		System.out.println("Konec simulace");
		Collections.sort(letouny, (l1, l2) -> (int)(l2.getCas() - l1.getCas()));
		double casSimulace = letouny.get(0).getCas();
		System.out.printf("Simulace trvala: %.0f\n",casSimulace);
		System.out.printf("Bylo prepraveno %d koni.\n", kone.size());
		Main.retezec += String.format("Simulace trvala: %.0f",casSimulace);
		tiskarna.tiskni(casSimulace);
	}
	
	/**
	 * Metoda rozhodne zda letoun po nabrani kone poleti do Parize nebo k dalsimu koni
	 * @return
	 */
	public boolean letetDoParize(ArrayList<Kun> koneKPreprave, Letoun l) {
		index = 0;
		double vzdalenostDoParize = Utils.spoctiVzdalenost(l, Main.a, Main.b), vzdalenostKeKoni = 0;
		while(index < koneKPreprave.size() && vzdalenostDoParize * MAX_VZDALENOST > vzdalenostKeKoni) {
			vzdalenostKeKoni = Utils.spoctiVzdalenost(l, koneKPreprave.get(index));
			if(!(l.getM() < l.getAktNakl() + l.getNasledujiciKun().getM() + koneKPreprave.get(index).getM())) {
				return false;
			}
			index++;
		}
		return true;
	}
	
	/*/**
	 * Zakladni simulace pomoci greedy algoritmu
	 */
	/*public static void prvniSimulace() {
		System.out.println("Zacatek simulace:");
		ArrayList<Kun> koneKPreprave = (ArrayList<Kun>) Main.kone.clone();
		ArrayList<Letoun >letouny = Main.letouny;
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
	}*/
}
