package app;

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
			for(Mensaje mensaje : modeloChat.getChats().get(0).getListaMensajes()) {
				mensajes.add(mensaje);
			}
		}
		
	}
	
	public void cambiarLista(List<Mensaje> listaMensajes) {

		for(Mensaje mensaje : listaMensajes) {
			mensajes.add(mensaje);
		}
	}
	
	public void añadirMensaje(Mensaje mensaje) {
		mensajes.add(mensaje);
		System.out.println(mensajes);
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
