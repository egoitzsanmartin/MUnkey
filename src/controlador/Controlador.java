package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import interfaz.PanelInicioSesion;
import interfaz.Ventana;

public class Controlador implements ActionListener, PropertyChangeListener{
	
	Ventana ventana;
	
	public Controlador(Ventana ventana) {
		this.ventana = ventana;
		this.ventana.setContentPane(new PanelInicioSesion(this));
		ventana.revalidate();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
