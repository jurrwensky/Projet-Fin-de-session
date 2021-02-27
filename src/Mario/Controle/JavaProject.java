package Mario.Controle;

import Mario.Vue.Menu;
import javax.swing.JFrame;

/**
 * Classe de contrôle JavaProject
 * @author Arthur
 */
public class JavaProject {
	/**
	 * Méthode principale
	 * @param args Paramètres de lancement
	 */
	public static void main(String args[]) {
		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setUndecorated(true);
		menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		menu.setResizable(false);
		menu.setVisible(true);
	}
}