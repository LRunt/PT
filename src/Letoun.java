/**
 * @author Lukas Runt
 * @version 1.0 (25-09-2021)
 */
public class Letoun {
	
	/** Uchovava zaznam kolik letounu existuje*/
	private static int pocetLetounu = 0;
	/** Cislo letounu (ID)*/
	private int cislo; 
	/** Souradnice letadla*/
	private double X;
	/** Souradnice letadla*/
	private double Y;
	/** Maximalni nosnost letounu*/
	private int M;
	/** Rychlost letu*/
	private double V;
	/** Cas vylozeni koni na palube */
	private int celkemN = 0;
	
	/**
	 * Konstruktor letadla
	 * @param X - x-ova souradnice
	 * @param Y - y-ova souradnice
	 * @param M - nosnost letounu
	 * @param V - rychlost letu
	 */
	public Letoun(double X, double Y, int M, double V) {
		this.X = X;
		this.Y = Y;
		this.M = M;
		this.V = V;
		cislo = pocetLetounu++;
	}
	
	/**
	 * Letoun startuje
	 */
	public void start() {
		System.out.printf("Cas: %f, Letoun: %d, Start z mista: %f, %f\n", Main.cas, cislo, X, Y);
	}
	
	/**
	 * Letoun naklada kone a bude nakladat dalsiho
	 * @param Kun, ktery se naklada
	 * @param Kun ke kteremmu se poleti
	 */ 
	public void letKeKoni(Kun kun1, Kun kun2) {
		System.out.printf("Cas: %f, Letoun: %d, Naklad kone: %d, Odlet v: %f, Let ke koni: %d\n", Main.cas, cislo, kun1.getCislo(), Main.cas + kun1.getN(), kun2.getCislo());
		Main.cas += kun1.getN();
		celkemN += kun1.getN();
	}
	
	/**
	 * Letoun nalozil kone a leti na olympianu
	 * @param kun1 Kun, ktery se naklada
	 */
	public void letDoFrancie(Kun kun1) {
		System.out.printf("Cas: %f, Letoun: %d, Naklad kone: %d, Odlet v: %f, Let do Francie\n", Main.cas, cislo, kun1.getCislo(), Main.cas + kun1.getN());
		Main.cas += kun1.getN();
		celkemN += kun1.getN();
	}
	
	/**
	 * Letoun vylozil kone a leti znovu
	 * @param kun1 Kun, ke kteremu se poleti
	 */
	public void letZFrancieKeKoni(Kun kun1) {
		System.out.printf("Cas: %f, Letoun: %d, Pristani ve Francii, Odlet v: %f, Let ke koni: %d\n", Main.cas, cislo, Main.cas + celkemN, cislo);
		Main.cas += celkemN;
		celkemN = 0;
	}
	
	public void letounPristal() {
		System.out.printf("Cas: %f, Letoun: %d, Pristani ve Francii, Vylozeno v: %f\n", Main.cas, cislo, Main.cas + celkemN);
		Main.cas += celkemN;
		celkemN = 0;
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
	
	public String toString() {
		return String.format("Letoun %d: x = %f, y = %f, m = %d, v = %f", cislo, X, Y, M, V);
	}

}
