package Mario.Vue;

import Mario.Modele.Joueur;
import Mario.Modele.Jeu;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Classe vue de la fenêtre principale
 * @author Arthur
 */
public class PrincipalFrame extends JFrame {
	JeuPanel jeuPanel;
	Jeu jeu;
	Joueur joueur;
	NouvellePartie nouvellePartie;
	int numero, questionProgres, plafond, sol;
	JButton boutonAide, boutonRetour;
	boolean gauche, droite, haut;

	/**
	 * Constructeur
	 */
	public PrincipalFrame() {
		JPanelBackground panelQuestion = new JPanelBackground();
		panelQuestion.setLayout(null);
		add(panelQuestion);
	}

    /**
     * Constructeur du frame principal
	 * @param nouvellePartie Objet de la classe NouvellePartie, pour le rendre visible ou pas ultérieurement
	 * @param niveau Le niveau du jeu à initialiser
     */
    public PrincipalFrame(NouvellePartie nouvellePartie, int niveau) {
		setTitle("Jeu");
		setResizable(false);
		this.nouvellePartie = nouvellePartie;
		this.jeu = nouvellePartie.menu.jeu;
		this.sol = (int) (0.893 * Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 150);

		this.plafond = 0;
		this.joueur = new Joueur(300, this.sol); //290 Jurrwensky 3000x2000 //378 2560x1440

		jeuPanel = new JeuPanel(this.jeu, this.joueur);
		jeuPanel.changerNiveau(niveau);
		jeuPanel.setLayout(null);

		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/aide.png")).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Icon icon = new ImageIcon(image);
		boutonAide = new JButton("", icon);
		boutonAide.setFocusable(false);
		boutonAide.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, 0, 100, 100);
		boutonAide.setOpaque(false);
		boutonAide.setBorderPainted(false);
		boutonAide.setContentAreaFilled(false);
		boutonAide.setFocusPainted(false);
		boutonAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == boutonAide) {
					AideFrame aideJeu = new AideFrame("AideJeu.txt");
					aideJeu.setSize(800, 600);
					aideJeu.setLocationRelativeTo(null);
					aideJeu.setVisible(true);
				}
			}
		});
		jeuPanel.add(boutonAide);

		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/back.png")).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		boutonRetour = new JButton("", icon);
		boutonRetour.setFocusable(false);
		boutonRetour.setBounds(0, 0, 100, 100);
		boutonRetour.setOpaque(false);
		boutonRetour.setBorderPainted(false);
		boutonRetour.setContentAreaFilled(false);
		boutonRetour.setFocusPainted(false);
		boutonRetour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == boutonRetour) {
					nouvellePartie.jeuFrame.setVisible(false);
					nouvellePartie.setVisible(true);
				}
			}
		});
		jeuPanel.add(boutonRetour);

		Thread principalFrameThread = new principalFrameThread();
		principalFrameThread.start();

		GestEvtAction listener = new GestEvtAction();
		addKeyListener(listener);

		jeuPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "retour");
		jeuPanel.getActionMap().put("retour", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				nouvellePartie.setVisible(true);
			}
		});

		add(jeuPanel);
	}

	public int getSol() {
		return sol;
	}

	public int getPlafond() {
		return plafond;
	}

	private class principalFrameThread extends Thread {
		@Override
		public void run() {
			boolean boucle = true;
			while (boucle) {
				repaint();
				if (jeu.isArrete()) {
					gauche = false;
					droite = false;
					haut = false;
				}
				if (gauche)
					jeuPanel.deplacerBackground(-4);
				if (droite)
					jeuPanel.deplacerBackground(4);
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					boucle = false;
					JOptionPane.showMessageDialog(null, "Problème de thread", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private class GestEvtAction extends KeyAdapter {
		// touche du clavier relâchée
		@Override
		public void keyPressed(KeyEvent e) {
			char c = e.getKeyChar();
			if ((c == 'a' || c == 'A') && !gauche) {
				gauche = true;
				joueur.setVersDroite(false);
				joueur.setMarche(true);
			} else if ((c == 'd' || c == 'D') && !droite) {
				droite = true;
				joueur.setVersDroite(true);
				joueur.setMarche(true);
				jeuPanel.deplacerBackground(4);
			} else if ((c == 'w' || c == 'W') && !haut) {
				haut = true;
				joueur.setSaut(true);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
				haut = false;
			} else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd' || e.getKeyChar() == 'A' || e.getKeyChar() == 'D') {
				joueur.setMarche(false);
				gauche = false;
				droite = false;
			}
		}

		public void keyTyped(KeyEvent e) {}
	}
}