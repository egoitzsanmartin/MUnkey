package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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
import serial.Serial;
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
	Serial serial;
	final int sendL = 'l';
	
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
		//this.serial = new Serial();
		principal.revalidate();
	}
	
	

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contentEquals("entrar")) {
			String cont= encriptacionHash256(login.leerContraseña());
			if(listaUsuarios.validarLogIn(login.leerUsuario(), cont) !=null) {
			user=listaUsuarios.validarLogIn(login.leerUsuario(), cont);
			principal.setContentPane(new Base(this));
			principal.revalidate();
			serial.writer.sendData(sendL);
			}
		}
		if(e.getActionCommand().contentEquals("nuevaCuenta")) {
			registro=new Registro(this);
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("crearUsuario")) {
			if(listaUsuarios.buscarUsuario(registro.leerUsuario())==1) {
				if(registro.leerContraseña1().equals(registro.leerContraseña2())) {
					String pass= encriptacionHash256(registro.leerContraseña1());
					Usuario usuario=new Usuario(registro.leerUsuario(),pass,registro.leerNombre(),
							registro.leerCorreo(),"normal");
					
					listaUsuarios.add(usuario);	
					conectar.guardarDatosUsuarios(usuario);
					
					JOptionPane.showMessageDialog(new JFrame(), " Usuario creado corréctamente", "PROCESO TERMINADO", JOptionPane.INFORMATION_MESSAGE);				
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
		if(e.getActionCommand().contentEquals("top")) {
			Obra obra=new Obra(sendL, null, null, null, null, null, null, null);
			principal.setContentPane(new VisorLibro(this, user,obra));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("masObra")) {
			Obra obra=new Obra(sendL, null, null, null, null, null, null, null);
			principal.setContentPane(new SeccionObra(this, user, null, listaComentarios));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("perfil")) {
			principal.setContentPane(new Perfil(this, user));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("md")) {
			new MensajeDirecto(this, listaUsuarios, user);
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
			int id=listaObras.getListaObras().size()+1;
			
			Obra obra=new Obra(id,user.getUsername(),subir.leerTitulo(), subir.leerPortada(),
							   subir.leerPDF(), subir.leerGenero(),subir.leerIdioma(),date);
			
			JOptionPane.showMessageDialog(new JFrame(), "Obra guardada correctamente", "PROCESO TERMINADO", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(obra);
			listaObras.add(obra);
			conectar.guardarDatosObras(obra);
			principal.setContentPane(new Base(this));
			principal.revalidate();
		}
	}

	String encriptacionHash256(String cont) {
	    MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    byte[] byteOfTextToHash = cont.getBytes(StandardCharsets.UTF_8);
	    byte[] hashedByetArray = digest.digest(byteOfTextToHash);
	    String encoded = Base64.getEncoder().encodeToString(hashedByetArray);
	    return encoded;
	}
}
