package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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
import objetos.Usuarios;



public class SeccionObra extends JPanel {
	String usuario;
	Usuarios listaUsuarios;
	JTextField cajaTexto;
	
	Controlador controlador;
	RendererTexto rendererTexto;
	DefaultListModel<Comentario> model;
	JList<Comentario> listaTexto;
	JScrollPane panelVisor;
	JScrollPane panelChat;
	Usuario user;
	Obra obra;
	Comentarios comentarios;
	Likes likes;
	

	public SeccionObra(Controlador controlador, Usuario user, Obra obra) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.user=user;
		this.obra=obra;
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		model = new DefaultListModel<>();
		listaTexto = new JList<>( model );
		this.add(crearPanelCentro(), BorderLayout.CENTER);
		this.add(panelAbajo(),BorderLayout.WEST);
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
		panel.add(crearPanelInfo(),BorderLayout.NORTH);
		return panel;
	}
	private Component crearPanelInfo() {
		JPanel panel= new JPanel (new GridLayout(1,2));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 190, 70));
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
		boton.addActionListener(controlador);
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
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		for(Comentario comentario:obra.getListaComentarios()) {
			JLabel mensaje=new JLabel(comentario.getfSubida().toString()+" "+comentario.getAutor()+":\n"+comentario.getComentario());
			panel.add(mensaje);
		}
		return panel;
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

}
