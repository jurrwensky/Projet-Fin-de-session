package Mario.Modele;

import java.io.*;
import javax.swing.JOptionPane;

/**
* Classe modèle Jeu qui s'occupe du chargement de sauvegarde et du sauvgardement de la session du jeu
* @author Arthur
*/
public class Jeu {
	private boolean enMarche = true;
	private int niveau, score = 0;

	/**
	* Constructeur vide de la classe Jeu
	*/
	public Jeu() {}

	public int getNiveau() {
		return this.niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void ajouterPoints(int points) {
		this.score += points;
	}

	public void arreter() {
		this.enMarche = false;
	}

	public void resumer() {
		this.enMarche = true;
	}

	public boolean isArrete() {
		return !this.enMarche;
	}

	/**
	* Méthode qui sauvegarde le progrès du jeu dans un fichier
	* @param niveau Le niveau du jeu à sauvegarder
	* @throws Exception Lancée lorsqu'il y a un problème de fichier
	*/
	public void sauvegarder(int niveau) throws Exception {
		BufferedWriter fichierSauvegarde = null;
		try {
			fichierSauvegarde = new BufferedWriter(new FileWriter("sauvegarde.dat")); // modifier constructeur avec un mode append
			String chaine = Integer.toString(niveau);
			fichierSauvegarde.write(chaine);
			fichierSauvegarde.newLine();
			fichierSauvegarde.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problème de fichier de sauvegarde", "Erreur", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				if (fichierSauvegarde != null)
					fichierSauvegarde.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Problème de fermeture du fichier de sauvegarde", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	* Méthode qui charge la sauvegarde qui est dans un fichier
	* @throws Exception Lancée lorsqu'il y a un problème de fichier
	*/
	public void chargerSauvegarde() throws Exception {
		BufferedReader fichierSauvegarde = null;
		String ligne;
		try {
			fichierSauvegarde = new BufferedReader(new FileReader("sauvegarde.dat"));
			while ((ligne = fichierSauvegarde.readLine()) != null) {
				try {
					this.niveau = Integer.parseInt(ligne);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Niveau invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Le fichier de sauvegarde n'a pas pu être trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problème de fichier de sauvegarde", "Erreur", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				if (fichierSauvegarde != null)
					fichierSauvegarde.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Problème de fermeture du fichier de sauvegarde", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}