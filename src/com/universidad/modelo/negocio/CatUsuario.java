package com.universidad.modelo.negocio;
// Generated 14/10/2018 11:11:11 AM by Hibernate Tools 4.0.1.Final

/**
 * CatUsuario generated by hbm2java
 */
public class CatUsuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4451822493853345123L;
	private int id;
	private CatPerfil catPerfil;
	private String descripcion;
	private String correo;
	private String telefono;
	private String contrasenia;
	private Boolean estatus;

	public CatUsuario() {
	}

	public CatUsuario(int id, CatPerfil catPerfil, String descripcion, String contrasenia) {
		this.id = id;
		this.catPerfil = catPerfil;
		this.descripcion = descripcion;
		this.contrasenia = contrasenia;
	}

	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CatPerfil getCatPerfil() {
		return this.catPerfil;
	}

	public void setCatPerfil(CatPerfil catPerfil) {
		this.catPerfil = catPerfil;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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
		CatUsuario other = (CatUsuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	

}