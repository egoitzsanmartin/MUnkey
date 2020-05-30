package sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import objetos.Chat;
import objetos.Comentario;
import objetos.Like;
import objetos.Mensaje;
import objetos.Obra;
import objetos.Usuario;

public class Conectar {

Connection con;

public Conectar() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/MUNKEY?userTimezone=true&serverTimezone=UTC","root","1234");
	}catch (Exception e) {
		System.err.println("Error: "+e);
	}
}

public List<Usuario> cargarDatosUsuarios() {
	Conectar cn=new Conectar();
	Statement st;
	ResultSet rs;
	Usuario user;
	List<Usuario>listaUsuarios=new ArrayList<>();
	
	try {
		st=cn.con.createStatement();
		rs=st.executeQuery("select * from usuario");
		while (rs.next()) {
			String linea=(rs.getString("username")+"$"+rs.getString("passwd")+"$"+rs.getString("nombre")+"$"+
					  rs.getString("correo")+"$"+ rs.getString("tipo"));
			String [] valores = linea.split("[$]");
			user= new Usuario(valores[0],valores[1],valores[2],valores[3],valores[4]);
		System.out.println(user);	
		listaUsuarios.add(user);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return listaUsuarios;
}

public void guardarDatosUsuarios(Usuario usuario) {
	Conectar cn=new Conectar();
	Statement st;
	ResultSet rs;
	String accion="INSERT into usuario VALUES" + "('"+usuario.getUsername()+"','"+usuario.getNombre()+"','"+
			usuario.getCorreo()+"','"+usuario.getTipo()+"','art/mono.png','"+usuario.getPassword()+"')";
	try {
		st=cn.con.createStatement();
		st.executeUpdate(accion);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


}

public List<Obra> cargarDatosObras() {
	Conectar cn=new Conectar();
	Statement st;
	ResultSet rs;
	Obra obra;
	List<Obra>listaObras=new ArrayList<>();
	try {
		st=cn.con.createStatement();
		rs=st.executeQuery("select * from obra");
		while (rs.next()) {
			String linea=(rs.getInt("obraID")+"$"+ rs.getString("autor")+"$"+ rs.getString("titulo")+"$"+ 
						  rs.getString("portada")+"$"+ rs.getString("pdf")+"$"+ rs.getString("genero")+"$"+ 
						  rs.getString("idioma")+"$"+  rs.getDate("Fsubida"));
			String [] valores = linea.split("[$]");
			obra=new Obra(Integer.valueOf(valores[0]),valores[1],valores[2],valores[3],
					valores[4],valores[5], valores[6],Date.valueOf(valores[7]));
			System.out.println(obra);	
			listaObras.add(obra);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return listaObras;
}

public List<Like> cargarDatosLikes() {
	Conectar cn=new Conectar();
	Statement st;
	ResultSet rs;
	Like like;
	List<Like>listaLikes=new ArrayList<>();
	try {
		st=cn.con.createStatement();
		rs=st.executeQuery("select * from meGusta");
		while (rs.next()) {
			String linea=(rs.getDate("Fsubida")+"$"+ rs.getString("autor")+"$"+  rs.getInt("obraID"));
			String [] valores = linea.split("[$]");
			like=new Like(Date.valueOf(valores[0]),valores[1],Integer.valueOf(valores[2]));
			System.out.println(like);	
			listaLikes.add(like);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return listaLikes;
}

public List<Comentario> cargarDatosComentarios() {
	Conectar cn=new Conectar();
	Statement st;
	ResultSet rs;
	Comentario comentario;
	List<Comentario>listaComentarios=new ArrayList<>();
	try {
		st=cn.con.createStatement();
		rs=st.executeQuery("select * from comentario");
		while (rs.next()) {
			String linea=(rs.getDate("Fsubida")+"$"+ rs.getString("autor")+"$"+  rs.getInt("obraID")+"$"+  rs.getString("descripcion"));
			String [] valores = linea.split("[$]");
			comentario=new Comentario(Date.valueOf(valores[0]),valores[1],Integer.valueOf(valores[2]), valores[3]);
			System.out.println(comentario);	
			listaComentarios.add(comentario);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return listaComentarios;
}

public List<Chat> cargarDatosChats() {
	Conectar cn=new Conectar();
	Statement st;
	ResultSet rs;
	Mensaje mensaje;
	Chat chat;
	List<Mensaje>listaMensaje=new ArrayList<>();
	List<Chat>listaChats=new ArrayList<>();
	try {
		st=cn.con.createStatement();
		rs=st.executeQuery("select * from conversacion");
		while (rs.next()) {
			String linea=(rs.getInt("conversacionID")+"$"+ rs.getString("usuario1")+"$"+rs.getString("usuario2"));
			String [] valores = linea.split("[$]");
			chat=new Chat(Integer.valueOf(valores[0]),valores[1], valores[2]);	
			listaChats.add(chat);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		st=cn.con.createStatement();
		rs=st.executeQuery("select * from mensaje");
		while (rs.next()) {
			String linea=(rs.getTimestamp("Fsubida")+"$"+ rs.getString("autor")+"$"+rs.getInt("conversacionID")+"$"+rs.getString("descripcion"));
			String [] valores = linea.split("[$]");
			mensaje=new Mensaje(Timestamp.valueOf(valores[0]),valores[1], Integer.valueOf(valores[2]),valores[3]);
			listaMensaje.add(mensaje);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for(int i=0;i<listaChats.size();i++) {
		for(int j=0;j<listaMensaje.size();j++) {
			if(listaChats.get(i).getConversacionID()==listaMensaje.get(j).getConversacionID()) {
				listaChats.get(i).add(listaMensaje.get(j));
			}
		}
	}
	System.out.println(listaChats);
	return listaChats;
}

}
