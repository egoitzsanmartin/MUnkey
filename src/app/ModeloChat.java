package app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import objetos.Chat;

public class ModeloChat extends AbstractListModel<Chat>{
	
	List<Chat> chats;
	MensajeDirecto panel;
	String usuario;
	
	public ModeloChat(MensajeDirecto panel, Controlador controlador, String usuario) {
		chats = new ArrayList<>();
		this.usuario = usuario;
		this.panel = panel;
		for(Chat chat : controlador.listaChats.getListaChats()) {
			if(chat.getUsuario1().equals(usuario) || chat.getUsuario2().equals(usuario)) {
				chats.add(chat);
				chat.addPropertyChangeListener(panel);
			}
		}
	}
	
	public void addChat(Chat chat) {
		chats.add(chat);
		this.fireContentsChanged("chatAñadido", -1, chats.size());
	}

	public List<Chat> getChats() {
		return chats;
	}

	@Override
	public Chat getElementAt(int index) {
		
		return chats.get(index);
	}

	@Override
	public int getSize() {
		
		return chats.size();
	}
	
}
