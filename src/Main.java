

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
	public static Kun[] kone;
	/** pole letounu */
	public static Letoun[] letouny;
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
	
	/**
	 * Metoda vypise pole koni do konzole
	 */
	public static void vypisKoni() {
		for(int i = 0; i < kone.length; i++) {
			System.out.println(kone[i].toString());
		}
	}
	
	/**
	 * Metoda vypise pole letounu do konzole
	 */
	public static void vypisLetounu() {
		for(int i = 0; i < letouny.length; i++) {
			System.out.println(letouny[i].toString());
		}
	}
	
	/*public static void nacitaniRychle(String jmenoSouboru) {
		//[] vstup = new ArrayList<>(); 
		
		try {
			List<String> seznamRadek = Files.readAllLines(Paths.get(jmenoSouboru));
			vstup = seznamRadek.stream().filter(s -> s.matches("[0-9]+")).toArray();
			for(String radka : seznamRadek) {
				System.out.println(radka);
			}
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
		long start, stop;
		start = System.currentTimeMillis();
		parser("data/parser.txt");
		/*System.out.printf("Pariz: x = %f, y = %f \n",a, b);
		System.out.printf("Pocet koni: %d \n", K);
		vypisKoni();
		System.out.printf("Pocet letounu: %d \n", L);
		vypisLetounu();*/
		stop = System.currentTimeMillis();
		System.out.println("Trvani programu: " + (stop - start));
		letouny[0].start();
		letouny[0].letKeKoni(kone[0], kone[1]);
		letouny[0].letDoFrancie(kone[1]);
		letouny[0].letZFrancieKeKoni(kone[2]);
		letouny[0].letounPristal();
		//nacitaniRychle("data/parser.txt");
	}

}
