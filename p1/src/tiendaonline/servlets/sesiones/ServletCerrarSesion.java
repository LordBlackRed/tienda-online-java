package tiendaonline.servlets.sesiones;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tiendaonline.ServletIndex;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletCerrarSesion extends HttpServlet {

	private static final long serialVersionUID = -9035537034488771085L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		sesion.invalidate();

		// Lo ponemos a 0 para comprobar a la hora de la compra si existe un
		// envio seleccionado y para que no haya ningœn problema en cualquier
		// otra compra
		ServletIndex.idEnvioCompra = 0L;

		response.sendRedirect("Index");
	}

}
