package app;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import objetos.Chat;
import objetos.Mensaje;

public class ModeloTexto extends AbstractListModel<Mensaje>{
	
	List<Mensaje> mensajes;
	MensajeDirecto panel;
	ModeloChat modeloChat;
	

	public ModeloTexto(MensajeDirecto panel, Controlador controlador, ModeloChat modeloChat) {
		mensajes = new ArrayList<>();
		this.modeloChat = modeloChat;
		this.panel = panel;
		if(modeloChat.getChats().size() > 0) {
			if(modeloChat.getChats().get(0).getListaMensajes() != null) {
				for(Mensaje mensaje : modeloChat.getChats().get(0).getListaMensajes()) {
					mensajes.add(mensaje);
					mensaje.addPropertyChangeListener(panel);
				}
			}
		}
		
	}
	
	public void cambiarLista(List<Mensaje> listaMensajes) {
		mensajes = new ArrayList<>();
		mensajes.addAll(listaMensajes);
		this.fireContentsChanged("cambioLista", -1, mensajes.size());
	}
	
	public void add(Mensaje mensaje) {
		mensajes.add(mensaje);
		this.fireContentsChanged("mensajeAñadido", -1, mensajes.size());
	}
	
	@Override
	public Mensaje getElementAt(int index) {
		
		return mensajes.get(index);
	}

	@Override
	public int getSize() {
		
		return mensajes.size();
	}

	
}
