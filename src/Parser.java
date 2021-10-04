import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0 (04-10-2021)
 */
public class Parser {
	
	Scanner sc;
	String retezec;
	

	/**
	 * 
	 */
	public Parser(String jmenoSouboru) throws IOException{
		sc = new Scanner(Paths.get(jmenoSouboru));
	}
	
	public int [] getSouradnice() {
		int [] souradnice = new int[2];
		int tmp = 0;
		while (sc.hasNext()) {
			retezec = sc.next();
			if(isKomentar(retezec))
				continue;
			else if(tmp == 0) {
				souradnice[0] = Integer.parseInt(retezec);
				tmp++;
			}
			else {
				souradnice[1] = Integer.parseInt(retezec);
				break;
			}
		}
		return souradnice;
	}
	
	public Kun[] getKone() {
		Kun[] kone = null;
		Kun kun = new Kun();
		int pocetK = 0, aktK = 0, tmp = 0;
		while (sc.hasNext()) {
			retezec = sc.next();
			if(isKomentar(retezec))
				continue;
			switch (tmp) {
				case 0: 
					pocetK = Integer.parseInt(retezec);
					kone = new Kun[pocetK];
					tmp = 1;
					break;
				case 1:
					kun = new Kun();
					kun.setX(Integer.parseInt(retezec));
					tmp = 2;
					break;
				case 2:
					kun.setY(Integer.parseInt(retezec));
					tmp = 3;
					break;
				case 3:
					kun.setM(Integer.parseInt(retezec));
					tmp = 4;
					break;
				case 4:
					kun.setN(Integer.parseInt(retezec));
					kone[aktK] = kun;
					aktK++;
					tmp = 1;
					break;	
			}
			if(aktK == pocetK)
				break;
		}
		return kone;
	}
	
	public Letoun[] getLetouny() {
		Letoun[] letouny = null;
		Letoun let = new Letoun();
		int pocetL = 0, aktL = 0, tmp = 0;
		while (sc.hasNext()) {
			retezec = sc.next();
			if(isKomentar(retezec))
				continue;
			switch (tmp) {
				case 0: 
					pocetL = Integer.parseInt(retezec);
					letouny = new Letoun[pocetL];
					tmp = 1;
					break;
				case 1:
					let = new Letoun();
					let.setX(Integer.parseInt(retezec));
					tmp = 2;
					break;
				case 2:
					let.setY(Integer.parseInt(retezec));
					tmp = 3;
					break;
				case 3:
					let.setM(Integer.parseInt(retezec));
					tmp = 4;
					break;
				case 4:
					let.setV(Double.parseDouble(retezec));
					letouny[aktL] = let;
					aktL++;
					tmp = 1;
					break;	
			}
			if(aktL == pocetL)
				break;
		}
		return letouny;
	}
	
	private boolean isKomentar(String retezec) {
		if(retezec.equals(":-)")) {
			sc.nextLine();
			return true;
		}
		return false;
	}
}
