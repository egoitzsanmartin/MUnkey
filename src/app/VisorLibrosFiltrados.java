package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objetos.Like;
import objetos.Obra;
import objetos.Obras;



public class VisorLibrosFiltrados extends JPanel implements ActionListener {
	Controlador controlador;
	JLabel nombre,icono;
	Obras listaObras;
	String clave, filtro;
	List<Obra> listaFiltrada;
	int principio;
	JButton derecha, izquierda;
	JPanel panel, panelBotonDerecha, panelBotonIzquierda;

	public VisorLibrosFiltrados(Controlador controlador, Obras listaLibros, String clave, String filtro) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.listaObras=listaObras;
		principio=0;
		this.clave=clave;
		this.filtro=filtro;
		listaFiltrada=new ArrayList<>();
		if (filtro.equals("Género")) {
			listaFiltrada=filtrarPorGenero(listaLibros, clave);
		}
		if (filtro.equals("Idioma")) {
			listaFiltrada=filtrarPorIdioma(listaLibros, clave);
		}
		if (filtro.equals("Título")) {
			listaFiltrada=filtrarPorTitulo(listaLibros, clave);
		}
		if (filtro.equals("Autor")) {
			listaFiltrada=filtrarPorAutor(listaLibros, clave);
		}
		if (filtro.equals("Likes")) {
			listaFiltrada=filtrarPorLike(listaLibros, clave);
		}
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(crearPanelCentro(), BorderLayout.CENTER);
		this.add(panelAbajo(), BorderLayout.SOUTH);
		this.add(crearPanelBotonDerecha(), BorderLayout.EAST);
		this.add(crearPanelBotonIzquierda(), BorderLayout.WEST);
	}

	private List<Obra> filtrarPorLike(Obras listaLibros, String clave) {
		List<Obra> filtrado=new ArrayList<>();
		for(Obra obra:listaLibros.getListaObras()) {
			for(Like like:obra.getListaLikes()) {
			if(like.getAutor().equals(clave)) {
				filtrado.add(obra);
			}
			}
		}
		return filtrado;
	}


	private Component crearPanelCentro() {
		panel = new JPanel(new GridLayout(2, 5, 40, 40));
		panel.setBorder(BorderFactory.createEmptyBorder(90, 30, 0, 20));
		panel.setOpaque(false);
		
		crearBotones();
		
		
		return panel;
	}
	private Component crearPanelBotonIzquierda() {
		panelBotonIzquierda = new JPanel();
		panelBotonIzquierda.setBorder(BorderFactory.createEmptyBorder(200, 30, 0, 20));
		panelBotonIzquierda.setOpaque(false);
		if(listaFiltrada.size()>6 ) {
		izquierda = new JButton(new ImageIcon("art/botones/back.png"));
		izquierda.setVisible(false);
		izquierda.addActionListener(this);
		izquierda.setActionCommand("Izquierda");
		izquierda.setContentAreaFilled(false);
		izquierda.setBorderPainted(false);
		panelBotonIzquierda.add(izquierda);
		}
		return panelBotonIzquierda;
	}

	private Component crearPanelBotonDerecha() {
		panelBotonDerecha = new JPanel();
		panelBotonDerecha.setBorder(BorderFactory.createEmptyBorder(200, 30, 0, 20));
		panelBotonDerecha.setOpaque(false);
		if(listaFiltrada.size()>6) {
		derecha = new JButton(new ImageIcon("art/botones/next.png"));
		derecha.addActionListener(this);
		derecha.setActionCommand("Derecha");
		derecha.setContentAreaFilled(false);
		derecha.setBorderPainted(false);
		panelBotonDerecha.add(derecha);
		}
		return panelBotonDerecha;
	}
	
	public void crearBotones() {
		for(int i = principio; (i < principio + 6 && i < listaFiltrada.size()); i++) {
			ImageIcon icon = new ImageIcon("obras/"+listaFiltrada.get(i).getPortada());
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(260, 260,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newIcon = new ImageIcon(newimg);
			JButton boton = new JButton(newIcon);
			boton.addActionListener(controlador);
			boton.setActionCommand(Integer.toString(listaFiltrada.get(i).getObraID()));
			boton.setContentAreaFilled(false);
			boton.setBorderPainted(false);
			panel.add(boton);
	}
	}


	private List<Obra> filtrarPorGenero(Obras listaLibros, String clave) {
		List<Obra> filtrado=new ArrayList<>();
		for(Obra obra:listaLibros.getListaObras()) {
			if(obra.getGenero().contains(clave)) {
				filtrado.add(obra);
			}
		}
		return filtrado;
	}
	private List<Obra> filtrarPorTitulo(Obras listaLibros, String clave) {
		List<Obra> filtrado=new ArrayList<>();
		for(Obra obra:listaLibros.getListaObras()) {
			if(obra.getTitulo().contains(clave)) {
				filtrado.add(obra);
			}
		}
		return filtrado;
	}
	private List<Obra> filtrarPorAutor(Obras listaLibros, String clave) {
		List<Obra> filtrado=new ArrayList<>();
		for(Obra obra:listaLibros.getListaObras()) {
			if(obra.getAutor().contains(clave)) {
				filtrado.add(obra);
			}
		}
		return filtrado;
	}
	private List<Obra> filtrarPorIdioma(Obras listaLibros, String clave) {
		List<Obra> filtrado=new ArrayList<>();
		for(Obra obra:listaLibros.getListaObras()) {
			if(obra.getIdioma().contains(clave)) {
				filtrado.add(obra);
			}
		}
		return filtrado;
	}

	public Component panelVacio() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		JLabel vacio = new JLabel();
		panel.add(vacio, BorderLayout.WEST);
		return panel;
	}
	
	public Component panelAbajo() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));
		panel.setOpaque(false);
		JButton home = new JButton(new ImageIcon("art/botones/home.png"));
		home.addActionListener(controlador);
		home.setActionCommand("home");
		home.setToolTipText("Vuelve al menu principal");
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		
		panel.add(home, BorderLayout.WEST);
		
		return panel;
	}
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("art/fondo.png");
		g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Derecha")) {
			principio += 6;
			this.remove(panel);
			this.remove(panelBotonDerecha);
			this.remove(panelBotonIzquierda);
			this.add(crearPanelCentro(), BorderLayout.CENTER);
			if((principio + 6) > listaFiltrada.size()) {
				derecha.setVisible(false);
			}
			izquierda.setVisible(true);
			this.add(panelBotonDerecha, BorderLayout.EAST);
			this.add(panelBotonIzquierda, BorderLayout.WEST);
			this.revalidate();
			
		}
		if(e.getActionCommand().equals("Izquierda")) {
			principio -= 6;
			this.remove(panel);
			this.remove(panelBotonDerecha);
			this.remove(panelBotonIzquierda);
			this.add(crearPanelCentro(), BorderLayout.CENTER);
			if(principio <= 0) {
				izquierda.setVisible(false);
			}
			derecha.setVisible(true);
			this.add(panelBotonDerecha, BorderLayout.EAST);
			this.add(panelBotonIzquierda, BorderLayout.WEST);
			this.revalidate();
		}
		
	}

	

}
