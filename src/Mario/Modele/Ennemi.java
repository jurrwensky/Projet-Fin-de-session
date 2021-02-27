package Mario.Modele;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Classe d'ennemi
 * @author Sangwoo
 */
public class Ennemi {
	private int largeur, hauteur;
	private int x, y;
	private boolean marche, droite;

	public Ennemi(int x, int y, int largeur, int hauteur) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.x = x;
		this.y = (int) (0.84 * screenSize.getHeight());
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public Ennemi() {}

	// getters
	public int getX() {
		return x;
	}

	 public int getY() {
		return y;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}
	
	public boolean estMarche() {
		return marche;
	}

	public boolean estDroite() {
		return droite;
	}

	// setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public void setDroite(boolean droite) {
		this.droite = droite;
	}
}