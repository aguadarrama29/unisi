package com.universidad.servicio;

import java.util.List;

import com.universidad.modelo.negocio.CatDepto;
import com.universidad.modelo.negocio.CatEmpresa;
import com.universidad.modelo.negocio.CatPerfil;
import com.universidad.modelo.negocio.CatUsuario;
import com.universidad.modelo.negocio.Empleado;


public interface IUniversidadServicio {
	public CatUsuario login(CatUsuario usuario);
	
	// Usuario
	public List<CatUsuario> obtenerUsuarios();

	public void guardarUsuario(CatUsuario usuario);

	public CatUsuario obtenerUsuarioXId(int id);

	public void actualizarUsuario(CatUsuario usuario);

	public void eliminarUsuario(CatUsuario usuario);
	
	// Perfil
	public List<CatPerfil> obtenerPerfiles();

	public void guardarUsuario(CatPerfil catPerfil);

	public CatPerfil obtenerPerfilXId(int id);

	public void actualizarPerfil(CatPerfil catPerfil);

	public void eliminarPerfil(CatPerfil catPerfil);
	
	// Empleado
	public List<Empleado> obtenerEmpleados();

	public void guardarEmpleado(Empleado empleado);

	public Empleado obtenerEmpleadoXId(int id);

	public void actualizarEmpleado(Empleado empleado);

	public void eliminarEmpleado(Empleado empleado);
	
	public List<Empleado> obtenerEmpleadosXFiltro(int tipo,int id);
	
	public List<Empleado> obtenerEmpleadosXLetra(String letra);
	
	//empresa
	public List<CatEmpresa> obtenerCatEmpresas();
	
	public CatEmpresa obtenerCatEmpresaXId(int id);
	
	//depto
	public List<CatDepto> obtenerCatDeptos();
	
	public CatDepto obtenerCatDeptoXId(int id);
	
	public List<CatDepto> obtenerDeptosXEmpresa(int id);
	
	
	
}
