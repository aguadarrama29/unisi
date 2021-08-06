package com.universidad.seguridad;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent pe) {		
		FacesContext facesContext = pe.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();		

		boolean isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);		

		if (session == null) {
			NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
			nh.handleNavigation(facesContext, null, "login");
		} else {
			Object currentUser = session.getAttribute("usuario");

			if (!isLoginPage && (currentUser == null || currentUser == "")) {
				NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
				nh.handleNavigation(facesContext, null, "login");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent pe) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}