package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objetos.Chat;
import objetos.Usuario;
import objetos.Usuarios;

public class DialogoEnviarMensaje extends JDialog implements ActionListener{
	
	JTextField txNombre;
	Controlador controlador;
	String usuario;
	Usuarios usuarios;
	
	public DialogoEnviarMensaje (JFrame ventana, Controlador controlador, String usuario, Usuarios usuarios) {
		super (ventana, "Datos Envio", true);
		this.controlador = controlador;
		this.usuario = usuario;
		this.usuarios = usuarios;
		this.setLocation(250,100);
		this.setSize(350, 150);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new GridLayout(2,1));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add (crearPanelDatos(),BorderLayout.CENTER);
		panel.add (crearPanelBotones(),BorderLayout.SOUTH);
		return panel;
	}



	private Component crearPanelDatos() {
		
		JPanel panel = new JPanel (new FlowLayout());
		JLabel label = new JLabel("Usuario: ");
		txNombre = new JTextField(20);
		txNombre.setBorder(BorderFactory.createLoweredBevelBorder());
		
		panel.add(label);
		panel.add(txNombre);
		
		return panel;
	}

	private JPanel crearPanelBotones() {
		JPanel panel = new JPanel ();
		JButton bOk = new JButton ("Confirmar");
		bOk.setActionCommand("ok");
		bOk.addActionListener(this);
		panel.add(bOk);
		JButton bCancel = new JButton ("Cancelar");
		bCancel.setActionCommand("cancel");
		bCancel.addActionListener(this);
		panel.add(bCancel);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("ok")) {
			for(Usuario usuario : usuarios.getListaUsuarios()) {
				if(usuario.getNombre().equals(txNombre.getText())) {
					controlador.listaChats.getListaChats().add(new Chat(controlador.listaChats.getListaChats().size()+1, this.usuario, txNombre.getText()));
				}
			}
			
		}
		this.dispose();
	}
}
