package co.edu.unbosque.dto;

public class EmisoraDTO {

	private String nombreBanda;
	private String tipoEmisora;
	private String generoMusical;

	public EmisoraDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public EmisoraDTO(String nombreBanda, String tipoEmisora, String generoMusical) {
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

}
