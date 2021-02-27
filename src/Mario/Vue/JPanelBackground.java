package Mario.Vue;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
* Classe vue avec un dessin en graphise Java
* @author Robert
*/
public class JPanelBackground extends JPanel {
	private boolean pleinEcran = false;
	Color orange = new Color(255, 153, 0);
	Color bleu = new Color(0, 204, 255);
	Color jaune = new Color(255, 255, 0);
	double ratio = 1;

	public void setPleinEcran(boolean pleinEcran) {
		this.pleinEcran = pleinEcran;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g.create();
		super.paintComponent(g2D);

		if (pleinEcran) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			ratio = screenSize.getWidth() / screenSize.getHeight();
			if (ratio > 16/9)
				ratio = screenSize.getWidth() / 1920;
			else
				ratio = screenSize.getHeight() / 1080;
			g2D.scale(ratio, ratio);
		}

		// rectangle ciel
		g2D.setColor(bleu);
		g2D.fill(new Rectangle2D.Double(0, 0, 5000, 1000));

		g2D.setColor(new Color(153, 102, 0)); //Rectangle terre
		g2D.fill(new Rectangle2D.Double(0, 1000, 5000, 100));

		// montagne 1
		g2D.setColor(Color.GREEN);
		g2D.setStroke(new BasicStroke(100.0f));
		g2D.draw(new Arc2D.Double(300, 930, 300, 100, 35, 110, Arc2D.CHORD));

		// montagne 2
		g2D.setStroke(new BasicStroke(50.0f));
		g2D.draw(new Arc2D.Double(1600, 932.5, 200, 200, 35, 110, Arc2D.CHORD));

		// texte
		g2D.setColor(Color.BLUE);
		g2D.setFont(new Font("Arial", Font.BOLD, 60));
		g2D.drawString("Choix du niveau", 600, 70);

		g2D.setStroke(new BasicStroke(1.0f));
		g2D.scale(1, 1);
	}
}
