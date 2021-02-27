package Mario.Vue;

import Mario.Modele.Question;
import Mario.Modele.KoopaTroopa;
import Mario.Modele.Joueur;
import Mario.Modele.Jeu;
import Mario.Modele.Background;
import Mario.Modele.Goomba;
import Mario.Modele.Ennemi;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Classe responsable du graphisme d'une partie du jeu
 * @author Arthur, Robert, Sangwoo
 */
public class JeuPanel extends JPanel {
	final int NOMBRE_ENNEMIS = 3;
	Background background;
	BufferedImage image;
	double scale;
	int backgroundPosition = 0, backgroundLargeur, plancherY;
	int niveau, niveauLargeur, niveauHauteur, niveauFin;
	int progres;
	JButton boutonAide, boutonRetour;
	Jeu jeu;
	Joueur joueur;
	Ennemi ennemi;
	Question question = new Question();
	QuestionFrame questionFrame;
	Goomba[] goombas = new Goomba[NOMBRE_ENNEMIS];
	KoopaTroopa[] koopaTroopas = new KoopaTroopa[NOMBRE_ENNEMIS];
	String[] questionEtReponse = new String[2];

	/**
	 * Constructeur
	 * @param jeu Objet de la classe Jeu, utilisé pour appeler ses méthodes au besoin
	 * @param joueur Objet de la classe Joueur, utilisé pour appeler ses méthodes au besoin
	 */
	public JeuPanel(Jeu jeu, Joueur joueur) {
		this.jeu = jeu;
		this.questionFrame = new QuestionFrame(this.jeu, this);
		this.joueur = joueur;
		for (int i = 0; i < NOMBRE_ENNEMIS; i++) {
			goombas[i] = new Goomba(0, 0);
			koopaTroopas[i] = new KoopaTroopa(0, 0);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.drawImage(image,
			0, 0, getWidth(), getHeight(),
			backgroundPosition, 0,
			backgroundPosition + niveauHauteur * getWidth() / getHeight(), niveauHauteur,
			null);

		// champignons et tortues
		scale = 1.0 * niveauHauteur / getHeight();
		for (int i = 0; i < NOMBRE_ENNEMIS; i++) {
			g2d.drawImage(goombas[i].getImage(), (int) (goombas[i].getX() - 1.0 * backgroundPosition / scale), goombas[i].getY(), null);
			g2d.drawImage(koopaTroopas[i].getImage(), (int) (koopaTroopas[i].getX() - 1.0 * backgroundPosition / scale), goombas[i].getY(), null);
		}

		// débogage
		/*
		g2d.drawString(getWidth() + " " + getHeight(), 130, 30);
		g2d.drawString(
				"gW=" + niveauHauteur * getWidth() / getHeight() +
				" nH=" + niveauHauteur +
				" scale=" + scale +
				" bP=" + this.backgroundPosition +
				" getW=" + getWidth() +
				" getH=" + getHeight() +
				" goomba.getX=" + this.goombas[0].getX() +
				" goomba.calcX=" + (this.goombas[0].getX() - this.backgroundPosition) +
				" calc=" + (this.goombas[0].getX() - this.backgroundPosition) / scale
				" calc=" + (0) / scale
		, 200, 200);
		*/

		/*
		g2d.setStroke(new BasicStroke(5.0f));
		g2d.setColor(Color.RED);
		for (int i = 0; i < NOMBRE_ENNEMIS; i++) {
			g2d.drawRect(goombas[i].getX() - (int) (1.0 * backgroundPosition / scale), goombas[i].getY(), goombas[i].getLargeur(), goombas[i].getHauteur());
			g2d.drawRect(koopaTroopas[i].getX() - (int) (1.0 * backgroundPosition / scale), koopaTroopas[i].getY(), koopaTroopas[i].getLargeur(), koopaTroopas[i].getHauteur());
		}

		g2d.setColor(Color.GREEN);
		g2d.drawRect(joueur.getX(), joueur.getY(), joueur.getLargeur(), joueur.getHauteur());

		g2d.setColor(Color.GREEN);
		g2d.drawLine(0, (int) (0.893 * getHeight()), 1440, (int) (0.893 * getHeight()));
		*/

		// personnage
		if (joueur.isSaut())
			g2d.drawImage(joueur.saute(), joueur.getX(), joueur.getY(), null);
		else
			g2d.drawImage(joueur.marche(4), joueur.getX(), joueur.getY(), null);

		// score
		g2d.setColor(Color.RED);
		Font font = new Font("Arial", Font.BOLD, 30);
		g2d.setFont(font);
		FontMetrics m = g2d.getFontMetrics(font);
		g2d.drawString("Score: " + jeu.getScore(), (int) (1.0 * getWidth() / 2 - (m.stringWidth("Score: " + jeu.getScore()) / 2)), 80);
	}

	/**
	 * Fonction qui s'occupe du déplacement du fond d'écran
	 * @param deplacement Nombre de pixels
	 */
	public void deplacerBackground(int deplacement) {
		if (!jeu.isArrete()) {
			backgroundPosition += deplacement;
			backgroundPosition = Math.min(backgroundPosition, niveauFin);
			backgroundPosition = Math.max(0, backgroundPosition);
			if (backgroundPosition == niveauFin) {
				jeu.arreter();
				joueur.setMarche(false);
				demanderQuestion();
			}
		}
		repaint();
		if (!jeu.isArrete()) {
			for (int i = 0; i < goombas.length; i++) {
				if (verifierCollisions(goombas[i])) {
					joueur.setMarche(false);
					this.jeu.arreter();
					this.ennemi = goombas[i];
					demanderQuestion();
				}
			}
			for (int i = 0; i < koopaTroopas.length; i++) {
				if (verifierCollisions(koopaTroopas[i])) {
					joueur.setMarche(false);
					this.jeu.arreter();
					this.ennemi = koopaTroopas[i];
					demanderQuestion();
				}
			}
		}
	}

	/**
	 * Méthode qui vérifie si le personnage a touché un des ennemis
	 * @param ennemi L'ennemi avec qui comparer les coordonnées
	 * @return Vrai si le personnage est en contact avec l'ennemi, faux sinon
	 */
	public boolean verifierCollisions(Ennemi ennemi) {
		int pScaled = (int) (1.0 * backgroundPosition / scale);
		return (
				joueur.getX() + joueur.getLargeur() >= ennemi.getX() - pScaled
				&& joueur.getX() <= ennemi.getX() + ennemi.getLargeur() - pScaled
				) && joueur.getY() + joueur.getHauteur() >= ennemi.getY();
	}

	/**
	 * Méthode qui initialise l'affichage du frame avec la question
	 */
	public void demanderQuestion() {
		questionEtReponse = question.getQuestionEtReponse(niveau, this.progres);
		this.progres++;
		questionFrame.setSize(500, 300);
		questionFrame.setLocationRelativeTo(null);
		questionFrame.setQuestionEtReponse(questionEtReponse);
		questionFrame.setAlwaysOnTop(true);
		questionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//questionFrame.setUndecorated(true);
		questionFrame.setVisible(true);
		questionFrame.requestFocus();
	}

	/**
	 * Méthode qui enlève l'ennemi du chemin, une fois qu'il a été "conquis"
	 */
	public void enleverEnnemi() {
		if (backgroundPosition == niveauFin) {
			niveau++;
			
			try {
				jeu.sauvegarder(niveau);
				changerNiveau(niveau);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else
			this.ennemi.setX(-100);
	}

	/**
	 * La fonction qui charge le fichier image du niveau
	 * @param niveau Le niveau à charger
	 * @return Valeur booléenne. true si chargement réussi, false sinon
	 */
	public boolean changerNiveau(int niveau) {
		try {
			this.niveau = niveau++;
			if (niveau == 7) {
				niveau = 1;
				this.niveau = 1;
			}
			this.progres = 0;
			image = ImageIO.read(getClass().getResourceAsStream("/niveau" + niveau + ".png"));
			backgroundPosition = 0;
			niveauLargeur = image.getWidth();
			niveauHauteur = image.getHeight();
			niveauFin = niveauLargeur - 375;
			changerPositions((int) Math.ceil(1.0 * niveau / 2));
			return true;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}

	/**
	 * Méthode qui bouge les ennemis à leurs places pour chaque niveau
	 * @param niveau
	 */
	public void changerPositions(int niveau) {
		switch (niveau) {
			case 1:
				goombas[0].setX(1000);
				koopaTroopas[0].setX(4000);
				goombas[1].setX(7000);
				koopaTroopas[1].setX(10000);
				goombas[2].setX(13000);
				koopaTroopas[2].setX(16000);
				break;
			case 2:
				goombas[0].setX(1000);
				koopaTroopas[0].setX(4000);
				goombas[1].setX(7000);
				koopaTroopas[1].setX(9000);
				goombas[2].setX(11000);
				koopaTroopas[2].setX(13000);
				break;
			case 3:
				goombas[0].setX(1000);
				koopaTroopas[0].setX(4000);
				goombas[1].setX(7000);
				koopaTroopas[1].setX(10000);
				goombas[2].setX(13000);
				koopaTroopas[2].setX(16000);
				break;
			default:
				break;
		}
		repaint();
	}
}