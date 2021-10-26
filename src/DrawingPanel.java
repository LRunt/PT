import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 */

/**
 * @author Lukas Runt
 *
 */
public class DrawingPanel extends Component {
	
	private static double scale;
	private static double posunutiX;
	private static double posunutiY;
	private static boolean jeVeFrancii = false;
	private final int POMER_KRUHU = 4;

	public DrawingPanel() {
		this.setPreferredSize(new Dimension(810, 810));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		drawPozadi(g2);
		setScale();
		setPosunutiX();
		setPosunutiY();
		drawKone(g2);
		drawPariz(g2);
		drawLetouny(g2);
		drawSimulace(g2);
	}
	
	private void drawPozadi(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	private void setScale() {
		double iW = Utils.getNejvetsiXKone() - Utils.getNejmensiXKone();
		double iH = Utils.getNejvetsiYKone() - Utils.getNejmensiYKone();
		double scaleX = ((double)800) / iW;
		double scaleY = ((double)800) / iH;
		scale = Math.min(scaleX, scaleY);
	}
	
	private void setPosunutiX() {
		posunutiX = 0 - Main.a + this.getWidth()/2;
	}
	
	private void setPosunutiY() {
		posunutiY = 0 - Main.b + this.getHeight()/2;
	}
	
	private void drawKone(Graphics2D g2) {
		g2.setColor(Color.RED);
		for(Kun kun : Main.kone) {
			g2.drawOval((int)((kun.getX() * scale) + posunutiX) - POMER_KRUHU/2, (int)((kun.getY() * scale) + posunutiY)- POMER_KRUHU/2, POMER_KRUHU, POMER_KRUHU);
		}
	}
	
	private void drawPariz(Graphics g2) {
		g2.setColor(Color.BLUE);
		g2.drawOval((int)((Main.a * scale) + posunutiX) - POMER_KRUHU/2, (int)((Main.b * scale) + posunutiY) - POMER_KRUHU/2, POMER_KRUHU, POMER_KRUHU);
	}
	
	private void drawLetouny(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		for(Letoun letoun : Main.letouny) {
			g2.fillOval((int)((letoun.getX() * scale) + posunutiX), (int)((letoun.getY() * scale) + posunutiY), 5, 5);
		}
	}
	
	private void drawSimulace(Graphics g2) {
		System.out.println("Zacatek simulace:");
		if(Main.letouny.get(0).getX() == Main.a &&  Main.letouny.get(0).getX() == Main.b) {
			jeVeFrancii = true;
		}
		ArrayList<Kun> koneKPreprave = Main.kone;
		Main.letouny.get(0).start();
		Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(Main.letouny.get(0), k1) - Utils.spoctiVzdalenost(Main.letouny.get(0), k2)));
		while(koneKPreprave.size() > 0) {
			Kun nasledujiciKun = koneKPreprave.get(0);
			Collections.sort(koneKPreprave, (k1, k2) -> (int)(Utils.spoctiVzdalenost(nasledujiciKun, k1) * 1000 - Utils.spoctiVzdalenost(nasledujiciKun, k2) * 1000));
			//leti do francie
			if(koneKPreprave.size() == 1) {
				g2.drawLine((int)((Main.letouny.get(0).getX() * scale) + posunutiX), (int)((Main.letouny.get(0).getY() * scale) + posunutiY), (int)((koneKPreprave.get(0).getX() * scale) + posunutiX), (int)((koneKPreprave.get(0).getY() * scale) + posunutiY));
				Main.letouny.get(0).letDoFrancie(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				jeVeFrancii = true;
			//leti z francie
			} else if(jeVeFrancii) {
				g2.drawLine((int)((Main.letouny.get(0).getX() * scale) + posunutiX), (int)((Main.letouny.get(0).getY() * scale) + posunutiY), (int)((koneKPreprave.get(0).getX() * scale) + posunutiX), (int)((koneKPreprave.get(0).getY() * scale) + posunutiY));
				Main.letouny.get(0).letZFrancieKeKoni(koneKPreprave.get(0));
				jeVeFrancii = false;
			//leti do francie
			} else if(Main.letouny.get(0).getM() < Main.letouny.get(0).getAktNakl() + koneKPreprave.get(0).getM() + koneKPreprave.get(1).getM() ) {
				g2.drawLine((int)((Main.letouny.get(0).getX() * scale) + posunutiX), (int)((Main.letouny.get(0).getY() * scale) + posunutiY), (int)((koneKPreprave.get(0).getX() * scale) + posunutiX), (int)((koneKPreprave.get(0).getY() * scale) + posunutiY));
				g2.drawLine((int)((Main.a * scale) + posunutiX), (int)((Main.b * scale) + posunutiY), (int)((koneKPreprave.get(0).getX() * scale) + posunutiX), (int)((koneKPreprave.get(0).getY() * scale) + posunutiY));
				Main.letouny.get(0).letDoFrancie(koneKPreprave.get(0));
				koneKPreprave.remove(0);
				jeVeFrancii = true;
			//leti k dalsimu koni
			} else{
				g2.drawLine((int)((Main.letouny.get(0).getX() * scale) + posunutiX), (int)((Main.letouny.get(0).getY() * scale) + posunutiY), (int)((koneKPreprave.get(0).getX() * scale) + posunutiX), (int)((koneKPreprave.get(0).getY() * scale) + posunutiY));
				Main.letouny.get(0).letKeKoni(koneKPreprave.get(0), koneKPreprave.get(1));
				koneKPreprave.remove(0);
			}
			
		}
		Main.letouny.get(0).letounPristal();
		System.out.println("Konec simulace");
	}
}
