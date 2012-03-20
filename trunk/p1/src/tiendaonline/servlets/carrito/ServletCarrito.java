package tiendaonline.servlets.carrito;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Envio;
import tiendaonline.clases.Fabricante;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletCarrito extends HttpServlet{

	private static final long serialVersionUID = -432112664639003517L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Envio> empresasEnvio = MisMetodos.obtenerEmpresasEnvio(request);
		
		MisMetodos.asignarRequest(request, categorias, fabricantes);
		request.setAttribute(MisAtributos.empresasEnvio.toString(), empresasEnvio);
		
		request.getRequestDispatcher("carrito.jsp").forward(request, response);

	}
	
}
