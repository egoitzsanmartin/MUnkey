package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.Controlador;

public class PanelInicioSesion extends JPanel {
	
	Controlador controlador;
	JTextField usuario;
	JPasswordField contraseña;
	
	public PanelInicioSesion(Controlador controlador) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		this.add(crearPanelDatos(), BorderLayout.CENTER);
	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 0, 80));
		panel.setBorder(BorderFactory.createEmptyBorder(100, 40, 40, 150));
		panel.setOpaque(false);
		JButton boton = new JButton("Jugar");
		boton.addActionListener(controlador);
		boton.setActionCommand("jugar");
		
		panel.add(crearPanelUsuario());
		panel.add(crearPanelContraseña());
		
		return panel;
	}


	private Component crearPanelContraseña() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Contraseña:    ");
		label.setFont(new Font("arial", Font.BOLD, 40));
		panel.setOpaque(false);
		contraseña = new JPasswordField(20);
		contraseña.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contraseña.setFont((new Font("arial", Font.BOLD, 40)));
		panel.add(label, BorderLayout.WEST);
		panel.add(contraseña, BorderLayout.CENTER);
		return panel;
	}

	private Component crearPanelUsuario() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Usuario:          ");
		label.setFont(new Font("arial", Font.BOLD, 40));
		panel.setOpaque(false);
		usuario = new JTextField(20);
		usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		usuario.setFont((new Font("arial", Font.BOLD, 40)));
		
		panel.add(label, BorderLayout.WEST);
		panel.add(usuario, BorderLayout.CENTER);
		return panel;
	}
	
	public String leerUsuario() {
		return usuario.getText();
	}
	
	public String leerContraseña() {
		return String.copyValueOf(contraseña.getPassword());
	}

}
