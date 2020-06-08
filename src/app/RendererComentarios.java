package app;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import objetos.Comentario;
import objetos.Mensaje;

public class RendererComentarios implements ListCellRenderer<Comentario> {
	String usuario;
	public RendererComentarios(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Comentario> list, Comentario value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel label = new JLabel ();
		if(value.getAutor().equals(usuario)) {
			label.setForeground(Color.GREEN);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setText(value.getComentario());
		}else {
			label.setForeground(Color.BLACK);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setText(value.getAutor()+": "+value.getComentario());
		}
		return label;
	}



}
