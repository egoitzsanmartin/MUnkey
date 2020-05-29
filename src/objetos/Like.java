package objetos;

import java.sql.Date;

public class Like {
Date fSubida;
String autor;
int obraID;

public Like(Date fSubida, String autor, int obraID) {
	super();
	this.fSubida = fSubida;
	this.autor = autor;
	this.obraID = obraID;
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
@Override
public String toString() {
	return "Like [fSubida=" + fSubida + ", autor=" + autor + ", obraID=" + obraID + "]";
}
}
