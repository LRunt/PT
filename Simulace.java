import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 */

/**
 * @author Lukas
 *
 */
public class Simulace {
	
	private ArrayList<Kun> konici;
	private ArrayList<Letoun> letadla = Main.letouny;
	private Graf graf;

	/**
	 * 
	 */
	public Simulace(ArrayList<Kun> kone) {
		konici = (ArrayList<Kun>) kone.clone();
		graf = createGraf();
		simulace();
	}

	public Graf createGraf() {
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
		boolean jeVeFrancii = false;
		Main.serazeniLetounu();
		Collections.sort(konici, (k1, k2) -> (int)(Utils.spoctiVzdalenost(letadla.get(0), k1) - Utils.spoctiVzdalenost(letadla.get(0), k2)));
		letadla.get(0).start();
		int aktKun, nasKun, prevezeniKone = 0, pocetKoni = konici.size();
		while(prevezeniKone <= pocetKoni) {
			aktKun = konici.get(0).getPoradi();
			nasKun = graf.edges[aktKun].neighbour;
			if(prevezeniKone == pocetKoni - 1) {
				letadla.get(0).letDoFrancie(Main.kone.get(aktKun));
				jeVeFrancii = true;
				prevezeniKone++;
				//leti z parize
			} else if(jeVeFrancii) {
				letadla.get(0).letZFrancieKeKoni(Main.kone.get(aktKun));
				//leti do parize
			} else if(letadla.get(0).getM() < letadla.get(0).getAktNakl() + Main.kone.get(aktKun).getM() + Main.kone.get(nasKun).getM()) {
				letadla.get(0).letDoFrancie(Main.kone.get(aktKun));
			} else{
				letadla.get(0).letKeKoni(Main.kone.get(aktKun), Main.kone.get(nasKun));
			}
		}
		
		
	}
	
}
