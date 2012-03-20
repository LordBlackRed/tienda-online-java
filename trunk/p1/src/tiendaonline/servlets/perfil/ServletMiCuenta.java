package tiendaonline.servlets.perfil;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletMiCuenta extends HttpServlet{

	private static final long serialVersionUID = -8433570479677679165L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		
		MisMetodos.asignarRequest(request, categorias, fabricantes);
		
		request.getRequestDispatcher("perfil.jsp").forward(request, response);

	}
		
}
