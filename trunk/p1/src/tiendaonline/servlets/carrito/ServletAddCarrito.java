package tiendaonline.servlets.carrito;

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
import tiendaonline.enumerados.MisAtributos;

public class ServletAddCarrito extends HttpServlet {

	private static final long serialVersionUID = -8799360003713298141L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idProducto = request.getParameter("id");
		int cantidad = 1;
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		Producto producto = entityManager.find(Producto.class, Long.parseLong(idProducto));	
		transaction.commit();
		
		//Actualizamos el carrito
		List<Producto> carrito = (List<Producto>) request.getSession().getAttribute(MisAtributos.carrito.toString());
		
		boolean productoEnCarrito = false;
		for(Producto productoCarrito: carrito){
			System.out.println(productoCarrito);
			if (productoCarrito.equals(producto)){
				//Actualizamos la cantidad que existe dentro del carrito
				productoEnCarrito = true;
				producto.setCantidad(productoCarrito.getCantidad() + cantidad);
				carrito.remove(productoCarrito);
				break;
			}
		}
		
		if (!productoEnCarrito){
			producto.setCantidad(cantidad);
		}
		
		carrito.add(producto);
		System.out.println("-----");
		response.sendRedirect("Index");
		
	}
	
}
