package co.edu.unbosque.model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.faces.bean.ManagedBean;

import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.dao.CancionDAO;
import co.edu.unbosque.dto.CancionDTO;
import co.edu.unbosque.dto.EmisoraDTO;

@ManagedBean
public class cancionBean {
	private String nombreCancion;
	private String nombreArtista;
	private String generoMusical;
	private String rutaDelArchivo;
	private EmisoraDTO emisora;
	private int resultado;

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

	public EmisoraDTO getEmisora() {
		return emisora;
	}

	public void setEmisora(EmisoraDTO emisora) {
		this.emisora = emisora;
	}

	public void eliminarCancion(String nombreCancion) throws IOException {

		CancionDAO cancion = new CancionDAO();
		this.resultado = cancion.eliminarCancion(nombreCancion);
	}

	public void registrar() throws IOException {
		CancionDAO temp = new CancionDAO();
		String isApproved = "";

		CancionDTO cancion = new CancionDTO(this.getNombreCancion(), this.getNombreArtista(), this.getGeneroMusical(),
				this.getRutaDelArchivo(), CookiesBean.getEmisoraFromCookies());
		this.resultado = temp.crearCancion(cancion);
	}

	public void actualizar() throws IOException { // ACTUALIZAR EN CONSTRUCCION
		CancionDAO temp = new CancionDAO();
		this.resultado = temp.actualizarCancion(nombreCancion, null);
	}

}
