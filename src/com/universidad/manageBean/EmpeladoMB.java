package com.universidad.manageBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.universidad.modelo.negocio.CatDepto;
import com.universidad.modelo.negocio.CatEmpresa;
import com.universidad.modelo.negocio.CatPerfil;
import com.universidad.modelo.negocio.Empleado;
import com.universidad.seguridad.UserSession;
import com.universidad.servicio.IUniversidadServicio;
import com.universidad.util.MsgUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@ManagedBean(name = "empleadoMB")
@ViewScoped
public class EmpeladoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 959107711113516850L;
	@ManagedProperty(value = "#{UniversidadServicio}")
	IUniversidadServicio iUniversidadServicio;
	@ManagedProperty(value = "#{userSession}")
	UserSession userSession;

	private static final Logger logger = Logger.getLogger(EmpeladoMB.class);
	
	private Empleado empleado;
	private List<Empleado> lista;
	private List<Empleado> listaFilter;
	private Empleado slcEmpleado;
	
	private Integer accion;
	private List<CatEmpresa> listaEmpresas;
	private List<CatDepto> listaDeptos;
	private Integer idEmpre;
	private Integer idDepto;
	
	private Integer idTipo;
	private String palabra;

	public EmpeladoMB() {

	}

	@PostConstruct
	public void inicio() {
		accion=0;
		//listaFilter=new ArrayList<Empleado>();
		
		slcEmpleado= new Empleado();		
		listaDeptos= new ArrayList<>();
		empleado= new Empleado();
		listaEmpresas= getiUniversidadServicio().obtenerCatEmpresas();
		lista=getiUniversidadServicio().obtenerEmpleados();
	}
	
	public void cargaDeptos() {
		listaDeptos=getiUniversidadServicio().obtenerDeptosXEmpresa(idEmpre);
	}

	public void editar(Empleado empl) {
		empleado = empl;
		accion=2;
	}
	
	public void cargaEmpleados() {
		Integer id=0;
		lista=new ArrayList<>();
		
		if(idTipo==3) {
			lista=getiUniversidadServicio().obtenerEmpleadosXLetra(palabra);
		}else {
			if(idTipo==1)
				id=idEmpre;else if(idTipo==2)
					id=idDepto;
			System.out.println("qqqq"+id+"   tipo"+idTipo);
			lista=getiUniversidadServicio().obtenerEmpleadosXFiltro(idTipo, id);
		}
		
	}
	
	public void guardar() {
		CatEmpresa ce = new CatEmpresa();
		ce.setIdEmpresa(idEmpre);
		
		
		try {

			empleado.setCatEmpresa(ce);
			if (empleado.getId() == 0) {
				
				getiUniversidadServicio().guardarEmpleado(empleado);

				lista.add(empleado);

				MsgUtil.msgInfo("Exito!", "Registro Guardado.");
			} else {
				
				getiUniversidadServicio().actualizarEmpleado(empleado);
				MsgUtil.msgInfo("Exito!", "Registro Actualizado.");
				
			}

			empleado = new Empleado();

		} catch (Exception e) {
			e.printStackTrace();
			MsgUtil.msgError("Error!", "Registro no procesado.");
		}

	}

	public void editar() {
		empleado = slcEmpleado;
	}

	public void eliminarEmpleado(Empleado emp) {
		try {
			getiUniversidadServicio().eliminarEmpleado(emp);;
			lista.remove(emp);
			MsgUtil.msgInfo("Exito!", "Registro Eliminado.");
		} catch (Exception e) {
			e.printStackTrace();
			MsgUtil.msgError("Error!", "Registro no eliminado.");
		}
	}

	public void cancelar() {
	/*	empleado = new Empleado();
		slcEmpleado = null;*/
	}
	
	public List<CatPerfil> getlistaPerfil() {
		return getiUniversidadServicio().obtenerPerfiles();
	}

	
	public void limpiar() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("registroEmpleado.xhtml");
	}
	
	public void agregar() {
		accion=1;	
		System.out.println("qu sera333666667777");
	}
	
	public void imprimirContactos() {

		ExternalContext context;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		context = facesContext.getExternalContext();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		String absoluteDiskPath = context.getRealPath("/resources/");
		String reportPath = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/resources/reportes/empleadosReporte.jasper");

		JasperPrint jasperPrint;
		HashMap<String, Object> parametros = new HashMap<String, Object>();

		// cambio para amndarle lo que trae la tabla
		JRBeanCollectionDataSource beanCollectionDataSource;
		if (listaFilter != null) {
			beanCollectionDataSource = new JRBeanCollectionDataSource(listaFilter);
		} else {
			beanCollectionDataSource = new JRBeanCollectionDataSource(lista);
		}

		parametros.put("patch", absoluteDiskPath);

		try {
			jasperPrint = JasperFillManager.fillReport(reportPath, parametros, beanCollectionDataSource);
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=\"" + "Empleados"+".pdf" + "\"");
			response.setContentLength(pdf.length);
			response.getOutputStream().write(pdf, 0, pdf.length);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (JRException ex) {
			StringWriter stack = new StringWriter();
			ex.printStackTrace(new PrintWriter(stack));
			logger.error(stack.toString());
		} catch (IOException ioex) {
			StringWriter stack = new StringWriter();
			ioex.printStackTrace(new PrintWriter(stack));
			logger.error(stack.toString());
		}
		facesContext.responseComplete();
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public IUniversidadServicio getiUniversidadServicio() {
		return iUniversidadServicio;
	}

	public void setiUniversidadServicio(IUniversidadServicio iUniversidadServicio) {
		this.iUniversidadServicio = iUniversidadServicio;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Empleado> getLista() {
		return lista;
	}

	public void setLista(List<Empleado> lista) {
		this.lista = lista;
	}

	public List<Empleado> getListaFilter() {
		return listaFilter;
	}

	public void setListaFilter(List<Empleado> listaFilter) {
		this.listaFilter = listaFilter;
	}

	public Empleado getSlcEmpleado() {
		return slcEmpleado;
	}

	public void setSlcEmpleado(Empleado slcEmpleado) {
		this.slcEmpleado = slcEmpleado;
	}

	public Integer getAccion() {
		return accion;
	}

	public void setAccion(Integer accion) {
		this.accion = accion;
	}

	public List<CatEmpresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<CatEmpresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public List<CatDepto> getListaDeptos() {
		return listaDeptos;
	}

	public void setListaDeptos(List<CatDepto> listaDeptos) {
		this.listaDeptos = listaDeptos;
	}

	public Integer getIdEmpre() {
		return idEmpre;
	}

	public void setIdEmpre(Integer idEmpre) {
		this.idEmpre = idEmpre;
	}

	public Integer getIdDepto() {
		return idDepto;
	}

	public void setIdDepto(Integer idDepto) {
		this.idDepto = idDepto;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	

	

}
