package objetos;

import java.util.ArrayList;
import java.util.List;

import sql.Conectar;

public class Comentarios {
	List<Comentario> listaComentarios;
	public Comentarios() {
		super();
		listaComentarios=new ArrayList<>();
		Conectar con=new Conectar();
		listaComentarios=con.cargarDatosComentarios();
	}
	
	
	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}


	public boolean add (Comentario comentario) {
		listaComentarios.add(comentario);
		return true;
	}
}
