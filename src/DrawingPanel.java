import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
			g2.fillOval((int)((kun.getX() * scale) + posunutiX), (int)((kun.getY() * scale) + posunutiY), 4, 4);
		}
	}
	
	private void drawPariz(Graphics g2) {
		g2.setColor(Color.BLUE);
		g2.fillOval((int)((Main.a * scale) + posunutiX), (int)((Main.b * scale) + posunutiY)/2, 4, 4);
	}
	
	private void drawLetouny(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		for(Letoun letoun : Main.letouny) {
			g2.fillOval((int)((letoun.getX() * scale) + posunutiX), (int)((letoun.getY() * scale) + posunutiY), 4, 4);
		}
	}
}
