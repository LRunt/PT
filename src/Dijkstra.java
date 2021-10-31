import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 
 * @author Lukas Runt, Martina Mlezivova
 * @version 1.1 (23-10-2021)
 */
public class Dijkstra {
    private class Node implements Comparable<Node> {
        public int kun;
        public double cas;

        public Node(int kun, double cas) {
            this.kun = kun;
            this.cas = cas;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cas < o.cas)
                return -1;
            else if(this.cas > o.cas)
                return 1;
            else
                return 0;
        }
    }
 /*   private double[][] matrixKoni;
    private double[][] matrixLetounKun;

    public void matrixVzdalenosti(ArrayList<Kun> kone, ArrayList<Letoun> letouny) {
        matrixKoni = new double[kone.size()][kone.size()];
        for(int i = 0; i < kone.size(); i++) {
            for(int j = 0; j < i; j++) {
                if(i == j)
                    matrixKoni[i][j] = 0;
                else
                    matrixKoni[i][j] = matrixKoni[j][i] = Utils.spoctiVzdalenost(kone.get(i), kone.get(j));
            }
        }
        matrixLetounKun = new double[letouny.size()][kone.size()];
        for(int i = 0; i < letouny.size(); i++) {
            for(int j = 0; j < kone.size(); j++) {
                matrixLetounKun[i][j] = Utils.spoctiVzdalenost(letouny.get(i), kone.get(j));
            }
        }
    }
  */


    public void dijkstra(ArrayList<Kun> kone, ArrayList<Letoun> letouny, double a, double b) {
        double [] casy = new double[kone.size()];
        double [] nosnost = new double[kone.size()];
        int [] predchudce = new int[kone.size()];
        int [] koneNaPalube = new int[kone.size()];

        for (int i=0;i < letouny.size();i++) {
            PriorityQueue<Node> pq = new PriorityQueue<>();

            for (int j = 0; j < kone.size(); j++) {
                casy[j] = Utils.spoctiCas(letouny.get(i), kone.get(j));
                //TODO kontrola nosnosti
                nosnost[j] = letouny.get(i).getM() - kone.get(j).getM();
                predchudce[j] = -1; //barvení
                koneNaPalube[j] = 1;
                pq.add(new Node(j, casy[j]));
            }

            while(!pq.isEmpty()) {
                Node aktKun = pq.remove();

                for (int j = 0; j < kone.size(); j++) {
                    if(j == aktKun.kun || predchudce[j] == aktKun.kun)
                        continue;
                    if(predchudce[j] == -1) {
                        if(( nosnost[aktKun.kun] -  kone.get(j).getM() ) >= 0) {
                            casy[j] = casy[aktKun.kun] + Utils.spoctiCas(letouny.get(i).getV(), kone.get(aktKun.kun) , kone.get(j));
                            nosnost[j] = nosnost[aktKun.kun] -  kone.get(j).getM();
                            predchudce[j] = aktKun.kun;
                            koneNaPalube[j] = koneNaPalube[aktKun.kun] + 1;
                            pq.add(new Node(j, casy[j]));
                        }
                    }
                    else if(koneNaPalube[aktKun.kun] + 1 == koneNaPalube[j]) {
                        if(( nosnost[aktKun.kun] -  kone.get(j).getM() ) >= 0) {
                            if(casy[j] > (casy[aktKun.kun] + Utils.spoctiCas(letouny.get(i).getV(), kone.get(aktKun.kun) , kone.get(j)))) {
                                casy[j] = casy[aktKun.kun] + Utils.spoctiCas(letouny.get(i).getV(), kone.get(aktKun.kun) , kone.get(j));
                                nosnost[j] = nosnost[aktKun.kun] -  kone.get(j).getM();
                                predchudce[j] = aktKun.kun;
                                koneNaPalube[j] = koneNaPalube[aktKun.kun] + 1;
                                pq.add(new Node(j, casy[j]));
                            }

                        }
                    }
                }
                //TODO hotovej uzel
                //predchudce[aktKun.kun] = -2; //hotovej kun, nazdar
            }
        }


    }
}