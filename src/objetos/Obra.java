package objetos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Obra {
int obraID;
String autor;
String titulo;
String portada;
String PDF;
String genero;
String Idioma;
Date fecha;
List<Like> listaLikes;
List<Comentario> listaComentarios;
public Obra(int obraID,String autor, String titulo, String portada, String pDF, String genero, String idioma, Date fecha) {
	super();
	this.obraID = obraID;
	this.autor = autor;
	this.titulo = titulo;
	this.portada = portada;
	PDF = pDF;
	this.genero = genero;
	Idioma = idioma;
	this.fecha = fecha;
	listaLikes=new ArrayList<>();
	listaComentarios=new ArrayList<>();
}

public void CargarComentarios(Comentarios listaTodos) {
	for(Comentario comentario:listaTodos.getListaComentarios()) {
		if(comentario.getObraID()==obraID) {
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
		if(like.getObraID()==obraID) {
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
public boolean deleteLike (Like like) {
	for(int i=0;i<listaLikes.size();i++)
	if(like.getAutor().equals(listaLikes.get(i).getAutor())) {
	listaLikes.remove(i);
	}
	return true;
}

public int getObraID() {
	return obraID;
}

public String getAutor() {
	return autor;
}

public String getTitulo() {
	return titulo;
}
public String getPortada() {
	return portada;
}
public String getPDF() {
	return PDF;
}
public String getGenero() {
	return genero;
}
public String getIdioma() {
	return Idioma;
}
public Date getFecha() {
	return fecha;
}
@Override
public String toString() {
	return "Obra [obraID=" + obraID + ", autor=" + autor + ", titulo=" + titulo + ", portada=" + portada + ", PDF="
			+ PDF + ", genero=" + genero + ", Idioma=" + Idioma + ", fecha=" + fecha + "]";
}


}
