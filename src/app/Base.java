package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
		this.add(crearPanelCentro(),BorderLayout.CENTER);
		this.add(crearBotonesAbajo(), BorderLayout.SOUTH);
	}
	
	private Component crearPanelCentro() {
		Random r = new Random();
		int numero= r.nextInt((15-1) + 1);
		String frase= Integer.toString(numero);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(70, 0, 30, 20));
		panel.setOpaque(false);
		JButton boton = new JButton(new ImageIcon("art/frases/"+frase+".png"));
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		panel.add(boton, BorderLayout.CENTER);
		return panel;
	}

	private Component crearBotonesArriba() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 170, 30, 20));
		panel.setOpaque(false);
		JButton boton = new JButton(new ImageIcon("art/botones/subir.png"));
		boton.addActionListener(controlador);
		boton.setActionCommand("subir");
		boton.setToolTipText("Comparte tus obras con la comunidad");
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		
		JButton lupa = new JButton(new ImageIcon("art/botones/lupa.png"));
		lupa.addActionListener(controlador);
		lupa.setActionCommand("lupa");
		lupa.setToolTipText("Busca la obra que quieras leer");
		lupa.setContentAreaFilled(false);
		lupa.setBorderPainted(false);
		
		panel.add(boton, BorderLayout.EAST);
		panel.add(lupa, BorderLayout.WEST);
		
		return panel;
	}
	
	private Component crearBotonesAbajo() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
		panel.setOpaque(false);
		JButton perfil = new JButton(new ImageIcon("art/botones/perfil.png"));
		perfil.addActionListener(controlador);
		perfil.setActionCommand("perfil");
		perfil.setToolTipText("Tu perfil");
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
		obrasTop.setToolTipText("La mejor obra del momento");
		obrasTop.setContentAreaFilled(false);
		obrasTop.setBorderPainted(false);
		
		JButton obrasRandom = new JButton(new ImageIcon("art/botones/random.png"));
		obrasRandom.addActionListener(controlador);
		obrasRandom.setActionCommand("random");
		obrasRandom.setToolTipText("Una obra aleatoria. Ideal si no sabes que leer");
		obrasRandom.setContentAreaFilled(false);
		obrasRandom.setBorderPainted(false);
		
		JButton obrasgeneral = new JButton(new ImageIcon("art/botones/general.png"));
		obrasgeneral.addActionListener(controlador);
		obrasgeneral.setActionCommand("general");
		obrasgeneral.setToolTipText("Todas las obras que hay en la aplicación");
		obrasgeneral.setContentAreaFilled(false);
		obrasgeneral.setBorderPainted(false);
		
		panel.add(obrasgeneral);
		panel.add(obrasTop);
		panel.add(obrasRandom);
		//panel.add(panelVacio());
		
		return panel;
	}

}
