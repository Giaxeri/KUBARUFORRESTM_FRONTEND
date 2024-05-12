package co.edu.unbosque.model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.dao.CancionDAO;
import co.edu.unbosque.dto.CancionDTO;
import co.edu.unbosque.dto.EmisoraDTO;

@ManagedBean
public class CancionBean {
	private String nombreCancion;
	private String nombreArtista;
	private String generoMusical;
	private String rutaDelArchivo;
	private String nombreEmisora;

	public CancionBean() {
		// TODO Auto-generated constructor stub
	}

	public CancionBean(String nombreCancion, String nombreArtista, String generoMusical, String rutaDelArchivo,
			String nombreEmisora) {
		super();
		this.nombreCancion = nombreCancion;
		this.nombreArtista = nombreArtista;
		this.generoMusical = generoMusical;
		this.rutaDelArchivo = rutaDelArchivo;
		this.nombreEmisora = nombreEmisora;
	}

	public String getNombreCancion() {
		return nombreCancion;
	}

	public void setNombreCancion(String nombreCancion) {
		this.nombreCancion = nombreCancion;
	}

	public String getNombreArtista() {
		return nombreArtista;
	}

	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}

	public String getGeneroMusical() {
		return generoMusical;
	}

	public void setGeneroMusical(String generoMusical) {
		this.generoMusical = generoMusical;
	}

	public String getRutaDelArchivo() {
		return rutaDelArchivo;
	}

	public void setRutaDelArchivo(String rutaDelArchivo) {
		this.rutaDelArchivo = rutaDelArchivo;
	}

	public String getNombreEmisora() {
		return nombreEmisora;
	}

	public void setNombreEmisora(String nombreEmisora) {
		this.nombreEmisora = nombreEmisora;
	}

	public void eliminarCancion(String nombreCancion) throws IOException {

		CancionDAO cancion = new CancionDAO();
		cancion.eliminarCancion(nombreCancion);
	}

	public String registrar() throws IOException {
		try {
			CancionDAO temp = new CancionDAO();

			CancionDTO cancion = new CancionDTO(this.getNombreCancion(), this.getNombreArtista(),
					this.getGeneroMusical(), this.getRutaDelArchivo(), CookiesBean.getEmisoraFromCookies());
			temp.crearCancion(cancion);

			// Mensaje de éxito
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancion registrada correctamente.", null));

			// Redirección a gestioncanciones.xhtml
			return "gestioncanciones?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar la cancion.", null));
			return null;
		}
	}

	public void actualizar() throws IOException {
		CancionDAO temp = new CancionDAO();
		CancionDTO cancion = new CancionDTO(this.getNombreCancion(), this.getNombreArtista(), this.getGeneroMusical(),
				this.getRutaDelArchivo(), CookiesBean.getEmisoraFromCookies());
		temp.actualizarCancion(nombreCancion, cancion);
	}

}