package tiendaonline.servlets.sesiones;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

public class ServletRegistrarse extends HttpServlet{

	private static final long serialVersionUID = 1541990379202953661L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		
		request.setAttribute(MisAtributos.categorias.toString(), categorias);
		
		request.getRequestDispatcher("registrarse.jsp").forward(request, response);
	}
}
