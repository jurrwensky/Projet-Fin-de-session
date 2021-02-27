package Mario.Modele;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * Classe du joueur
 * @author Jurrwensky
 */
public class Joueur {
	private Image imgMario;
	private boolean saut; // vrai si mario saute
	private int compteurSaut; // durée du saut et hauteur de saut
	private int largeur, hauteur;
	private int x, y, sol;
	private boolean marche;
	private boolean versDroite;
	private int compteur;
    
	public Joueur(int x, int y, int largeur, int hauteur) {
		this.x = x;
		this.y = y;
		this.sol = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.compteur = 0;
		this.marche = false;
		this.versDroite = true;
	}

	public Joueur(int x , int y) {
		this(x, y, 28, 50);
		ImageIcon iconMario = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/m_arret_droite.png")));
		this.imgMario = iconMario.getImage();
		this.hauteur = this.imgMario.getHeight(null);
		this.largeur = this.imgMario.getWidth(null);

		this.saut = false;
		this.compteurSaut = 0;
	}

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

	public boolean isMarche() {
		return marche;
	}

	public boolean isVersDroite() {
		return versDroite;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setMarche(boolean marche) {
		this.marche = marche;
	}

	public void setVersDroite(boolean versDroite) {
		this.versDroite = versDroite;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public Image getImgMario() {
		return imgMario;
	}

	public boolean isSaut() {
		return saut;
	}

	public void setSaut(boolean saut) {
		this.saut = saut;
	}

	public Image saute() {
		ImageIcon icon;
		Image img;
		String str;

		this.compteurSaut++;

		//saut
		if (this.compteurSaut <= 10) {
			if (this.getY() > 0)
				this.setY(this.getY() - 25); //(0 est la hauteur du plafond)
			else
				this.compteurSaut = 15;
			if (this.isVersDroite() == true)
				str = "m_saut_droite.png";
			else
				str = "m_saut_gauche.png";
			//retombé
		} else if (this.getY() < this.sol) {
			this.setY(this.getY() + 25);
			if (this.isVersDroite())
				str = "m_saut_droite.png";
			else
				str = "m_saut_gauche.png";
			//fin du mouvement
		} else {
			if (this.isVersDroite())
				str = "m_arret_droite.png";
			else
				str = "m_arret_gauche.png";

			this.saut = false;
			this.compteurSaut = 0;
		}
		icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/" + str)));
		img = icon.getImage();
		return img;
	}

	public Image marche(int frequence) {
		String str;
		ImageIcon icon;
		Image img;

		if (!this.marche) {
			if (this.versDroite)
				str = "m_arret_droite.png";
			else
				str = "m_arret_gauche.png";
		} else {
			this.compteur++;
			if (this.compteur / frequence == 0) {
				if (this.versDroite == true)
					str = "m_arret_droite.png";
				else
					str = "m_arret_gauche.png";
			} else {
				if (this.versDroite == true)
					str = "m_marche_droite.png";
				else
					str = "m_marche_gauche.png";
			}
			if (this.compteur == 2 * frequence)
				this.compteur = 0;
		}

		icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/" + str)));
		img = icon.getImage();
		return img;
	}
}