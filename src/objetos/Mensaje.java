package objetos;

import java.sql.Timestamp;

public class Mensaje {
Timestamp fecha;
String autor;
int conversacionID;
String descripcion;
public Mensaje(Timestamp fecha, String autor, int conversacionID, String descripcion) {
	super();
	this.fecha = fecha;
	this.autor = autor;
	this.conversacionID = conversacionID;
	this.descripcion = descripcion;
}
public Timestamp getFecha() {
	return fecha;
}
public String getAutor() {
	return autor;
}
public int getConversacionID() {
	return conversacionID;
}
public String getDescripcion() {
	return descripcion;
}
@Override
public String toString() {
	return "\nMensaje [conversacionID=" + conversacionID+ ", fecha=" + fecha + ", autor=" + autor + ", descripcion="
			+ descripcion + "]";
}


}
