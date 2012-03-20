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
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
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

		List<Producto> productos = MisMetodos.obtenerProductos(request);
		Producto productoCarro = null;
		// Buscamos 1¼ el producto que se ha a–adido al carrito
		for (Producto producto : productos) {
			if (producto.getId().getId() == Long.parseLong(idProducto)) {
				productoCarro = producto;
				break;
			}
		}

		// Actualizamos el carrito
		@SuppressWarnings("unchecked")
		List<Producto> carrito = (List<Producto>) request.getSession()
				.getAttribute(MisAtributos.carrito.toString());

		boolean productoEnCarrito = false;
		for (Producto productoCarrito : carrito) {
			if (productoCarrito.equals(productoCarro)) {
				// Actualizamos la cantidad que existe dentro del carrito
				productoEnCarrito = true;
				productoCarro.setCantidad(productoCarrito.getCantidad()
						+ cantidad);
				transaction.begin();
				carrito.remove(productoCarrito);
				transaction.commit();

				break;
			}
		}

		entityManager.close();

		if (!productoEnCarrito) {
			productoCarro.setCantidad(cantidad);
		}
		// obtenemos la p‡gina en la que estaba trabajando el usuario para
		// regresar a la misma
		String pagina = request.getParameter("pag");

		carrito.add(productoCarro);
		response.sendRedirect("Index?pag=" + pagina);

	}

}
