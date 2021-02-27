package Mario.Vue;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * Classe vue NouvellePartie qui s'occupe du menu de sélection du niveau
 * @author Jurrwensky, Arthur
 */
public class NouvellePartie extends JFrame implements ActionListener {
	JButton boutonNv1, boutonNv2, boutonNv3, boutonNv4, boutonNv5, boutonNv6, boutonRetour;
	Menu menu;
	PrincipalFrame jeuFrame; 

	/**
	 * Constructeur paramétré de la classe NouvellePartie
	 * @param menu Objet de la classe Menu, pour le rendre visible ou pas ultérieurement
	 */
	public NouvellePartie(Menu menu) {
		this.menu = menu;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		JPanelBackground panel = new JPanelBackground();
		panel.setLayout(null);
		panel.setPleinEcran(true);

		boutonNv1 = new JButton("", new ImageIcon(getToolkit().getImage(getClass().getResource("/niveau1.png"))));
		boutonNv1.setBounds(30, 160, 400, 230);
		boutonNv1.setActionCommand("0");
		boutonNv1.addActionListener(this);
		boutonNv1.setToolTipText("Cliquez pour choisir le niveau 1. Ordre croissant de la difficulté des questions.");

		boutonNv2 = new JButton("", new ImageIcon(getToolkit().getImage(getClass().getResource("/niveau2.png"))));
		boutonNv2.setBounds(640, 160, 400, 230);
		boutonNv2.setActionCommand("1");
		boutonNv2.addActionListener(this);
		boutonNv2.setToolTipText("Cliquez pour choisir le niveau 2. Ordre croissant de la difficulté des questions.");

		boutonNv3 = new JButton("", new ImageIcon(getToolkit().getImage(getClass().getResource("/niveau3.png"))));
		boutonNv3.setBounds(1270, 160, 400, 230);
		boutonNv3.setActionCommand("2");
		boutonNv3.addActionListener(this);
		boutonNv3.setToolTipText("Cliquez pour choisir le niveau 3. Ordre croissant de la difficulté des questions.");

		boutonNv4 = new JButton("" ,new ImageIcon(getToolkit().getImage(getClass().getResource("/niveau4.png"))));
		boutonNv4.setBounds(30, 610, 400, 230);
		boutonNv4.setActionCommand("3");
		boutonNv4.addActionListener(this);
		boutonNv4.setToolTipText("Cliquez pour choisir le niveau 4. Ordre croissant de la difficulté des questions.");

		boutonNv5 = new JButton("" ,new ImageIcon(getToolkit().getImage(getClass().getResource("/niveau5.png"))));
		boutonNv5.setBounds(640, 610, 400, 230);
		boutonNv5.setActionCommand("4");
		boutonNv5.addActionListener(this);
		boutonNv5.setToolTipText("Cliquez pour choisir le niveau 5. Ordre croissant de la difficulté des questions.");

		boutonNv6 = new JButton("" ,new ImageIcon(getToolkit().getImage(getClass().getResource("/niveau6.png"))));
		boutonNv6.setBounds(1270, 610, 400, 230);
		boutonNv6.setActionCommand("5");
		boutonNv6.addActionListener(this);
		boutonNv6.setToolTipText("Cliquez pour choisir le niveau 6. Ordre croissant de la difficulté des questions.");

		ImageIcon icon = new ImageIcon(getToolkit().getImage(getClass().getResource("/back.png")));
		Image image = icon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		boutonRetour = new JButton("" ,icon);

		boutonRetour.setBounds(0, 0, 100, 100);
		boutonRetour.setOpaque(false);
		boutonRetour.setBorderPainted(false);
		boutonRetour.setContentAreaFilled(false);
		boutonRetour.setFocusPainted(false);
		boutonRetour.addActionListener(this);
		boutonRetour.setToolTipText("Cliquez sur ce bouton pour revenir au menu principal");

		panel.add(boutonNv1);
		panel.add(boutonNv2);
		panel.add(boutonNv3);
		panel.add(boutonNv4);
		panel.add(boutonNv5);
		panel.add(boutonNv6);
		panel.add(boutonRetour);

		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "quitter");
		panel.getActionMap().put("quitter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add(panel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() != boutonRetour) {
			if (jeuFrame == null) {
				jeuFrame = new PrincipalFrame(this, Integer.parseInt(e.getActionCommand()));
				jeuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				jeuFrame.setUndecorated(true);
			}
			if (jeuFrame.jeuPanel.changerNiveau(Integer.parseInt(e.getActionCommand()))) {
				jeuFrame.setVisible(true);
				this.setVisible(false);
			}
		} else {
			menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
			menu.setVisible(true);
			this.setVisible(false);
		}
	}
}