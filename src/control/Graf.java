package control;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Trida {@code Graf} reprezentuje instanci grafu
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0 (30-10-2021)
 */
public class Graf {

		/** Sousedi jednotlivych vrcholu (hrany) */
		public Link[] edges;
		/** oznaceni, jestli byl vrchol grafu navstiven*/
		public boolean[] navstiveno;
		
		public Graf(int vertexCount) {
			this.edges = new Link[vertexCount];
			this.navstiveno = new boolean[vertexCount];
		}

		/**
		 * Prida do grafu novou obousmernou hranu
		 * @param start cislo pocatecniho vrcholu
		 * @param vaha vaha hrany
		 * @param end cislo koncoveho vrcholu 
		 */
		public void addEdge(int start, int end, double vaha) {
			Link nS = new Link(end, vaha, edges[start]);		
			edges[start] = nS;
			Link nE = new Link(start, vaha, edges[end]);
			edges[end] = nE;
		}
		
		/**
		 * Metoda pocita nejkratsi vzdalenost mezi vrcholy, pokud cesta mezi vrcholy neexistuje vraci se -1
		 * @param start startovni bod
		 * @param end koncovy bod
		 * @return vzdalenost mezi vrcholy
		 */
		public int shortestPathLength(int start, int end) {
			int[] mark = new int[edges.length];
			int[] vzdalenost = new int[edges.length];
			mark[start] = 1;
			vzdalenost[start] = 0;
			
			Queue<Integer> q = new LinkedList<>();
			q.add(start);
			while(!q.isEmpty()) {
				int v = q.poll();
				if(v == end) {
					break;
				}
				Link l = edges[v];
				while(l != null) {
					int n = l.neighbour;
					l = l.next;
					if(vzdalenost[n] == 0) {
						vzdalenost[n] = vzdalenost[v] + 1;
					}
					if (mark[n] == 0) {
						mark[n] = 1;
						q.add(n);
					}
				}
				mark[v] = 2;
			}
			if(mark[end] == 0) {
				return -1; //vrchol nenalezen
			}
			return vzdalenost[end];
		}

}

/**
 * Prvek spojoveho seznamu pro ulozeni sousedu vrcholu grafu
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.0
 */
class Link {
	/** Cislo souseda */
	int neighbour;
	/** vaha hrany*/
	double vaha;
	/** Odkaz na dalsiho souseda */
	Link next;
  
	/**
	 * Vytvori novy prvek seznamu pro ulozeni souseda vrcholu grafu
	 * @param n cislo souseda
	 * @param next odkaz na dalsiho souseda
	 */
	public Link(int n, double vaha, Link next) {
		this.neighbour = n;
		this.vaha = vaha;
		this.next = next;
	}
}
