package Mario.Modele;

import java.awt.*;
import java.io.File;
import javax.imageio.*;

/**
* Classe modèle Background, responsable du fond d'écran (image du niveau du jeu)
* @author Arthur
*/
public class Background {
	Image image;
	int position = 0, positionMin = 0, positionMax;

	/**
	* Constructeur principal
	* @param positionMax Position maximale du fond d'écran
	*/
	public Background(int positionMax) {
		this.positionMax = positionMax;
	}

	/**
	* Méthode pour déplacer le fond d'écran
	* @param position La position du fond d'écran
	*/
	public void deplacer(int position) {
		this.positionMin = Math.min(Math.max(this.positionMin, position), this.positionMax);
	}

	/**
	* Méthode pour réinitialiser les paramètres du fond d'écran à 0
	*/
	public void reinitialiser() {
		this.position = 0;
		this.positionMin = 0;
	}

	/**
	* Méthode qui charge l'image d'un niveau
	* @param niveau Le niveau du jeu
	*/
	public void changerNiveau(int niveau) throws Exception {
		try {
			image = ImageIO.read(new File("niveau" + niveau + ".png"));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	* Méthode qui dessine le fond d'écran
	* @param g2d Contexte graphique 2D
	*/
	public void dessiner(Graphics2D g2d) {
		/*
		g2d.drawImage(image,
			0, 0, getWidth(), getHeight(),
			backgroundPosition, 0,
			backgroundPosition + niveauHauteur * getWidth() / getHeight(), niveauHauteur,
		null);
		*/
	}
}