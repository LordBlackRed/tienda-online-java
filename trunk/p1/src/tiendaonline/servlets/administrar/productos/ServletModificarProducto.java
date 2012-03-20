package tiendaonline.servlets.administrar.productos;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.clases.Producto;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletModificarProducto extends HttpServlet {

	private static final long serialVersionUID = 8347127449428910820L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String nombreProducto = request.getParameter("nombreProducto");
		String urlImagen = request.getParameter("urlImagen");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		String descripcion = request.getParameter("descripcion");
		double precio = Double.parseDouble(request.getParameter("precio"));
		String idProducto =request.getParameter("idProducto");
		String categoriaString = request.getParameter("categoriaProducto");
		
		List<Producto> productos = MisMetodos.obtenerProductos(request);
		Producto producto = MisMetodos.obtenerProducto(productos, idProducto);
		producto.setCantidad(cantidad);
		producto.setDescripcion(descripcion);
		producto.setNombre(nombreProducto);
		producto.setPrecio(precio);
		producto.setUrlImagen(urlImagen);
		producto.setCategoriaString(categoriaString);
		
		//Modificamos el producto
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(producto);
		transaction.commit();
		
		entityManager.close();
		
		response.sendRedirect("Detalles?id="+idProducto);
	}
	
}
