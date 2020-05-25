package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javafx.scene.paint.Color;

public class MensajeDirecto extends JFrame implements ActionListener{
	String usuario;
	JTextField cajaTexto;
	JButton boton;
	Controlador controlador;
	
	public MensajeDirecto(Controlador controlador){
		super("Mensaje Directo");
		this.controlador = controlador;
		this.setSize (800,600);
		this.setLocation(600,200);
		this.setContentPane(crearPanelVentana());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setBackground(new java.awt.Color(109, 37, 0, 255));
		panel.add(crearPanelVisor(), BorderLayout.CENTER);
		panel.add(crearPanelMensaje(), BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelMensaje() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBackground(new java.awt.Color(109, 37, 0, 255));
		cajaTexto = new JTextField(40);
		boton = new JButton(new ImageIcon("art/botones/enviar.png"));
		boton.addActionListener(this);
		panel.add(cajaTexto, BorderLayout.WEST);
		panel.add(boton, BorderLayout.EAST);
		return panel;
	}


	private Component crearPanelVisor() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setBackground(new java.awt.Color(109, 37, 0, 255));
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
