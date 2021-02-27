package Mario.Vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Mario.Modele.Jeu;
import Mario.Vue.JeuPanel;

/**
 * Classe vue qui affiche une question au joueur
 * @author Dark
 */
public class QuestionFrame extends JFrame implements ActionListener {
	final int POINTS_REPONSE = 5, TENTATIVES_MAX = 3;
	int tentatives;
	Jeu jeu;
	JeuPanel jeuPanel;
	String question, reponse;
	JPanel questionPanel = new JPanel();
	JTextArea texteQuestion, feedback;
	JTextField texteReponse;
	JButton boutonReponse;
	Font font = new Font("Arial", Font.PLAIN, 18);

	/**
	 * Constructeur de la classe QuestionFrame
	 * @param jeu Objet de la classe Jeu, utilisé pour appeler ses méthodes au besoin
	 * @param jeuPanel Objet de la classe JeuPanel, utilisé pour appeler ses méthodes au besoin
	 */
	public QuestionFrame(Jeu jeu, JeuPanel jeuPanel) {
		this.jeu = jeu;
		this.jeuPanel = jeuPanel;

		Color vert = new Color(186, 255, 218);
		questionPanel.setBackground(new Color(106, 140, 255));

		setTitle("Question");
		texteQuestion = new JTextArea("", 3, 26);
		texteQuestion.setEditable(false);
		texteQuestion.setWrapStyleWord(true);
		texteQuestion.setLineWrap(true);
		texteQuestion.setFont(font);
		texteQuestion.setBackground(vert);
		questionPanel.add(texteQuestion);

		texteReponse = new JTextField(15);
		texteReponse.setFont(font);
		texteReponse.setBackground(vert);
		questionPanel.add(texteReponse);

		boutonReponse = new JButton("Répondre");
		boutonReponse.addActionListener(this);
		questionPanel.add(boutonReponse);

		feedback = new JTextArea("", 3, 22);
		feedback.setEditable(false);
		feedback.setWrapStyleWord(true);
		feedback.setLineWrap(true);
		feedback.setFont(font);
		feedback.setBackground(vert);
		questionPanel.add(feedback);

		add(questionPanel);
	}

	/**
	 * Méthode qui est appelée pour transmettre la question et la reponse à cette classe
	 * @param questionEtReponse
	 */
	public void setQuestionEtReponse(String[] questionEtReponse) {
		this.tentatives = 5;
		this.question = questionEtReponse[0];
		texteQuestion.setText(this.question);
		this.reponse = questionEtReponse[1];
		//texteReponse.setText(this.reponse);
		texteReponse.setText("");
		texteReponse.requestFocus();
		feedback.setText("Score: " + this.jeu.getScore() +
				"\nTentatives restantes: " + this.tentatives);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boutonReponse) {
			if (texteReponse.getText().equalsIgnoreCase(reponse)) {
				this.setVisible(false);
				jeu.ajouterPoints(this.POINTS_REPONSE);
				jeuPanel.enleverEnnemi();
				jeu.resumer();
			} else if (this.tentatives > 0) {
				this.tentatives--;
				jeu.ajouterPoints(-1);
				feedback.setText(
						"Score: " + this.jeu.getScore() +
						"\nTentatives restantes: " + this.tentatives +
						"\nRéponse \"" + texteReponse.getText() + "\" incorrecte." +
						"\n-1 point");
				if (this.tentatives == 0) {
					jeuPanel.enleverEnnemi();
					jeu.resumer();
					this.setVisible(false);
				}
			}
		}
	}
}