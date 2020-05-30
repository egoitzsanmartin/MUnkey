package app;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Registro extends JDialog{

	/**
	 * 
	 */

	JLabel username, contraseña1, contraseña2, nombre, correo, foto, registrar;
	JButton cancelar, aceptar, seleccionar;
	Controlador controlador;
	JFileChooser fotoChooser;
	JPasswordField passwordField1, passwordField2;
	FileNameExtensionFilter filtroFoto;
	JTextField userTextField, nombreTextField, correoTextField;
	
	public Registro(Controlador controlador)
	{
		super(new JDialog());
		this.controlador = controlador;
		this.setSize(830,800);
		this.setLocation(300,150);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(20, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(new java.awt.Color(44, 44, 40, 255));
		panel.add(crearPanelNorte(), BorderLayout.NORTH);
		panel.add(crearPanelCentro(), BorderLayout.CENTER);
		panel.add(crearPanelSur(), BorderLayout.SOUTH);
		return panel;
	}
	
	

	private Component crearPanelSur() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setOpaque(false);
		JButton aceptar = new JButton(new ImageIcon("art/botones/aceptar.png"));
		aceptar.addActionListener(controlador);
		aceptar.setActionCommand("crearUsuario");
		aceptar.setContentAreaFilled(false);
		aceptar.setBorderPainted(false);
		panel.add(aceptar);

		JButton cancelar = new JButton(new ImageIcon("art/botones/cancelar.png"));
		cancelar.addActionListener(controlador);
		cancelar.setActionCommand("cancelarUsuario");
		cancelar.setContentAreaFilled(false);
		cancelar.setBorderPainted(false);
		panel.add(cancelar);
		
		return panel;
	}

	private Component crearPanelCentro() {	
		JPanel panel= new JPanel(new GridLayout(6, 2, 30, 30));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 50));
		panel.setOpaque(false);
		username=new JLabel("Nombre de usuario:");
		username.setFont(new Font("myriad pro", Font.BOLD, 25));
		username.setForeground(Color.ORANGE);
		panel.add(username);
		
		userTextField=new JTextField();
		userTextField.setFont(new Font("myriad pro", Font.BOLD, 15));
		userTextField.setBorder(BorderFactory.createLineBorder(Color.orange));
		userTextField.setBackground(Color.ORANGE);
		panel.add(userTextField);
		
		nombre=new JLabel("Nombre:");
		nombre.setFont(new Font("myriad pro", Font.BOLD, 25));
		nombre.setForeground(Color.ORANGE);
		panel.add(nombre);
		
		nombreTextField=new JTextField();
		nombreTextField.setFont(new Font("myriad pro", Font.BOLD, 15));
		nombreTextField.setBorder(BorderFactory.createLineBorder(Color.orange));
		nombreTextField.setBackground(Color.ORANGE);
		panel.add(nombreTextField);
		
		correo=new JLabel("Correo electrónico:");
		correo.setFont(new Font("myriad pro", Font.BOLD, 25));
		correo.setForeground(Color.ORANGE);
		panel.add(correo);
		
		correoTextField=new JTextField();
		correoTextField.setFont(new Font("myriad pro", Font.BOLD, 15));
		correoTextField.setBorder(BorderFactory.createLineBorder(Color.orange));
		correoTextField.setBackground(Color.ORANGE);
		panel.add(correoTextField);
		
		contraseña1=new JLabel("Contraseña:");
		contraseña1.setFont(new Font("myriad pro", Font.BOLD, 25));
		contraseña1.setForeground(Color.ORANGE);
		panel.add(contraseña1);
		
		passwordField1 = new JPasswordField(20);
		passwordField1.setBorder(BorderFactory.createLineBorder(Color.orange));
		passwordField1.setBackground(Color.orange);
		passwordField1.setFont((new Font("myriad pro", Font.BOLD, 40)));
		panel.add(passwordField1);
		
		contraseña2=new JLabel("Repetir contraseña:");
		contraseña2.setFont(new Font("myriad pro", Font.BOLD, 25));
		contraseña2.setForeground(Color.ORANGE);
		panel.add(contraseña2);
		
		passwordField2 = new JPasswordField(20);
		passwordField2.setBorder(BorderFactory.createLineBorder(Color.orange));
		passwordField2.setBackground(Color.orange);
		passwordField2.setFont((new Font("myriad pro", Font.BOLD, 40)));
		panel.add(passwordField2);
		
		return panel;
	}
	

	private Component crearPanelNorte() {
		JPanel panel=new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 250, 30, 50));
		panel.setOpaque(false);
		registrar=new JLabel("REGISTRO");
		registrar.setFont(new Font("myriad pro", Font.BOLD, 40));
		registrar.setForeground(Color.ORANGE);
		panel.add(registrar,BorderLayout.CENTER);
		return panel;
	}
	
	public String leerUsuario() {
		return userTextField.getText();
	}
	public String leerContraseña1() {
		return String.copyValueOf(passwordField1.getPassword());
	}
	public String leerContraseña2() {
		return String.copyValueOf(passwordField2.getPassword());
	}
	public String leerNombre() {
		return nombreTextField.getText();
	}
	public String leerCorreo() {
		return correoTextField.getText();
	}

}