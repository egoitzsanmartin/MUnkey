package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import objetos.Usuario;



public class Perfil extends JPanel {
	Controlador controlador;
	JLabel nombre,icono;

	public Perfil(Controlador controlador, Usuario user) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		this.add(crearBotonesArriba(user),BorderLayout.NORTH);
		this.add(crearBotonesCentro(), BorderLayout.CENTER);
		this.add(panelAbajo(), BorderLayout.SOUTH);
	}
	
	private Component crearBotonesArriba(Usuario user) {
		JPanel panel=new JPanel(new GridLayout(2,5,0,0));
		panel.setBorder(BorderFactory.createEmptyBorder(170, 350, 0, 0));
		panel.setOpaque(false);
		nombre=new JLabel(user.getUsername());
		nombre.setFont(new Font ("myriad pro", Font.BOLD, 55));
		nombre.setForeground(Color.ORANGE);
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setVerticalAlignment(SwingConstants.CENTER);
		
		String image=(user.getFoto());
		
		if(image==null) {
		icono=new JLabel (new ImageIcon("art/perfilBase.png"));	
		}
		else {
			icono=new JLabel (new ImageIcon(image));
		}
		panel.add(panelVacio());
		panel.add(panelVacio());
		panel.add(panelVacio());
		panel.add(panelVacio());
		panel.add(nombre);
		panel.add(panelVacio());
		panel.add(panelVacio());
		panel.add(panelVacio());
		panel.add(panelVacio());
		panel.add(icono);
		return panel;
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
	
	private Component crearBotonesCentro() {
		JPanel panel = new JPanel(new GridLayout(1, 5, 20, 40));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 20));
		panel.setOpaque(false);
		JButton fav = new JButton(new ImageIcon("art/botones/favMenu.png"));
		fav.addActionListener(controlador);
		fav.setActionCommand("favoritas");
		fav.setToolTipText("Una lista de las obras que más te han gustado");
		fav.setContentAreaFilled(false);
		fav.setBorderPainted(false);
		
		JButton misObras = new JButton(new ImageIcon("art/botones/misObras.png"));
		misObras.addActionListener(controlador);
		misObras.setActionCommand("misObras");
		misObras.setToolTipText("La lista de las obras que has compartido con la comunidad");
		misObras.setContentAreaFilled(false);
		misObras.setBorderPainted(false);
		
		JButton md = new JButton(new ImageIcon("art/botones/md.png"));
		md.addActionListener(controlador);
		md.setActionCommand("md");
		md.setToolTipText("Mira las conversaciones que has tenido con las editoriales");
		md.setContentAreaFilled(false);
		md.setBorderPainted(false);
		
		panel.add(panelVacio());
		panel.add(fav);
		panel.add(misObras);
		panel.add(md);
		panel.add(panelVacio());
		
		return panel;
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("art/fondoPerfil.png");
		g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
