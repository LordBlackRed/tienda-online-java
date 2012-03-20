package tiendaonline.servlets.administrar.productos;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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
public class ServletEliminarProducto extends HttpServlet {

	private static final long serialVersionUID = 4921162632448875547L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String idProducto = request.getParameter("id");

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		String jpql = "select producto from Producto producto";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Producto> productos = ((List<Producto>) query.getResultList());

		for (Producto producto : productos) {
			if (producto.getId().getId() == Long.parseLong(idProducto)) {
				// Boramos cualquier rastro de ese producto en otros lugares,
				// como los fav en usuario
				// Al estar subido en GAE no podemos usar la t’pica acci—n de
				// eliminaci—n en Cascada, aunque el GAE s’ te trae esa opci—n
				// es demasiado limitada para nuestro objetivo
				MisMetodos.eliminarFavUsuarioPro(request, idProducto);

				transaction.begin();
				entityManager.remove(producto);
				transaction.commit();
				break;
			}
		}
		String pagina = request.getParameter("pag");

		response.sendRedirect("Index?pag=" + pagina);
	}

}
