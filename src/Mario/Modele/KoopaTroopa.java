package Mario.Modele;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Classe d'ennemi
 * @author Sangwoo
 */
public class KoopaTroopa extends Ennemi {
	private Image image;
	
	public KoopaTroopa(int x, int y) {
		super(x, y, 20, 20);
		this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/koopatroopa.png")).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
		this.setHauteur(80);
		this.setLargeur(80);
	}
	
	public Image getImage() {
		return image;
	}
}