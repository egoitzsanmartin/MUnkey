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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;



public class BuscarLibro extends JDialog{


	String [] filtrar = {"Género", "Idioma", "Título", "Autor"};
	JButton buscar;
	JComboBox filtro;
	JTextField buscar2;
	Controlador controlador;
	
	public BuscarLibro(Controlador controlador)
	{
		super(new JDialog());
		this.controlador = controlador;
		this.setSize(500,180);
		this.setLocation(700,300);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(20, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panel.add(crearPanelCentro(), BorderLayout.CENTER);
		panel.add(crearPanelSur(), BorderLayout.SOUTH);
		return panel;
	}
	
	

	private Component crearPanelSur() {
	JPanel panel2 = new JPanel(new FlowLayout());
		
		buscar = new JButton("Buscar");
		buscar.setFont(new Font ("Myriad pro", Font.BOLD, 20));
		panel2.add(buscar);
		buscar.setActionCommand("BuscarLibro");
		buscar.addActionListener(controlador);
		
		return panel2;
	}

	private Component crearPanelCentro() {
		JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 5));
		panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		
		buscar2 = new JTextField();
		buscar2.setFont(new Font ("Myriad pro", Font.BOLD, 15));
        panelCentro.add(buscar2);
        
        filtro = new JComboBox<>(filtrar);
        filtro.setFont(new Font ("Myriad pro", Font.BOLD, 15));
        filtro.setActionCommand("filtro");
		filtro.addActionListener(controlador);
        panelCentro.add(filtro);
		return panelCentro;
	}
	
	public String leerClave(){
		return buscar2.getText();
	}
	public String leerFiltro(){
		return filtro.getSelectedItem().toString();
	}
}