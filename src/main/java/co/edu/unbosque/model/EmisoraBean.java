package co.edu.unbosque.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import co.edu.unbosque.dao.EmisoraDAO;
import co.edu.unbosque.dto.EmisoraDTO;

@ManagedBean

public class EmisoraBean {

	private String nombreBanda;
	private String tipoEmisora;
	private String generoMusical;
	private int resultado;

	public EmisoraBean() {
		// TODO Auto-generated constructor stub
	}

	public EmisoraBean(String nombreBanda, String tipoEmisora, String generoMusical, int resultado) {
		super();
		this.nombreBanda = nombreBanda;
		this.tipoEmisora = tipoEmisora;
		this.generoMusical = generoMusical;
		this.resultado = resultado;
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

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public String crearEmisora() throws IOException {
		new EmisoraDAO();
		EmisoraDTO emisora = new EmisoraDTO(this.getNombreBanda(), this.getTipoEmisora(), this.getGeneroMusical());

		EmisoraDAO.postJSON(emisora);

		CookiesBean.createCookieForEmisora(emisora);

		return "gestioncanciones.xhtml";

	}

	public ArrayList<EmisoraDTO> mostrar() throws IOException, ParseException, org.json.simple.parser.ParseException {
		EmisoraDAO emi = new EmisoraDAO();
		return emi.getJSON();
	}

}
