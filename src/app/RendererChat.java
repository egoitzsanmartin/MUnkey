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
	
	public RendererChat (Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Chat> list, Chat value, int index, boolean isSelected,
			boolean cellHasFocus) {
		ImageIcon icono;
		JLabel nombreUsuario;
		nombreUsuario = new JLabel(value.getUsuario2());
		nombreUsuario.setFont(new Font("myriad pro", Font.BOLD, 35));
		icono = (usuarios.escogerUsuario(value.getUsuario2()).getFoto()) == null? new ImageIcon("art/perfilBase.png") : new ImageIcon(usuarios.escogerUsuario(value.getUsuario2()).getFoto());
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(BorderLayout.WEST, new JLabel(icono));
		panel.add(BorderLayout.EAST, nombreUsuario);
		
		return panel;
	}

}
