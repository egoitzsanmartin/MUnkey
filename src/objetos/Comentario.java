package objetos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.sql.Timestamp;

public class Comentario {
	Timestamp fSubida;
	String autor;
	int obraID;
	String comentario;
	PropertyChangeSupport soporte;
	
	public Comentario(Timestamp fSubida, String autor, int obraID, String comentario) {
		super();
		this.fSubida = fSubida;
		this.autor = autor;
		this.obraID = obraID;
		this.comentario = comentario;
		soporte = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener (PropertyChangeListener listener) {
		soporte.addPropertyChangeListener(listener);
	}
	public Timestamp getfSubida() {
		return fSubida;
	}
	public String getAutor() {
		return autor;
	}
	public int getObraID() {
		return obraID;
	}
	public String getComentario() {
		return comentario;
	}
	@Override
	public String toString() {
		return autor+ "\n"+comentario + "\n"+fSubida;
	}
	
	
	
}
