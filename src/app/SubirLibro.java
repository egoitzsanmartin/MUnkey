package src.app;
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



public class SubirLibro extends JDialog{

	String [] tipos = {"Comedia", "Miedo", "Acción", "Policíaca", "Fantástica", "Ciencia ficción", "otros"};
	String extension = "*.TXT, txt";
	JLabel titulo, nombre, genero, pdf, portada;
	JButton cancelar, aceptar, seleccionar, seleccionar2;
	//Libro libro;
	Controlador controlador;
	JFileChooser portada1, pdf1;
	FileNameExtensionFilter filtroPortada, filtroPdf;
	JComboBox generoo;
	JTextField nombree, portada2, pdf2;
	
	public SubirLibro(Controlador controlador)
	{
		super(new JDialog());
		this.controlador = controlador;
		this.setSize(830,330);
		this.setLocation(300,150);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(20, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(crearPanelNorte(), BorderLayout.NORTH);
		panel.add(crearPanelCentro(), BorderLayout.CENTER);
		panel.add(crearPanelSur(), BorderLayout.SOUTH);
		return panel;
	}
	
	

	private Component crearPanelSur() {
		JPanel panel2 = new JPanel(new FlowLayout());
		panel2.setOpaque(false);
		JButton aceptar = new JButton(new ImageIcon("art/botones/aceptar.png"));
		aceptar.addActionListener(controlador);
		aceptar.setActionCommand("aceptar");
		aceptar.setContentAreaFilled(false);
		aceptar.setBorderPainted(false);
		panel2.add(aceptar);

		JButton cancelar = new JButton(new ImageIcon("art/botones/cancelar.png"));
		cancelar.addActionListener(controlador);
		cancelar.setActionCommand("cancelar");
		cancelar.setContentAreaFilled(false);
		cancelar.setBorderPainted(false);
		panel2.add(cancelar);
		
		return panel2;
	}

	private Component crearPanelCentro() {
		JPanel panelCentro = new JPanel(new GridLayout(2, 3, 20, 5));
		panelCentro.setOpaque(false);
		panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		
		portada = new JLabel("Portada:");
		portada.setFont(new Font ("myriad pro", Font.BOLD, 20));
		panelCentro.add(portada);
		
		portada2 = new JTextField();
		portada2.setFont(new Font ("myriad pro", Font.BOLD, 12));
		portada2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		portada2.setBackground(Color.GRAY);
        portada2.setToolTipText("Inserta la ruta de la portada");
        panelCentro.add(portada2);
        
        seleccionar =  new JButton(new ImageIcon("art/botones/buscar.png"));
        seleccionar.setActionCommand("Seleccionar");
		seleccionar.addActionListener(controlador);
		seleccionar.setContentAreaFilled(false);
		seleccionar.setBorderPainted(false);
        panelCentro.add(seleccionar);
            
        seleccionar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                seleccionar("JPG", "jpg", panelCentro, portada2);
            }
        });
        pdf = new JLabel("Obra (PDF):");
		pdf.setFont(new Font ("myriad pro", Font.BOLD, 20));
		panelCentro.add(pdf);
		
		pdf2 = new JTextField();
		pdf2.setFont(new Font ("myriad pro", Font.BOLD, 12));
		pdf2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		pdf2.setBackground(Color.GRAY);
        pdf2.setToolTipText("Inserta la ruta del PDF");
        panelCentro.add(pdf2);
       
        seleccionar2 =  new JButton(new ImageIcon("art/botones/buscar.png"));
        seleccionar2.setActionCommand("Seleccionar2");
		seleccionar2.addActionListener(controlador);
		seleccionar2.setContentAreaFilled(false);
		seleccionar2.setBorderPainted(false);
        panelCentro.add(seleccionar2);
        seleccionar2.addActionListener(new ActionListener(){
        public void actionPerformed (ActionEvent e){
        	seleccionar("PDF","pdf", panelCentro, pdf2);
        	}
        });
        
		return panelCentro;
	}
	
	void seleccionar(String descripcion, String extension, JPanel panel, JTextField texto) {
                
            	JFileChooser fileChooser=new JFileChooser();
                
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                
                FileNameExtensionFilter filtro = new FileNameExtensionFilter(descripcion, extension);

                fileChooser.setFileFilter(filtro);
                
                int seleccion=fileChooser.showSaveDialog(panel);
                 
                if(seleccion==JFileChooser.APPROVE_OPTION){
                 
                    File fichero=fileChooser.getSelectedFile();
                 
                    texto.setText(fichero.getAbsolutePath());
                 
                    /*try(FileReader portada1=new FileReader(fichero)){
                        String cadena="";
                        int valor=portada1.read();
                        while(valor!=-1){
                            cadena=cadena+(char)valor;
                            valor=portada1.read();
                        }
                        portada2.setText(cadena);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }*/
                }
	};

	private Component crearPanelNorte() {
		JPanel panelNorte = new JPanel(new GridLayout(2, 2, 40, 10));
		panelNorte.setOpaque(false);
		panelNorte.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		titulo = new JLabel("Titulo:");
		titulo.setFont(new Font ("myriad pro", Font.BOLD, 20));
		panelNorte.add(titulo);
		
		nombree = new JTextField();
		nombree.setFont(new Font ("myriad pro", Font.BOLD, 20));
		nombree.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		nombree.setBackground(Color.GRAY);
		panelNorte.add(nombree);
		
		genero = new JLabel("Género:");
		genero.setFont(new Font ("myriad pro", Font.BOLD, 20));
		panelNorte.add(genero);
		
		generoo = new JComboBox<>(tipos);
		generoo.setFont(new Font ("myriad pro", Font.BOLD, 20));
		generoo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		generoo.setBackground(Color.GRAY);
		panelNorte.add(generoo);
		
		return panelNorte;
	}
	
	void hola() {
		
 
	}


}