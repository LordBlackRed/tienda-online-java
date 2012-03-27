package tiendaonline.servlets.contacto;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletContacto extends HttpServlet{

	private static final long serialVersionUID = -916555103459150176L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		List<Producto> productosCabecera = MisMetodos.obtenerProductos(request);
		
		MisMetodos.asignarRequest(request, categorias, fabricantes);
		request.setAttribute(MisAtributos.productosCabecera.toString(), productosCabecera);
		
		request.getRequestDispatcher("contacto.jsp").forward(request, response);
	
	}
}
