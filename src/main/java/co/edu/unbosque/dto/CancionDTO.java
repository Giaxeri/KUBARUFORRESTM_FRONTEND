package co.edu.unbosque.dto;

//canciones

public class CancionDTO {
	private String nombreCancion;
	private String nombreArtista;
	private String generoMusical;
	private String rutaDelArchivo; // Ruta o referencia al archivo MP3
	private EmisoraDTO emisora;

	public CancionDTO() {
		// TODO Auto-generated constructor stub
	}

	public CancionDTO(String nombreCancion, String nombreArtista, String generoMusical, String rutaDelArchivo,
			EmisoraDTO emisora) {
		super();
		this.nombreCancion = nombreCancion;
		this.nombreArtista = nombreArtista;
		this.generoMusical = generoMusical;
		this.rutaDelArchivo = rutaDelArchivo;
		this.emisora = emisora;
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

	public EmisoraDTO getEmisora() {
		return emisora;
	}

	public void setEmisora(EmisoraDTO emisora) {
		this.emisora = emisora;
	}

}
