package com.universidad.modelo.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.universidad.modelo.negocio.CatDepto;
import com.universidad.modelo.negocio.CatEmpresa;
import com.universidad.modelo.negocio.CatPerfil;
import com.universidad.modelo.negocio.CatUsuario;
import com.universidad.modelo.negocio.Empleado;

public class UniversidadDAO implements Serializable, IUniversidadDAO {

	private static final long serialVersionUID = 867528696699888532L;
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UniversidadDAO.class);
	private SessionFactory sessionFactory;

	@Override
	public CatUsuario login(CatUsuario usuario) {
		String stringQuery;
		Query query;
		stringQuery = "FROM CatUsuario u WHERE u.descripcion = :usuario AND u.contrasenia = :contrasenia";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.setString("usuario", usuario.getDescripcion());
		query.setString("contrasenia", usuario.getContrasenia());
		return (CatUsuario) query.uniqueResult();
	}

	// Perfil
	@SuppressWarnings("unchecked")
	@Override
	public List<CatPerfil> obtenerPerfiles() {
		String stringQuery;
		Query query;
		stringQuery = "FROM CatPerfil ORDER BY descripcion";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		return query.list();
	}

	@Override
	public void guardarUsuario(CatPerfil catPerfil) {
		getSessionFactory().getCurrentSession().save(catPerfil);
	}

	@Override
	public CatPerfil obtenerPerfilXId(int id) {
		String stringQuery;
		Query query;
		stringQuery = "FROM CatPerfil us WHERE us.id = :id";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.setInteger("id", id);
		return (CatPerfil) query.uniqueResult();
	}

	@Override
	public void actualizarPerfil(CatPerfil catPerfil) {
		getSessionFactory().getCurrentSession().update(catPerfil);
	}

	@Override
	public void eliminarPerfil(CatPerfil catPerfil) {
		getSessionFactory().getCurrentSession().delete(catPerfil);
	}

	// Usuario
	@SuppressWarnings("unchecked")
	@Override
	public List<CatUsuario> obtenerUsuarios() {
		String stringQuery;
		Query query;
		stringQuery = "FROM CatUsuario ORDER BY descripcion";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		return query.list();
	}

	@Override
	public void guardarUsuario(CatUsuario usuario) {
		getSessionFactory().getCurrentSession().save(usuario);
	}

	@Override
	public CatUsuario obtenerUsuarioXId(int id) {
		String stringQuery;
		Query query;
		stringQuery = "FROM CatUsuario us WHERE us.id = :id";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.setInteger("id", id);
		return (CatUsuario) query.uniqueResult();
	}

	@Override
	public void actualizarUsuario(CatUsuario usuario) {
		getSessionFactory().getCurrentSession().update(usuario);
	}

	@Override
	public void eliminarUsuario(CatUsuario usuario) {
		getSessionFactory().getCurrentSession().delete(usuario);
	}
	
	
	// Empleado
	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> obtenerEmpleados() {
		String stringQuery;
		Query query;
		stringQuery = "FROM Empleado ORDER BY fecIngreso";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		return query.list();
	}

	@Override
	public void guardarEmpleado(Empleado empleado) {
		getSessionFactory().getCurrentSession().save(empleado);
	}

	@Override
	public Empleado obtenerEmpleadoXId(int id) {
		String stringQuery;
		Query query;
		stringQuery = "FROM Empleado us WHERE us.id = :id";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.setInteger("id", id);
		return (Empleado) query.uniqueResult();
	}

	@Override
	public void actualizarEmpleado(Empleado empleado) {
		getSessionFactory().getCurrentSession().update(empleado);
	}

	@Override
	public void eliminarEmpleado(Empleado empleado) {
		getSessionFactory().getCurrentSession().delete(empleado);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> obtenerEmpleadosXFiltro(int tipo,int id) {
		String stringQuery;
		String consulta="";
		
		if(tipo==2) {
			consulta="FROM Empleado WHERE catDepto.idDepto= :id";
		}else if(tipo==1) {
			consulta="FROM Empleado WHERE catEmpresa.idEmpresa= :id";
		}
		
		Query query;
		stringQuery = consulta;
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.setInteger("id", id);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> obtenerEmpleadosXLetra(String letra) {
		String stringQuery;
		
		Query query;
		stringQuery = "FROM Empleado WHERE nombre  LIKE :letra";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.setString("letra",  "%"+letra+"%");
		return query.list();
	}
	
	
	//empresa
	@SuppressWarnings("unchecked")
	@Override
	public List<CatEmpresa> obtenerCatEmpresas(){
		String stringQuery;
		Query query;
		stringQuery = "FROM CatEmpresa ORDER BY descripcion";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		return query.list();
	}
	
	public CatEmpresa obtenerCatEmpresaXId(int id) {
		String stringQuery;
		Query query;
		stringQuery = "FROM CatEmpresa us WHERE us.id = :id";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.setInteger("id", id);
		return (CatEmpresa) query.uniqueResult();
	}
	
	//depto
	@SuppressWarnings("unchecked")
	@Override
	public List<CatDepto> obtenerCatDeptos(){
		String stringQuery;
		Query query;
		stringQuery = "FROM CatDepto ORDER BY nombre";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		return query.list();
	}
	
	public CatDepto obtenerCatDeptoXId(int id) {
		String stringQuery;
		Query query;
		stringQuery = "FROM CatDepto us WHERE us.id = :id";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.setInteger("id", id);
		return (CatDepto) query.uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CatDepto> obtenerDeptosXEmpresa(int id){
		String stringQuery;
		Query query;
		stringQuery = "FROM CatDepto dep WHERE dep.catEmpresa.idEmpresa= :id ORDER BY dep.nombre";
		query = getSessionFactory().getCurrentSession().createQuery(stringQuery);
		query.setInteger("id", id);
		return query.list();
	}
	
	


	// fin
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
