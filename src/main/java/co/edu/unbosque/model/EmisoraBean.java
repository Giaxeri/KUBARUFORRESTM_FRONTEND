package co.edu.unbosque.model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.dao.CancionDAO;
import co.edu.unbosque.dao.EmisoraDAO;
import co.edu.unbosque.dto.CancionDTO;
import co.edu.unbosque.dto.EmisoraDTO;

@ManagedBean

public class EmisoraBean {

	private String nombreBanda;
	private String tipoEmisora;
	private String generoMusical;
	private int resultado;

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

	public void crearEmisora() throws IOException {
		EmisoraDAO temp = new EmisoraDAO();
		String isApproved = "";
		EmisoraDTO emisora = new EmisoraDTO(this.getNombreBanda(), this.getTipoEmisora(), this.getGeneroMusical());

		this.resultado = temp.postJSON(emisora);

		CookiesBean.createCookieForEmisora(emisora);
	}

	public ArrayList<EmisoraDTO> mostrar() throws IOException, ParseException, org.json.simple.parser.ParseException {
		EmisoraDAO emi = new EmisoraDAO();
		return emi.getJSON();
	}

}
