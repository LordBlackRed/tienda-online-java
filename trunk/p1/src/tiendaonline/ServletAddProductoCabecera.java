package tiendaonline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Producto;
import tiendaonline.listeners.ContextoListener;
import tiendaonline.metodos.MisMetodos;

public class ServletAddProductoCabecera extends HttpServlet{

	private static final long serialVersionUID = -1531862338770172391L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idProducto1 = request.getParameter("producto1");
		String idProducto2 = request.getParameter("producto2");
		String idProducto3 = request.getParameter("producto3");
		String idProducto4 = request.getParameter("producto4");
		String idProducto5 = request.getParameter("producto5");
		System.out.println("idProducto5 " + idProducto5);
		List<Producto> productosCabecera = new ArrayList<Producto>();
		List<Producto> productos = MisMetodos.obtenerProductos(request);
		
		Producto producto1 = MisMetodos.obtenerProducto(productos, idProducto1);
		Producto producto2 = MisMetodos.obtenerProducto(productos, idProducto2);
		Producto producto3 = MisMetodos.obtenerProducto(productos, idProducto3);
		Producto producto4 = MisMetodos.obtenerProducto(productos, idProducto4);
		Producto producto5 = MisMetodos.obtenerProducto(productos, idProducto5);
		
		productosCabecera.add(producto1);
		productosCabecera.add(producto2);
		productosCabecera.add(producto3);
		productosCabecera.add(producto4);
		productosCabecera.add(producto5);
		System.out.println(producto5.getDescripcion());
		
		ContextoListener.productosCabecera = productosCabecera;
		
		response.sendRedirect("Index");
	}

}
