package objetos;

import java.util.ArrayList;
import java.util.List;

import sql.Conectar;

public class ListaChats {
	List<Chat> listaChats;
	public ListaChats() {
		super();
		listaChats=new ArrayList<>();
		Conectar con=new Conectar();
		listaChats=con.cargarDatosChats();
	}
	
	
	public List<Chat> getListaChats() {
		return listaChats;
	}


	public boolean add (Chat chat) {
		listaChats.add(chat);
		return true;
	}
}
