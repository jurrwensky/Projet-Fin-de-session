package Mario.Vue;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import Mario.Modele.Jeu;

/**
 * Classe du menu principal
 * @author Jurrwensky, Robert, Arthur
 */
public class Menu extends JFrame implements ActionListener {
	Jeu jeu = new Jeu();
	JButton boutonJouer, boutonAide, boutonQuitter;
	NouvellePartie nouvellePartie;

	/**
	 * Constructeur de la classe Menu
	 */
	public Menu() {
		setTitle("Mario");
		//Dimension screenSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/*double ratio = screenSize.getWidth() / screenSize.getHeight();
		if (ratio > 16/9)
			ratio = screenSize.getWidth() / 1920;
		else
			ratio = screenSize.getHeight() / 1080;*/

		JPanelBackground panel = new JPanelBackground();

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		panel.add(Box.createGlue(), c);

		c.gridx = 2;
		panel.add(Box.createGlue(), c);

		c.gridx = 1;
		c.weightx = 0.3;
		c.fill = GridBagConstraints.BOTH;

		c.gridy = 0;
		panel.add(Box.createGlue(), c);

		Font font = new Font("Arial", Font.PLAIN, 40);
		boutonJouer = new JButton("Jouer");
		boutonJouer.setFont(font);
		c.gridy = 1;
		c.weighty = 0.15;
		c.insets = new Insets(10, 0, 0, 0);
		panel.add(boutonJouer, c);

		boutonAide = new JButton("Aide");
		boutonAide.setFont(font);
		c.gridy = 2;
		panel.add(boutonAide, c);

		boutonQuitter = new JButton("Quitter");
		boutonQuitter.setFont(font);
		c.gridy = 3;
		panel.add(boutonQuitter, c);

		c.gridy = 4;
		c.weightx = 0.3;
		c.weighty = 0.65;
		c.insets = new Insets(0, 0, (int) Math.round(screenSize.getHeight() - screenSize.getWidth() * 9 / 16), 0);
		panel.add(Box.createGlue(), c);

		add(panel);

		boutonJouer.addActionListener(this);
		boutonAide.addActionListener(this);
		boutonQuitter.addActionListener(this);

		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "quitter");
		panel.getActionMap().put("quitter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	class JPanelBackground extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2D = (Graphics2D) g.create();
			super.paintComponent(g2D);

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double ratio = screenSize.getWidth() / screenSize.getHeight();
			if (ratio > 16/9)
				ratio = screenSize.getWidth() / 1920;
			else
				ratio = screenSize.getHeight() / 1080;
			g2D.scale(ratio, ratio);

			Font font = new Font("Times New Roman", Font.BOLD, 100);
			g2D.setFont(font);

			Color orange = new Color(255, 153, 0);
			Color bleu = new Color(0, 204, 255);
			Color jaune = new Color(255, 255, 0);

			/*g2D.setColor(Color.blue); //Rectangle
			g2D.fill(new Rectangle2D.Double( 0, 30, 9000, 1000 ));*/

			//g2D.setPaint(new GradientPaint(5,30,Color.white,35,100,Color.blue,false)); //Rectangle ciel
			/* g2D.setPaint(new GradientPaint(250,60,orange,350,100,bleu,false));//Couleur du ciel
			g2D.fill(new Rectangle2D.Double( 0, 0, 9000,1000 ));*/

			Point2D center = new Point2D.Float(250, 200);
			float radius = 700;
			float[] dist = {0.0f, 0.01f, 1.0f};
			Color[] colors = {jaune, orange, bleu};
			RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
			g2D.setPaint(p);
			g2D.fill(new Rectangle2D.Double(0, 0, 3000, 1000));

			// soleil, cerle jaune
			g2D.setColor(Color.YELLOW);
			g2D.fillOval(100, 50, 300, 300);

			// terre, rectangle brun
			g2D.setColor(new Color(153, 102, 0));
			g2D.fill(new Rectangle2D.Double(0, 1000, 2000, 100));

			// montagnes
			g2D.setColor(new Color(0, 255, 0));
			g2D.setStroke(new BasicStroke(100.0f));
			g2D.draw(new Arc2D.Double(300, 930, 300, 100, 35, 110, Arc2D.CHORD));

			g2D.setColor(new Color(0,255,0));
			g2D.setStroke(new BasicStroke(50.0f));
			g2D.draw(new Arc2D.Double(1600, 932.5, 200, 200, 35, 110, Arc2D.CHORD));

			// nuages
			g2D.setColor(Color.WHITE);
			g2D.setStroke(new BasicStroke(15.0f));
			g2D.fill(new Arc2D.Double(150,200 , 300, 100, 0, 360, Arc2D.OPEN));

			g2D.setStroke(new BasicStroke(10.0f));
			g2D.fill(new Arc2D.Double(30, 100 , 300, 100, 0, 360, Arc2D.OPEN));

			g2D.fill(new Arc2D.Double(500, 110 , 300, 100, 0, 360, Arc2D.OPEN));

			g2D.fill(new Arc2D.Double(820, 70 , 300, 100, 0, 360, Arc2D.OPEN));

			g2D.fill(new Arc2D.Double(1150, 50 , 300, 100, 0, 360, Arc2D.OPEN));

			g2D.fill(new Arc2D.Double(1600, 90 , 300, 100, 0, 360, Arc2D.OPEN));

			g2D.setColor(Color.RED);
			g2D.drawString("Mario", 820, 400);

			// contour rempli jaune des boutons
			g2D.setColor(new Color(220, 44, 199));
			g2D.draw(new Rectangle2D.Double(761, 420, 398, 400));

			// contour vide mauve des boutons 
			g2D.setColor(new Color(250, 250, 120));
			g2D.fill(new Rectangle2D.Double(761, 420, 398, 400));

			g2D.setStroke(new BasicStroke(1.0f));
			g2D.scale(1, 1);
		}
	}

	/**
	 * Écouteur d'événements des boutons du menu
	 * @param e Objet ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boutonJouer) {
			if (nouvellePartie == null) {
				nouvellePartie = new NouvellePartie(this);
				nouvellePartie.setExtendedState(JFrame.MAXIMIZED_BOTH);
				nouvellePartie.setUndecorated(true);
			}
			nouvellePartie.setVisible(true);
			this.setVisible(false);
		} else if (e.getSource() == boutonAide) {
			AideFrame aideFrame = new AideFrame("Aide.txt");
			aideFrame.setSize(800, 600);
			aideFrame.setLocationRelativeTo(null);
			aideFrame.setVisible(true);
		} else if (e.getSource() == boutonQuitter) {
			int option = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir quitter?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}