package src.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Perfil extends JPanel {
	Controlador controlador;
	
	public Perfil(Controlador controlador) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(crearBotonesCentro(), BorderLayout.CENTER);
	}
	
	public Component panelVacio() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		JLabel vacio = new JLabel();
		panel.add(vacio, BorderLayout.WEST);
		return panel;
	}
	
	private Component crearBotonesCentro() {
		JPanel panel = new JPanel(new GridLayout(1, 5, 20, 40));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 20));
		panel.setOpaque(false);
		JButton fav = new JButton(new ImageIcon("art/botones/fav.png"));
		fav.addActionListener(controlador);
		fav.setActionCommand("favoritas");
		fav.setContentAreaFilled(false);
		fav.setBorderPainted(false);
		
		JButton misObras = new JButton(new ImageIcon("art/botones/misObras.png"));
		misObras.addActionListener(controlador);
		misObras.setActionCommand("misObras");
		misObras.setContentAreaFilled(false);
		misObras.setBorderPainted(false);
		
		JButton md = new JButton(new ImageIcon("art/botones/md.png"));
		md.addActionListener(controlador);
		md.setActionCommand("md");
		md.setContentAreaFilled(false);
		md.setBorderPainted(false);
		
		panel.add(panelVacio());
		panel.add(fav);
		panel.add(misObras);
		panel.add(md);
		panel.add(panelVacio());
		
		return panel;
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("art/fondo.png");
		g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
