package co.edu.unbosque.model;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import co.edu.unbosque.dto.EmisoraDTO;

@ManagedBean
public class CookiesBean {

	public static void createSessionForEmisora(EmisoraDTO emisora) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(true);

		session.setAttribute("nombreEmisora", emisora.getNombreBanda());

		facesContext.responseComplete();
	}

	public static String getEmisoraFromSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);

		if (session != null) {
			return (String) session.getAttribute("nombreEmisora");
		} else {
			return null;
		}
	}

	public static void deleteSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		facesContext.responseComplete();
	}
}
