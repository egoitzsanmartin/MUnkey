package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;

import objetos.Comentarios;
import objetos.Likes;
import objetos.ListaChats;
import objetos.Obras;
import objetos.Usuarios;


public class Controlador implements ActionListener, PropertyChangeListener {
	JFrame principal;
	Usuarios listaUsuarios;
	Obras listaObras;
	Likes listaLikes;
	Comentarios listaComentarios;
	ListaChats listaChats;
	
	PropertyChangeSupport soporte;
	
	public Controlador(JFrame principal) {
		this.principal = principal;
		this.principal.setContentPane(new Menu(this));
		this.soporte = new PropertyChangeSupport(this);
		listaUsuarios=new Usuarios();
		listaObras=new Obras();
		listaLikes=new Likes();
		listaComentarios=new Comentarios();
		listaChats=new ListaChats();
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
		if(e.getActionCommand().contentEquals("home")) {
			principal.setContentPane(new Base(this));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("perfil")) {
			principal.setContentPane(new Perfil(this));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("md")) {
			new MensajeDirecto(this);
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("subir")) {
			new SubirLibro(this);
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("cancelar")) {
			principal.setContentPane(new Base(this));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("aceptar")) {
			principal.setContentPane(new Base(this));
			principal.revalidate();
		}
	}

}
