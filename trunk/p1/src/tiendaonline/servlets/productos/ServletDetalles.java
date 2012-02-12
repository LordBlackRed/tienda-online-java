package tiendaonline.servlets.productos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

public class ServletDetalles extends HttpServlet{

	private static final long serialVersionUID = -1252991606342186892L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idProducto = request.getParameter("id");
		List<Producto> productos = MisMetodos.obtenerProductos(request);
		Producto producto = MisMetodos.obtenerProducto(productos, idProducto);
		
		request.setAttribute(MisAtributos.producto.toString(), producto);
		request.getRequestDispatcher("detalles.jsp").forward(request, response);
	}
	
}
