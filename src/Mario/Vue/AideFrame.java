package Mario.Vue;

import java.awt.Font;
import java.io.*;
import javax.swing.*;

/**
 * Classe vue de la fenêtre d'aide
 * @author Arthur
 */
public class AideFrame extends JFrame {
	JPanel aidePanel;
	JTextArea aide;
	JScrollPane scrollPane;

    /**
     * Constructeur du frame d'aide
     */
    public AideFrame(String nomFichier) {
		setTitle("Aide");
		BufferedReader fichier = null;
		String ligne;
		aidePanel = new JPanel();
		aide = new JTextArea("", 15, 40);
		aide.setFont(new Font("Arial", Font.PLAIN, 22));
		try {
			fichier = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + nomFichier), "UTF-8"));
			while ((ligne = fichier.readLine()) != null) {
				aide.append(ligne + "\n");
			}
		} catch (EOFException e) {
			JOptionPane.showMessageDialog(null, "Problème de fin de fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Le fichier «" + nomFichier + "» n'a pas pu être trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Problème d'accès au fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problème de fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				if (fichier != null)
					fichier.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Problème de fermeture du fichier " + nomFichier + "»", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		aide.setEditable(false);
		aide.setWrapStyleWord(true);
		aide.setLineWrap(true);
		scrollPane = new JScrollPane(aide);
		aidePanel.add(scrollPane);
		add(aidePanel);
	}
}