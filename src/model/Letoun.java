package model;
import java.util.ArrayList;
import java.util.List;

import control.Utils;
import view.Main;


/**
 * Instance tridy Letoun reprezentuje Letouny, ktere maji za ukol dopravit kone do Parize
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.2 (28-10-2021)
 */
public class Letoun {
	
	/** Uchovava zaznam kolik letounu existuje*/
	public static int pocetLetounu = 0;
	/** Cislo letounu (ID)*/
	private final int PORADI = ++pocetLetounu; 
	/** Souradnice letouny*/
	private double X;
	/** Souradnice letouny*/
	private double Y;
	/** Maximalni nosnost letounu*/
	private int M;
	/** Aktualni naklad letounu*/
	private int aktNakl;
	/** Koni na palube*/
	private int pocKoni;
	/** Rychlost letu*/
	private double V;
	/** Cas vylozeni koni na palube */
	private double celkemN;
	/** Cas letu letadla */
	private double cas = 0;
	/** Nasledujici kun */
	private Kun nasledujiciKun;
	/** Uchovava jestli je letoun v Parizi */
	private boolean jeVParizi;
	/** Celkova doba, kdy bylo letadlo ve vzduchu*/
	private double celkDobaLetu = 0;
	/** Uchovava udaje o casu, poctu koni na palube, mistech pristani a aktualnim zatizeni */
	public String statistika = String.format("Letoun %d\nCas;Pocet koni;X;Y;Zatizeni;Kone na palube\n", PORADI);
	/** Kone v letadle*/
	public List<Kun> nalKone = new ArrayList<Kun>();
	/** Startovni pozice letounu*/
	private final double startX;
	/** Startovni pozive letounu*/
	private final double startY;
	
	/**
	 * Konstruktor letounu
	 * @param X X-ova souradnice
	 * @param Y Y-ova souradnice
	 * @param M Nosnost letounu
	 * @param V Rychlost letu
	 */
	public Letoun(double X, double Y, int M, double V) {
		this.X = X;
		this.Y = Y;
		this.M = M;
		this.V = V;
		startX = X;
		startY = Y;
		aktNakl = 0;
		celkemN = 0;
		pocKoni = 0;
		jeVParizi = false;
	}
	
	/**
	 * Letoun startuje
	 */
	public void start() {
		System.out.printf("Cas: %.0f Letoun: %d, Start z mista: %.0f, %.0f\n", cas, PORADI, X, Y);
		Main.retezec += String.format("Cas: %.0f Letoun: %d, Start z mista: %.0f, %.0f\n", cas, PORADI, X, Y);
		editStatistika();
	}
	
	/**
	 * Letoun naklada kone a bude nakladat dalsiho
	 * @param kun1 Kun, ktery je nakladan
	 * @param kun2 Kun, ke kteremu se poleti
	 */ 
	public void letKeKoni(Kun kun1, Kun kun2) {
		presun(kun1.getX(), kun1.getY());
		System.out.printf("Cas: %.0f, Letoun: %d, Naklad kone: %d, Odlet v: %.0f, Let ke koni: %d\n", cas, PORADI, kun1.getPoradi(), cas + kun1.getN(), kun2.getPoradi());
		Main.retezec += String.format("Cas: %.0f, Letoun: %d, Naklad kone: %d, Odlet v: %.0f, Let ke koni: %d\n", cas, PORADI, kun1.getPoradi(), cas + kun1.getN(), kun2.getPoradi());
		nalKone.add(kun1);
		cas += kun1.getN();
		celkemN += kun1.getN();
		aktNakl += kun1.getM();
		pocKoni++;
		editStatistika();
	}
	
	/**
	 * Letoun nalozil kone a leti na olympianu
	 * @param kun1 Kun, ktery je nakladan
	 */
	public void letDoFrancie(Kun kun1) {
		presun(kun1.getX(), kun1.getY());
		System.out.printf("Cas: %.0f, Letoun: %d, Naklad kone: %d, Odlet v: %.0f, Let do Francie\n", cas, PORADI, kun1.getPoradi(), cas + kun1.getN());
		Main.retezec += String.format("Cas: %.0f, Letoun: %d, Naklad kone: %d, Odlet v: %.0f, Let do Francie\n", cas, PORADI, kun1.getPoradi(), cas + kun1.getN());
		nalKone.add(kun1);
		cas += kun1.getN();
		celkemN += kun1.getN();
		aktNakl += kun1.getM();
		pocKoni++;
		jeVParizi = true;
		presun(Main.a, Main.b);
		editStatistika();
	}
	
	/**
	 * Letoun vylozil kone a leti znovu
	 * @param kun1 Kun, ke kteremu se poleti
	 */
	public void letZFrancieKeKoni(Kun kun1) {
		System.out.printf("Cas: %.0f, Letoun: %d, Pristani ve Francii, Prevezeno koni: %d, Odlet v: %.0f, Let ke koni: %d\n", cas, PORADI, pocKoni, cas + celkemN, kun1.getPoradi());
		Main.retezec += String.format("Cas: %.0f, Letoun: %d, Pristani ve Francii, Prevezeno koni: %d, Odlet v: %.0f, Let ke koni: %d\n", cas, PORADI, pocKoni, cas + celkemN, kun1.getPoradi());
		cas += celkemN;
		celkemN = 0;
		aktNakl = 0;
		pocKoni = 0;
		jeVParizi = false;
		presun(kun1.getX(), kun1.getY());
		editStatistika();
		nalKone = new ArrayList<>();
	}
	
	/**
	 * Letoun vylozil kone v Parizi, kde zustava
	 */
	public void letounPristal() {
		presun(Main.a, Main.b);
		System.out.printf("Cas: %.0f, Letoun: %d, Pristani ve Francii, Prepraveno koni %d, Vylozeno v: %.0f\n", cas, PORADI, pocKoni, cas + celkemN);
		Main.retezec += String.format("Cas: %.0f, Letoun: %d, Pristani ve Francii, Prepraveno koni %d, Vylozeno v: %.0f\n", cas, PORADI, pocKoni, cas + celkemN);
		cas += celkemN;
		celkemN = 0;
		aktNakl = 0;
		pocKoni = 0;
		editStatistika();
		nalKone.stream().forEach(k -> k.setCas(cas));
		nalKone = new ArrayList<>();
	}
	
	/**
	 * Metoda pise statistiky konim, ktere letoun prepravuje
	 */
	public void editStatistika() {
		//Cas; aktualni pocet koni na palube; x-ova souradnice, y-ova souradnice; statistika vytizeni v %
		statistika += String.format("%.0f;%d;%.0f;%.0f;%d;", cas, pocKoni, X, Y, (int)(aktNakl/(M/100.0)));
		try {
			for(int i = 0; i < nalKone.size(); i++) {
				statistika += String.format("%d, ",nalKone.get(i).getPoradi());
				nalKone.get(i).statistika += String.format("%.0f;%.0f;%.0f\n", cas, X, Y);
			}
		}catch(Exception e) {
			statistika += "zadny kun";
		}
		statistika += "\n";
		
	}
	
	/**
	 * Metoda presouva letadlo z bodu A do bodu B
	 */
	private void presun(double x, double y) {
		double dobaLetu = Utils.spoctiVzdalenost(this , x, y) / V;
		cas += dobaLetu;
		setCelkDobaLetu(getCelkDobaLetu() + dobaLetu);
		setX(x);
		setY(y);
	}
	
	/**
	 * Vrati X-ovou souradnici letounu
	 * @return X-ova souradnice letounu
	 */
	public double getX() {
		return X;
	}
	
	/**
	 * Nastavi X-ovou souradnici letounu
	 * @param X nova X-ova souradnice
	 */
	public void setX(double X) {
		this.X = X;
	}
	
	/**
	 * Vrati Y-ovou souradnici letounu
	 * @return Y-ova souradnice letounu
	 */
	public double getY() {
		return Y;
	}
	
	/**
	 * Nastavi Y-ovou souradnici letounu
	 * @param Y nova Y-ova souradnice
	 */
	public void setY(double Y) {
		this.Y = Y;
	}
	
	/**
	 * Vrati nosnost letounu
	 * @return Nonost letounu
	 */
	public int getM() {
		return M;
	}
	
	/**
	 * Nastavi nosnost letounu
	 * @param M Nosnost letounu
	 */
	public void setM(int M) {
		if (M < 0) {
			System.out.println("Nosnost nesmi byt zaporna!");
		}else {
			this.M = M;
		}
	}
	
	/**
	 * Vrati rychlost letounu
	 * @return Rychlost letounu
	 */
	public double getV() {
		return V;
	}
	
	/**
	 * Nastavi rychlost letounu
	 * @param V Rychlost letounu
	 */
	public void setV(double V) {
		if (V < 0) {
			System.out.println("Rychlost nesmi byt zaporna!");
		}else {
			this.V = V;
		}
	}
	
	/**
	 * Vrati aktualni nalozeni letounu
	 * @return Aktualni nalozeni letounu
	 */
	public int getAktNakl() {
		return aktNakl;
	}

	/**
	 * Nastavi aktualni naklad letounu
	 * @param aktNakl Novy aktualni naklad letounu
	 */
	public void setAktNakl(int aktNakl) {
		this.aktNakl = aktNakl;
	}
	
	/**
	 * Vrati kone, pro ktereho letoun poleti
	 * @return Kun pro ktereho letoun poleti
	 */
	public Kun getNasledujiciKun() {
		return nasledujiciKun;
	}
	
	/**
	 * Nastavi nasledujiciho zastavku letounu
	 * @param nasledujiciKun Kun kam letoun poleti
	 */
	public void setNasledujiciKun(Kun nasledujiciKun) {
		this.nasledujiciKun = nasledujiciKun;
	}
	
	/**
	 * Vrati poradove cislo (ID) letounu
	 * @return Poradove cislo letounu (ID)
	 */
	public int getPoradi() {
		return PORADI;
	}
	
	/**
	 * Vrati cas, ktery letoun pracoval
	 * @return Cas, ktery letoun pracoval
	 */
	public double getCas() {
		return cas;
	}
	
	/**
	 * Nastavi novy cas, ktery letoun pracuje
	 * @param newCas Novy cas, ktery letoun pracuje
	 */
	public void setCas(double newCas) {
		if(newCas < 0) {
			System.out.println("Cas nemuze byt zaporny!");
		}else {
			this.cas = newCas;
		}
	}
	
	/**
	 * Vraci zda je letoun v Parizi
	 * @return true - letoun je v Parizi, false - letoun neni v Parizi
	 */
	public boolean isJeVParizi() {
		return jeVParizi;
	}
	
	/**
	 * Nastavi zda je, nebo neni letoun v Parizi
	 * @param je Stav letounu
	 */
	public void setJeVParizi(boolean je) {
		this.jeVParizi = je;
	}
	
	/**
	 * Vrati X-ovou startovaci souradnici letounu
	 * @return X-ova startovaci souradnice letounu
	 */
	public double getStartX() {
		return startX;
	}
	
	/**
	 * Vrati Y-ovou startovaci souradnici letounu
	 * @return Y-ova startovaci souradnice letounu
	 */
	public double getStartY() {
		return startY;
	}
	
	/**
	 * Vrati celkovou dobu vsech letu letounu
	 * @return Celkova doba vsech letu
	 */
	public double getCelkDobaLetu() {
		return celkDobaLetu;
	}

	/**
	 * Nastavi celkovou dobu po, ktery je letoun ve vzduchu
	 * @param celkDobaLetu Nova celkova doba letu
	 */
	public void setCelkDobaLetu(double celkDobaLetu) {
		if(celkDobaLetu < 0) {
			System.out.println("Celkova doba letu nemuze byt zaporna!");
		}else {
			this.celkDobaLetu = celkDobaLetu;
		}
	}
	
	/**
	 * Vrati textovou reprezentaci instance letounu
	 * @return Textova reprezentace instance letounu
	 */
	public String toString() {
		return String.format("Letoun %d: x = %.02f, y = %.02f, m = %d, v = %.02f", PORADI, X, Y, M, V);
	}

	

	

}
