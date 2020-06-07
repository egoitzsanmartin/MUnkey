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
import objetos.ListaChats;
import objetos.Usuario;
import objetos.Usuarios;
import sql.Conectar;

public class DialogoEnviarMensaje extends JDialog implements ActionListener{
	
	JTextField txNombre;
	ModeloChat modeloChat;
	String usuario;
	Usuarios usuarios;
	ListaChats listaChat;
	Conectar conectar;
	
	public DialogoEnviarMensaje (JFrame ventana, ModeloChat modeloChat, String usuario, Usuarios usuarios, ListaChats listaChat) {
		super (ventana, "Datos Envio", true);
		conectar=new Conectar();
		this.modeloChat = modeloChat;
		this.usuario = usuario;
		this.usuarios = usuarios;
		this.listaChat=listaChat;
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
					Chat chat = new Chat(listaChat.getListaChats().size()+1, this.usuario, txNombre.getText());
					modeloChat.addChat(chat);
					conectar.guardarDatosChats(chat);
				}	
			}	
		}
		this.dispose();
	}
}
