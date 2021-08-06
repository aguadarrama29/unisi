package com.universidad.seguridad;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.universidad.modelo.negocio.CatUsuario;



@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;
	private CatUsuario usuario;
	

	public UserSession() {
		
	}	

	public int getTipo() {
		FacesContext context = FacesContext.getCurrentInstance();
		usuario = (CatUsuario) context.getExternalContext().getSessionMap().get("usuario");
		return usuario.getCatPerfil().getId();
	}

	public String getNombre() {
		FacesContext context = FacesContext.getCurrentInstance();
		usuario = (CatUsuario) context.getExternalContext().getSessionMap().get("usuario");
		return usuario.getDescripcion();
	}

	public CatUsuario getUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		usuario = (CatUsuario) context.getExternalContext().getSessionMap().get("usuario");
		return usuario;
	}
	
	

}