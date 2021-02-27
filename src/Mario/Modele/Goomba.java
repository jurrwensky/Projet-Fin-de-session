package Mario.Modele;

/**
 * Classe d'ennemi
 * @author Sangwoo
 */

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Goomba extends Ennemi {
	private Image image;
	// private final int PAUSE = 15;
	// private int dxGoomba;

	public Goomba(int x, int y) {
		super(x, y, 20, 20);
		// super.setDroite(true);
		// super.setMarche(true);

		this.image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/goomba.png")).getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
		this.setHauteur(80);
		this.setLargeur(80);
	}

	public Image getImage() {
		return image;
	}

	/*
	public void bouge() {
		if (super.estDroite() == true)
			this.dxGoomba = 10;
		else
			this.dxGoomba = -10;
		super.setX(super.getX() + this.dxGoomba);
	}
	*/

/*
	@Override
	public void run() {
		try{
			Thread.sleep(20);
			
		}
		catch(InterruptedException e){
		}
		
		while(true){
			this.bouge();
			try{
				Thread.sleep(PAUSE);}
					
			catch (InterruptedException e){
		}
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
   */ 
}