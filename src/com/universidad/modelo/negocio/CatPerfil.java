package com.universidad.modelo.negocio;
// Generated 14/10/2018 11:11:11 AM by Hibernate Tools 4.0.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * CatPerfil generated by hbm2java
 */
public class CatPerfil implements java.io.Serializable {

	
	private static final long serialVersionUID = -899268451696958457L;
	private int id;
	private String descripcion;

	public CatPerfil() {
	}

	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatPerfil other = (CatPerfil) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

}
