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



public class Menu extends JPanel {
	Controlador controlador;
	JTextField usuario;
	JPasswordField contraseña;
	
	public Menu(Controlador controlador) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(crearPanelDatos(), BorderLayout.WEST);
		//this.add(crearPanelBoton(), BorderLayout.SOUTH);
	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(6, 1, 0, 70));
		panel.setBorder(BorderFactory.createEmptyBorder(90, 40, 30, 150));
		panel.setOpaque(false);
		JButton boton = new JButton("Jugar");
		boton.addActionListener(controlador);
		boton.setActionCommand("jugar");
		panel.add(panelVacio());
		panel.add(crearPanelUsuario());
		panel.add(crearPanelContraseña());
		panel.add(crearPanelBoton());
		
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
		ImageIcon icon = new ImageIcon("art/login.png");
		g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}


	private Component crearPanelContraseña() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		contraseña = new JPasswordField(20);
		contraseña.setBorder(BorderFactory.createLineBorder(Color.orange));
		contraseña.setBackground(Color.orange);
		contraseña.setFont((new Font("myriad pro", Font.BOLD, 40)));
		contraseña.setText("contraseña");
		panel.add(contraseña, BorderLayout.WEST);
		return panel;
	}

	private Component crearPanelUsuario() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		usuario = new JTextField(20);
		usuario.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		usuario.setBackground(Color.ORANGE);
		usuario.setFont((new Font("myriad pro", Font.BOLD, 40)));
		usuario.setText("usuario");
		panel.add(usuario, BorderLayout.CENTER);
		return panel;
	}
	
	public String leerUsuario() {
		return usuario.getText();
	}
	
	public String leerContraseña() {
		return String.copyValueOf(contraseña.getPassword());
	}

	private Component crearPanelBoton() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 50));
		panel.setOpaque(false);
		JButton boton = new JButton(new ImageIcon("art/botones/entrar.png"));
		boton.addActionListener(controlador);
		boton.setActionCommand("entrar");
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		
		panel.add(boton, BorderLayout.WEST);
		
		return panel;
	}

}
