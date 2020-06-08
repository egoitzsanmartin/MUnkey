package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import objetos.Chat;
import objetos.Mensaje;
import objetos.Usuario;
import objetos.Usuarios;
import sql.Conectar;


public class MensajeDirecto extends JFrame implements ActionListener, ListSelectionListener, PropertyChangeListener {
	String usuario;
	Usuarios listaUsuarios;
	boolean editorial;
	JTextField cajaTexto;
	ModeloChat modeloChat;
	ModeloTexto modeloMensaje;
	JButton botonEnviar;
	JButton botonConversación;
	Controlador controlador;
	RendererChat rendererChat;
	RendererTexto rendererTexto;
	JList<Chat> listaChat;
	JList<Mensaje> listaTexto;
	JScrollPane panelVisor;
	JScrollPane panelChat;
	Conectar conectar;
	
	public MensajeDirecto(Controlador controlador, Usuarios listaUsuarios, Usuario usuario){
		super("Mensaje Directo");
		this.controlador = controlador;
		conectar=new Conectar();
		this.usuario = usuario.getNombre();
		if(usuario.getTipo().equals("editorial")) {
			this.editorial=true;
		}else {
			this.editorial=false;
		}
		this.listaUsuarios = listaUsuarios;
		modeloChat = new ModeloChat(this, this.controlador, this.usuario);
		modeloMensaje = new ModeloTexto(this, this.controlador, modeloChat);
		listaChat = new JList<>();
		listaTexto = new JList<>();
		listaChat.addListSelectionListener(this);
		rendererChat = new RendererChat(listaUsuarios, this.usuario);
		rendererTexto = new RendererTexto(this.usuario);
		listaChat.setSelectedIndex(0);
		this.setSize (800,600);
		this.setLocation(600,200);
		this.setContentPane(crearPanelVentana());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
	}

	private Container crearPanelVentana() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, crearPanelIzquierda(), crearPanelChat());
		panel.setEnabled(false);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setBackground(new java.awt.Color(255, 251, 242, 255));
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
		botonEnviar = new JButton(new ImageIcon("art/botones/enviar.png"));
		botonEnviar.addActionListener(this);
		botonEnviar.setActionCommand("Enviar");
		botonEnviar.setContentAreaFilled(false);
		botonEnviar.setBorderPainted(false);
		panel.add(cajaTexto, BorderLayout.WEST);
		panel.add(botonEnviar, BorderLayout.EAST);
		return panel;
	}
	
	private Component crearPanelBoton() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setOpaque(false);
		panel.setBackground(new java.awt.Color(255, 251, 242, 255));
		botonConversación = new JButton(new ImageIcon("art/botones/enviar.png"));
		botonConversación.addActionListener(this);
		botonConversación.setActionCommand("Conversacion");
		botonConversación.setContentAreaFilled(false);
		botonConversación.setBorderPainted(false);
		panel.add(botonConversación, BorderLayout.CENTER);
		return panel;
	}
	public Component crearPanelIzquierda() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new java.awt.Color(255, 251, 242, 255));
		
		panel.add(crearPanelConversaciones(), BorderLayout.CENTER);
		if(editorial) {
		panel.add(crearPanelBoton(), BorderLayout.SOUTH);
		}
		return panel;
	}
	
	private Component crearPanelConversaciones() {
		panelChat = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelChat.setBackground(new java.awt.Color(255, 251, 242, 255));
		listaChat.setModel(modeloChat);
		listaChat.setCellRenderer(rendererChat);
		panelChat.setViewportView(listaChat);
		return panelChat;
	}

	private Component crearPanelVisor() {
		panelVisor = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		listaTexto.setModel(modeloMensaje);
		listaTexto.setCellRenderer(rendererTexto);
		panelVisor.setViewportView(listaTexto);
		return panelVisor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Enviar")) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Mensaje mensaje = new Mensaje(timestamp, usuario, modeloChat.getChats().get(listaChat.getSelectedIndex()).getConversacionID(), cajaTexto.getText());
			System.out.println(mensaje);
			modeloMensaje.add(mensaje);
			conectar.guardarDatosMensaje(mensaje);
			modeloChat.getChats().get(listaChat.getSelectedIndex()).add(mensaje);
			cajaTexto.setText("");
			this.repaint();
		}
		
		if(e.getActionCommand().equals("Conversacion")) {
			new DialogoEnviarMensaje(this, modeloChat, usuario, listaUsuarios, controlador.listaChats);
			this.repaint();
		}
	
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(listaChat.getSelectedValue() != null) {		
			modeloMensaje.cambiarLista(modeloChat.getElementAt(listaChat.getSelectedIndex()).getListaMensajes());
			this.repaint();
		}
		
	}

	@Override
	public void dispose() {
		controlador.listaChats.getListaChats().clear();
		controlador.listaChats.getListaChats().addAll(modeloChat.getChats());
		super.dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		panelChat.revalidate();
		panelVisor.revalidate();
		this.repaint();
		
	}
}
