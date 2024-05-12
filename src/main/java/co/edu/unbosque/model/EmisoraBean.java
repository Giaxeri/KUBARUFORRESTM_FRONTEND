package co.edu.unbosque.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import co.edu.unbosque.dao.EmisoraDAO;
import co.edu.unbosque.dto.EmisoraDTO;

@ManagedBean

public class EmisoraBean {

	private String nombreBanda;
	private String tipoEmisora;
	private String generoMusical;

	public EmisoraBean() {
		// TODO Auto-generated constructor stub
	}

	public EmisoraBean(String nombreBanda, String tipoEmisora, String generoMusical) {
		super();
		this.nombreBanda = nombreBanda;
		this.tipoEmisora = tipoEmisora;
		this.generoMusical = generoMusical;
	}

	public String getNombreBanda() {
		return nombreBanda;
	}

	public void setNombreBanda(String nombreBanda) {
		this.nombreBanda = nombreBanda;
	}

	public String getTipoEmisora() {
		return tipoEmisora;
	}

	public void setTipoEmisora(String tipoEmisora) {
		this.tipoEmisora = tipoEmisora;
	}

	public String getGeneroMusical() {
		return generoMusical;
	}

	public void setGeneroMusical(String generoMusical) {
		this.generoMusical = generoMusical;
	}

	public String crearEmisora() {
		try {
			EmisoraDTO emisora = new EmisoraDTO(this.nombreBanda, this.tipoEmisora, this.generoMusical);

			EmisoraDAO.postJSON(emisora);

			CookiesBean.createCookieForEmisora(emisora);

			return "gestioncanciones?faces-redirect=true";
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar la emisora.", null));
			return null;
		}
	}

	public ArrayList<EmisoraDTO> obtenerEmisoras() throws ParseException, org.json.simple.parser.ParseException {
		try {
			EmisoraDAO emisoraDAO = new EmisoraDAO();
			return emisoraDAO.getJSON(); // Obtener lista de emisoras desde el DAO
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al obtener las emisoras.", null));
			return new ArrayList<>(); // Retornar lista vacía en caso de error
		}
	}

}
