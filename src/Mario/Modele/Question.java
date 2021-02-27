package Mario.Modele;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Classe modèle Question, responsable des questions scientifiques
 * @author Robert, Arthur
 */
public class Question {
	final int NOMBRE_NIVEAUX = 6, NOMBRE_QUESTIONS = 10;
	ArrayList<Integer> numeros;
	int numero, random;
	Random rand;
	String[] questionEtReponse;
	String[][] questions, reponses;

	/**
	 * Constructeur
	 */
	public Question() {
		numeros = new ArrayList<Integer>(NOMBRE_QUESTIONS);
		rand = new Random();
		questionEtReponse = new String[2];
		questions = new String[NOMBRE_NIVEAUX][NOMBRE_QUESTIONS];
		reponses = new String[NOMBRE_NIVEAUX][NOMBRE_QUESTIONS];
		chargerQuestions();
	}

	/**
	 * Méthode qui lit les questions d'un fichier
	 */
	public void chargerQuestions() {
		BufferedReader fichier = null;
		int niveau = 0, numero = 0;
		String ligne;
		try {
			fichier = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Questions.txt"), "UTF-8"));
			while ((ligne = fichier.readLine()) != null) {
				if (ligne.contains("niveau")) {
					//System.out.println(ligne);
					niveau = Integer.parseInt(ligne.substring(7)) - 1;
					numero = 0;
				} else {
					if (ligne.contains("\\n"))
						ligne = ligne.replace("\\n", System.getProperty("line.separator"));
					questions[niveau][numero] = ligne;
					reponses[niveau][numero] = fichier.readLine();
					//System.out.println(niveau + " " + numero + " " + ligne + " ####### " + reponses[niveau][numero]);
					numero++;
				}
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Le fichier «Questions.txt» n'a pas pu être trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problème de fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				if (fichier != null)
					fichier.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Problème de fermeture du fichier «Questions.txt»", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Méthode qui choisit une question aléatoire
	 * @param niveau Le niveau de la question
	 * @param numero Le progrès du joueur, pour éviter la même question
	 * @return Tableau avec la question et la réponse
	 */
	public String[] getQuestionEtReponse(int niveau, int numero) {
		if (numero == 0) {
			numeros.clear();
			for (int i = 0; i < NOMBRE_QUESTIONS; i++) {
				numeros.add(i, i);
			}
		}
		numero = numeros.get(random);
		numeros.remove(random);
		questionEtReponse[0] = questions[niveau][numero];
		questionEtReponse[1] = reponses[niveau][numero];
		return questionEtReponse;
	}
}