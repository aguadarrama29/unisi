package com.universidad.manageBean;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.universidad.modelo.negocio.CatUsuario;
import com.universidad.servicio.IUniversidadServicio;
import com.universidad.util.MsgUtil;
import com.universidad.util.ResultTypeUtil;



@ManagedBean(name = "login")
@SessionScoped
public class Login implements Serializable {

	@ManagedProperty(value = "#{UniversidadServicio}")
	IUniversidadServicio iUniversidadServicio;

	private static final long serialVersionUID = 1L;
	private CatUsuario usuario;

	public Login() {
		this.initObjects();
	}

	private void initObjects() {
		usuario = new CatUsuario();
	}

	public String doLogin() throws IOException {
		usuario =getiUniversidadServicio().login(usuario);
		String resultado = null;
		if (usuario != null) {
			if (usuario.getCatPerfil().getId()==2) {
				resultado = ResultTypeUtil.ADMINISTRADOR;
			}

			if (usuario.getCatPerfil().getId()==1) {
				resultado = ResultTypeUtil.USUARIO;
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("usuario", usuario);
		} else {
			MsgUtil.msgError("Error!", "El usuario y/o contraseña son incorrectos");
			this.initObjects();
		}

		return resultado;
	}

	

	public CatUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(CatUsuario usuario) {
		this.usuario = usuario;
	}

	public IUniversidadServicio getiUniversidadServicio() {
		return iUniversidadServicio;
	}

	public void setiUniversidadServicio(IUniversidadServicio iUniversidadServicio) {
		this.iUniversidadServicio = iUniversidadServicio;
	}

	
	

}