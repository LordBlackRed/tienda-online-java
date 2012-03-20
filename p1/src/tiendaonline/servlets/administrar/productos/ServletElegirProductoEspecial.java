package tiendaonline.servlets.administrar.productos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.listeners.ContextoListener;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletElegirProductoEspecial extends HttpServlet {

	private static final long serialVersionUID = 8347127449428910820L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idProductoEspecial = request.getParameter(MisAtributos.productoEspecial.toString());
		List<Producto> productos = MisMetodos.obtenerProductos(request);
		Producto productoEspecial = MisMetodos.obtenerProducto(productos, idProductoEspecial);
		
		ContextoListener.productoEspecial = productoEspecial;
		
		response.sendRedirect("Index");
	}
	
}
