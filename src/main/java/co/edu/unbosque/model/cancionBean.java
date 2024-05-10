package co.edu.unbosque.model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.faces.bean.ManagedBean;

import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.dto.EmisoraDTO;

@ManagedBean
public class cancionBean {
	private String nombreCancion;
	private String nombreArtista;
	private String generoMusical;
	private String rutaDelArchivo;
	private EmisoraDTO emisora;

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
	public static int eliminarCancion(String nombreCancion) throws IOException {
		URL url = new URL(nombreCancion + "canciones/eliminar/{nombre}");

		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		try {
			http.setRequestMethod("DELETE");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			// JSON para eliminar por nombre de la canción
			JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("nombre", nombreCancion);

			OutputStream outputStream = http.getOutputStream();
			outputStream.write(jsonRequest.toString().getBytes(StandardCharsets.UTF_8));
			outputStream.flush();

			int respuesta = http.getResponseCode();
			return respuesta;
		} finally {
			http.disconnect();
		}
	}

}
