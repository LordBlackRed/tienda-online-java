package tiendaonline.servlets.footers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.metodos.MisMetodos;

public class ServletPrivacidad extends HttpServlet {

	private static final long serialVersionUID = -5228618310363085689L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);

		MisMetodos.asignarRequest(request, categorias, fabricantes);
		
		request.getRequestDispatcher("privacidad.jsp")
		.forward(request, response);
	}

}
