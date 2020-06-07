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

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import objetos.Obra;
import objetos.Usuario;



public class VisorLibro extends JPanel {
	Controlador controlador;
	JLabel nombre,icono;
	Obra obra;
	Usuario user;

	public VisorLibro(Controlador controlador, Usuario user, Obra obra) {
		super(new BorderLayout());
		this.controlador = controlador;
		this.user = user;
		this.obra = obra;
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
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
		panel.setBorder(BorderFactory.createEmptyBorder(200, 20, 30, 20));
		panel.setOpaque(false);
		JButton home = new JButton(new ImageIcon("art/botones/home.png"));
		home.addActionListener(controlador);
		home.setActionCommand("home");
		home.setToolTipText("Vuelve al menu principal");
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		
		JButton masObra = new JButton(new ImageIcon("art/botones/masObra.png"));
		masObra.addActionListener(controlador);
		masObra.setActionCommand("masObra");
		masObra.setToolTipText("Deja aquí tu comentario o añade la obra a tu lista de favoritas");
		masObra.setContentAreaFilled(false);
		masObra.setBorderPainted(false);
		
		JButton reportar = new JButton(new ImageIcon("art/botones/reportar.png"));
		reportar.setToolTipText("¿Esta obra incumple las leyes de derechos de autor? Envia un correo a munkey.copyright@gmail.com con la información pertinente, nosotros nos encargamos del resto.");
		reportar.setContentAreaFilled(false);
		reportar.setBorderPainted(false);
		
		panel.add(reportar, BorderLayout.NORTH);
		panel.add(masObra, BorderLayout.CENTER);
		panel.add(home, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private Component crearPanelCentro() {
		String filePath="C:/Users/Unai/Desktop/pbl/Munkey 41/obras/"+obra.getPDF();
		SwingController controller = new SwingController();
	    SwingViewBuilder factory = new SwingViewBuilder(controller);
	    JPanel viewerComponentPanel = factory.buildViewerPanel();
	    controller.getDocumentViewController().setAnnotationCallback(
	    new org.icepdf.ri.common.MyAnnotationCallback(
	    controller.getDocumentViewController()));
	    controller.openDocument(filePath);
	    viewerComponentPanel.setBorder(BorderFactory.createEmptyBorder(140, 20, 30, 40));
	    viewerComponentPanel.setOpaque(false);
		return viewerComponentPanel;
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("art/fondo.png");
		g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
