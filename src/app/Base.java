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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import app.Controlador;



public class Base extends JPanel {
	Controlador controlador;
	
	public Base(Controlador controlador) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(crearPanelBotonesIzquierda(), BorderLayout.WEST);
		this.add(crearBotonesArriba(), BorderLayout.NORTH);
		this.add(crearBotonesAbajo(), BorderLayout.SOUTH);
	}
	
	private Component crearBotonesArriba() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 20));
		panel.setOpaque(false);
		JButton boton = new JButton(new ImageIcon("art/botones/subir.png"));
		boton.addActionListener(controlador);
		boton.setActionCommand("subir");
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		
		panel.add(boton, BorderLayout.EAST);
		
		return panel;
	}
	
	private Component crearBotonesAbajo() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
		panel.setOpaque(false);
		JButton perfil = new JButton(new ImageIcon("art/botones/perfil.png"));
		perfil.addActionListener(controlador);
		perfil.setActionCommand("perfil");
		perfil.setContentAreaFilled(false);
		perfil.setBorderPainted(false);
		
		panel.add(perfil, BorderLayout.WEST);
		
		return panel;
	}

	public Component panelVacio() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		JLabel vacio = new JLabel();
		panel.add(vacio, BorderLayout.WEST);
		return panel;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("art/fondo.png");
		g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}

	


	private Component crearPanelBotonesIzquierda() {
		JPanel panel = new JPanel(new GridLayout(5, 1, 0, 40));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 50));
		panel.setOpaque(false);
		JButton obrasTop = new JButton(new ImageIcon("art/botones/top.png"));
		obrasTop.addActionListener(controlador);
		obrasTop.setActionCommand("top");
		obrasTop.setContentAreaFilled(false);
		obrasTop.setBorderPainted(false);
		
		JButton obrasRandom = new JButton(new ImageIcon("art/botones/random.png"));
		obrasRandom.addActionListener(controlador);
		obrasRandom.setActionCommand("random");
		obrasRandom.setContentAreaFilled(false);
		obrasRandom.setBorderPainted(false);
		
		
		panel.add(obrasTop);
		panel.add(obrasRandom);
		//panel.add(panelVacio());
		
		return panel;
	}

}
