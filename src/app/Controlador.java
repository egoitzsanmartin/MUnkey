package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import objetos.Comentarios;
import objetos.Likes;
import objetos.ListaChats;
import objetos.Obra;
import objetos.Obras;
import objetos.Usuario;
import objetos.Usuarios;
import sql.Conectar;


public class Controlador implements ActionListener, PropertyChangeListener {
	JFrame principal;
	Usuarios listaUsuarios;
	Obras listaObras;
	Likes listaLikes;
	Comentarios listaComentarios;
	ListaChats listaChats;
	Registro registro;
	Menu login;
	Usuario user;
	SubirLibro subir;
	Conectar conectar;
	
	PropertyChangeSupport soporte;
	
	public Controlador(JFrame principal) {
		this.principal = principal;
		login=new Menu(this);
		this.principal.setContentPane(login);
		this.soporte = new PropertyChangeSupport(this);
		listaUsuarios=new Usuarios();
		listaObras=new Obras();
		listaLikes=new Likes();
		listaComentarios=new Comentarios();
		listaChats=new ListaChats();
		conectar=new Conectar();
		principal.revalidate();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contentEquals("entrar")) {
			if(listaUsuarios.validarLogIn(login.leerUsuario(), login.leerContraseña()) !=null) {
			user=listaUsuarios.validarLogIn(login.leerUsuario(), login.leerContraseña());
			principal.setContentPane(new Base(this));
			principal.revalidate();
			}
		}
		if(e.getActionCommand().contentEquals("nuevaCuenta")) {
			registro=new Registro(this);
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("crearUsuario")) {
			if(listaUsuarios.buscarUsuario(registro.leerUsuario())==1) {
				if(registro.leerContraseña1().equals(registro.leerContraseña2())) {
					Usuario usuario=new Usuario(registro.leerUsuario(),registro.leerContraseña1(),registro.leerNombre(),
							registro.leerCorreo(),"normal");
					
					listaUsuarios.add(usuario);	
					conectar.guardarDatosUsuarios(usuario);
					
					JOptionPane.showMessageDialog(new JFrame(), " Usuario creado", "Notificación", JOptionPane.OK_OPTION);				
				}else {
					JOptionPane.showMessageDialog(new JFrame(), " Contraseñas diferentes", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}
			}
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
			subir=new SubirLibro(this);
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("cancelar")) {
			principal.setContentPane(new Base(this));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("aceptar")) {
		
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			
			Obra obra=new Obra(listaObras.getListaObras().size()+1,user.getUsername(),subir.leerTitulo(), subir.leerPortada(),
					subir.leerPDF(), subir.leerGenero(),subir.leerIdioma(),date);
			principal.setContentPane(new Base(this));
			principal.revalidate();
		}
	}

}
