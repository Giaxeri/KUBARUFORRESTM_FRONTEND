package co.edu.unbosque.dao;

import java.io.IOException; 

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import co.edu.unbosque.dto.EmisoraDTO;

public class EmisoraDAO {

	private static URL url;
	private static String sitio = "http://localhost:8088/";

	public static ArrayList<EmisoraDTO> getJSON()
			throws IOException, ParseException, org.json.simple.parser.ParseException {
		url = new URL(sitio + "EmisoraDTO/listar");
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}
		ArrayList<EmisoraDTO> lista = new ArrayList<EmisoraDTO>();
		lista = parseingEmisoraDTO(json);
		http.disconnect();
		return lista;
	}

	public static ArrayList<EmisoraDTO> parseingEmisoraDTO(String json)
			throws ParseException, org.json.simple.parser.ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<EmisoraDTO> lista = new ArrayList<EmisoraDTO>();
		JSONArray EmisoraDTO = (JSONArray) jsonParser.parse(json);
		Iterator i = EmisoraDTO.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			EmisoraDTO emisora = new EmisoraDTO();
			emisora.setNombreBanda(innerObj.getString("NombreEmisora: ".toString()));
			emisora.setTipoEmisora(innerObj.getString("modoTransmicion: ".toString()));
			emisora.setGeneroMusical(innerObj.getString("tipoMusica: ".toString()));
			lista.add(emisora);
		}
		return lista;
	}

	public static int postJSON(EmisoraDTO emisora) throws IOException {
		url = new URL(sitio + "emisora/guardar");

		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();
		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{" + "\"nombreEmisora\":\"" + emisora.getNombreBanda() + "\",\"modoTransmicion\": \""
				+ emisora.getTipoEmisora() + "\",\"tipoMusica\": \"" + emisora.getGeneroMusical() + "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int updateEmisora(String nombreEmisora, EmisoraDTO nuevaEmisora) throws IOException {
		url = new URL(sitio + "emisora/actualizar");

		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		try {
			http.setRequestMethod("PUT");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			// Construccion de los neuvos datos
			JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("nombreEmisora", nombreEmisora);
			jsonRequest.put("modoTransmicion", nuevaEmisora.getTipoEmisora());
			jsonRequest.put("tipoMusica", nuevaEmisora.getGeneroMusical());

			OutputStream outputStream = http.getOutputStream();
			outputStream.write(jsonRequest.toString().getBytes(StandardCharsets.UTF_8));
			outputStream.flush();

			int respuesta = http.getResponseCode();
			http.disconnect();
			return respuesta;
		} catch (ProtocolException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static int deleteEmisora(String nombreEmisora) throws IOException {
		url = new URL(sitio + "emisora/eliminar");

		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		try {
			http.setRequestMethod("DELETE");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			// JSON para eliminar por nombre
			JSONObject jsonRequest = new JSONObject();
			jsonRequest.put("nombreEmisora", nombreEmisora); // Nombre de la emisora a eliminar

			OutputStream outputStream = http.getOutputStream();
			outputStream.write(jsonRequest.toString().getBytes(StandardCharsets.UTF_8));
			outputStream.flush();

			int respuesta = http.getResponseCode();
			http.disconnect();
			return respuesta;
		} catch (ProtocolException e) {
			e.printStackTrace();
			return -1;
		}
	}

}