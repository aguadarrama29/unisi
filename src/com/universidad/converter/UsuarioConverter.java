package com.universidad.converter;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.log4j.Logger;

import com.universidad.modelo.negocio.CatUsuario;
import com.universidad.servicio.IUniversidadServicio;

@ManagedBean(name = "usaurioConverter")
@RequestScoped
public class UsuarioConverter implements Converter {

	@ManagedProperty(value = "#{UniversidadServicio}")
	IUniversidadServicio iUniversidadServicio;

	private static final Logger logger = Logger.getLogger(UsuarioConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent component, String value) {
		try {
			if (value != null && value.trim().length() > 0) {
				return getiUniversidadServicio().obtenerUsuarioXId(Integer.valueOf(value));
			} else {
				return null;
			}
		} catch (Exception e) {
			StringWriter stack = new StringWriter();
			e.printStackTrace(new PrintWriter(stack));
			logger.error(stack.toString());
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return String.valueOf(((CatUsuario) value).getId());
		} else {
			return null;
		}
	}

	public IUniversidadServicio getiUniversidadServicio() {
		return iUniversidadServicio;
	}

	public void setiUniversidadServicio(IUniversidadServicio iUniversidadServicio) {
		this.iUniversidadServicio = iUniversidadServicio;
	}

	

	

}
