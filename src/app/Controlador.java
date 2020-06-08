package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import objetos.Comentario;
import objetos.Comentarios;
import objetos.Like;
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
	BuscarLibro buscar;
	SeccionObra seccionObra;
	Conectar conectar;
	Serial serial;
	Obra enUso;
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
		cargarLikesComentariosObras();
		conectar=new Conectar();
		this.serial = new Serial();
		principal.revalidate();
	}
	
	public void cargarLikesComentariosObras() {
		for(Obra obra:listaObras.getListaObras()) {
			obra.CargarComentarios(listaComentarios);
			obra.CargarLikes(listaLikes);
		}
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
			user.CargarComentarios(listaComentarios);
			user.CargarLikes(listaLikes);
			}
		}
		if(e.getActionCommand().contentEquals("nuevaCuenta")) {
			registro=new Registro(this);
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("nuevoLike")) {
			int cont=0;
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			Like meGusta=new Like(date, user.getUsername(), enUso.getObraID());
			for(Like like:user.getListaLikes()) {
				if(like.getObraID()==meGusta.getObraID()){
					cont++;
				}
			}
			if(cont==0) {
			listaLikes.add(meGusta);
			user.addLike(meGusta);
			enUso.addLike(meGusta);
			conectar.guardarDatosLike(meGusta);
			}
			else {
				listaLikes.deleteLike(meGusta);
				user.deleteLike(meGusta);
				enUso.deleteLike(meGusta);
				conectar.borrarLike(user.getUsername(), meGusta.getObraID());
			}
			seccionObra=new SeccionObra(this, user, enUso);
			principal.setContentPane(seccionObra);
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("favoritas")) {
			
			principal.setContentPane(new VisorLibrosFiltrados(this, listaObras,user.getNombre(), "Likes"));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("random")) {
			Random r = new Random();
			int numero= r.nextInt((listaObras.getListaObras().size()-1) + 1);
			Obra obra=listaObras.getListaObras().get(numero);
			enUso=obra;
			principal.setContentPane(new VisorLibro(this, user,enUso));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("general")) {
			principal.setContentPane(new VisorLibrosFiltrados(this, listaObras,"", "Autor"));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("crearUsuario")) {
			if(listaUsuarios.buscarUsuario(registro.leerUsuario())==1) {
				if(registro.leerContraseña1().length()>=10) {
				if(registro.leerContraseña1().equals(registro.leerContraseña2())) {
					String pass= encriptacionHash256(registro.leerContraseña1());
					Usuario usuario=new Usuario(registro.leerUsuario(),pass,registro.leerNombre(),
							registro.leerCorreo(),"normal");
					
					listaUsuarios.add(usuario);	
					conectar.guardarDatosUsuarios(usuario);
					
					JOptionPane.showMessageDialog(new JFrame(), " Usuario creado corréctamente", "PROCESO TERMINADO", JOptionPane.INFORMATION_MESSAGE);				
					registro.dispose();
				}else {
					JOptionPane.showMessageDialog(new JFrame(), " Contraseñas diferentes", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				}
				}else {
					JOptionPane.showMessageDialog(new JFrame(), " Contraseñas diferentes o no son lo suficiente largas (mínimo 10 carácteres)", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("home")) {
			principal.setContentPane(new Base(this));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("top")) {
			enUso=listaObras.getListaObras().get(0);
			int size=0;
			for(Obra obra:listaObras.getListaObras()) {
				if (obra.getListaLikes().size()>=size && obra.getListaLikes().size() >= enUso.getListaLikes().size()) {
					enUso=obra;
				}
			}
			principal.setContentPane(new VisorLibro(this, user, enUso));
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("masObra")) {
			seccionObra=new SeccionObra(this, user, enUso);
			principal.setContentPane(seccionObra);
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("misObras")) {
			principal.setContentPane(new VisorLibrosFiltrados(this, listaObras, user.getNombre(), "Autor"));
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
		if(e.getActionCommand().contentEquals("lupa")) {
			buscar=new BuscarLibro(this);
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("BuscarLibro")) {
			String clave=buscar.leerClave();
			String filtro=buscar.leerFiltro();
			principal.setContentPane(new VisorLibrosFiltrados(this, listaObras, clave, filtro));
			buscar.dispose();
			principal.revalidate();
		}
		if(e.getActionCommand().contentEquals("cancelar")) {
			principal.setContentPane(new Base(this));
			principal.revalidate();
		}
		for(Obra obra:listaObras.getListaObras()) {
			if(e.getActionCommand().contentEquals(Integer.toString(obra.getObraID()))){
				enUso=obra;
				principal.setContentPane(new VisorLibro(this, user, enUso));
				principal.revalidate();
			}
		}
		if(e.getActionCommand().contentEquals("aceptar")) {
		
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			int id=listaObras.getListaObras().size()+1;
			
			Obra obra=new Obra(id,user.getUsername(),subir.leerTitulo(), subir.leerPortada(),
							   subir.leerPDF(), subir.leerGenero(),subir.leerIdioma(),date);
			
			JOptionPane.showMessageDialog(new JFrame(), "Obra guardada correctamente", "PROCESO TERMINADO", JOptionPane.INFORMATION_MESSAGE);
			subir.dispose();
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
