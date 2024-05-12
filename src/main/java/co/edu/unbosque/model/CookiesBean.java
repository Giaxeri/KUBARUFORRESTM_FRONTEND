package co.edu.unbosque.model;

import javax.faces.bean.ManagedBean;
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

		FacesContext.getCurrentInstance().responseComplete();
	}

	public static String getEmisoraFromCookies() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Cookie[] cookies = request.getCookies();
		String nombreEmisora = null;

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("nombreEmisora".equals(cookie.getName())) {
					nombreEmisora = cookie.getValue();
					break; // No necesitamos seguir iterando si encontramos el nombre de la emisora
				}
			}
		}
		return nombreEmisora;
	}

	public static void deleteCookies() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("nombreEmisora".equals(cookie.getName())) {
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
