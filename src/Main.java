import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Lukas Runt
 * @version 1.0 (24-09-2021)
 */
public class Main {
	
	/** Souradnice Parize X*/
	public static int a;
	/** Souradnice Parize Y*/
	public static int b;
	/** pocet koni*/
	public static int K;
	/** pocet letounu*/
	public static int L;
	
	
	/**
	 * Metoda na nacitani dat ze souboru
	 * :-) - oznaceni komentare -> necist data az do konce radku
	 * @param jmenoSouboru jak se jmenuje soubor, ze ktereho ziskame data
	 */
	public static void parser(String jmenoSouboru) {
		String retezec;
		try(Scanner sc = new Scanner(Paths.get(jmenoSouboru))){
			while(sc.hasNext()) {
				retezec = sc.next();
				if(retezec.equals(":-)")) {
					sc.nextLine();
				} else {
					System.out.println(retezec);
				}
			}
		}catch (Exception ex){
			System.out.println("Doslo k chybe pri cteni souboru: " + jmenoSouboru + "(" + ex.getMessage() + ")");
		}
	}

	/**
	 * Vstupni metoda
	 * @param args
	 */
	public static void main(String[] args) {
		parser("data/tutorial.txt");
		
	}

}
