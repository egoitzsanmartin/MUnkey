package objetos;

import java.sql.Date;

public class Obra {
int obraID;
String autor;
String titulo;
String portada;
String PDF;
String genero;
String Idioma;
Date fecha;
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
