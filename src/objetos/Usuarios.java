package objetos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

		public int buscarUsuario(String nombre) {
			int valido=1;
			for(Usuario usuario : listaUsuarios) {
				if(usuario.getUsername().contentEquals(nombre)) {
				JOptionPane.showMessageDialog(new JFrame(), "Usuario repetido; intrduzca uno nuevo", "ERROR", JOptionPane.ERROR_MESSAGE);
				valido=0;
				}
			}
		return valido;
		}
		
		public Usuario escogerUsuario(String nombre) {
			Usuario valor = null;
			for(Usuario usuario : listaUsuarios) {
				if(usuario.getUsername().contentEquals(nombre)) {
					valor = usuario;
				}
			}
			return valor;
		}
		
		public List<Usuario> getListaUsuarios() {
			return listaUsuarios;
		}


		public Usuario validarLogIn(String nombre, String contraseña) {
			for(Usuario usuario : listaUsuarios) {
				if(usuario.getUsername().equals(nombre) && usuario.getPassword().equals(contraseña)) {
					return usuario;
				}
			}
			JOptionPane.showMessageDialog(new JFrame(), "Usuario o Contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			return null;
		}
}