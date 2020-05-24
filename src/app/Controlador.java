package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;

import app.Menu;


public class Controlador implements ActionListener, PropertyChangeListener {
	JFrame principal;

	
	PropertyChangeSupport soporte;
	
	public Controlador(JFrame principal) {
		this.principal = principal;
		this.principal.setContentPane(new Menu(this));
		this.soporte = new PropertyChangeSupport(this);
		principal.revalidate();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contentEquals("entrar")) {
			principal.setContentPane(new Base(this));
			principal.revalidate();
		}
		
	}

}
