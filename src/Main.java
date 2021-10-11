import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.2 (04-10-2021)
 */
public class Main {
	
	/** Souradnice Parize X*/
	public static double a;
	/** Souradnice Parize Y*/
	public static double b;
	/** pocet koni*/
	public static int K;
	/** pocet letounu*/
	public static int L;
	/** pole koni */
	public static ArrayList<Kun> kone;
	/** pole letounu */
	public static ArrayList<Letoun> letouny;
	/** Cas od zacatku simulace */
	public static double cas;
	
	/**
	 * Metoda na nacitani dat ze souboru :-) - oznaceni komentare -> necist data az
	 * do konce radku
	 * 
	 * @param jmenoSouboru jak se jmenuje soubor, ze ktereho ziskame data
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
	
	public static void simulace() {
		System.out.println("Zacatek simulace");
		ArrayList<Kun> koneKPreprave = kone;
		letouny.get(0).start();
		while(koneKPreprave.size() > 0) {
			Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(letouny.get(0), k1) - Utils.spoctiVzdalenost(letouny.get(0), k2)));
			if(koneKPreprave.size() == 1) {
				letouny.get(0).letDoFrancie(koneKPreprave.get(0));
				koneKPreprave.remove(0);
			}else if(letouny.get(0).getX() == a &&  letouny.get(0).getX() == b) {
				letouny.get(0).letZFrancieKeKoni(koneKPreprave.get(0));
				koneKPreprave.remove(0);
			} else{
				letouny.get(0).letKeKoni(koneKPreprave.get(0), koneKPreprave.get(1));
				koneKPreprave.remove(0);
			}
			
		}
		letouny.get(0).letounPristal();
		System.out.println("Konec simulace");
	}
	
	/**
	 * Metoda vypise pole koni do konzole
	 */
	public static void vypisKoni() {
		kone.stream().forEach(s -> System.out.println(s));
	}
	
	/**
	 * Metoda vypise pole letounu do konzole
	 */
	public static void vypisLetounu() {
		letouny.stream().forEach(s -> System.out.println(s));
	}
	
	/*public static void nacitaniRychle(String jmenoSouboru) {
		try {
			List<String> seznamRadek = Files.readAllLines(Paths.get(jmenoSouboru));
			seznamRadek.stream().forEach(s -> s.replace(":-)", "☺"));
			seznamRadek.stream().forEach(s -> s = s.split("☺")[0]);
			seznamRadek.stream().forEach(s -> System.out.println(s));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Vstupni metoda
	 * @param args
	 */
	public static void main(String[] args) {
		parser("data/parser.txt");
		simulace();
	}

}
