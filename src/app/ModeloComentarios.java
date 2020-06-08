package app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import objetos.Comentario;
import objetos.Obra;

public class ModeloComentarios extends AbstractListModel<Comentario>{
	
	List<Comentario> comentarios;
	SeccionObra panel;
	ModeloChat modeloChat;
	

	public ModeloComentarios(SeccionObra panel, Controlador controlador, Obra obra) {
		comentarios = new ArrayList<>();
		this.panel = panel;
		if(obra.getListaComentarios() != null) {
			for(Comentario comentario : obra.getListaComentarios()) {
				comentarios.add(comentario);
				comentario.addPropertyChangeListener(panel);
			}
		}
		
	}
	
	public void add(Comentario comentario) {
		comentarios.add(comentario);
		this.fireContentsChanged("mensajeAñadido", -1, comentarios.size());
	}
	
	@Override
	public Comentario getElementAt(int index) {
		
		return comentarios.get(index);
	}

	@Override
	public int getSize() {
		
		return comentarios.size();
	}

	
}
