/**
 * Instance tridy Letoun reprezentuje Letouny, ktere maji za ukol dopravit kone do Parize
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.1 (01-10-2021)
 */
public class Letoun {
	
	/** Uchovava zaznam kolik letounu existuje*/
	private static int pocetLetounu = 0;
	/** Cislo letounu (ID)*/
	private final int PORADI = pocetLetounu++; 
	/** Souradnice letouny*/
	private double X;
	/** Souradnice letouny*/
	private double Y;
	/** Maximalni nosnost letounu*/
	private int M;
	/** Aktualni naklad letounu*/
	private int aktNakl;
	/** Rychlost letu*/
	private double V;
	/** Cas vylozeni koni na palube */
	private double celkemN;
	/** cas letu letadla */
	private double cas = 0;
	
	/**
	 * Konstruktor letouny
	 * @param X - x-ova souradnice
	 * @param Y - y-ova souradnice
	 * @param M - nosnost letounu
	 * @param V - rychlost letu
	 */
	public Letoun(double X, double Y, int M, double V) {
		this();
		this.X = X;
		this.Y = Y;
		this.M = M;
		this.V = V;
	}
	
	/*Defaultni konstruktor*/
	public Letoun() {
		setAktNakl(0);
		celkemN = 0;
	}
	
	/**
	 * Letoun startuje
	 */
	public void start() {
		System.out.printf("Cas: %.0f Letoun: %d, Start z mista: %.0f, %.0f\n", cas, PORADI, X, Y);
	}
	
	/**
	 * Letoun naklada kone a bude nakladat dalsiho
	 * @param Kun, ktery se naklada
	 * @param Kun ke kteremmu se poleti
	 */ 
	public void letKeKoni(Kun kun1, Kun kun2) {
		presun(kun1.getX(), kun1.getY());
		System.out.printf("Cas: %.0f, Letoun: %d, Naklad kone: %d, Odlet v: %.0f, Let ke koni: %d\n", cas, PORADI, kun1.getPoradi(), cas + kun1.getN(), kun2.getPoradi());
		cas += kun1.getN();
		celkemN += kun1.getN();
		aktNakl += kun1.getM();
	}
	
	/**
	 * Letoun nalozil kone a leti na olympianu
	 * @param kun1 Kun, ktery se naklada
	 */
	public void letDoFrancie(Kun kun1) {
		presun(kun1.getX(), kun1.getY());
		System.out.printf("Cas: %.0f, Letoun: %d, Naklad kone: %d, Odlet v: %.0f, Let do Francie\n", cas, PORADI, kun1.getPoradi(), cas + kun1.getN());
		cas += kun1.getN();
		celkemN += kun1.getN();
		aktNakl += kun1.getM();
		presun(Main.a, Main.b);
	}
	
	/**
	 * Letoun vylozil kone a leti znovu
	 * @param kun1 Kun, ke kteremu se poleti
	 */
	public void letZFrancieKeKoni(Kun kun1) {
		System.out.printf("Cas: %.0f, Letoun: %d, Pristani ve Francii, Odlet v: %.0f, Let ke koni: %d\n", cas, PORADI, cas + celkemN, kun1.getPoradi());
		cas += celkemN;
		celkemN = 0;
		aktNakl = 0;
		presun(kun1.getX(), kun1.getY());
	}
	
	/**
	 * Letoun vyloozil kone v Parizi na zustava
	 */
	public void letounPristal() {
		presun(Main.a, Main.b);
		System.out.printf("Cas: %.0f, Letoun: %d, Pristani ve Francii, Vylozeno v: %.0f\n", cas, PORADI, cas + celkemN);
		cas += celkemN;
		celkemN = 0;
		aktNakl = 0;
		
	}
	
	/**
	 * Metoda presouva letadlo z bodu A do bodu B
	 */
	private void presun(double x, double y) {
		cas += Utils.spoctiVzdalenost(this , x, y) / V;
		setX(x);
		setY(y);
	}
	
	public double getX() {
		return X;
	}
	
	public void setX(double X) {
		this.X = X;
	}
	
	public double getY() {
		return Y;
	}
	
	public void setY(double Y) {
		this.Y = Y;
	}
	
	public int getM() {
		return M;
	}
	
	public void setM(int M) {
		this.M = M;
	}
	
	public double getV() {
		return V;
	}
	
	public void setV(double V) {
		this.V = V;
	}
	
	public int getAktNakl() {
		return aktNakl;
	}

	public void setAktNakl(int aktNakl) {
		this.aktNakl = aktNakl;
	}
	
	/**
	 * @return textova reprezentace instance letounu
	 */
	public String toString() {
		return String.format("Letoun %d: x = %f, y = %f, m = %d, v = %f", PORADI, X, Y, M, V);
	}

	

}
