package interfaz;

import javax.swing.JFrame;

import controlador.Controlador;

public class Ventana extends JFrame {

	public static void main(String[] args) {
	
		Ventana programa = new Ventana();
		Controlador controlador = new Controlador(programa);
	}
	
	public Ventana() {
		super("MUnkey");
		this.setSize (1920, 1080);
		this.setLocation(0,0);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		this.setVisible(true);
	}	

}
