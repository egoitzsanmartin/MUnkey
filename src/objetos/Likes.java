package objetos;

import java.util.ArrayList;
import java.util.List;

import sql.Conectar;

public class Likes {
	List<Like> listaLikes;
	public Likes() {
		super();
		listaLikes=new ArrayList<>();
		Conectar con=new Conectar();
		listaLikes=con.cargarDatosLikes();
}
	public boolean add (Like like) {
		listaLikes.add(like);
		return true;
}
}
