package objetos;

import java.util.ArrayList;
import java.util.List;

import sql.Conectar;

public class Usuarios{
	List<Usuario> listaUsuarios;
		public Usuarios() {
			super();
			listaUsuarios=new ArrayList<>();
			Conectar con=new Conectar();
			listaUsuarios=con.cargarDatosUsuarios();
		}
		
		
		public boolean add (Usuario usuario) {
			listaUsuarios.add(usuario);
			return true;
		}

		
}