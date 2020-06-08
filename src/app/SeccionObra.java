package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import objetos.Comentario;
import objetos.Comentarios;
import objetos.Like;
import objetos.Likes;
import objetos.Obra;
import objetos.Usuario;
import sql.Conectar;



public class SeccionObra extends JPanel implements PropertyChangeListener, ActionListener {
	
	JTextField cajaTexto;
	Controlador controlador;
	RendererComentarios rendererComentario;
	ModeloComentarios model;
	JList<Comentario> listaComentarios;
	JScrollPane panelComentarios;
	Usuario user;
	Obra obra;
	Comentarios comentarios;
	Likes likes;
	Conectar conectar;
	

	public SeccionObra(Controlador controlador, Usuario user, Obra obra) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.user=user;
		this.obra=obra;
		conectar = new Conectar();
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		model = new ModeloComentarios(this, this.controlador, this.obra);
		listaComentarios = new JList<>();
		rendererComentario = new RendererComentarios(this.user.getNombre());
		this.add(crearPanelCentro(), BorderLayout.CENTER);
		this.add(panelAbajo(),BorderLayout.WEST);
		this.add(crearPanelInfo(),BorderLayout.NORTH);
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
		
		panel.add(home, BorderLayout.SOUTH);
		
		
		return panel;
	}
	
	private Component crearPanelCentro() {
		JPanel panel=new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(300, 20, 30, 70));
		panel.setOpaque(false);
		panel.add(crearPanelLikes(),BorderLayout.EAST);
		panel.add(crearPanelChat(), BorderLayout.CENTER);
		panel.add(crearPanelCaja(),BorderLayout.SOUTH);
		return panel;
	}
	private Component crearPanelInfo() {
		JPanel panel= new JPanel (new GridLayout(1,2));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 200, 40, 70));
		panel.setOpaque(false);
		JLabel titulo=new JLabel(obra.getTitulo());
		titulo.setFont(new Font ("myriad pro", Font.BOLD, 50));
		titulo.setForeground(Color.ORANGE);
		
		panel.add(titulo);
		
		JLabel autor=new JLabel("por "+obra.getAutor());
		autor.setFont(new Font ("myriad pro", Font.BOLD, 30));
		autor.setForeground(Color.ORANGE);
		
		panel.add(autor);
		return panel;
	}

	private Component crearPanelLikes() {
		JPanel panel = new JPanel(new GridLayout(2,2));
		panel.setOpaque(false);
		JButton boton = new JButton(new ImageIcon("art/botones/fav.png"));
		boton.addActionListener(controlador);
		boton.setActionCommand("nuevoLike");
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);

		int numeroLikes=obra.getListaLikes().size();
		JLabel labelLikes = new JLabel(Integer.toString(numeroLikes));
		labelLikes.setFont(new Font ("myriad pro", Font.BOLD, 70));
		labelLikes.setForeground(Color.ORANGE);
		for(Like like:obra.getListaLikes()) {
			if(like.getAutor().equals(user.getUsername()));
			boton = new JButton(new ImageIcon("art/botones/favSi.png"));
			boton.addActionListener(controlador);
			boton.setActionCommand("nuevoLike");
			boton.setContentAreaFilled(false);
			boton.setBorderPainted(false);
		}
		panel.add(panelVacio());
		panel.add(boton);
		panel.add(panelVacio());
		panel.add(labelLikes);
		return panel;
	}

	private Component crearPanelCaja() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setOpaque(false);
		cajaTexto = new JTextField(40);
		JButton boton = new JButton(new ImageIcon("art/botones/enviar.png"));
		boton.addActionListener(this);
		boton.setActionCommand("nuevoComentario");
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		for(Comentario comentario:user.getListaComentarios()) {
			if(comentario.getObraID()==obra.getObraID()) {
				boton.setOpaque(false);
			}
		}
		panel.add(cajaTexto, BorderLayout.WEST);
		panel.add(boton, BorderLayout.EAST);
		return panel;
	}

	private Component crearPanelChat() {
		panelComentarios = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listaComentarios.setModel(model);
		listaComentarios.setCellRenderer(rendererComentario);
		panelComentarios.setViewportView(listaComentarios);
		return panelComentarios;
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("art/fondo.png");
		g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public String leerMensaje(){
		return cajaTexto.getText();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		panelComentarios.revalidate();
		this.repaint();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("nuevoComentario")) {
			Timestamp date = new Timestamp(System.currentTimeMillis());
			Comentario comentario = new Comentario(date, user.getNombre(), obra.getObraID(), cajaTexto.getText());
			
			obra.getListaComentarios().add(comentario);
			model.add(comentario);
			conectar.guardarDatosComentario(comentario);
			cajaTexto.setText("");
			this.repaint();
		}
		
	}

}
