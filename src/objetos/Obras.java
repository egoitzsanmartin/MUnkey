package objetos;

import java.util.ArrayList;
import java.util.List;

import sql.Conectar;

public class Obras {
	List<Obra> listaObras;
	
	public Obras() {
		super();
		listaObras=new ArrayList<>();
		Conectar con=new Conectar();
		listaObras=con.cargarDatosObras();
	}

	
	public List<Obra> getListaObras() {
		return listaObras;
	}


	public boolean add (Obra obra) {
		listaObras.add(obra);
		return true;
	}
}
