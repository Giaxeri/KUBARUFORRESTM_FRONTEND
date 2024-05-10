package co.edu.unbosque.model;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unbosque.dto.EmisoraDTO;

@ManagedBean
public class CookiesBean {

	public static void createCookieForEmisora(EmisoraDTO emisora) {

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();

		Cookie cookieNombreEmisora = new Cookie("nombreEmisora", emisora.getNombreBanda());
		cookieNombreEmisora.setMaxAge(3600); // Una hora de duración
		response.addCookie(cookieNombreEmisora);

		Cookie cookieTipoEmisora = new Cookie("tipoEmisora", emisora.getTipoEmisora());
		cookieTipoEmisora.setMaxAge(3600); // Una hora de duración
		response.addCookie(cookieTipoEmisora);

		Cookie cookieGeneroMusical = new Cookie("generoMusical", emisora.getGeneroMusical());
		cookieGeneroMusical.setMaxAge(3600); // Una hora de duración
		response.addCookie(cookieGeneroMusical);

		FacesContext.getCurrentInstance().responseComplete();
	}

	public static EmisoraDTO getEmisoraFromCookies() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Cookie[] cookies = request.getCookies();
		EmisoraDTO emisora = new EmisoraDTO();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("nombreEmisora".equals(cookie.getName())) {
					emisora.setNombreBanda(cookie.getValue());
				} else if ("tipoEmisora".equals(cookie.getName())) {
					emisora.setTipoEmisora(cookie.getValue());
				} else if ("generoMusical".equals(cookie.getName())) {
					emisora.setGeneroMusical(cookie.getValue());
				}
			}
		}
		return emisora;
	}

	public static void deleteCookies() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("nombreEmisora".equals(cookie.getName()) || "tipoEmisora".equals(cookie.getName())
						|| "generoMusical".equals(cookie.getName())) {
					cookie.setValue(null);
					cookie.setMaxAge(0); // Eliminar la cookie
					cookie.setPath("/"); // Asegúrate de establecer el path adecuado
					response.addCookie(cookie);
				}
			}
		}
		FacesContext.getCurrentInstance().responseComplete();
	}
}
