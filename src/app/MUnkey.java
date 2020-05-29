package app;


import javax.swing.JFrame;

import objetos.Usuarios;
import sql.Conectar;

public class MUnkey extends JFrame{
	Usuarios listaUsuarios;
	Conectar con;
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