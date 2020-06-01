package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class MensajeDirecto extends JFrame implements ActionListener, ListSelectionListener{
	String usuario;
	Usuarios listaUsuarios;
	JTextField cajaTexto;
	ModeloChat modeloChat;
	ModeloTexto modeloMensaje;
	JButton boton;
	Controlador controlador;
	RendererChat rendererChat;
	RendererTexto rendererTexto;
	JList<Chat> listaChat;
	JList<Mensaje> listaTexto;
	JScrollPane panelVisor;
	JScrollPane panelChat;
	
	
	public MensajeDirecto(Controlador controlador, Usuarios listaUsuarios, Usuario usuario){
		super("Mensaje Directo");
		this.controlador = controlador;
		this.usuario = usuario.getNombre();
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
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, crearPanelConversaciones(), crearPanelChat());
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
		boton = new JButton(new ImageIcon("art/botones/enviar.png"));
		boton.addActionListener(this);
		boton.setContentAreaFilled(false);
		boton.setBorderPainted(false);
		panel.add(cajaTexto, BorderLayout.WEST);
		panel.add(boton, BorderLayout.EAST);
		return panel;
	}
	
	private Component crearPanelConversaciones() {
		panelChat = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		modeloChat.getChats().get(0).getListaMensajes().add(new Mensaje(timestamp, usuario, modeloChat.getChats().get(0).getConversacionID(), cajaTexto.getText()));
		cajaTexto.setText("");
		
		modeloChat = new ModeloChat(this, this.controlador, this.usuario);
		modeloMensaje = new ModeloTexto(this, this.controlador, modeloChat);
		this.setContentPane(crearPanelVentana());
		
		this.setVisible(true);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			modeloMensaje.cambiarLista(modeloChat.getElementAt(listaChat.getSelectedIndex()).getListaMensajes());
		}
		
	}
}
