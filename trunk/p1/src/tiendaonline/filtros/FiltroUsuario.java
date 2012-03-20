package tiendaonline.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class FiltroUsuario implements Filter {

	private FilterConfig config;
	private String urlLogin;

	@Override
	public void destroy() {
		setConfig(null);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// Extraer Sesi—n
		HttpSession session = ((HttpServletRequest) request).getSession();

		if (session.getAttribute(MisAtributos.usuario.toString()) == null) {
			// NO hay una session con ususario
			((HttpServletResponse) response).sendRedirect(urlLogin);

		} else {
			Usuario usuario = (Usuario) session
					.getAttribute(MisAtributos.usuario.toString());
			if (usuario.getNick().equals("admin")) {
				// el usuario es administrador
				((HttpServletResponse) response).sendRedirect(urlLogin);
			} else {
				chain.doFilter(request, response);
			}
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.setConfig(config);

		// Tambien se pueden cargar los parametros
		// Configura url desconexi—n
		this.urlLogin = config.getInitParameter("urlLogin");
		if (urlLogin == null || urlLogin.trim().length() == 0) {
			// Error al cargar la url de login
			throw new ServletException("No se ha configurado URL de login");
		}
	}

	public FilterConfig getConfig() {
		return config;
	}

	public void setConfig(FilterConfig config) {
		this.config = config;
	}
}