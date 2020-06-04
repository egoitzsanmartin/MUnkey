package objetos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Timestamp;

public class Mensaje {
Timestamp fecha;
String autor;
int conversacionID;
String descripcion;
PropertyChangeSupport soporte;

public Mensaje(Timestamp fecha, String autor, int conversacionID, String descripcion) {
	super();
	this.fecha = fecha;
	this.autor = autor;
	this.conversacionID = conversacionID;
	this.descripcion = descripcion;
	soporte = new PropertyChangeSupport(this);
}
public void addPropertyChangeListener (PropertyChangeListener listener) {
	soporte.addPropertyChangeListener(listener);
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
