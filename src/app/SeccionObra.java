package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;

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
import objetos.Obra;
import objetos.Usuario;
import objetos.Usuarios;



public class SeccionObra extends JPanel {
	String usuario;
	Usuarios listaUsuarios;
	JTextField cajaTexto;
	ModeloChat modeloChat;
	JButton boton;
	Controlador controlador;
	RendererTexto rendererTexto;
	DefaultListModel<Comentario> model;
	JList<Comentario> listaTexto;
	JScrollPane panelVisor;
	JScrollPane panelChat;
	

	public SeccionObra(Controlador controlador, Usuario user, Obra obra, Comentarios comentarios) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		model = new DefaultListModel<>();
		listaTexto = new JList<>( model );
		comprobarComentarios(obra, comentarios);
		this.add(crearPanelCentro(obra), BorderLayout.CENTER);
		this.add(panelAbajo(),BorderLayout.WEST);
	}
	

	private void comprobarComentarios(Obra obra, Comentarios comentarios) {
		for(int i=0;i<comentarios.getListaComentarios().size();i++) {
			if(comentarios.getListaComentarios().get(i).getObraID()==obra.getObraID()) {
				model.addElement(comentarios.getListaComentarios().get(i));
			}
		}
		
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
	
	private Component crearPanelCentro(Obra obra) {
		JPanel panel = new JPanel(new BorderLayout());
		
		return panel;
	}
	private Component crearPanelChat() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setBackground(new java.awt.Color(255, 251, 242, 255));
		panel.add(crearPanelVisor(), BorderLayout.CENTER);
		panel.add(crearPanelMensaje(), BorderLayout.SOUTH);
		return panel;
	}


	private Component crearPanelMensaje() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setOpaque(false);
		cajaTexto = new JTextField(20);
		boton = new JButton(new ImageIcon("art/botones/enviar.png"));
		boton.setActionCommand("Comentario");
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		panel.add(cajaTexto, BorderLayout.WEST);
		panel.add(boton, BorderLayout.EAST);
		return panel;
	}
	private Component crearPanelVisor() {
		panelVisor = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelVisor.setViewportView(listaTexto);
		return panelVisor;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("art/fondo.png");
		g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
