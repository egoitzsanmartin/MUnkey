package objetos;

import java.util.ArrayList;
import java.util.List;

import sql.Conectar;

public class Chat {
int conversacionID;
String usuario1;
String usuario2;
List<Mensaje> listaMensajes;

public Chat(int conversacionID, String usuario1, String usuario2) {
	super();
	listaMensajes=new ArrayList<>();
	this.conversacionID = conversacionID;
	this.usuario1 = usuario1;
	this.usuario2 = usuario2;
}


public int getConversacionID() {
	return conversacionID;
}


public String getUsuario1() {
	return usuario1;
}


public String getUsuario2() {
	return usuario2;
}


public List<Mensaje> getListaMensajes() {
	return listaMensajes;
}


public boolean add (Mensaje mensaje) {
	listaMensajes.add(mensaje);
	return true;
}


@Override
public String toString() {
	return "Chat [conversacionID=" + conversacionID + ", usuario1=" + usuario1 + ", usuario2=" + usuario2
			+ ", listaMensajes=" +"\n"+ listaMensajes + "]";
}

}
