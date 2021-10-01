import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Lukas Runt
 * @version 1.0 (24-09-2021)
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
	public static Letoun[] letadla;
	
	
	/**
	 * Metoda na nacitani dat ze souboru
	 * :-) - oznaceni komentare -> necist data az do konce radku
	 * @param jmenoSouboru jak se jmenuje soubor, ze ktereho ziskame data
	 */
	public static void parser(String jmenoSouboru) {
		String retezec;
		try(Scanner sc = new Scanner(Paths.get(jmenoSouboru))){
			int cast = 0, aktK = 0, aktL = 0, pocetAtributu = 4;
			while(sc.hasNext()) {
				retezec = sc.next();
				
				if(retezec.equals(":-)")) {
					sc.nextLine();
				} else {
					switch (cast) {
						case 0:
							a = Double.parseDouble(retezec);
							cast++;
							break;
						case 1:
							b = Double.parseDouble(retezec);
							cast++;
							break;
						case 2:
							K = Integer.parseInt(retezec);
							cast++;
							kone = new Kun[K];
							break;
						case 3:
							double x = 0, y = 0;
							int m = 0, n = 0, p = 0;
							while(aktK < K) {
								if(retezec.equals(":-)")) {
									sc.nextLine();
								} else {
									System.out.println(retezec);
									switch(p) {
									case 0:
										x = Double.parseDouble(retezec);
										p++;
										break;
									case 1:
										y = Double.parseDouble(retezec);
										p++;
										break;
									case 2:
										m = Integer.parseInt(retezec);
										p++;
										break;
									case 3:
										n = Integer.parseInt(retezec);
										p = 0;
										kone[aktK] = new Kun(x, y, m, n);
										aktK++;
										break;
									}
								}
								if(aktK != K) {
									retezec = sc.next();
								}
							}
							cast++;
							break;
						case 4:
							L = Integer.parseInt(retezec);
							cast++;
							letadla = new Letoun[L];
							break;
						case 5:
							double X = 0, Y = 0;
							int M = 0, P = 0;
							double V;
							while(aktL < L) {
								if(retezec.equals(":-)")) {
									sc.nextLine();
								} else {
									System.out.println(retezec);
									switch(P) {
									case 0:
										X = Double.parseDouble(retezec);
										P++;
										break;
									case 1:
										Y = Double.parseDouble(retezec);
										P++;
										break;
									case 2:
										M = Integer.parseInt(retezec);
										P++;
										break;
									case 3:
										V = Double.parseDouble(retezec);
										P = 0;
										letadla[aktL] = new Letoun(X, Y, M, V);
										aktL++;
										break;
									}
								}
								if(aktL != L) {
									retezec = sc.next();
								}
							}
							cast++;
							break;
					}
					System.out.println(retezec);
				}
			}
		}catch (Exception ex){
			System.out.println("Doslo k chybe pri cteni souboru: " + jmenoSouboru + "(" + ex.getMessage() + ")");
		}
	}
	
	/**
	 * Metoda vypise pole koni do konmzole
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
		for(int i = 0; i < letadla.length; i++) {
			System.out.println(letadla[i].toString());
		}
	}

	/**
	 * Vstupni metoda
	 * @param args
	 */
	public static void main(String[] args) {
		parser("data/grid2000.txt");
		System.out.printf("Pariz: x = %f, y = %f \n",a, b);
		System.out.printf("Pocet koni: %d \n", K);
		vypisKoni();
		System.out.printf("Pocet letounu: %d \n", L);
		vypisLetounu();
	}

}
