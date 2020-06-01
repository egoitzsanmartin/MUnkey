package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import objetos.Chat;
import objetos.Usuarios;


public class RendererChat implements ListCellRenderer<Chat>{
	
	Usuarios usuarios;
	String usuario;
	
	public RendererChat (Usuarios usuarios, String usuario) {
		this.usuarios = usuarios;
		this.usuario = usuario;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Chat> list, Chat value, int index, boolean isSelected,
			boolean cellHasFocus) {
		ImageIcon icono = null;
		JLabel nombreUsuario = null;
	
		if(usuario.equals(value.getUsuario2())) {
			icono = (usuarios.escogerUsuario(value.getUsuario1()).getFoto()) == null? new ImageIcon("art/perfilBase.png") : new ImageIcon(usuarios.escogerUsuario(value.getUsuario2()).getFoto());
			nombreUsuario = new JLabel(value.getUsuario1());
		}
		if(usuario.equals(value.getUsuario1())) {
			icono = (usuarios.escogerUsuario(value.getUsuario2()).getFoto()) == null? new ImageIcon("art/perfilBase.png") : new ImageIcon(usuarios.escogerUsuario(value.getUsuario2()).getFoto());
			nombreUsuario = new JLabel(value.getUsuario2());
		}
		nombreUsuario.setFont(new Font("myriad pro", Font.BOLD, 35));
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(BorderLayout.WEST, new JLabel(icono));
		panel.add(BorderLayout.EAST, nombreUsuario);
		
		return panel;
	}

}
