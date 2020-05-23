package app;


import javax.swing.JFrame;

import app.Menu;
import app.Controlador;

public class MUnkey extends JFrame{

	public MUnkey(){
		super("MUnkey");
		this.setSize (1920, 1080);
		this.setLocation(0,0);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	public static void main(String args[]) {

    	JFrame munkey = new MUnkey();
    	Controlador controlador = new Controlador(munkey);
    }
}