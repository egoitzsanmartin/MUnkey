package objetos;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
String username;
String password;
String nombre;
String correo;
String foto;
String tipo;
List<Like> listaLikes;
List<Comentario> listaComentarios;
public Usuario(String username, String password, String nombre, String correo, String tipo) {
	super();
	this.username = username;
	this.password = password;
	this.nombre = nombre;
	this.correo = correo;
	this.tipo = tipo;
	listaLikes=new ArrayList<>();
	listaComentarios=new ArrayList<>();
}

public boolean deleteLike (Like like) {
	for(int i=0;i<listaLikes.size();i++)
	if(like.getObraID()==listaLikes.get(i).getObraID()) {
	listaLikes.remove(i);
	}
	return true;
}

public void CargarComentarios(Comentarios listaTodos) {
	for(Comentario comentario:listaTodos.getListaComentarios()) {
		if(comentario.getAutor().equals(username)) {
			addComentario(comentario);
		}
	}
}
public List<Comentario> getListaComentarios() {
	return listaComentarios;
}
public boolean addComentario(Comentario comentario) {
	listaComentarios.add(comentario);
	return true;
}

public void CargarLikes(Likes listaTodos) {
	for(Like like:listaTodos.getListaLikes()) {
		if(like.getAutor().equals(username)) {
			addLike(like);
		}
	}
}
public List<Like> getListaLikes() {
	return listaLikes;
}
public boolean addLike (Like like) {
	listaLikes.add(like);
	return true;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getFoto() {
	return foto;
}
public void setFoto(String foto) {
	this.foto = foto;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public String getUsername() {
	return username;
}
public String getCorreo() {
	return correo;
}
@Override
public String toString() {
	return "Usuario [username=" + username + ", password=" + password + ", nombre=" + nombre + ", correo=" + correo
			+ ", foto=" + foto + ", tipo=" + tipo + "]";
}
}
