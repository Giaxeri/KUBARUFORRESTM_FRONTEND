package co.edu.unbosque.dao;

//PRUEBA DAO
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.primefaces.shaded.json.JSONObject;
import co.edu.unbosque.dto.*;

public class CancionDAO {

	private static final String sitio = "http://localhost:8088/";

	public static int crearCancion(CancionDTO cancion) throws IOException {
		URL url = new URL(sitio + "canciones/guardar");

		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		try {
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			// Construcción del objeto JSON para la canción
			JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("nombre", cancion.getNombreCancion());
			jsonRequest.put("artista", cancion.getNombreArtista());
			jsonRequest.put("generoMusical", cancion.getGeneroMusical());
			jsonRequest.put("ubicacionMP3", cancion.getRutaDelArchivo());
			jsonRequest.put("emisora", cancion.getEmisora());

			OutputStream outputStream = http.getOutputStream();
			outputStream.write(jsonRequest.toString().getBytes(StandardCharsets.UTF_8));
			outputStream.flush();

			int respuesta = http.getResponseCode();
			return respuesta;
		} finally {
			http.disconnect();
		}
	}

	public static int actualizarCancion(String nombreCancion, CancionDTO nuevaCancion) throws IOException {
		URL url = new URL(sitio + "canciones/actualizar");

		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		try {
			http.setRequestMethod("PUT");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			// Construcción del objeto JSON para la actualización de la canción
			JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("nombre", nombreCancion); // Nombre de la canción a actualizar
			jsonRequest.put("artista", nuevaCancion.getNombreArtista());
			jsonRequest.put("generoMusical", nuevaCancion.getGeneroMusical());
			jsonRequest.put("ubicacionMP3", nuevaCancion.getRutaDelArchivo());
			jsonRequest.put("emisora", nuevaCancion.getEmisora());

			OutputStream outputStream = http.getOutputStream();
			outputStream.write(jsonRequest.toString().getBytes(StandardCharsets.UTF_8));
			outputStream.flush();

			int respuesta = http.getResponseCode();
			return respuesta;
		} finally {
			http.disconnect();
		}
	}

	public static int eliminarCancion(String nombreCancion) throws IOException {
		URL url = new URL(sitio + "canciones/eliminar");

		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		try {
			http.setRequestMethod("DELETE");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			// JSON para eliminar por nombre de la canción
			JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("nombre", nombreCancion); // Nombre de la canción a eliminar

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
