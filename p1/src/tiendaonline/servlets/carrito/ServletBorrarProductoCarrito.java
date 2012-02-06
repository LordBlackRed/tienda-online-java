package tiendaonline.servlets.carrito;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Producto;
import tiendaonline.enumerados.MisAtributos;

public class ServletBorrarProductoCarrito extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String idProducto = request.getParameter("id");
		
		List<Producto> carrito = (List<Producto>) request.getSession().getAttribute(MisAtributos.carrito.toString());
		
		for (Producto producto: carrito){
			if (producto.getId().getId() == Long.parseLong(idProducto)){
				carrito.remove(producto);
				break;
			}
		}
		
		response.sendRedirect("Carrito");
	}
	
}
