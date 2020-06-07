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
	
	public List<Like> getListaLikes() {
		return listaLikes;
	}
	
	public boolean deleteLike (Like like) {
		for(int i=0;i<listaLikes.size();i++)
		if(like.getObraID()==listaLikes.get(i).getObraID() && like.getAutor().equals(listaLikes.get(i).getAutor())) {
		listaLikes.remove(i);
		}
		return true;
	}

	public boolean add (Like like) {
		listaLikes.add(like);
		return true;
}
}
