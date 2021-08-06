package com.universidad.servicio;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.universidad.modelo.dao.IUniversidadDAO;
import com.universidad.modelo.negocio.CatDepto;
import com.universidad.modelo.negocio.CatEmpresa;
import com.universidad.modelo.negocio.CatPerfil;
import com.universidad.modelo.negocio.CatUsuario;
import com.universidad.modelo.negocio.Empleado;


@Transactional(readOnly = true)
public class UniversidadServicio implements Serializable,IUniversidadServicio{	


	private static final long serialVersionUID = 8221183343999201085L;
	IUniversidadDAO universidadDAO;
	
	@Override
	public CatUsuario login(CatUsuario usuario) {
		return getUniversidadDAO().login(usuario);
	}
	
	// Usuario
		@Override
		public List<CatUsuario> obtenerUsuarios(){
			return getUniversidadDAO().obtenerUsuarios();
		}

		@Transactional(readOnly = false)
		@Override
		public void guardarUsuario(CatUsuario usuario){
			getUniversidadDAO().guardarUsuario(usuario);
		}

		@Override
		public CatUsuario obtenerUsuarioXId(int id){
			return getUniversidadDAO().obtenerUsuarioXId(id);
		}

		@Transactional(readOnly = false)
		@Override
		public void actualizarUsuario(CatUsuario usuario){
			getUniversidadDAO().actualizarUsuario(usuario);
		}

		@Transactional(readOnly = false)
		@Override
		public void eliminarUsuario(CatUsuario usuario){
			getUniversidadDAO().eliminarUsuario(usuario);
		}
		
		// Perfil
		@Override
		public List<CatPerfil> obtenerPerfiles(){
			return getUniversidadDAO().obtenerPerfiles();
		}

		@Transactional(readOnly = false)
		@Override
		public void guardarUsuario(CatPerfil catPerfil){
			getUniversidadDAO().guardarUsuario(catPerfil);
		}

		@Override
		public CatPerfil obtenerPerfilXId(int id){
			return getUniversidadDAO().obtenerPerfilXId(id);
		}

		@Transactional(readOnly = false)
		@Override
		public void actualizarPerfil(CatPerfil catPerfil){
			getUniversidadDAO().actualizarPerfil(catPerfil);
		}

		@Transactional(readOnly = false)
		@Override
		public void eliminarPerfil(CatPerfil catPerfil){
			getUniversidadDAO().eliminarPerfil(catPerfil);
		}
		
		
		// Empleado
		@Override
		public List<Empleado> obtenerEmpleados(){
			return getUniversidadDAO().obtenerEmpleados();
		}

		@Transactional(readOnly = false)
		@Override
		public void guardarEmpleado(Empleado empleado) {
			getUniversidadDAO().guardarEmpleado(empleado);
		}

		@Override
		public Empleado obtenerEmpleadoXId(int id) {
			return getUniversidadDAO().obtenerEmpleadoXId(id);
		}

		@Transactional(readOnly = false)
		@Override
		public void actualizarEmpleado(Empleado empleado) {
			getUniversidadDAO().actualizarEmpleado(empleado);
		}

		@Transactional(readOnly = false)
		@Override
		public void eliminarEmpleado(Empleado empleado) {
			getUniversidadDAO().eliminarEmpleado(empleado);
		}
		
		public List<Empleado> obtenerEmpleadosXFiltro(int tipo,int id){
			return getUniversidadDAO().obtenerEmpleadosXFiltro(tipo,id);
		}
		
		public List<Empleado> obtenerEmpleadosXLetra(String letra){
			return getUniversidadDAO().obtenerEmpleadosXLetra(letra);
		}
		
		
		//empresa
		@Override
		public List<CatEmpresa> obtenerCatEmpresas(){
			return getUniversidadDAO().obtenerCatEmpresas();
		}
		
		@Override
		public CatEmpresa obtenerCatEmpresaXId(int id) {
			return getUniversidadDAO().obtenerCatEmpresaXId(id);
		}
		
		//depto
		@Override
		public List<CatDepto> obtenerCatDeptos(){
			return getUniversidadDAO().obtenerCatDeptos();
		}
		
		@Override
		public CatDepto obtenerCatDeptoXId(int id) {
			return getUniversidadDAO().obtenerCatDeptoXId(id);
		}
		
		@Override
		public List<CatDepto> obtenerDeptosXEmpresa(int id){
			return getUniversidadDAO().obtenerDeptosXEmpresa(id);
		}
		
		

		public IUniversidadDAO getUniversidadDAO() {
			return universidadDAO;
		}

		public void setUniversidadDAO(IUniversidadDAO universidadDAO) {
			this.universidadDAO = universidadDAO;
		}
		
		
		

}
