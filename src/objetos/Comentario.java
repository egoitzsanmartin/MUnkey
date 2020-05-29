package objetos;

import java.sql.Date;

public class Comentario {
	Date fSubida;
	String autor;
	int obraID;
	String comentario;
	public Comentario(Date fSubida, String autor, int obraID, String comentario) {
		super();
		this.fSubida = fSubida;
		this.autor = autor;
		this.obraID = obraID;
		this.comentario = comentario;
	}
	public Date getfSubida() {
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
		return "Comentario [fSubida=" + fSubida + ", autor=" + autor + ", obraID=" + obraID + "\n"+"comentario="
				+ comentario + "]";
	}
	
	
	
}
