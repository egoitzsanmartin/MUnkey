package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import objetos.Mensaje;

public class RendererTexto implements ListCellRenderer<Mensaje> {
	String usuario;
	public RendererTexto(String usuario) {
		this.usuario = usuario;
	}
	@Override
	public Component getListCellRendererComponent(JList<? extends Mensaje> list, Mensaje value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel=new JPanel(new BorderLayout());
		JLabel label = new JLabel ();
		if(value.getAutor().equals(usuario)) {
			label.setForeground(Color.BLACK);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setText(value.getDescripcion());
			panel.add(label, BorderLayout.EAST);
			panel.setBackground(Color.GREEN);
		}else {
			label.setForeground(Color.BLACK);
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setText(value.getAutor()+": "+value.getDescripcion());
			panel.add(label, BorderLayout.WEST);
		}
		return panel;
	}



}
