package control;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Kun;
import model.Letoun;
import view.Main;

/**
 * Trida {@code Simulace} se stara o simulavani prepravy koni do Parize
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 2.2 (20-11-2021)
 */
public class Simulace {
	/** Typedef koni z mainu*/
	private final List<Kun> kone = Main.kone;
	/** Typedef letounu z mainu*/
	private final List<Letoun> letouny = Main.letouny;
	/** Graf konu*/
	private Graf graf;
	/** Index kone v seznamu */ 
	private int index = 0;
	/** Odstranuje chybu */
	private static final int K = 1000;
	/** Kolikrat muze byt kun dal, nez letoun poleti do Parize */
	private static final int MAX_VZDALENOST = 2;
	/** Instance, ktera tiskne statistiky */
	private static Tisk tiskarna = new Tisk();

	/**
	 * Metoda vytvori graf
	 * @return Graf
	 */
	public Graf createGraf() {
		@SuppressWarnings("unchecked")
		List<Kun> konici = (List<Kun>) ((ArrayList<Kun>) kone).clone(); 
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
	
	/**
	 * Metoda provede simulaci pomoci grafu
	 */
	public void simulace() {
		@SuppressWarnings("unchecked")
		List<Kun> konici = (List<Kun>) ((ArrayList<Kun>) kone).clone(); 
		boolean jeVeFrancii = false;
		Utils.serazeniLetounu(letouny);
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
	 * Metoda provede simulaci s jedn�m nebo vice letadly pomoci greedy algoritmu
	 */
	@SuppressWarnings("unchecked")
	public void greedySimulace() {
		int pocetKoni = kone.size(), prevezenoKoni = 0;
		System.out.println("Zacatek simulace:");
		List<Kun> koneKPreprave = (List<Kun>) ((ArrayList<Kun>) kone).clone();
		List<Letoun> letounyKPreprave = (ArrayList<Letoun>) ((ArrayList<Letoun>) letouny).clone();
		if(letounyKPreprave.size() == 0) {
			System.out.println("Zadne letadlo neni k dispozici.\nVsechny kone museji jit do Parize pesky.");
		}
		if(koneKPreprave.size() == 0) {
			System.out.println("Neni potreba prepravit zadne kone.");
		}
		Utils.selekceLetadel(letounyKPreprave);
		vejdouSeVsichni(letounyKPreprave, koneKPreprave);
		Utils.selekceLetadel(letounyKPreprave);
		while(pocetKoni >= prevezenoKoni) {
			Collections.sort(letounyKPreprave,(l1, l2) -> (int)(l1.getCas() * K - l2.getCas() * K));
			Letoun aktLet = letounyKPreprave.get(0);
			if(letounyKPreprave.get(0).getNasledujiciKun() == null && koneKPreprave.size() >= 1) {
				letounyKPreprave.get(0).start();
				Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(aktLet, k1) * K - Utils.spoctiVzdalenost(aktLet, k2) * K));
				letounyKPreprave.get(0).setNasledujiciKun(koneKPreprave.get(0));
				koneKPreprave.remove(0);
			} else if(koneKPreprave.size() > 1) {
				Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(aktLet.getNasledujiciKun(), k1) * K - Utils.spoctiVzdalenost(aktLet.getNasledujiciKun(), k2) * K));
			}
			if(koneKPreprave.size() == 0) {
				Collections.sort(letounyKPreprave,(l1, l2) -> (int)(l1.getCas() * K - l2.getCas() * K));
				for (Letoun letoun : letounyKPreprave) {
					if(!letoun.getNasledujiciKun().isStav()) {
						letoun.letDoFrancie(letoun.getNasledujiciKun());
						prevezenoKoni++;
						letoun.getNasledujiciKun().setStav(true);
					}
				}
				break;
				//leti z parize
			} else if(letounyKPreprave.get(0).isJeVParizi()) {
				if(koneKPreprave.size() == 0 || letounyKPreprave.get(0).getNasledujiciKun() == null) {
					letounyKPreprave.get(0).letounPristal();
				} else {
					letounyKPreprave.get(0).letZFrancieKeKoni(letounyKPreprave.get(0).getNasledujiciKun());
				}
				//leti do parize
			} else if(letetDoParize(koneKPreprave, letounyKPreprave.get(0))) {
				letounyKPreprave.get(0).letDoFrancie(letounyKPreprave.get(0).getNasledujiciKun());
				//koneKPreprave.remove(0);
				letounyKPreprave.get(0).setNasledujiciKun(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				prevezenoKoni++;
			} else{
				letounyKPreprave.get(0).letKeKoni(letounyKPreprave.get(0).getNasledujiciKun(), koneKPreprave.get(index));
				letounyKPreprave.get(0).setNasledujiciKun(koneKPreprave.get(index));
				koneKPreprave.remove(index);
				prevezenoKoni++;
			}
		}
		letounyKPreprave.stream().filter(l -> l.getNasledujiciKun() != null).forEach(l -> l.letounPristal());
		System.out.println("Konec simulace");
		Collections.sort(letounyKPreprave, (l1, l2) -> (int)(l2.getCas() - l1.getCas()));
		double casSimulace = letounyKPreprave.get(0).getCas();
		System.out.printf("Simulace trvala: %.0f\n",casSimulace);
		Main.retezec += String.format("Simulace trvala: %.0f",casSimulace);
		tiskarna.tiskni(casSimulace);
		nastavStartovniStav();
	}
	
	/**
	 * Metoda zjisti zda se vejdou vsechny kone do letadla, ty cose nevejdou odstrani
	 */
	private void vejdouSeVsichni(List<Letoun> letadla, List<Kun> kone) {
		Utils.serazeniPodleNosnosti(letadla);
		for (int i = 0; i < kone.size(); i++) {
			if(kone.get(i).getM() > letadla.get(0).getM()) {
				System.out.print(kone.get(i) + " se nevejde do zadneho letadla a tedy bezi sam do Parize.\n");
				kone.remove(i); 
				i--;
			}
		}
	}
	
	/**
	 * Metoda nastavi vsem letounum startovni pozici
	 */
	private void nastavStartovniStav() {
		letouny.stream().forEach(l -> l.setX(l.getStartX()));
		letouny.stream().forEach(l -> l.setY(l.getStartY()));
		letouny.stream().forEach(l -> l.setCas(0));
		letouny.stream().forEach(l -> l.statistika = String.format("Letoun %d\nCas;Pocet koni;X;Y;Zatizeni;Kone na palube\n", l.getPoradi()));
		letouny.stream().forEach(l -> l.setJeVParizi(false));
		letouny.stream().forEach(l -> l.setNasledujiciKun(null));
		letouny.stream().forEach(l -> l.setCelkDobaLetu(0));
		kone.stream().forEach(k -> k.statistika = String.format("Kun %d\nCas;X;Y\n", k.getPoradi()));
		kone.stream().forEach(k -> k.setStav(false));
	}
	
	/**
	 * Metoda rozhodne zda letoun po nabrani kone poleti do Parize nebo k dalsimu koni
	 * @return true - letoun poleti do Parize, false - letoun nepoleti do Parize
	 */
	private boolean letetDoParize(List<Kun> koneKPreprave, Letoun l) {
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
}
